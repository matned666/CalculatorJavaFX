package Saper;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("\\FXML\\Saper.fxml"));
        StackPane stackPane = loader.load();
        SaperController controller = loader.getController();
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("SUPER SAPER FX");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}