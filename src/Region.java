import java.util.HashSet;
import java.util.Set;

public class Region {
    int thickness;
    Parity parity = null;
    Set<Node> queried;
    Set<Node> commited;
    Set<Node> visibilty;

    public Region() {
        queried = new HashSet<>();
        commited = new HashSet<>();
        visibilty = new HashSet<>();
        thickness = 0;
    }

    public void addCommited(Node node) {
        commited.add(node);
        node.setRegion(this);
        node.setState(State.Commited);
    }

    public void addQueried(Node node) {
        queried.add(node);
        node.setRegion(this);
        node.setState(State.Queried);
    }

    public void addVisibilty(Node node) {
        visibilty.add(node);
        node.setRegion(this);
        node.setState(State.Seen);
    }

    public void addCommitedSet(Set<Node> setCom) {
        for (Node node : setCom)
            addCommited(node);
    }

    public void addQueriedSet(Set<Node> setQue) {
        for (Node node : setQue)
            addQueried(node);
    }

    public void addVisibiltySet(Set<Node> setVis) {
        for (Node node : setVis)
            addVisibilty(node);
    }

    public void setThick(int thickness) {
        this.thickness = thickness;
    }

    public Parity getParity() {
        return parity;
    }

    public void setParity(Parity parity) {
        this.parity = parity;
    }

    public void increaseThick() {
        thickness++;
    }

    public int getThickness() {
        return thickness;
    }

    public void deleteVisibilty(Node node) {
        visibilty.remove(node);
    }

    public Set<Node> getQueried() {
        return queried;
    }

    public Set<Node> getCommited() {
        return commited;
    }

    public Set<Node> getVisibilty() {
        return visibilty;
    }
}

