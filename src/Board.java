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
                    System.out.print("x");
                else if(node.getState().equals(State.Seen))
                    System.out.print("o");
                else
                    System.out.print(node);
            }
            System.out.println();
        }
    }
    public Node getNode(int row, int col){
        return nodeArray[row][col];
    }
    public void play(int row, int col){
        // Check the condition 1: All nodes in B(v,r) are unseen.
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
        if (!seenNode && node.getState().equals(State.Unseen)){
            node.printNeighbour();
            Region region = new Region();
            node.setState(State.Queried);
            node.setColor(Color.Zero);
            region.addQueried(node);
            node.setRegion(region);
            for (Node e:areaSelected) {
                region.addVisibilty(e);
                e.setState(State.Seen);
                e.setRegion(region);
            }
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
                if (e.getState().equals(State.Unseen)) {
                    region.addVisibilty(e);
                    e.setState(State.Seen);
                    e.setRegion(region);
                }
            }
            if(node.getState().equals(State.Seen)) {
                node.setState(State.Commited);
                node.commit();
                region.addCommited(node);
                region.deleteVisibilty(node);
            }
            else if(node.getState().equals(State.Unseen)){
                node.setState(State.Queried);
                node.setRegion(region);
                region.addQueried(node);
                node.commit();
            }
        }
    }
}
