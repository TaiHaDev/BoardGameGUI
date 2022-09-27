package comp1140.ass2.game;

public abstract class Building {

    private Player owner;

    public Building() { }

    public Building(Player owner) {
        this.owner = owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Player getOwner() {
        return owner;
    }


}
