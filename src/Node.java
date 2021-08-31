import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Node {
    int row, col;
    State state;
    Region region;
    Value value = null;
    ArrayList<Node> rangeNeighbourhood;
    ArrayList<Node> adj;
    JButton button;

    public Node(int i, int j) {
        row = i;
        col = j;
        state = State.Unseen;
        region = null;
        rangeNeighbourhood = new ArrayList<>();
        adj = new ArrayList<>();
    }

    public void setButton(JButton button) {
        this.button = button;
    }

    public ArrayList<Node> getAdj() {
        return adj;
    }

    public void addAdj(Node e) {
        adj.add(e);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void printXY() {
        System.out.println(row + " " + col);
    }

    public void printNeighbour() {
        System.out.println("This node's neighbours:");
        for (Node e : rangeNeighbourhood) {
            e.printXY();
        }
    }

    public void addNeighbor(Node node) {
        rangeNeighbourhood.add(node);
    }

    public ArrayList<Node> geteNeighbourhood() {
        return rangeNeighbourhood;
    }

    public String toString() {
        if (value.equals(Value.Zero))
            return "0";
        else if (value.equals(Value.One))
            return "1";
        else
            return "2";
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
        if (value.equals(Value.Zero))
            button.setBackground(Color.YELLOW);
        else if (value.equals(Value.One))
            button.setBackground(Color.RED);
        else if (value.equals(Value.Two))
            button.setBackground(Color.blue);
    }

    public void commit() {
        if (region.getParity().equals(Parity.Even_One)) {
            if ((row + col) % 2 == 0)
                setValue(Value.One);
            else
                setValue(Value.Zero);
        } else {
            if ((row + col) % 2 == 0)
                setValue(Value.Zero);
            else
                setValue(Value.One);
        }
    }

    public String getStateName() {
        if (state.equals(State.Seen))
            return "Seen";
        else if (state.equals(State.Unseen))
            return "Unseen";
        else
            return "commited";
    }
}
