package comp1140.ass2.handlers;

import comp1140.ass2.pipeline.ChainableHandler;
import comp1140.ass2.gameobjects.GameInstance;

public record ScoreStateHandler(GameInstance game) implements ChainableHandler<String> {

    @Override
    public String handle(String encodedString) {
        int currentChar = 0;
        while (currentChar < encodedString.length() && 'W' <= encodedString.charAt(currentChar) && encodedString.charAt(currentChar) <= 'Z') {
            String name = String.valueOf(encodedString.charAt(currentChar++));
            int score = Integer.parseInt(String.valueOf(encodedString.charAt(currentChar++)) + encodedString.charAt(currentChar++));
            game.getPlayers().stream()
                    .filter(e -> e.getUniqueId().equals(name))
                    .findFirst()
                    .ifPresent(p -> p.setScore(score));
        }

        return encodedString.substring(currentChar);
    }

}

