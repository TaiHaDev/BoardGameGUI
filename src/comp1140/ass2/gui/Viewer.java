package comp1140.ass2.gui;

import comp1140.ass2.game.GameInstance;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;

    private final Group root = new Group();
    private final Group controls = new Group();
    private Label playerTextField = new Label();
    private TextField boardTextField;


    /**
     * Show the state of a (single player's) board in the window.
     *
     * @param boardState The string representation of the board state.
     */
    void displayState(String boardState) {
        // FIXME Task 6: implement the state viewer
        try {
            GameInstance gameInstance = new GameInstance(boardState);
            StringBuilder outputString = new StringBuilder();
            for (var e : gameInstance.players) {
                outputString.append(gameInstance.toString(e));
            }
            playerTextField = new Label(outputString.toString());
            playerTextField.setMaxWidth(1200);
            playerTextField.setWrapText(true);
            playerTextField.setLayoutY(45);

        } catch (NumberFormatException e) {
            playerTextField = new Label("Invalid input");
            playerTextField.setLayoutY(350);
            playerTextField.setLayoutX(550);
        } finally {
            playerTextField.setFont(Font.font("arial", 16));
            playerTextField.setTextFill(Color.LIGHTSKYBLUE);
            root.getChildren().add(playerTextField);
        }
    }

    /**
     * Create a basic text field for input and a refresh button.
     */
    private void makeControls() {
        Label boardLabel = new Label("Board State:");
        boardTextField = new TextField();
        boardTextField.setPrefWidth(500);
        Button button = new Button("Show");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (!playerTextField.getText().isEmpty()) {
                    playerTextField.setText("");
                }
                displayState(boardTextField.getText());
            }
        });
        HBox hb = new HBox();
        hb.getChildren().addAll(boardLabel, boardTextField, button);
        hb.setSpacing(10);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Board State Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
