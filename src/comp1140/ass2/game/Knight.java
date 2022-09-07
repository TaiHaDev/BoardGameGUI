package comp1140.ass2.game;

import java.util.Arrays;
import java.util.Map;

public class Knight extends Building{

    private Resource jokerResource;
    private boolean isJoker;
    private boolean wildCard;
    public static Map<Resource, Integer> COST = Map.of(Resource.ORE,1,
            Resource.GRAIN, 1, Resource.WOOL,1);
    public static int point = 0;

    private int[] neighbours;

    public Knight(Player owner) {
        super(owner);
    }

    public Knight(Player owner, Resource jokerResource, boolean isJoker, boolean wildCard, int[] neighbours) {
        super(owner);
        this.jokerResource = jokerResource;
        this.isJoker = isJoker;
        this.wildCard = wildCard;
        this.neighbours = neighbours;
    }

    public int[] getNeighbours() {
        return neighbours;
    }

    @Override
    public Map<Resource, Integer> getCost() {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    public boolean isJoker() {
        return false;
    }

    public void setJoker(boolean joker) {
        this.isJoker = joker;
    }


    public void setWildCard(boolean wildCard) {
        this.wildCard = wildCard;
    }

    public Resource getJokerResource() {
        return jokerResource;
    }

    public void setJokerResource(Resource jokerResource) {
        this.jokerResource = jokerResource;
    }

    @Override
    public String toString() {
        return "Knight{" +
                "jokerResource=" + jokerResource +
                ", isJoker=" + isJoker +
                ", wildCard=" + wildCard +
                ", neighbours=" + Arrays.toString(neighbours) +
                ", owner=" + getOwner() +
                '}';
    }
}
