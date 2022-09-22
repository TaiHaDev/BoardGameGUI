package comp1140.ass2.game;

public abstract class OwnableBuilding {

    private Player owner;

    public OwnableBuilding() { }

    public OwnableBuilding(Player owner) {
        this.owner = owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }


}
