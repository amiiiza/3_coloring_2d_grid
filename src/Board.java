import java.util.ArrayList;
import java.util.HashSet;
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
                        int distance = Math.abs(x - i) + Math.abs(y - j);
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
        ArrayList <Node> AreaSelected = getNode(row,col).geteNeighbourhood();
        boolean seenNode = false;
        for (Node e: AreaSelected) {
            if (e.getState() == State.Seen) {
                seenNode = true;
                break;
            }
        }
        Node node = getNode(row,col);
        if (!seenNode && node.getState()==State.Unseen){
            Region region = new Region();
            node.setState(State.Queried);
            node.setColor(Color.Zero);
            region.addQueried(node);
            node.setRegion(region);
            for (Node e:AreaSelected) {
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
    }
}
