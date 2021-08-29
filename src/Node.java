import java.util.ArrayList;

public class Node {
    int row, col;
    State state;
    Region region = null;
    Color color = null;
    ArrayList <Node> rangeNeighbourhood = null;

    public void setColor(Color color) {
        this.color = color;
    }

    public State getState() {
        return state;
    }


    public Node(int i, int j) {
        row = i;
        col = j;
        state = State.Unseen;
        region = null;
        rangeNeighbourhood = new ArrayList<>();
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }


    public void setState(State state) {
        this.state = state;
    }

    public void addNeighbor(Node node){
        rangeNeighbourhood.add(node);
    }

    public ArrayList<Node> geteNeighbourhood() {
        return rangeNeighbourhood;
    }
}
