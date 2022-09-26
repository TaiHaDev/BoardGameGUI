package comp1140.ass2.gameobjects.buildings;

import comp1140.ass2.gameobjects.Player;
import comp1140.ass2.helpers.Resource;

import java.util.Map;

public class Road extends Building {

    public static final Map<Resource, Integer> COST = Map.of(Resource.BRICK,1, Resource.LUMBER, 1);

    private final int start;
    private final int end;

    public Road(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Road(int start, int end, Player owner) {
        super(owner);
        this.start = start;
        this.end = end;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    /**
     * Returns if this instance of the Road
     * class is equal to another.
     *
     * As roads are symmetric, if the second
     * object is another instance of a road,
     * returns true if its start and end are
     * swapped around (and the owner is the same).
     *
     * @param o  some given object to test its equivalence
     * @return true iff o has the same start and
     *          end (regardless of order), and has the same owner.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Road road) {
            return road.getOwner() == this.getOwner() &&
                    ((road.getStart() == this.getStart() && road.getEnd() == this.getEnd()) ||
                    (road.getStart() == this.getEnd() && road.getEnd() == this.getStart()));
        }
        return false;
    }

}
