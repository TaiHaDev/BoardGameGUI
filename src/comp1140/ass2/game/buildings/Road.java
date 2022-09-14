package comp1140.ass2.game.buildings;

import comp1140.ass2.game.board.Player;
import comp1140.ass2.game.helper.Resource;

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
