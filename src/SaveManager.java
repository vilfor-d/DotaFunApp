import javafx.stage.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import java.io.*;
import javafx.event.*;
import javafx.scene.image.*;
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.text.*;
import java.util.ArrayList;

class SaveManager {

 Stage saveStage;
 private double xOffset;
 private double yOffset;
 ArrayList<Button> saveloadButtonArray;
 ArrayList<Button> deleteButtonArray;
 
 SaveManager(String typeOfDialog, ObservableList<CheckBox> checkList) {

   if (typeOfDialog == "save") {

     makeGUI("save");
     makeSave(saveloadButtonArray, checkList);

   } else {

     makeGUI("load");
     makeLoadAction(saveloadButtonArray);

   }
 


 }

 private void makeSave(ArrayList<Button> alButton, ObservableList<CheckBox> saveList) {

  for( Button button : alButton) {

    button.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {

        boolean check = false;

        if(button.getText() != "Empty") {
 
        check = confirmSave();

        } 

        if(check) inputSaveName(button);


      }

    });

  }   

 }

 private void inputSaveName(Button but) {

   Stage nameStage = new Stage();
   nameStage.initModality(Modality.WINDOW_MODAL);
   nameStage.initOwner(saveStage);
   nameStage.initStyle(StageStyle.UNDECORATED);
   nameStage.initStyle(StageStyle.TRANSPARENT);

   BorderPane saveBorder = new BorderPane();
   saveBorder.setStyle("-fx-background-color:saddlebrown;" +"-fx-border-width:5;" + "-fx-border-radius:16;" + "-fx-font-family: Cambria;" + "-fx-border-color:olive");
   Scene nameScene = new Scene (saveBorder,300,120);
   nameScene.setFill(Color.TRANSPARENT);

   Rectangle rect = new Rectangle(300,120);
   rect.setArcHeight(40.0);
   rect.setArcWidth(40.0);
   saveBorder.setClip(rect);

   Button acceptNameButton = new Button("Accept");
   Button cancelNameButton = new Button("Cancel");

   acceptNameButton.setStyle("-fx-background-color: darkkhaki;" + "-fx-background-insets: 0 2 2 0;" + "-fx-padding: 8;" + "-fx-font-size:15;");
   cancelNameButton.setStyle("-fx-background-color: darkkhaki;" + "-fx-background-insets: 0 0 2 2;" + "-fx-padding: 8;" + "-fx-font-size:15;");
 
   cancelNameButton.setOnAction(new EventHandler<ActionEvent>() {
  
     @Override
     public void handle(ActionEvent ae) {

       nameStage.close();
     }
   });

   FlowPane fieldPane = new FlowPane();
   fieldPane.setAlignment(Pos.CENTER);

   TextField nameField = new TextField();
   nameField.setFont(new Font("Comic Sans MS",18)); 
   nameField.setPrefColumnCount(14);
   fieldPane.getChildren().add(nameField);


   nameField.setPromptText("Write save name");
    
   nameField.setOnKeyTyped(event ->{
     int maxCharacters = 16;
     if(nameField.getText().length() > maxCharacters) event.consume();
   });

   acceptNameButton.setOnAction(new EventHandler<ActionEvent>() {
     @Override
     public void handle(ActionEvent ae) {

       but.setText(nameField.getText());
       nameStage.close();
     }
   }); 


   HBox buttonBox = new HBox();
   buttonBox.setStyle("-fx-padding: 0 0 3 0");
   buttonBox.setAlignment(Pos.CENTER);

   buttonBox.setHgrow(acceptNameButton, Priority.ALWAYS);
   buttonBox.setHgrow(cancelNameButton, Priority.ALWAYS);

   acceptNameButton.setMaxWidth(130);
   cancelNameButton.setMaxWidth(130);

   buttonBox.getChildren().addAll(acceptNameButton,cancelNameButton);
  

   saveBorder.setBottom(buttonBox);
   saveBorder.setCenter(fieldPane);

   nameStage.setScene(nameScene);
   nameStage.show();


 }

 private boolean confirmSave() {

   Stage confirmStage = new Stage();
   boolean result = false;
   confirmStage.initModality(Modality.WINDOW_MODAL);
   confirmStage.initOwner(saveStage);
   confirmStage.initStyle(StageStyle.UNDECORATED);
   confirmStage.initStyle(StageStyle.TRANSPARENT);
   
   BorderPane confirmBorder = new BorderPane();
   confirmBorder.setStyle("-fx-background-color:saddlebrown;" +"-fx-border-width:5;" + "-fx-border-radius:16;" + "-fx-font-family: Cambria;" + "-fx-border-color:olive");
   Scene mainScene = new Scene (confirmBorder,300,120);
   mainScene.setFill(Color.TRANSPARENT);

   Rectangle rect = new Rectangle(300,120);
   rect.setArcHeight(40.0);
   rect.setArcWidth(40.0);
   confirmBorder.setClip(rect);

   Text warningText = new Text("Overwrite save file?");
   warningText.setStyle("-fx-fill:black;" + "-fx-font-size:22");
  
   Button acceptButton = new Button("Accept");
   Button cancelButton = new Button("Cancel");

   acceptButton.setStyle("-fx-background-color: darkkhaki;" + "-fx-background-insets: 0 2 2 0;" + "-fx-padding: 8;" + "-fx-font-size:15;" + "-fx-text-alignment: center");
   cancelButton.setStyle("-fx-background-color: darkkhaki;" + "-fx-background-insets: 0 0 2 2;" + "-fx-padding: 8;" + "-fx-font-size:15;" + "-fx-text-alignment: center");


   acceptButton.setOnAction(new EventHandler<ActionEvent>() {
  
     @Override
     public void handle(ActionEvent ae) {

       result = true;
       confirmStage.close();
     }
   });

   cancelButton.setOnAction(new EventHandler<ActionEvent>() {
  
     @Override
     public void handle(ActionEvent ae) {

       result = false;
       confirmStage.close();
     }
   });

   HBox buttonBox = new HBox();
   buttonBox.setAlignment(Pos.CENTER);

   buttonBox.setHgrow(acceptButton, Priority.ALWAYS);
   buttonBox.setHgrow(cancelButton, Priority.ALWAYS);

   acceptButton.setMaxWidth(130);
   cancelButton.setMaxWidth(130);

   buttonBox.getChildren().addAll(acceptButton,cancelButton);
  

   confirmBorder.setBottom(buttonBox);
   confirmBorder.setCenter(warningText);


   confirmStage.setScene(mainScene);
   confirmStage.show();
   


   

 }  


 private void makeLoadAction(ArrayList<Button> alButton) {

  for( Button button : alButton) {

    button.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {

        System.out.print("button work" + "\n");
      }

    });

  }   

 }

 private void makeGUI(String mode) {

   saveStage = new Stage();
   saveStage.initModality(Modality.APPLICATION_MODAL);
   saveStage.initStyle(StageStyle.UNDECORATED);
   saveStage.initStyle(StageStyle.TRANSPARENT);
   InputStream saveIcon = getClass().getResourceAsStream("AppImages/saveIcon.png");
   Image imageStage = new Image(saveIcon);
   saveStage.getIcons().add(imageStage);

   if(mode=="save") {

     saveStage.setTitle("Save Window");

   } else {
 
     saveStage.setTitle("Load Window"); 
   }


   Button closeButton = new Button();
   
   closeButton.setStyle("-fx-background-image: url('AppImages/cross')");

   closeButton.setOnAction(new EventHandler<ActionEvent>() { 

     public void handle(ActionEvent ev) {

   saveStage.close();

   }});

   InputStream iconStream = getClass().getResourceAsStream("AppImages/cross.png");
   Image image = new Image(iconStream);
   ImageView iv = new ImageView(image);
   closeButton.setGraphic(iv);
   closeButton.setStyle("-fx-background-color: transparent");


   BorderPane borderMain = new BorderPane();
   borderMain.setStyle("-fx-background-image: url('AppImages/saveloadBack.jpeg')");

   Rectangle rect = new Rectangle(350,450);
   rect.setArcHeight(60.0);
   rect.setArcWidth(60.0);
   borderMain.setClip(rect);


   Text topText = new Text();
   if (mode == "save") {
     
   topText.setX(50); 
   topText.setY(130);          
   topText.setText("Select Save Slot");
   topText.setFont(new Font(27));
   topText.setStrokeWidth(2);
   topText.setStroke(Color.DARKTURQUOISE);
   topText.setFill(Color.DARKTURQUOISE);
  
   } else {

   topText.setX(50); 
   topText.setY(130);            
   topText.setText("Select Load Slot");
   topText.setFont(new Font(27));
   topText.setStrokeWidth(2);
   topText.setStroke(Color.DARKTURQUOISE);
   topText.setFill(Color.DARKTURQUOISE);

   }

   HBox topHBOX = new HBox();

   Region hBoxRegion = new Region();
   topHBOX.setHgrow(hBoxRegion,Priority.ALWAYS);

   Region hBoxRegion2 = new Region();
   topHBOX.setHgrow(hBoxRegion2,Priority.ALWAYS);

   topHBOX.setMargin(closeButton, new Insets(5, 0, 0, 0));
   topHBOX.setMargin(topText, new Insets(5, 0, 0, 40));
   topHBOX.getChildren().addAll(hBoxRegion2,topText,hBoxRegion,closeButton);


   VBox centerVBOX = new VBox(15);
   centerVBOX.setAlignment(Pos.CENTER);

   saveloadButtonArray = new ArrayList<Button>();
   deleteButtonArray = new ArrayList<Button>();

   for (int x = 0; x<7; x++) {


     GridPane gridPane = new GridPane();
     gridPane.setAlignment(Pos.CENTER);
     gridPane.getColumnConstraints().add(new ColumnConstraints(200));
     Button fileButton = new Button("Empty");
     Button deleteButton = new Button();
     fileButton.setMaxWidth(Double.MAX_VALUE);
     fileButton.setMaxHeight(Double.MAX_VALUE);
     fileButton.setMinHeight(39);
     gridPane.setMargin(fileButton,new Insets(0,0,0,0));
     deleteButton.setMinHeight(39);
     gridPane.setHgrow(fileButton, Priority.ALWAYS);
     gridPane.setVgrow(fileButton, Priority.ALWAYS);

     fileButton.setStyle("-fx-font-family: Cambria;" + "-fx-font-size: 17;" + "-fx-background-color:cornflowerblue;" + "-fx-background-radius: 20 0 0 20;" + "-fx-text-fill:black;" + "-fx-background-insets:0");
     fileButton.setAlignment(Pos.BASELINE_LEFT);
     deleteButton.setStyle("-fx-background-color:cornflowerblue;" + "-fx-background-radius: 0 20 20 0;" + "-fx-background-insets:0;" + "-fx-graphic: url('AppImages/garbageIcon.png')");
     gridPane.add(fileButton,0,0);
     gridPane.add(deleteButton,1,0);

     saveloadButtonArray.add(fileButton);
     deleteButtonArray.add(deleteButton);
     centerVBOX.getChildren().add(gridPane);

   }
 
   


   borderMain.setCenter(centerVBOX);
   borderMain.setTop(topHBOX);
   Scene mainScene = new Scene(borderMain, 350, 450);
   mainScene.setFill(Color.TRANSPARENT);


     
   mainScene.setOnMousePressed(event -> {
     xOffset = saveStage.getX() - event.getScreenX();
     yOffset = saveStage.getY() - event.getScreenY();
   });

   mainScene.setOnMouseDragged(event -> {
     saveStage.setX(event.getScreenX() + xOffset);
     saveStage.setY(event.getScreenY() + yOffset);
   });
   
   saveStage.setScene(mainScene);
   saveStage.sizeToScene();
   saveStage.show();
 } 
}