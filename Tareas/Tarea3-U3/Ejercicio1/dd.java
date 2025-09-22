import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Una calculadora gráfica simple para operaciones lógicas básicas usando JavaFX.
 * Permite al usuario seleccionar valores booleanos para dos entradas (A y B)
 * y calcular los resultados de las operaciones AND, OR, NOT y XOR.
 */
public class dd extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Calculadora Lógica");

        // --- Panel Principal ---
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setAlignment(Pos.CENTER);
        mainLayout.setStyle("-fx-background-color: #f0f0f0;");

        // --- Etiqueta de Título ---
        Label titleLabel = new Label("Calculadora Lógica");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #333;");

        // --- Sección de Entrada A ---
        Label labelA = new Label("Entrada A:");
        labelA.setStyle("-fx-font-size: 16px;");
        ToggleGroup groupA = new ToggleGroup();
        RadioButton trueA = new RadioButton("True");
        trueA.setToggleGroup(groupA);
        trueA.setSelected(true); // Valor por defecto
        trueA.setStyle("-fx-font-size: 14px;");
        RadioButton falseA = new RadioButton("False");
        falseA.setToggleGroup(groupA);
        falseA.setStyle("-fx-font-size: 14px;");
        HBox inputBoxA = new HBox(10, labelA, trueA, falseA);
        inputBoxA.setAlignment(Pos.CENTER);

        // --- Sección de Entrada B ---
        Label labelB = new Label("Entrada B:");
        labelB.setStyle("-fx-font-size: 16px;");
        ToggleGroup groupB = new ToggleGroup();
        RadioButton trueB = new RadioButton("True");
        trueB.setToggleGroup(groupB);
        trueB.setSelected(true); // Valor por defecto
        trueB.setStyle("-fx-font-size: 14px;");
        RadioButton falseB = new RadioButton("False");
        falseB.setToggleGroup(groupB);
        falseB.setStyle("-fx-font-size: 14px;");
        HBox inputBoxB = new HBox(10, labelB, trueB, falseB);
        inputBoxB.setAlignment(Pos.CENTER);

        VBox inputsContainer = new VBox(15, inputBoxA, inputBoxB);
        inputsContainer.setAlignment(Pos.CENTER);

        // --- Sección de Operaciones ---
        Button andButton = new Button("A AND B");
        Button orButton = new Button("A OR B");
        Button notAButton = new Button("NOT A");
        Button xorButton = new Button("A XOR B");
        
        // Estilo común para los botones
        String buttonStyle = "-fx-background-color: #007bff; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10 20; -fx-background-radius: 5;";
        String buttonHoverStyle = "-fx-background-color: #0056b3;";
        
        andButton.setStyle(buttonStyle);
        orButton.setStyle(buttonStyle);
        notAButton.setStyle(buttonStyle);
        xorButton.setStyle(buttonStyle);

        // Efecto hover para los botones
        andButton.setOnMouseEntered(e -> andButton.setStyle(buttonStyle + buttonHoverStyle));
        andButton.setOnMouseExited(e -> andButton.setStyle(buttonStyle));
        orButton.setOnMouseEntered(e -> orButton.setStyle(buttonStyle + buttonHoverStyle));
        orButton.setOnMouseExited(e -> orButton.setStyle(buttonStyle));
        notAButton.setOnMouseEntered(e -> notAButton.setStyle(buttonStyle + buttonHoverStyle));
        notAButton.setOnMouseExited(e -> notAButton.setStyle(buttonStyle));
        xorButton.setOnMouseEntered(e -> xorButton.setStyle(buttonStyle + buttonHoverStyle));
        xorButton.setOnMouseExited(e -> xorButton.setStyle(buttonStyle));

        HBox operationsBox = new HBox(10, andButton, orButton, notAButton, xorButton);
        operationsBox.setAlignment(Pos.CENTER);

        // --- Sección de Resultado ---
        Label resultLabel = new Label("Resultado:");
        resultLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 15; -fx-border-color: #ccc; -fx-border-width: 1; -fx-background-color: #ffffff; -fx-min-width: 200; -fx-alignment: center;");
        resultLabel.setAlignment(Pos.CENTER);

        // --- Lógica de los Eventos ---
        andButton.setOnAction(event -> {
            boolean valA = trueA.isSelected();
            boolean valB = trueB.isSelected();
            resultLabel.setText("Resultado: " + (valA && valB));
        });

        orButton.setOnAction(event -> {
            boolean valA = trueA.isSelected();
            boolean valB = trueB.isSelected();
            resultLabel.setText("Resultado: " + (valA || valB));
        });

        notAButton.setOnAction(event -> {
            boolean valA = trueA.isSelected();
            resultLabel.setText("Resultado: " + (!valA));
        });

        xorButton.setOnAction(event -> {
            boolean valA = trueA.isSelected();
            boolean valB = trueB.isSelected();
            resultLabel.setText("Resultado: " + (valA ^ valB));
        });

        // --- Ensamblar la Interfaz ---
        mainLayout.getChildren().addAll(titleLabel, inputsContainer, operationsBox, resultLabel);

        // --- Configurar la Escena y Mostrar ---
        Scene scene = new Scene(mainLayout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
