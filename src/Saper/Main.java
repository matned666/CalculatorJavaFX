package Saper;


import Calculator.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(this.getClass().getResource("\\FXML\\Saper.fxml"));
        GridPane gridPane = loader.load();

        SaperController controller = loader.getController();

        Scene scene = new Scene(gridPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("SUPER SAPER FX");
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);

    }
}


//public class Main {
//
//    public static void main(String... arg) {
//        int matrixField = 10, numberOfBombs = 10;
//        try {
//            if (numberOfBombs > matrixField * matrixField) throw new CustomException();
//            Game game = new Game(numberOfBombs, matrixField);
//        } catch (CustomException e) {
//            System.out.println("Too many bombs!!! The world has exploded");
//        }
//    }
//}
