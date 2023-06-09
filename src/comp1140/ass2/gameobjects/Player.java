package comp1140.ass2.gameobjects;

import javafx.scene.paint.Color;

import java.util.*;

public class Player {
    private final String uniqueId;
    private String displayName;
    private Date birthday;
    private Color color;
    private int score;

    public Player(String uniqueId) {
        this.uniqueId = uniqueId;
        Random rand = new Random();
        int r = rand.nextInt(0, 256);
        int g = rand.nextInt(0,256) ;
        int b = rand.nextInt(0,256);
        color = Color.rgb(r,g,b);
    }

    public Player(String uniqueId, Color color) {
        this.uniqueId = uniqueId;
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public Color getColor() {
        return this.color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return score == player.score &&
                Objects.equals(getUniqueId(), player.getUniqueId()) &&
                Objects.equals(getBirthday(), player.getBirthday()) &&
                Objects.equals(getColor(), player.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUniqueId(), getBirthday(), getColor(), score);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return uniqueId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
