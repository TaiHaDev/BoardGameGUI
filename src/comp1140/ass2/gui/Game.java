package comp1140.ass2.gui;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.actionstrategies.ActionFactory;
import comp1140.ass2.actionstrategies.ActionFactory.ActionType;
import comp1140.ass2.actionstrategies.ActionStrategy;
import comp1140.ass2.buildings.*;
import comp1140.ass2.game.Resource;
import comp1140.ass2.gameobjects.GameInstance;
import comp1140.ass2.gameobjects.Player;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.Pair;

import javax.swing.event.DocumentEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Game extends Application implements Initializable {


    private final FXMLLoader fxmlLoader = new FXMLLoader(Game.class.getResource("fxml/game.fxml"));
    private static final int WINDOW_WIDTH = 1200;
    private static final int WINDOW_HEIGHT = 700;
    private static int numberOfPlayers = 0;
    GameInstance game = GameInstance.getInstance();

    public SplitMenuButton tradeButton;
    public SplitMenuButton swapButton;
    public ImageView o;
    public AnchorPane dicesPane;
    public ImageView w;
    public ImageView g;
    public ImageView m;
    public ImageView l;
    public ImageView b;
    public Button doneButton;
    Player[] players;
    LocalDate[] playersDOB;

    String keep = "";
    private int starterRoadsCount = 1;
    public Circle playerColor1;
    public ImageView imageIcon1;
    public Label playerName1;
    public Circle playerColor4;
    public ImageView imageIcon4;
    public Label playerName4;
    public Circle playerColor2;
    public Circle playerColor3;
    public Label playerName3;
    public Label playerName2;
    public ImageView imageIcon3;
    public ImageView imageIcon2;

    @FXML
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
    public Text resourceList;
    public Text rolledResourceLabel;
    public Text turnText;
    public Text statsFreeText;
    public Text diceCountText;

    public ChoiceBox<Integer> playerNumberChoice;

    public Integer[] playerNumbers = {2, 3, 4};
    public SplitPane setupPane;
    public AnchorPane lowerPane;
    public AnchorPane wholePane;

    public Button rollDicesButton;

    public static void main(String[] args) {
        launch(args);
    }
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
        formatSetupPane();
    }

    public void startGame() throws IllegalAccessException, NoSuchFieldException {
        setEventHandlerForRoads();
        setEventHandlerForRollDicesButton();
        setEventHandlerForDoneButton();
        setEventHandlerForResidentialBuildings();
        setEventHandlerForKnight();
        setEventHandlerForSwapButton();
        setEventHandlerForTradeButton();
        setEventHandlerForCastle();
    }

    public void formatSetupPane() {
        playerNumberChoice.getItems().addAll(playerNumbers);
        double initialHeight = setupPane.getPrefHeight();
        playerNumberChoice.setOnAction(event -> {
            // reset pane
            lowerPane.getChildren().clear();
            lowerPane.setPrefHeight(0);
            setupPane.setPrefHeight(initialHeight);
            numberOfPlayers = playerNumberChoice.getValue();
            players = new Player[numberOfPlayers];
            playersDOB = new LocalDate[numberOfPlayers];
            renderPlayerSetup();
        });
    }

    public void renderPlayerStatus() throws NoSuchFieldException, IllegalAccessException {
        int layoutX = 80;
        int layoutY = 50;
        String playerName = "playerName";
        String playerColor = "playerColor";
        String imageIcon = "imageIcon";
        for (int i = 1; i <= numberOfPlayers; i++) {
            Player currentPlayer = players[i - 1];
            Label label = (Label) getClass().getDeclaredField(playerName + i).get(this);
            label.setText(currentPlayer.getUniqueId());
            Circle color = (Circle) getClass().getDeclaredField(playerColor + i).get(this);
            color.setFill(currentPlayer.getColor());
        }
        for (int i = 4; i > numberOfPlayers; i--) {
            ImageView imageView = (ImageView) getClass().getDeclaredField(imageIcon + i).get(this);
            imageView.setVisible(false);
            Label label = (Label) getClass().getDeclaredField(playerName + i).get(this);
            label.setVisible(false);
            Circle color = (Circle) getClass().getDeclaredField(playerColor + i).get(this);
            color.setVisible(false);
        }
    }





    public static void showError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Malformed board state");
        alert.setContentText("Check board state and try again.");

        alert.show();
    }

    public static void showWarning() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Invalid input");
        alert.setContentText("check your input's name and date of birth and try again.");
        alert.show();
    }


    public void renderPlayerSetup() {
        setupPane.setPrefHeight(setupPane.getPrefHeight() + 62 * numberOfPlayers + 30);
        lowerPane.setPrefHeight(lowerPane.getPrefHeight() + 62 * numberOfPlayers + 30);
        int layoutX = 200;
        int layoutY = 10;
        int labelLayoutX = 10;
        for (int i = 0; i < numberOfPlayers; i++) {
            String player = "Player" + (i + 1);
            Label label1 = new Label(player + "'s name: ");
            label1.setPrefWidth(150);
            label1.setLayoutX(labelLayoutX);
            label1.setLayoutY(layoutY);

            TextField playerNameTextField = new TextField();
            playerNameTextField.setPrefWidth(200);
            playerNameTextField.setLayoutX(layoutX);
            playerNameTextField.setLayoutY(layoutY - 5);
            playerNameTextField.setPromptText("Enter your name: ");
            final int currentI = i;
            playerNameTextField.setOnKeyTyped(event1 -> players[currentI] = new Player(playerNameTextField.getText()));

            layoutY += 30;
            Label label2 = new Label(player + "'s DOB: ");
            label2.setLayoutX(150);
            label2.setLayoutX(labelLayoutX);
            label2.setLayoutY(layoutY);

            DatePicker datePicker = new DatePicker();
            datePicker.setPrefWidth(200);
            datePicker.setLayoutY(layoutY - 5);
            datePicker.setLayoutX(layoutX);
            datePicker.setOnAction(event1 -> playersDOB[currentI] = datePicker.getValue());
            datePicker.setPromptText("Pick your birthday");
            datePicker.setValue(LocalDate.of(2003, 1, 1));
            layoutY += 30;
            lowerPane.getChildren().addAll(label1, label2, playerNameTextField, datePicker);
        }
        Button checkButton = new Button("Finish");
        checkButton.setPrefWidth(75);
        checkButton.setLayoutX(lowerPane.getPrefWidth() - 112);
        checkButton.setLayoutY(lowerPane.getPrefHeight() - 30);
        checkButton.setOnAction(event -> {
            try {
                if (Arrays.stream(players).anyMatch(player -> player.getUniqueId().startsWith(" "))) {
                    showWarning();
                } else {
                    GameInstance
                            .getInstance()
                            .getPlayers()
                            .addAll(IntStream
                                    .range(0, players.length)
                                    .mapToObj(i -> new Pair<Player, LocalDate>(players[i], playersDOB[i]))
                                    .sorted((o1, o2) -> - o1.getValue().compareTo(o2.getValue())).map(Pair::getKey)
                                    .toList());
                    setupPane.setVisible(false);
                    renderPlayerStatus();
                    startGame();
                }
            } catch (Exception ignored) {
                System.out.println(ignored.toString());
                showWarning();
            }

        });

        lowerPane.getChildren().add(checkButton);
    }




    public void renderDices(String dicesResult) throws NoSuchFieldException, IllegalAccessException {
        dicesPane.getChildren().clear();
        int layoutX = 15;
        int layoutY = 15;
        int diceCount = dicesResult.length();
        String currentKeep = new String(keep);
        for (int i = 0; i < diceCount; i++) {
            String diceName = dicesResult.substring(i, i+1);
            ImageView prototype = (ImageView) getClass().getDeclaredField(diceName).get(this);
            ImageView imageView = new ImageView(prototype.getImage());
            imageView.setFitWidth(97);
            imageView.setFitHeight(67);
            imageView.setPickOnBounds(true);
            imageView.setPreserveRatio(true);
            imageView.setLayoutX(layoutX);
            imageView.setLayoutY(layoutY);
            InnerShadow innerShadow = new InnerShadow();
            innerShadow.setWidth(200);
            if (currentKeep.contains(diceName)) {
                imageView.setEffect(innerShadow);
                currentKeep = currentKeep.replaceFirst(diceName, "");
            } else {
                imageView.setOnMouseClicked(event -> {
                    if (imageView.getEffect() == null) {
                        keep += diceName;
                        imageView.setEffect(innerShadow);
                    } else {
                        keep = keep.replaceFirst(diceName, "");
                        imageView.setEffect(null);
                    }
                });
            }
            dicesPane.getChildren().add(imageView);
            if (i < 2) layoutX += 80;
            if (i == 2) {
                layoutX = 15;
                layoutY += 80;
            }
            if (i > 2) layoutX += 80;
        }
    }

    public void setEventHandlerForKnight() throws NoSuchFieldException, IllegalAccessException {
        for (var entry : game.getBoard().getKnightBoard().entrySet()) {
            Knight knight = entry.getValue();
            int index = entry.getKey();
            String knightAddress = index < 10 ? "0" + index : "" + index;
            System.out.println("I am outside");
            Ellipse knightShape = (Ellipse) getClass().getDeclaredField("k" + knightAddress).get(this);
            System.out.println(knightAddress);
            knightShape.setOnMouseClicked(mouseEvent -> {
                System.out.println("I am inside");
                ActionStrategy actionStrategy = ActionFactory.of(game, game.getCurrentPlayer()).getActionByName(ActionType.BUILD);
                if (actionStrategy.isApplicable("K" + knightAddress)) {
                    actionStrategy.apply("K" + knightAddress);
                    knightShape.setFill(game.getCurrentPlayer().getColor());
                    knightShape.setOpacity(1);
                    renderGameInfo();
                    knightShape.setOnMouseClicked(null);
                }

            });

        }
    }
    public void setEventHandlerForDoneButton() {
        doneButton.setOnMouseClicked(mouseEvent -> {
            game.emptyDiceResult();
            game.setRollsDone(1);
            game.setDiceCount();
            game.getPlayers().poll();
            keep = "";
        });
    }

    public void setEventHandlerForRollDicesButton() {
        rollDicesButton.setOnMouseClicked(mouseEvent -> {
            ActionStrategy actionStrategy = ActionFactory.of(game, game.getCurrentPlayer()).getActionByName(ActionType.KEEP);
            int diceCount = game.getDiceCount();
            int rollsDone = game.getRollsDone();
            if (game.getRollsDone() > 1 && game.getRollsDone() < 4 && GameInstance.stringResourcesToMap(keep).equals(game.getDiceResult())) {
                game.setRollsDone(4);
                game.setDiceCount();
            } else if (diceCount > 2 && rollsDone == 1) {
                game.setDiceResult(GameInstance.stringResourcesToMap(CatanDiceExtra.rollDice(diceCount)));
                game.setRollsDone();
            } else if (diceCount > 2 && rollsDone > 0 && rollsDone < 3) {
                actionStrategy.apply(keep);
                game.setRollsDone();
            } else if (diceCount > 2 && rollsDone == 3) {
                actionStrategy.apply(keep);
                game.setRollsDone();
            }
            renderGameInfo();
        });
    }
    public void setEventHandlerForRoads() throws NoSuchFieldException, IllegalAccessException {
        for (Road road : game.getBoard().getRoads()) {
            int start = road.getStart();
            int end = road.getEnd();
            String a = road.getStart() < 10 ? "0" + start : "" + start;
            String b = road.getEnd() < 10 ? "0" + end : "" + end;
            Rectangle roadShape = (Rectangle) getClass().getDeclaredField("r" + a + b).get(this);
            roadShape.setOnMouseClicked(mouseEvent -> {
                ActionStrategy actionFactory = ActionFactory.of(game, game.getCurrentPlayer()).getActionByName(ActionType.BUILD);
                if (actionFactory.isApplicable("R" + a + b)) {
                    System.out.println(game.getCurrentPlayer());

                    actionFactory.apply("R" + a + b);
                    System.out.println(game.getDiceCount());
                    if (game.getDiceCount() == 0) {
                        roadShape.setFill((game.getPlayers().poll().getColor()));
                        if(starterRoadsCount == numberOfPlayers) {
                            game.setDiceCount();
                            game.setRollsDone();
                        }
                        else starterRoadsCount++;
                    } else if (game.getRollsDone() == 4) {
                        roadShape.setFill(game.getCurrentPlayer().getColor());
                        renderGameInfo();
                    }
                    roadShape.setOnMouseClicked(null);
                }
            });
        }
    }
    public void setEventHandlerForCastle() throws NoSuchFieldException, IllegalAccessException {
        Castle[] castlesBoard = game.getBoard().getCastleBoard();
        for (int i = 1; i <= castlesBoard.length; i++) {
            Polyline castleShape = (Polyline) getClass().getDeclaredField("c" + i).get(this);
            ActionStrategy actionStrategy = ActionFactory.of(game, game.getCurrentPlayer()).getActionByName(ActionType.BUILD);
            String argument = "R" + i;
            if (actionStrategy.isApplicable(argument)) {
                actionStrategy.apply(argument);
                castleShape.setFill(game.getCurrentPlayer().getColor());
                renderGameInfo();
            }
            castleShape.setOnMouseClicked(null);
        }
    }

    public void setEventHandlerForTradeButton() {
        tradeButton.setOnAction(event -> {
            if (game.getDiceResult().get(Resource.GOLD) > 1) {
                for (Resource resource : Resource.values()) {
                    MenuItem menuItem = new MenuItem(resource.toString());
                    menuItem.setOnAction(event1 -> {
                        ActionStrategy actionStrategy = ActionFactory.of(game, game.getCurrentPlayer()).getActionByName(ActionType.TRADE);
                        actionStrategy.apply(String.valueOf(resource.getId()));
                        renderGameInfo();
                        tradeButton.getItems().clear();
                    });
                    tradeButton.getItems().add(menuItem);
                }

            }
        });

    }

    public void setEventHandlerForSwapButton() {
        swapButton.setOnAction(mouseEvent -> {
            for (Resource resource : Resource.values()) {
                String id = String.valueOf(resource.getId());
                if(game.diceResultToString().contains(id) && !keep.contains(id)) {
                    List<Object> swappableResources = game
                            .getBoard()
                            .getKnightBoard()
                            .values().stream()
                            .filter(knight -> game.getCurrentPlayer().equals(knight.getOwner()) && !knight.isJoker())
                            .mapMulti((knight, consumer) -> {
                                if (knight.getJokerResource() == null) {
                                    for (Resource resource1 : Resource.values()) consumer.accept(resource1);
                                } else {
                                    consumer.accept(knight.getJokerResource());
                                }
                            })
                            .distinct()
                            .toList();
                    for (var rs : swappableResources) {
                        MenuItem menuItem = new MenuItem(resource.toString() + " to " + rs.toString());
                        menuItem.setOnAction(event -> {
                            ActionStrategy actionStrategy = ActionFactory.of(game, game.getCurrentPlayer()).getActionByName(ActionType.SWAP);
                            String argument = String.valueOf(resource.getId()) + ((Resource) rs).getId();
                            actionStrategy.apply(argument);
                            renderGameInfo();
                            swapButton.getItems().clear();
                        });
                        swapButton.getItems().add(menuItem);
                    }
                }
            }

        });

    }
    public void setEventHandlerForResidentialBuildings() throws NoSuchFieldException, IllegalAccessException {
        for (var entry : game.getBoard().getResidentialBuilding().entrySet()) {
            Building building = entry.getValue();
            int index = entry.getKey();
            String houseAddress = index < 10 ? "0" + index : "" + index;
            Polyline houseShape = (Polyline) getClass().getDeclaredField("h" + houseAddress).get(this);
            houseShape.setOnMouseClicked(mouseEvent -> {
                ActionStrategy actionStrategy = ActionFactory.of(game, game.getCurrentPlayer()).getActionByName(ActionType.BUILD);
                String argument = "";
                if (building instanceof Settlement) {
                    argument += "S";
                } else {
                    argument += "T";
                }
                argument += houseAddress;
                if (actionStrategy.isApplicable(argument)) {
                    actionStrategy.apply(argument);
                    houseShape.setFill(game.getCurrentPlayer().getColor());
                    renderGameInfo();
                    houseShape.setOnMouseClicked(null);
                }
            });
        }
    }
    public void renderGameInfo() {
        renderDiceInformation();
        renderStat();
        try {
            renderDices(GameInstance.game.diceResultToString());
        } catch (Exception exception) {
            System.out.println("Render dices function failed: " + exception.getMessage());
        }

    }
    public void renderDiceInformation() {
        Map<Resource, Integer> rolledResources = game.getDiceResult();
        System.out.println(game.getDiceResult());
        StringBuilder diceResults = new StringBuilder();
        turnText.setText("It's " + game.getCurrentPlayer().getUniqueId() + " turn.");
        if (rolledResources != null) {
            for (var entry : rolledResources.entrySet()) {
                if (entry.getValue() > 0) {
                    diceResults.append(entry.getValue()).append(" ").append(entry.getKey()).append("\n");
                }
            }
        }
        resourceList.setText(diceResults.toString());
    }
    public void renderStat() {
        StringBuilder stat = new StringBuilder();
        for (Player player : game.getPlayers()) {
            stat.append(player.getUniqueId()).append(" point(s): ").append(player.getScore()).append("\n");
        }
        statsFreeText.setText(stat.toString());
    }
}



