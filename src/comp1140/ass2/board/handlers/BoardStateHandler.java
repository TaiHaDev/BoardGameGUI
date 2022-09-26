package comp1140.ass2.board.handlers;

import comp1140.ass2.board.pipeline.ChainableHandler;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;
import comp1140.ass2.gameobjects.strategies.BuilderFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record BoardStateHandler(GameInstance game) implements ChainableHandler<String> {

    @Override
    public String handle(String encodedString) {
        int currentChar = 0;
        char playerChar = encodedString.charAt(0);
        while ('W' <= encodedString.charAt(currentChar) && encodedString.charAt(currentChar) <= 'Z') {
            final int finalChar = currentChar;
            if (game.getPlayers().stream().noneMatch(p -> p.getName().equals(String.valueOf(encodedString.charAt(finalChar))))) {
                game.getPlayers().addLast(new Player(String.valueOf(encodedString.charAt(currentChar))));
            }
            int end = encodedString.indexOf(playerChar + 1) > 0 ? encodedString.indexOf(++playerChar) : encodedString.indexOf('W', encodedString.indexOf('W') + 1);
            Matcher matcher = Pattern.compile("[A-V][0-9]*").matcher(encodedString.substring(currentChar, end));
            currentChar++;
            while (matcher.find()) {
                String structure = matcher.group(0);
                game.getPlayers().stream().filter(p -> p.getName().equals(String.valueOf(encodedString.charAt(finalChar))))
                        .findAny().ifPresent(player ->
                                new BuilderFactory(game, player)
                                        .getBuilderById(structure.charAt(0))
                                        .build(structure.substring(1)));
                currentChar += structure.length();
            }
            if (encodedString.indexOf(playerChar) == -1) {
                break;
            }
        }
        Math.pow(1, 2);
        return encodedString.substring(currentChar);
    }

}
