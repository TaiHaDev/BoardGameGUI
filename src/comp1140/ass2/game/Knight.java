package comp1140.ass2.game;

public class Knight implements Building{

    private Resource jokerResource;
    private boolean isJoker;
    private boolean wildCard;

    @Override
    public Resource[] getCost() {
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

    }

    public boolean isWildCard() {
        return false;
    }

    public void setWildCard(boolean wildCard) {

    }

    public Resource getJokerResource() {
        return null;
    }

    public void setJokerResource(Resource jokerResource) {

    }

}
