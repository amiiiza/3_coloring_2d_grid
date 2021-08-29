import java.util.ArrayList;

public class Node {
    int row, col;
    State state;
    Region region;
    Color color = null;
    ArrayList <Node> rangeNeighbourhood;


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

    public String toString(){
        if (color.equals(Color.Zero))
            return "0";
        else if(color.equals(Color.One))
            return "1";
        else
            return "2";
    }

}
