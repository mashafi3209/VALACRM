package sample;

import java.awt.Dimension;
import java.awt.Font;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 800, 675));
        primaryStage.show();

    }
    public static void errorMessage(String message) {
        UIManager.put("OptionPane.minimumSize", new Dimension(600, 200));
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
            "Arial", Font.BOLD, 40)));
    }

    public static void infoMessage(String message) {
        UIManager.put("OptionPane.minimumSize", new Dimension(600, 200));
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font(
            "Arial", Font.BOLD, 40)));

        JOptionPane.showMessageDialog(null, message, "Message",
            JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
