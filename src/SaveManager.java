import javafx.stage.*;
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
import javafx.scene.input.*;
import javafx.collections.*;

class SaveManager {

 Stage saveStage;
 private double xOffset;
 private double yOffset;
 ArrayList<Button> saveloadButtonArray;
 ArrayList<Button> deleteButtonArray;
 
 SaveManager(String typeOfDialog, boolean[] checkList, ObservableList<CheckBox> checkBoxList) {

   if (typeOfDialog == "save") {

     makeGUI("save");
     makeSave(saveloadButtonArray, checkList);

   } else {

     makeGUI("load");
     makeLoadAction(saveloadButtonArray , checkBoxList);
   }
 }

 private void makeSave(ArrayList<Button> alButton, boolean[] saveList) {

  for( Button button : alButton) {


    button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

      @Override
      public void handle(MouseEvent event) {
    
        String [] nameArr = button.getText().split(". ", 2);
        if(nameArr[1].equals( "Empty")) {
            inputSaveName(button, saveList);    
        } else {     	
            confirmSave(button, saveList);
        }
      }});
    }}


 private void inputSaveName(Button but, boolean[] checkList) {

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
   nameField.setFont(new Font("Cambria",20)); 
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
      
       String [] nameArr = but.getText().split(". ", 2);
       File saveRep = new File("./saves");
       for (String fileName : saveRep.list()) {
    	   String[] fileArr = fileName.split(". ",2);
    	   if (nameArr[0].equals(fileArr[0]))  {
    		   File delFile = new File("./saves/" + fileName);
    		   delFile.delete();
    	   }
       }
       but.setText(nameArr[0] + ". " + nameField.getText().replaceAll("[\\?\\:\\<\\>\\|\\*\"\\/\\\\]",""));
       try {
    	   ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("./saves/" + but.getText().replaceAll("[\\?\\:\\<\\>\\|\\*\"\\/\\\\]","") + ".ser"));
    	   boolean[] checkBoxArray = checkList;
    	   outputStream.writeObject(checkBoxArray);
    	   outputStream.close();
       } catch (Exception ex) { ex.printStackTrace(); }
       nameStage.close();
     }
   }); 


   HBox buttonBox = new HBox();
   buttonBox.setStyle("-fx-padding: 0 0 3 0");
   buttonBox.setAlignment(Pos.CENTER);

   HBox.setHgrow(acceptNameButton, Priority.ALWAYS);
   HBox.setHgrow(cancelNameButton, Priority.ALWAYS);

   acceptNameButton.setMaxWidth(130);
   cancelNameButton.setMaxWidth(130);

   buttonBox.getChildren().addAll(acceptNameButton,cancelNameButton);
  

   saveBorder.setBottom(buttonBox);
   saveBorder.setCenter(fieldPane);

   nameStage.setScene(nameScene);
   nameStage.show();
 }

 private void confirmSave(Button button, boolean[] checkBox) {

   Stage confirmStage = new Stage();
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

    	 inputSaveName(button, checkBox);
    	 confirmStage.close();
     }
   });

   cancelButton.setOnAction(new EventHandler<ActionEvent>() {
  
     @Override
     public void handle(ActionEvent ae) {

       confirmStage.close();
     }
   });

   HBox buttonBox = new HBox();
   buttonBox.setAlignment(Pos.CENTER);

   HBox.setHgrow(acceptButton, Priority.ALWAYS);
   HBox.setHgrow(cancelButton, Priority.ALWAYS);

   acceptButton.setMaxWidth(130);
   cancelButton.setMaxWidth(130);

   buttonBox.getChildren().addAll(acceptButton,cancelButton);
  

   confirmBorder.setBottom(buttonBox);
   confirmBorder.setCenter(warningText);


   confirmStage.setScene(mainScene);
   confirmStage.show();
 }  


 private void makeLoadAction(ArrayList<Button> alButton, ObservableList<CheckBox> checkBoxLoad) {

	 for( Button button : alButton) {


	    button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

	      @Override	
	      	public void handle(MouseEvent event) {
		    
	    	  String [] nameArr = button.getText().split(". ", 2);
		       if(nameArr[1].equals( "Empty")) {

		       } else {     	
 
		    	   confirmLoad(button , checkBoxLoad);
		       }
	      }});
 }}
 
 
 
 private void confirmLoad(Button button, ObservableList<CheckBox> checkBoxForLoad) {

	   Stage confirmLoadStage = new Stage();
	   confirmLoadStage.initModality(Modality.WINDOW_MODAL);
	   confirmLoadStage.initOwner(saveStage);
	   confirmLoadStage.initStyle(StageStyle.UNDECORATED);
	   confirmLoadStage.initStyle(StageStyle.TRANSPARENT);
	   
	   BorderPane confirmBorder = new BorderPane();
	   confirmBorder.setStyle("-fx-background-color:saddlebrown;" +"-fx-border-width:5;" + "-fx-border-radius:16;" + "-fx-font-family: Cambria;" + "-fx-border-color:olive");
	   Scene mainScene = new Scene (confirmBorder,300,120);
	   mainScene.setFill(Color.TRANSPARENT);

	   Rectangle rect = new Rectangle(300,120);
	   rect.setArcHeight(40.0);
	   rect.setArcWidth(40.0);
	   confirmBorder.setClip(rect);

	   String[] loadArr = button.getText().split(". ", 2);
	   Text warningText = new Text("Load save file " + loadArr[0] + "?");
	   warningText.setStyle("-fx-fill:black;" + "-fx-font-size:22");
	  
	   Button acceptLoadButton = new Button("Accept");
	   Button cancelLoadButton = new Button("Cancel");

	   acceptLoadButton.setStyle("-fx-background-color: darkkhaki;" + "-fx-background-insets: 0 2 2 0;" + "-fx-padding: 8;" + "-fx-font-size:15;" + "-fx-text-alignment: center");
	   cancelLoadButton.setStyle("-fx-background-color: darkkhaki;" + "-fx-background-insets: 0 0 2 2;" + "-fx-padding: 8;" + "-fx-font-size:15;" + "-fx-text-alignment: center");
	   
	   
	   acceptLoadButton.setOnAction(new EventHandler<ActionEvent>() {
		   
	     @Override
	     public void handle(ActionEvent ae) {
	    		 
	    	boolean[] list = null;
	   	    try {
	   	    	ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("./saves/" + button.getText() + ".ser"));
	   			list =  (boolean[]) inputStream.readObject();
	   	        inputStream.close();
	   	    } catch (Exception ex) {ex.printStackTrace();}
	   	     for(int count = 0; count<checkBoxForLoad.size();count++) {
	   	    	 checkBoxForLoad.get(count).setSelected(list[count]);
	   	     }
	    	 confirmLoadStage.close(); 
	     }
	   });

	   cancelLoadButton.setOnAction(new EventHandler<ActionEvent>() {
	  
	     @Override
	     public void handle(ActionEvent ae) {

	       confirmLoadStage.close();
	     }
	   });

	   HBox buttonBox = new HBox();
	   buttonBox.setAlignment(Pos.CENTER);

	   HBox.setHgrow(acceptLoadButton, Priority.ALWAYS);
	   HBox.setHgrow(cancelLoadButton, Priority.ALWAYS);
	   acceptLoadButton.setMaxWidth(130);
	   cancelLoadButton.setMaxWidth(130);
	   buttonBox.getChildren().addAll(acceptLoadButton,cancelLoadButton);
	  
	   confirmBorder.setBottom(buttonBox);
	   confirmBorder.setCenter(warningText);

	   confirmLoadStage.setScene(mainScene);
	   confirmLoadStage.show();
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
   HBox.setHgrow(hBoxRegion,Priority.ALWAYS);

   Region hBoxRegion2 = new Region();
   HBox.setHgrow(hBoxRegion2,Priority.ALWAYS);

   HBox.setMargin(closeButton, new Insets(5, 0, 0, 0));
   HBox.setMargin(topText, new Insets(5, 0, 0, 40));
   topHBOX.getChildren().addAll(hBoxRegion2,topText,hBoxRegion,closeButton);


   VBox centerVBOX = new VBox(15);
   centerVBOX.setAlignment(Pos.CENTER);

   saveloadButtonArray = new ArrayList<Button>();
   deleteButtonArray = new ArrayList<Button>();

   File checkFile = new File ("./saves" );
   for (int x = 0; x<7; x++) {
	 boolean existFile = false;
     GridPane gridPane = new GridPane();
     gridPane.setAlignment(Pos.CENTER);
     gridPane.getColumnConstraints().add(new ColumnConstraints(200));
     
     
	 Button fileButton = null;
     for(String checkName : checkFile.list()) {
    	 String[] checkArr = checkName.split(". " ,2);
    	 
    	 if ( checkArr[0].equals(Integer.toString(x+1))) {
       	    String[] arrForButton = checkName.split(".ser$");
    		fileButton = new Button(arrForButton[0]);
    		existFile = true;
    	 }
     }
     if(existFile==false) fileButton = new Button(x+1 + ". " + "Empty");
     Button deleteButton = new Button();
     deleteButton.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent event) {
			
			confirmDelete(deleteButton);			
		}
	 });
     fileButton.setMaxWidth(Double.MAX_VALUE);
     fileButton.setMaxHeight(Double.MAX_VALUE);
     fileButton.setMinHeight(39);
     GridPane.setMargin(fileButton,new Insets(0,0,0,0));
     deleteButton.setMinHeight(39);
     GridPane.setHgrow(fileButton, Priority.ALWAYS);
     GridPane.setVgrow(fileButton, Priority.ALWAYS);

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
 
 private void confirmDelete(Button button) {

	   Stage deleteStage = new Stage();
	   deleteStage.initModality(Modality.WINDOW_MODAL);
	   deleteStage.initOwner(saveStage);
	   deleteStage.initStyle(StageStyle.UNDECORATED);
	   deleteStage.initStyle(StageStyle.TRANSPARENT);
	   
	   BorderPane confirmBorder = new BorderPane();
	   confirmBorder.setStyle("-fx-background-color:saddlebrown;" +"-fx-border-width:5;" + "-fx-border-radius:16;" + "-fx-font-family: Cambria;" + "-fx-border-color:olive");
	   Scene mainScene = new Scene (confirmBorder,300,120);
	   mainScene.setFill(Color.TRANSPARENT);

	   Rectangle rect = new Rectangle(300,120);
	   rect.setArcHeight(40.0);
	   rect.setArcWidth(40.0);
	   confirmBorder.setClip(rect);

		int buttonIndex = deleteButtonArray.indexOf(button);
	   String[] deleteArr = saveloadButtonArray.get(buttonIndex).getText().split(". ", 2);
	   Text warningText = new Text("Delete save file " +  deleteArr[0] + "?");
	   warningText.setStyle("-fx-fill:black;" + "-fx-font-size:22");
	  
	   Button acceptButton = new Button("Accept");
	   Button cancelButton = new Button("Cancel");

	   acceptButton.setStyle("-fx-background-color: darkkhaki;" + "-fx-background-insets: 0 2 2 0;" + "-fx-padding: 8;" + "-fx-font-size:15;" + "-fx-text-alignment: center");
	   cancelButton.setStyle("-fx-background-color: darkkhaki;" + "-fx-background-insets: 0 0 2 2;" + "-fx-padding: 8;" + "-fx-font-size:15;" + "-fx-text-alignment: center");
	   
	   
	   acceptButton.setOnAction(new EventHandler<ActionEvent>() {
		   
	     @Override
	     public void handle(ActionEvent ae) {

	    	 removeSave(button);
	    	 deleteStage.close();
	     }
	   });

	   cancelButton.setOnAction(new EventHandler<ActionEvent>() {
	  
	     @Override
	     public void handle(ActionEvent ae) {

	    	 deleteStage.close();
	     }
	   });

	   HBox buttonBox = new HBox();
	   buttonBox.setAlignment(Pos.CENTER);

	   HBox.setHgrow(acceptButton, Priority.ALWAYS);
	   HBox.setHgrow(cancelButton, Priority.ALWAYS);

	   acceptButton.setMaxWidth(130);
	   cancelButton.setMaxWidth(130);

	   buttonBox.getChildren().addAll(acceptButton,cancelButton);
	  

	   confirmBorder.setBottom(buttonBox);
	   confirmBorder.setCenter(warningText);


	   deleteStage.setScene(mainScene);
	   deleteStage.show();
	 }  
 
 	private void removeSave(Button deleteButton) {
 		
		int delButtonIndex = deleteButtonArray.indexOf(deleteButton);
		String[] numberInName = saveloadButtonArray.get(delButtonIndex).getText().split(". ", 2);
		File removeFile = new File ("./saves/" +  saveloadButtonArray.get(delButtonIndex).getText() + ".ser");
		removeFile.delete();
		saveloadButtonArray.get(delButtonIndex).setText(numberInName[0] + ". " + "Empty");
 	}
}