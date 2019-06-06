package factory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main extends Application {

    private static final Logger log = LogManager.getLogger();

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        CarFactory carFactory = new CarFactory();
        controller.setCarFactory(carFactory);
        primaryStage.setScene(new Scene(root));
        primaryStage.setOnCloseRequest(event ->
                carFactory.stop());
        primaryStage.show();
    }
    public static void main(String[] args) {
        log.error("started.");
        launch(args);
    }
}
