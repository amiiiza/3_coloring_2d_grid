import java.util.ArrayList;

public class Node {
    int row, col;
    State state;
    Region region;
    Color color = null;
    ArrayList <Node> rangeNeighbourhood;
    ArrayList <Node> adj;

    public ArrayList<Node> getAdj() {
        return adj;
    }

    public void addAdj(Node e){
        adj.add(e);
    }
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
        adj = new ArrayList<>();
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }
    public void printXY(){
        System.out.println(row + " " + col);
    }
    public void printNeighbour(){
        System.out.println("This node's neighbours:");
        for (Node e:rangeNeighbourhood) {
            e.printXY();
        }
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

    public Color getColor() {
        return color;
    }

    public void  commit(){
        if (region.getParity().equals(Parity.Even_One)) {
            if((row + col)% 2 == 0 )
                setColor(Color.One);
            else
                setColor(Color.Zero);
        }
        else{
            if((row + col)% 2 == 0 )
                setColor(Color.Zero);
            else
                setColor(Color.One);
        }
    }
    public String getStateName(){
        if(state.equals(State.Seen))
            return "Seen";
        else if(state.equals(State.Unseen))
            return "Unseen";
        else
            return "commited";
    }
}
