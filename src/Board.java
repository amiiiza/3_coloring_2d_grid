import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Board {
    Node[][] nodeArray;
    int size;
    ArrayList<Region> regionList = new ArrayList<>();
    public void addRegion(Region region){
        regionList.add(region);
    }
    public Board(int size, int range) {
        this.size = size;
        nodeArray = new Node[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                nodeArray[i][j] = new Node(i, j);
            }
        }
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Node node = nodeArray[i][j];
                for (int y = Math.max(0, i - range); y < Math.min(size - 1, i + range) + 1; y++) {
                    for (int x = Math.max(0, j - range); x < Math.min(size - 1, j + range) + 1; x++) {
                        int distance = Math.abs(y - i) + Math.abs(x - j);
                        if (distance <= range && distance != 0) {
                            node.addNeighbor(nodeArray[y][x]);
                        }
                        if(distance == 1)
                            node.addAdj(nodeArray[y][x]);
                    }
                }
            }
        }
    }
    public void printBoard(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Node node = nodeArray[i][j];
                if (node.getState().equals(State.Unseen))
                    System.out.print(ANSI_RED + "x" + ANSI_RESET);
                else if(node.getState().equals(State.Seen))
                    System.out.print(ANSI_BLUE + "o"+ ANSI_RESET);
                else
                    System.out.print(ANSI_GREEN+ node+ANSI_RESET);
            }
            System.out.println();
        }
    }
    public Node getNode(int row, int col){
        return nodeArray[row][col];
    }

    public void play(int row, int col){
        ArrayList <Node> areaSelected = getNode(row,col).geteNeighbourhood();
        Set <Region> regionOfIntersection = new HashSet<>();
        boolean seenNode = false;
        for (Node e: areaSelected) {
            if (e.getState().equals(State.Seen)) {
                regionOfIntersection.add(e.region);
                seenNode = true;
            }
        }
        Node node = getNode(row,col);
        // Check the condition 1: All nodes in B(v,r) are unseen.
        if (!seenNode && node.getState().equals(State.Unseen)){
            node.printNeighbour();
            Region region = new Region();
            node.setColor(Color.Zero);
            region.addQueried(node);
            for (Node e:areaSelected)
                region.addVisibilty(e);
            if ((row + col) % 2 == 0)
                region.setParity(Parity.Even_Zero);
            else
                region.setParity(Parity.Even_One);
            addRegion(region);
        }
        // Check the condition 2: All nodes in B(v,r) is are either unseen or belong to the same group
        else if(regionOfIntersection.size() == 1){
            Iterator <Region> i = regionOfIntersection.iterator();
            Region region = i.next();
            for (Node e:areaSelected) {
                if (e.getState().equals(State.Unseen))
                    region.addVisibilty(e);
            }
            if(node.getState().equals(State.Seen)) {
                region.addQueried(node);
                region.deleteVisibilty(node);
                node.commit();
            }
            else if(node.getState().equals(State.Unseen)){
                region.addQueried(node);
                node.commit();
            }
        }
        // Check the condition 3: There are nodes in B(v,t)that belong to different groups
        else{
            int n = regionOfIntersection.size();
            System.out.println(n);
            Region [] list = new Region[n];
            System.arraycopy(regionOfIntersection.toArray(), 0, list, 0, n);
            Region r = list[0];
            for (int i = 1; i < list.length; i++) {
                r = joinRegion(r,list[i]);
            }
            if (node.getState().equals(State.Seen)) {
                r.addQueried(node);
                node.commit();
                r.deleteVisibilty(node);
            } else{
                r.addQueried(node);
                node.commit();
            }
            for (Node e:areaSelected) {
                if (e.getState().equals(State.Unseen))
                    r.addVisibilty(e);
            }
        }
    }

    public Region joinRegion(Region a, Region b){
        Region minThick = b, maxThick= a;
        Region finalRegion;
        if (a.getThickness() < b.getThickness()) {
            minThick = a;
            maxThick = b;
        }
        if (!a.getParity().equals(b.getParity())){
            adjCommit(minThick,State.Commited,Color.Zero,Color.One);
            adjCommit(minThick,State.Queried,Color.Zero,Color.One);
            adjCommit(minThick,State.Commited,Color.One,Color.Two);
            adjCommit(minThick,State.Queried,Color.One,Color.Two);
            adjCommit(minThick,State.Commited,Color.Two,Color.Zero);
            adjCommit(minThick,State.Queried,Color.Two,Color.Zero);
            minThick.increaseThick();
        }
        finalRegion = new Region();
        finalRegion.addCommitedSet(minThick.getCommited());
        finalRegion.addCommitedSet(maxThick.getCommited());
        finalRegion.addQueriedSet(minThick.getQueried());
        finalRegion.addQueriedSet(maxThick.getQueried());
        finalRegion.addVisibiltySet(minThick.getVisibilty());
        finalRegion.addVisibiltySet(maxThick.getVisibilty());
        finalRegion.setParity(maxThick.getParity());
        finalRegion.setThick(Math.max(maxThick.getThickness(),minThick.getThickness()));
        regionList.remove(minThick);
        regionList.remove(maxThick);
        regionList.add(finalRegion);
        return finalRegion;
    }
    public void adjCommit(Region region, State state, Color color1, Color color2){
        Node [] list;
        if (state.equals(State.Commited)){
            list = new Node[region.getCommited().size()];
            region.getCommited().toArray(list);
        } else {
            list = new Node[region.getQueried().size()];
            region.getQueried().toArray(list);
        }
        for (int i = 0; i < list.length; i++) {
            Node e = list[i];
            if (e.getColor().equals(color1)){
                for (Node p:e.getAdj()) {
                    if (p.getState().equals(State.Seen)){
                        region.addCommited(p);
                        region.deleteVisibilty(p);
                        p.setColor(color2);
                    }
                }
            }
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
}
