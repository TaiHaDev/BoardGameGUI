package comp1140.ass2.gui;

import comp1140.ass2.CatanDiceExtra;
import comp1140.ass2.game.GameInstance;
import comp1140.ass2.game.Road;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;


import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GameController implements Initializable {

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
    public Rectangle r4053;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        List<Shape> shapeList = new ArrayList<>();

    }
    @FXML
    public void input(ActionEvent event) {
        if (!CatanDiceExtra.isBoardStateWellFormed(textField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Malformed board state");
            alert.setContentText("Check board state and try again.");
            alert.show();
            return;
        }
        GameInstance gameInstance = new GameInstance(textField.getText());
        System.out.println(textField.getText());

        for (Road road : gameInstance.getRoads()) {
//            getClass().getField(road.source)
        }

        System.out.println("test");
    }
    @FXML
    public void changeColour() {
        c1.setFill(Color.RED);
    }


}