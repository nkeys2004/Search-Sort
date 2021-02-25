package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {

    public static void display(String title, String message, String sortType) {
        Stage AlertWindow = new Stage();
        AlertWindow.initModality(Modality.APPLICATION_MODAL);
        AlertWindow.setTitle(title);
        AlertWindow.setMinWidth(250);

        Label welcomeMessage = new Label();
        welcomeMessage.setText(sortType);
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Return to Home Screen");
        closeButton.setOnAction(e -> AlertWindow.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(welcomeMessage, label, closeButton);
        label.setAlignment(Pos.CENTER);

        Scene AlertScene = new Scene(layout, 300, 250);
        AlertWindow.setScene(AlertScene);
        AlertWindow.showAndWait();

    }

}
