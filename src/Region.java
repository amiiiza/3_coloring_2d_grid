import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

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

    public Parity getParity() {
        return parity;
    }

    public Region() {
        queried = new HashSet<>();
        commited = new HashSet<>();
        visibilty = new HashSet<>();
        thickness = 0;
    }

    public void setParity(Parity parity) {
        this.parity = parity;
    }

    Set<Node> queried;
    Set <Node> commited;
    Set <Node> visibilty;

    public void deleteVisibilty(Node node) {
        visibilty.remove(node);
    }
}

