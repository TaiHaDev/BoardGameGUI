package comp1140.ass2.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Viewer extends Application {

    private static final int VIEWER_WIDTH = 1200;
    private static final int VIEWER_HEIGHT = 700;
    FXMLLoader fxmlLoader = new FXMLLoader(Viewer.class.getResource("game-view.fxml"));

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(fxmlLoader.load(), VIEWER_WIDTH, VIEWER_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
