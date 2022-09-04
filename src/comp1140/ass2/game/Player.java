package comp1140.ass2.game;

import javafx.scene.paint.Color;

import java.util.Date;
import java.util.Objects;

public class Player {

    private String name;
    private Date birthday;
    private Color color;
    private int score;

    public Player(String name, Date birthday, Color color, int score) {
        this.name = name;
        this.birthday = birthday;
        this.color = color;
        this.score = score;
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
        return name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return score == player.score && Objects.equals(getName(), player.getName()) && Objects.equals(getBirthday(), player.getBirthday()) && Objects.equals(getColor(), player.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBirthday(), getColor(), score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return name;
    }
}
