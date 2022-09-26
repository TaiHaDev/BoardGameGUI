package comp1140.ass2.gui;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;
import comp1140.ass2.gameobjects.buildings.Building;
import comp1140.ass2.gameobjects.buildings.Castle;
import comp1140.ass2.gameobjects.buildings.Knight;
import comp1140.ass2.gameobjects.buildings.Road;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Viewer extends Application implements Initializable {

    private final FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("fxml/game-view.fxml"));
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;


    public Rectangle r0104;
    public Rectangle r0003;
    public Rectangle r0307;
    public Rectangle r0408;
    public Rectangle r0509;
    public Rectangle r0610;
    public Rectangle r1116;
    public Rectangle r1217;
    public Rectangle r1318;
    public Rectangle r1419;
    public Rectangle r1520;
    public Rectangle r2127;
    public Rectangle r2228;
    public Rectangle r2329;
    public Rectangle r2430;
    public Rectangle r2531;
    public Rectangle r2632;
    public Rectangle r3338;
    public Rectangle r3439;
    public Rectangle r3540;
    public Rectangle r3742;
    public Rectangle r3641;
    public Rectangle r4347;
    public Rectangle r4448;
    public Rectangle r4549;
    public Rectangle r4650;
    public Rectangle r4851;
    public Rectangle r4952;
    public Rectangle r3943;
    public Rectangle r4044;
    public Rectangle r4145;
    public Rectangle r3136;
    public Rectangle r3237;
    public Rectangle r2025;
    public Rectangle r1924;
    public Rectangle r1823;
    public Rectangle r2934;
    public Rectangle r2833;
    public Rectangle r1621;
    public Rectangle r1722;
    public Rectangle r0711;
    public Rectangle r1014;
    public Rectangle r0913;
    public Rectangle r0812;
    public Rectangle r0205;
    public Rectangle r4246;
    public Rectangle r5053;
    public Rectangle r0004;
    public Rectangle r3035;
    public Rectangle r4146;
    public Rectangle r3944;
    public Rectangle r4953;
    public Rectangle r4852;
    public Rectangle r4751;
    public Rectangle r3843;
    public Rectangle r2733;
    public Rectangle r2834;
    public Rectangle r4045;
    public Rectangle r2935;
    public Rectangle r2026;
    public Rectangle r1925;
    public Polyline h20;
    public Rectangle r0914;
    public Rectangle r1824;
    public Polyline h00;
    public Polyline h45;
    public Polyline h18;
    public Polyline h07;
    public Rectangle r0813;
    public Polyline h16;
    public Rectangle r1723;
    public Polyline h02;
    public Rectangle r3137;
    public Polyline h44;
    public Polyline h01;
    public Rectangle r0206;
    public Polyline h33;
    public Polyline h43;
    public Polyline h10;
    public Polyline h46;
    public Polyline h36;
    public Polyline h08;
    public Rectangle r3036;
    public Rectangle r1622;
    public Rectangle r0712;
    public Polyline h09;
    public Rectangle r1015;
    public Rectangle r0105;
    public Polyline h37;
    public Polyline h17;
    public Polyline h19;
    public Polyline h52;
    public Polyline h35;
    public Polyline h34;
    public Polyline c1;
    public Polyline c2;
    public Polyline c3;
    public Polyline c4;
    public Polyline h53;
    public Polyline h51;
    public TextField textField;
    public Ellipse k00;
    public Ellipse k01;
    public Ellipse k02;
    public Ellipse k03;
    public Ellipse k04;
    public Ellipse k05;
    public Ellipse k06;
    public Ellipse k07;
    public Ellipse k08;
    public Ellipse k09;
    public Ellipse k10;
    public Ellipse k11;
    public Ellipse k12;
    public Ellipse k13;
    public Ellipse k14;
    public Ellipse k15;
    public Ellipse k16;
    public Ellipse k17;
    public Ellipse k18;
    public Ellipse k19;
    public Button displayStateButton;
    public Circle playerXColor;
    public Circle playerYColor;
    public Text resourceList;
    public Text rolledResourceLabel;
    public Text turnText;
    public Text statsFreeText;
    public Text diceCountText;


    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH, WINDOW_HEIGHT);

        URL css = Game.class.getResource("css/myCSS.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayStateButton.setOnAction(event -> {
            try {
                displayState(textField.getText());
            } catch (NoSuchFieldException | IllegalAccessException e) {
                showError();
                e.printStackTrace();
            }
        });
    }

    private void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Malformed board state");
        alert.setContentText("Check board state and try again.");
        alert.show();
    }

    private void clearBoard(GameInstance game) throws NoSuchFieldException, IllegalAccessException {
        for (Road road : game.getBoard().getRoads()) {
            int start = road.getStart();
            int end = road.getEnd();
            String a = start < 10 ? "0" + start : String.valueOf(start);
            String b = end < 10 ? "0" + end : String.valueOf(end);
            Rectangle rectangle = (Rectangle) getClass().getDeclaredField("r" + a + b).get(this);
            rectangle.setFill(Color.WHITE);
        }
        for (var house : game.getBoard().getResidentialBuilding().entrySet()) {
            int ad = house.getKey();
            String address = ad < 10 ? "0" + ad : String.valueOf(ad);
            Polyline polyline = (Polyline) getClass().getDeclaredField("h" + address).get(this);
            polyline.setFill(Color.WHITE);
        }
        for (var knight : game.getBoard().getKnightBoard().entrySet()) {
            int ad = knight.getKey();
            String address = ad < 10 ? "0" + ad : String.valueOf(ad);
            Ellipse ellipse = (Ellipse) getClass().getDeclaredField("k" + address).get(this);
            ellipse.setOpacity(1);
            ellipse.setFill(Color.WHITE);
        }
        for (int i = 0; i < game.getBoard().getCastleBoard().length; i++) {
            Polyline polyline = (Polyline) getClass().getDeclaredField("c" + (i + 1)).get(this);
            polyline.setFill(Color.WHITE);
        }
    }
    // X62bgllowWK00K01K03R0003R0004R0104R0307R0408R0711R0712R0812R1116R1217S00S01S07XJ12K02K04K05K06R0105R0206R0509R0610R0813R0913R0914R1014R1015R1318R1419R1520R2026S02S09S20T10W05RX07A
    public void displayState(String boardState) throws NoSuchFieldException, IllegalAccessException {
        if (!CatanDiceExtra.isBoardStateWellFormed(boardState)) {
            showError();
            return;
        }

        GameInstance gameInstance;
        try {
            gameInstance = new GameInstance(boardState);
        } catch (Exception exception) {
            showError();
            exception.printStackTrace();
            return;
        }
        clearBoard(gameInstance);
        System.out.println(boardState);
        gameInstance.getPlayers()
                .forEach(player -> (player.getName().equals("X") ? playerXColor : playerYColor)
                        .setFill(player.getColor()));

        for (Road road : gameInstance.getBoard().getRoads()) {
            if (road.getOwner() == null) continue;
            int start = road.getStart();
            int end = road.getEnd();
            String a = start < 10 ? "0" + start : String.valueOf(start);
            String b = end < 10 ? "0" + end : String.valueOf(end);
            System.out.println(a + b);
            Rectangle rectangle = (Rectangle) getClass().getDeclaredField("r" + a + b).get(this);
            rectangle.setFill(road.getOwner().getColor());
        }

        for (Map.Entry<Integer, Building> house : gameInstance.getBoard().getResidentialBuilding().entrySet()) {
            if (house.getValue().getOwner() != null) {
                int ad = house.getKey();
                String address = ad < 10 ? "0" + ad : String.valueOf(ad);
                Polyline polyline = (Polyline) getClass().getDeclaredField("h" + address).get(this);
                polyline.setFill(house.getValue().getOwner().getColor());
            }
        }
        for (Map.Entry<Integer, Knight> knight : gameInstance.getBoard().getKnightBoard().entrySet()) {
            if (knight.getValue().getOwner() != null) {
                int ad = knight.getKey();
                String address = ad < 10 ? "0" + ad : String.valueOf(ad);
                Ellipse ellipse = (Ellipse) getClass().getDeclaredField("k" + address).get(this);
                ellipse.setOpacity(1);
                ellipse.setFill(knight.getValue().getOwner().getColor());
            }
        }
        for (int i = 0; i < gameInstance.getBoard().getCastleBoard().length; i++) {
            Castle[] castleBoard = gameInstance.getBoard().getCastleBoard();
            if (castleBoard[i].getOwner() != null) {
                Polyline polyline = (Polyline) getClass().getDeclaredField("c" + (i + 1)).get(this);
                polyline.setFill(castleBoard[i].getOwner().getColor());
            }
        }
        StringBuilder wallet = new StringBuilder();
        gameInstance.getDiceResult().forEach((resource, n) -> wallet.append(" - ").append(n).append(" ")
                .append(switch(resource) {
                    case WOOL -> "wool";
                    case GRAIN -> "grain";
                    case GOLD -> "gold";
                    case LUMBER -> "lumber";
                    case BRICK -> "brick" + (n != 1 ? "s" : "");
                    case ORE -> "ore" + (n != 1 ? "s" : "");
                })
                .append("\n"));
        resourceList.setText(wallet.toString());
        rolledResourceLabel.setOpacity(1);

        if (gameInstance.getPlayers().size() > 0) {
            turnText.setText("It is Player " + gameInstance.getPlayers().peek().getName() + "'s turn");
        }

        String dice = gameInstance.getDiceCount() == 1 ? "die" : "dice";
        String rollsHave = " roll" + (gameInstance.getRollsDone() == 1 ? " has" : "s have");
        diceCountText.setText("This turn, " + gameInstance.getRollsDone() + rollsHave + " been played, \nwith " + gameInstance.getDiceCount() + " " + dice + " available.");

        statsFreeText.setText("");
        List<Player> playersByScore = gameInstance.getPlayers().stream()
                .sorted(Comparator.comparingInt(Player::getScore).reversed())
                .collect(Collectors.toList());
        int place = 0;
        int currentScore = -1;
        for (Player player : playersByScore) {
            long count = playersByScore.stream().filter(e -> e.getScore() == player.getScore()).count();
            if (currentScore != player.getScore()) {
                place += count;
            }
            String placeString = (count > 1 ? "=" : "") + place;
            currentScore = player.getScore();
                    statsFreeText.setText(statsFreeText.getText() + placeString + ". Player " +
                    player.getName() + ": " + player.getScore() +
                    " point" + (player.getScore() == 1 ? "" : "s") + "\n");
        }
        statsFreeText.setText(statsFreeText.getText() + "\n");

        if (gameInstance.getLongestRoad() != null)
            statsFreeText.setText(statsFreeText.getText() + "Player " + gameInstance.getLongestRoad().getName() + " has the longest road!\n\n");
        if (gameInstance.getLargestArmy() != null)
            statsFreeText.setText(statsFreeText.getText() + "Player " + gameInstance.getLargestArmy().getName() + " has the largest army!");
    }

}

