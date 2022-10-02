package comp1140.ass2.handlers;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.pipeline.ChainableHandler;
import comp1140.ass2.gameobjects.Player;

public record TurnStateHandler(GameInstance game) implements ChainableHandler<String> {

    @Override
    public String handle(String encodedString) {
        char turn = encodedString.charAt(0);
        game.setDiceCount(Integer.parseInt(encodedString.substring(1, 2)));
        game.setRollsDone(Integer.parseInt(encodedString.substring(2, 3)));
        game.getPlayers().add(new Player(String.valueOf(turn)));

        int end = 1 + encodedString.substring(1).indexOf('W');
        game.setDiceResult(GameInstance.stringResourcesToMap(encodedString.substring(3, end)));

        return encodedString.substring(end);
    }

}

