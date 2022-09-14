package comp1140.ass2.game.buildings;

import comp1140.ass2.game.board.Player;

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
