package comp1140.ass2.actionstrategies;

import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;
// author Matthew
public class ActionFactory {
    public enum ActionType {
        KEEP("keep"),
        SWAP("swap"),
        TRADE("trade"),
        BUILD("build");

        private final String name;

        ActionType(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private final KeepAction keep;
    private final SwapAction swap;
    private final BuildAction build;
    private final TradeAction trade;

    private ActionFactory(GameInstance game, Player player) {
        keep = new KeepAction(game, player);
        swap = new SwapAction(game, player);
        build = new BuildAction(game, player);
        trade = new TradeAction(game, player);
    }

    public static ActionFactory of(GameInstance game, Player player) {
        return new ActionFactory(game, player);
    }

    public ActionStrategy getActionByName(ActionType name) {
        return switch(name) {
            case SWAP -> swap;
            case BUILD -> build;
            case TRADE -> trade;
            case KEEP -> keep;
        };
    }

}
