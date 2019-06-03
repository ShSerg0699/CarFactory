package factory;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    private CarFactory carFactory;
    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Label engineLabel;

    @FXML
    private Label carcassLabel;

    @FXML
    private Label accessoryLabel;

    @FXML
    private Label carLabel;

    @FXML
    private void initialize(){
        startButton.setOnAction(event -> {
            carFactory.run();
            System.out.println("run");
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    update();
                }
            };
            timer.start();
        });
        stopButton.setOnAction(event -> {
            carFactory.stop();
            System.out.println("stop");
        });

    }

    private void update(){
        engineLabel.setText(carFactory.getEngineStorageSize());
        carcassLabel.setText(carFactory.getCarcassStorageSize());
        accessoryLabel.setText(carFactory.getAccessoryStorageSize());
        carLabel.setText(carFactory.getCarStorageSize());
    }

    public void setCarFactory(CarFactory carFactory) {
        this.carFactory = carFactory;
    }
}

