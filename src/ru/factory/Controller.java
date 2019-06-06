package factory;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    private TextField engineTextField;

    @FXML
    private TextField carcassTextField;

    @FXML
    private TextField accessoryTextField;

    @FXML
    private TextField workerTextField;

    @FXML
    private TextField dealerTextField;

    @FXML
    private void initialize(){
        startButton.setOnAction(event -> {
            if(engineTextField.getText().isEmpty() ||
                    carcassTextField.getText().isEmpty() ||
                    accessoryTextField.getText().isEmpty() ||
                    workerTextField.getText().isEmpty() ||
                    dealerTextField.getText().isEmpty()){
                System.out.println("Enter all data");
                return;
            }

            System.out.println("run");
            try {
                carFactory.run(Integer.valueOf(engineTextField.getText()),
                        Integer.valueOf(carcassTextField.getText()),
                        Integer.valueOf(accessoryTextField.getText()),
                        Integer.valueOf(workerTextField.getText()),
                        Integer.valueOf(dealerTextField.getText()));
            }catch (NumberFormatException e){
                System.out.println("Incorrect data");
                return;
            }
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long now) {
                    update();
                }
            };
            timer.start();
        });
        stopButton.setOnAction(event -> {
            System.out.println("stop");
            carFactory.stop();
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

