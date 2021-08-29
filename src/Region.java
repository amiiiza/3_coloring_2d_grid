import java.util.ArrayList;

public class Region {
    int thickness;
    Parity parity = null;
    public void addCommited (Node node){
        commited.add(node);
    }

    public void addQueried(Node node){
        queried.add(node);
    }
    public void addVisibilty(Node node){
        visibilty.add(node);
    }

    public Region() {
        queried = new ArrayList<>();
        commited = new ArrayList<>();
        visibilty = new ArrayList<>();
        thickness = 0;
    }

    public void setParity(Parity parity) {
        this.parity = parity;
    }

    ArrayList <Node> queried;
    ArrayList <Node> commited;
    ArrayList <Node> visibilty;
}
