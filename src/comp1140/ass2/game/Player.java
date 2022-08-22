package comp1140.ass2.game;

import javafx.scene.paint.Color;

import java.util.Date;

public class Player {

    private String name;
    private Date birthday;
    private Color color;

    public Player(String name, Date birthday, Color color) {

    }

    /**
     * The game 'tries' to claim the passed road and
     * returns whether it worked (i.e., was the road
     * already claimed?, etc.).
     *
     * The passed road's owner is updated to the
     * current player. Colour handling is performed
     * in the UI class if this function returns true.
     *
     * @param road to attempt to claim for the current
     *             instance's player
     * @return true iff the claim was successful
     */
    public boolean claimRoad(Road road) {
        return false;
    }

    public String getName() {
        return null;
    }

    public void setName(String name) {

    }

    public Date getBirthday() {
        return null;
    }

    public void setBirthday(Date birthday) {

    }

    public Color getColor() {
        return null;
    }

    public void setColor(Color color) {

    }
}
