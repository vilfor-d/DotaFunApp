import javafx.application.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.image.*;
import javafx.scene.paint.*;
import javafx.scene.control.*;
import javafx.scene.*;
import java.io.*;
import javafx.scene.layout.*;
import javafx.event.*;
import javafx.scene.effect.*;
import javafx.scene.input.*;
import javafx.animation.*;
import javafx.util.*;

public class DotaFunApp extends Application{

  Button menuB;
  Button settingsB;
  Button exitB;
  Button arrowButton;
  Button randomButton;
  Button challengeButton;
  Button firstButton;
  FlowPane menuFlow;
  StackPane centerStack; 

  public static void main (String[] args) {
 
    launch();

  }

  @Override 
  public void start(Stage primaryStage) {

    System.setProperty("prism.lcdtext", "false");
    primaryStage.setTitle("Dota Fun App");
    InputStream iconStream = getClass().getResourceAsStream("AppImages/icon.png");
    Image image = new Image(iconStream);
    primaryStage.getIcons().add(image);
    StackPane stackMain = new StackPane();
    centerStack = new StackPane();
    AnchorPane anchPane = new AnchorPane();
    anchPane.setStyle( "-fx-background-color: lightseagreen;");

  
    VBox menuBox = new VBox();
    menuBox.setSpacing(5.0);
    menuBox.setStyle( "-fx-background-color: steelblue;");
    menuBox.setFillWidth(true);

    menuB = new Button("Menu");

    menuB.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent ae) {

        centerStack.getChildren().clear();
        centerStack.getChildren().add(makeMenuFlow());   
     
    }});


    VBox.setMargin(menuB, new Insets(10, 0, 0, 0));
    settingsB = new Button("Settings");
    exitB = new Button("Exit");
    VBox.setMargin(exitB, new Insets(0, 0, 5,0));

    menuB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    settingsB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    exitB.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

    menuB.setStyle("-fx-font-family: 'Comic Sans MS',sans-serif ; -fx-font-size: 14pt; -fx-text-fill: darkslateblue;");
    menuB.setBackground(new Background(new BackgroundFill(Color.STEELBLUE,CornerRadii.EMPTY, Insets.EMPTY)));
    exitB.setStyle("-fx-font-family: 'Comic Sans MS',sans-serif ; -fx-font-size: 14pt; -fx-text-fill: darkslateblue;");
    exitB.setBackground(new Background(new BackgroundFill(Color.STEELBLUE,CornerRadii.EMPTY, Insets.EMPTY)));
    settingsB.setStyle("-fx-font-family: 'Comic Sans MS',sans-serif ; -fx-font-size: 14pt; -fx-text-fill: darkslateblue;");
    settingsB.setBackground(new Background(new BackgroundFill(Color.STEELBLUE,CornerRadii.EMPTY, Insets.EMPTY)));
    
    exitB.setOnAction(new EventHandler<ActionEvent>() { public void handle(ActionEvent event) { primaryStage.close();}});



    arrowButton = new Button();
    arrowButton.setStyle("-fx-min-width: 15px; " +
                         "-fx-min-height: 100px; " +
                          "-fx-max-width: 15px; " +
                          "-fx-max-height: 100px;" +
                          "-fx-background-color: steelblue;" +
                          "-fx-border-color: royalblue;" +
                          "-fx-border-width: 2 2 2 0;" +
                          "-fx-border-radius: 0 10 10 0;" +
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
                          "-fx-border-radius: 0 10 10 0;" +
                          "-fx-background-radius: 0 10 10 0");
        }
    });


    VBox arrowBox = new VBox(arrowButton);
    arrowBox.setAlignment(Pos.CENTER_LEFT);
    arrowBox.setPrefHeight(menuBox.getPrefHeight());
    HBox hbox = new HBox(menuBox,arrowBox);

    TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(900), hbox);
    menuTranslation.setFromX(0);
    menuTranslation.setToX(-102);


    arrowButton.setOnAction(new EventHandler<ActionEvent>() { public void handle (ActionEvent event) { 

      Bounds boundsInScene = hbox.localToScene(hbox.getBoundsInLocal());
      if(boundsInScene.getMaxX() > 117.0) {
        menuTranslation.setRate(1);
        menuTranslation.play();

        AnchorPane.setLeftAnchor(centerStack, 0.0); 

      }

      if(boundsInScene.getMaxX() < 24.0) {

        menuTranslation.setRate(-1);
        menuTranslation.play();
        AnchorPane.setLeftAnchor(centerStack, 115.0); 

      }

    }});
    
    centerStack.getChildren().add(makeMenuFlow());

    Region spacer = new Region();
    VBox.setVgrow(spacer, Priority.ALWAYS);

    menuBox.getChildren().addAll(menuB,settingsB,spacer,exitB); 
  

    AnchorPane.setTopAnchor(centerStack,0.0);
    AnchorPane.setRightAnchor(centerStack,0.0);
    AnchorPane.setBottomAnchor(centerStack,0.0);
    AnchorPane.setLeftAnchor(centerStack, 115.0);
    AnchorPane.setTopAnchor(hbox,0.0);
    AnchorPane.setBottomAnchor(hbox,0.0);
    AnchorPane.setLeftAnchor(hbox,0.0);

    anchPane.getChildren().addAll(centerStack,hbox);
    
   
    stackMain.getChildren().add(anchPane);
    Scene mainScene = new Scene(stackMain,800,500);
    primaryStage.setScene(mainScene);


    primaryStage.show();


  }

  private StackPane makeChallengeAnim(String name) {

    final Label label = new Label(name);
    label.setStyle("-fx-text-fill: goldenrod; -fx-font-family: 'Comic Sans MS',serif; -fx-font-size: 30px ; -fx-text-alignment: center");
    StackPane glass = new StackPane();
    StackPane.setAlignment(label, Pos.CENTER);
    glass.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7);; -fx-background-radius: 20;");
    glass.setMaxWidth(150);
    glass.setMinWidth(150);
    glass.setMaxHeight(250);
    glass.setMinHeight(250);
    glass.getChildren().addAll(label);

    menuFlow.getChildren().add(glass);

    return glass;


  }



  private void shadowM(Button but) {

    DropShadow shadowE = new DropShadow();
    but.addEventHandler(MouseEvent.MOUSE_ENTERED, 
      new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            but.setEffect(shadowE);
            but.setBackground(new Background(new BackgroundFill(Color.DODGERBLUE,CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }); 

    but.addEventHandler(MouseEvent.MOUSE_EXITED, 
      new EventHandler<MouseEvent>() {
        @Override public void handle(MouseEvent e) {
            but.setEffect(null);
            but.setBackground(new Background(new BackgroundFill(Color.STEELBLUE,CornerRadii.EMPTY, Insets.EMPTY)));
        }
    });
  }

 private FlowPane makeMenuFlow() {

  
    menuFlow = new FlowPane();
    menuFlow.setAlignment(Pos.CENTER);
    menuFlow.setHgap(10.0);

 Button randomButton = new Button();
    Button challengeButton = new Button();
    Button firstButton = new Button();

    randomButton.setStyle("-fx-min-width: 150px; " +
                         "-fx-min-height: 250px; " +
                          "-fx-max-width: 150px; " +
                          "-fx-max-height: 250px;" +
                          "-fx-font-family: cursive; " +
                          "-fx-font-size: 14pt;" +
                          "-fx-background-image: url('AppImages/randomImage.png');" +
                          "-fx-border-radius: 20 20 20 20;" +
                          "-fx-background-radius: 20 20 20 20;" +
                          "-fx-background-color: black; " +
                          "-fx-background-insets: 0;" +
                          "-fx-effect: dropshadow(gaussian, #414040, 6, 0, 3, 3)"
    );    

    randomButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
      new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {

          randomButton.setGraphic(makeChallengeAnim("Random\nHero"));
          randomButton.setContentDisplay(ContentDisplay.CENTER);
       

    }});


    randomButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
      new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {

          randomButton.setGraphic(null);

    }});

    randomButton.setOnAction(new EventHandler<ActionEvent> () {
  
      @Override
      public void handle(ActionEvent ae) {
 
        centerStack.getChildren().clear();
        centerStack.getChildren().add(new RandomMode().getRandomGUI());   

    }});




    challengeButton.setStyle("-fx-min-width: 150px; " +
                         "-fx-min-height: 250px; " +
                          "-fx-max-width: 150px; " +
                          "-fx-max-height: 250px;" +
                          "-fx-font-family: cursive; " +
                          "-fx-font-size: 14pt;" +
                          "-fx-border-radius: 20 20 20 20;" +
                          "-fx-background-radius: 20 20 20 20;" +
                          "-fx-background-color: steelblue; " +
                          "-fx-background-insets: 0;" +
                          "-fx-effect: dropshadow(gaussian, #414040, 6, 0, 3, 3)"
    );



    challengeButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
      new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {

          challengeButton.setGraphic(makeChallengeAnim("Challenge"));
          challengeButton.setContentDisplay(ContentDisplay.CENTER);
       

    }});


    challengeButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
      new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {

          challengeButton.setGraphic(null);

    }});


    firstButton.setStyle("-fx-min-width: 150px; " +
                         "-fx-min-height: 250px; " +
                          "-fx-max-width: 150px; " +
                          "-fx-max-height: 250px;" +
                          "-fx-font-family: cursive; " +
                          "-fx-font-size: 14pt;" +
                          "-fx-background-radius: 20 20 20 20;" +
                          "-fx-background-color: steelblue; " +
                          "-fx-background-insets: 0;" +
                          "-fx-effect: dropshadow(gaussian, #414040, 6, 0, 3, 3)" 
    );



    firstButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
      new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {

          firstButton.setGraphic(makeChallengeAnim("FB Stat"));
          firstButton.setContentDisplay(ContentDisplay.CENTER);
       

    }});

    firstButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
      new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {

          firstButton.setGraphic(null);

    }});

    menuFlow.getChildren().addAll(randomButton,challengeButton,firstButton);

    return menuFlow;
 }
    
    
}
