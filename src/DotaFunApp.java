import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.*;
import java.io.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.effect.*;
import javafx.scene.input.*;

public class DotaFunApp extends Application{

  Button menuB;
  Button settingsB;
  Button exitB;
  Button arrowButton;

  public static void main (String[] args) {
 
    launch();

  }

  @Override 
  public void start(Stage primaryStage) {

    primaryStage.setTitle("Dota Fun App");
    InputStream iconStream = getClass().getResourceAsStream("icon.png");
    Image image = new Image(iconStream);
    primaryStage.getIcons().add(image);
    StackPane stackMain = new StackPane();
    BorderPane borderPane = new BorderPane();
    borderPane.setStyle( "-fx-background-color: lightseagreen;");

  
    VBox menuBox = new VBox();
    menuBox.setSpacing(1.0);
    menuBox.setStyle( "-fx-background-color: steelblue;");
    //menuBox.setBackground(new Background(new BackgroundFill(Color.STEELBLUE,CornerRadii.EMPTY, Insets.EMPTY)));
    menuBox.setFillWidth(true);

    menuB = new Button("Menu");
    settingsB = new Button("Settings");
    exitB = new Button("Exit");

    menuB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    settingsB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    exitB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

    menuB.setStyle("-fx-font-family: sans-serif ; -fx-font-size: 14pt; -fx-background-color: teal; -fx-text-fill: white; -fx-border-color: mintcream");
    exitB.setStyle("-fx-font-family: sans-serif ; -fx-font-size: 14pt; -fx-background-color: teal; -fx-text-fill: white; -fx-border-color: mintcream");
    settingsB.setStyle("-fx-font-family: sans-serif ; -fx-font-size: 14pt; -fx-background-color: teal; -fx-text-fill: white; -fx-border-color: mintcream");
    
    exitB.setOnAction(new EventHandler<ActionEvent>() { public void handle(ActionEvent event) { primaryStage.close();}});



    arrowButton = new Button("b");
    arrowButton.setStyle("-fx-min-width: 15px; " +
                         "-fx-min-height: 100px; " +
                          "-fx-max-width: 15px; " +
                          "-fx-max-height: 100px;" +
                          "-fx-background-color: steelblue;" +
                          "-fx-border-color: royalblue;" +
                          "-fx-border-width: 2 2 2 0;" +
                          "-fx-border-radius: 0 10 10 0;;" +
                          "-fx-background-radius: 0 10 10 0" 
                     );



    shadowM(menuB);
    shadowM(settingsB);
    shadowM(exitB);
    DropShadow shadowE = new DropShadow();
    menuBox.setEffect(shadowE);

    arrowButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
      new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            arrowButton.setStyle("-fx-min-width: 15px; " +
                         "-fx-min-height: 100px; " +
                          "-fx-max-width: 15px; " +
                          "-fx-max-height: 100px;" +
                          "-fx-background-color: steelblue;" +
                          "-fx-border-color: royalblue;" +
                          "-fx-border-width: 2 2 2 0;" +
                          "-fx-border-radius: 0 10 10 0;" +
                          "-fx-background-radius: 0 10 10 0;" +
"-fx-effect: dropshadow(gaussian, #414040, 6, 0, 3, 0)");

        }
    }); 

    arrowButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
      new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
           arrowButton.setStyle("-fx-min-width: 15px; " +
                         "-fx-min-height: 100px; " +
                          "-fx-max-width: 15px; " +
                          "-fx-max-height: 100px;" +
                          "-fx-background-color: steelblue;" +
                          "-fx-border-color: royalblue;" +
                          "-fx-border-width: 2 2 2 0;" +
                          "-fx-border-radius: 0 10 10 0;;" +
                          "-fx-background-radius: 0 10 10 0");
        }
    });


    VBox arrowBox = new VBox(arrowButton);
    arrowBox.setAlignment(Pos.CENTER_LEFT);
    arrowBox.setPrefHeight(menuBox.getPrefHeight());
    HBox hbox = new HBox(menuBox,arrowBox);

    menuBox.getChildren().addAll(menuB,settingsB,exitB);    

    borderPane.setLeft(hbox);    
    stackMain.getChildren().add(borderPane);
    Scene mainScene = new Scene(stackMain,900,500);
    primaryStage.setScene(mainScene);


    primaryStage.show();

  }

  private void shadowM(Node but) {

    DropShadow shadowE = new DropShadow();
    but.addEventHandler(MouseEvent.MOUSE_ENTERED, 
      new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            but.setEffect(shadowE);
        }
    }); 

    but.addEventHandler(MouseEvent.MOUSE_EXITED, 
      new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            but.setEffect(null);
        }
    });
  }
    
}
