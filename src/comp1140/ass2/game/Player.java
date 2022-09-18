package comp1140.ass2.game;

import javafx.scene.paint.Color;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Player {

    private final String name;
    private Date birthday;
    private final Color color;
    private int score;

    public Player(String name) {
        this.name = name;
        Random rand = new Random();
        int r = rand.nextInt(0, 256);
        int g = rand.nextInt(0,256) ;
        int b = rand.nextInt(0,256);
        color = Color.rgb(r,g,b);
    }

    public String getName() {
        return name;
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
                Objects.equals(getName(), player.getName()) &&
                Objects.equals(getBirthday(), player.getBirthday()) &&
                Objects.equals(getColor(), player.getColor());
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
