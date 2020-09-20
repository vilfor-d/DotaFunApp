import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.shape.*;
import javafx.util.Duration;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.animation.*;
import javafx.application.Platform;

import java.io.*;
import java.security.acl.Group;
import java.util.*;

class RandomMode { 
	
	int ImageViewCount = 0;
    ListView<CheckBox> heroListView;
    ObservableList<CheckBox> heroObs;
	HBox heroSpin;
    Label label;
    Button saveButton;
    Button loadButton;
    StackPane centerStack;
    String[] heroesNames = {"Abbadon","Alchemist","Ancient Apparition","Anti-Mage","Arc Warden","Axe","Bane","Batrider","Beastmaster","Bloodseeker","Bounty Hunter","Brewmaster","Bristleback","Broodmother","Centaur Warrunner","Chaos Knight","Chen","Clinkz","Clockwerk","Crystal Maiden","Dark Seer","Dark Willow","Dazzle","Death Prophet","Disruptor","Doom","Dragon Knight","Drow Ranger","Earth Spirit","Earthshaker","Elder Titan","Ember Spirit","Enchantress","Enigma","Faceless Void","Grimstroke","Gyrocopter","Huskar","Invoker","Io","Jakiro","Juggernaut","Keeper of the Light","Kunkka","Legion Commander","Leshrac","Lich","Lifestealer","Lina","Lion","Lone Druid","Luna","Lycan","Magnus","Mars","Medusa","Meepo","Mirana","Monkey King","Morphling","Naga Siren","Nature's Prophet","Necrophos","Night Stalker","Nyx Assassin","Ogre Magi","Omniknight","Oracle","Outworld Devourer","Pangolier","Phantom Assassin","Phantom Lancer","Phoenix","Puck","Pudge","Pugna","Queen of Pain","Razor","Riki","Rubick","Sand King","Shadow Demon","Shadow Fiend","Shadow Shaman","Silencer","Skywrath Mage","Slardar","Slark","Snapfire","Sniper","Spectre","Spirit Breaker","Storm Spirit","Sven","Techies","Templar Assassin","Terrorblade","Tidehunter","Timbersaw","Tinker","Tiny","Treant Protector","Troll Warlord","Tusk","Underlord","Undying","Ursa","Vengeful Spirit","Venomancer","Viper","Visage","Void Spirit","Warlock","Weaver","Windranger","Winter Wyvern","Witch Doctor","Wraith King","Zeus"};

  

  public StackPane getRandomGUI() {

    StackPane randomGUI = new StackPane();
    randomGUI.setStyle(" -fx-background-color: rgb(92, 76, 141)");
    BorderPane borderPane = new BorderPane();
    randomGUI.getChildren().add(borderPane);

    heroObs = FXCollections.observableArrayList();
    heroListView = new ListView<CheckBox>(heroObs);

    VBox listBoxPane = new VBox();
    listBoxPane.getChildren().add(heroListView);
    VBox.setVgrow(heroListView, Priority.ALWAYS);
    AnchorPane rightBox = new AnchorPane();
    
    saveButton = new Button("Save");
    saveButton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
    
  		boolean[] checkBool = new  boolean[heroObs.size()];
  	
        for(int x = 0; x<heroObs.size(); x++) {
        	checkBool[x] = heroObs.get(x).isSelected();
        }
    	  
        @SuppressWarnings("unused")
		SaveManager saveDialog = new SaveManager("save", checkBool, heroObs);
      
    }});

    loadButton = new Button("Load");
    loadButton.setOnAction(new EventHandler<ActionEvent>() {

      @Override
      public void handle(ActionEvent event) {
        boolean[] checkBool = new  boolean[heroObs.size()];
        
           for(int x = 0; x<heroObs.size(); x++) {
          	checkBool[x] = heroObs.get(x).isSelected();  
        }
    	  
		SaveManager saveDialog = new SaveManager("load", checkBool, heroObs );
		saveDialog.setObserverList(heroObs);
		saveDialog.setHeroSpinForLoad(heroSpin); 
    }});

    saveButton.setStyle("-fx-background-insets: 0;" +
      "-fx-background-color: royalblue;" +
      "-fx-background-radius: 0 0 10 10;" +
      "-fx-border-width: 0 1.5 1.5 1.5;" +
      "-fx-border-color: navy;" +
      "-fx-border-radius: 0 0 10 10;" +
      "-fx-font-family: 'Comic Sans MS',sans-serif;" +
      "-fx-font-size: 16"
    );
    loadButton.setStyle("-fx-background-insets: 0;" +
      "-fx-background-color: royalblue;" +
      "-fx-background-radius: 0 0 10 10;" +
      "-fx-border-width: 0 1.5 1.5 1.5;" +
      "-fx-border-color: navy;" +
      "-fx-border-radius: 0 0 10 10;" +
      "-fx-font-family: 'Comic Sans MS',sans-serif;" +
      "-fx-font-size: 16"
    );

    shadowM(loadButton);
    shadowM(saveButton);

    HBox listButtonPane = new HBox();
    listButtonPane.setMinHeight(50);
    listButtonPane.setMaxHeight(100);
    listButtonPane.setAlignment(Pos.TOP_CENTER);
    listButtonPane.getChildren().addAll(saveButton,loadButton);

    CheckBox allHeroesCheck = new CheckBox("All Heroes");
    allHeroesCheck.setSelected(true);
    CheckBox clearHeroesCheck = new CheckBox("Clear All");
    allHeroesCheck.setStyle("-fx-text-fill: black;" + "-fx-font-size: 13");
    allHeroesCheck.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {

      int x = 0;

      for (CheckBox box : heroObs) {

        if(x>1) {    
          box.setSelected(true);
        }

        if(x==1) {
          box.setSelected(false);
        }
        x++;
    }
	    ImageView[] heroImageActionArr = baseHeroIcons(heroObs);
	    heroSpin.getChildren().clear();
	    heroSpin.getChildren().addAll(heroImageActionArr[0],heroImageActionArr[1],heroImageActionArr[2],heroImageActionArr[3]);
	    }});

    clearHeroesCheck.setStyle("-fx-text-fill: black;" + "-fx-font-size: 13");

    clearHeroesCheck.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {

      int x = 0;

      for (CheckBox box : heroObs) {

        if(x!=1) {
          box.setSelected(false);
        }
      x++;
    }
	    heroSpin.getChildren().clear();
      }});

    heroListView.getStylesheets().add(getClass().getResource("css/heroListStyle.css").toExternalForm());

    heroObs.add(allHeroesCheck);
    heroObs.add(clearHeroesCheck);

    for(String hero : heroesNames) {

      CheckBox heroCheck = new CheckBox(hero);
      heroCheck.setSelected(true);
      heroCheck.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
      clearHeroesCheck.setSelected(false);
      }});
      heroCheck.setOnAction((event) -> {

    		 if(!heroCheck.isSelected()) {
    			 allHeroesCheck.setSelected(false);
    		 }
    		    ImageView[] heroImageActionArr = baseHeroIcons(heroObs);
    		    heroSpin.getChildren().clear();
    		    if(heroImageActionArr.length < 4) {
 
    		    	for(ImageView tempImageView : heroImageActionArr) {
        		    heroSpin.getChildren().add(tempImageView);
    		    	}
    		    	
    		    } else {
    		    heroSpin.getChildren().addAll(heroImageActionArr[0],heroImageActionArr[1],heroImageActionArr[2],heroImageActionArr[3]);
    		    }
      });
      heroCheck.setStyle("-fx-text-fill: black;" + "-fx-font-size: 13");
      heroObs.add(heroCheck);
    }

    VBox centerVbox = new VBox();
    centerVbox.setAlignment(Pos.CENTER);
    
    //Панель анимации
    BorderPane animPane = new BorderPane();
    StackPane stackAnim = new StackPane();
    heroSpin = new HBox(2);
	ImageView euclidCursor = new ImageView( new Image("AppImages/euclidCursor.png", 25,20,false,true));
    StackPane.setAlignment(euclidCursor, Pos.BOTTOM_CENTER);
    stackAnim.getChildren().addAll(heroSpin, euclidCursor);
    heroSpin.setStyle("-fx-background-color: linear-gradient(to right, slateblue, darkslateblue);" +"-fx-max-width:594;" + "-fx-min-width:594");
    heroSpin.setAlignment(Pos.CENTER);
    Rectangle spinRect = new Rectangle(594,84);
    heroSpin.setClip(spinRect);
    ImageView[] heroImageArr = baseHeroIcons(heroObs);
    heroSpin.getChildren().addAll(heroImageArr[0],heroImageArr[1],heroImageArr[2],heroImageArr[3]);
    
    Image bottomImageSpin = new Image("AppImages/bottomSpin.png");
    Image topImageSpin = new Image("AppImages/topSpin.png");
    ImageView imageVievBottomSpin = new ImageView(bottomImageSpin);
    ImageView imageVievTopSpin = new ImageView(topImageSpin);
    animPane.setBottom(imageVievBottomSpin);
    animPane.setTop(imageVievTopSpin);
    Image leftImageSpin = new Image("AppImages/leftSpin.png");
    Image rightImageSpin = new Image("AppImages/rightSpin.png");
    ImageView imageVievLeftSpin = new ImageView(leftImageSpin);
    ImageView imageVievRightSpin = new ImageView(rightImageSpin);
    animPane.setLeft(imageVievLeftSpin);
    animPane.setRight(imageVievRightSpin);
    
    
    animPane.setCenter(stackAnim);
    animPane.setStyle("-fx-background-color: indigo;" + "-fx-max-width:600;" + "-fx-min-width:600;");
    
    //Панель полученного героя
    BorderPane randomisedHeroPanel = new BorderPane();
    randomisedHeroPanel.setStyle("-fx-min-height:80;" + "-fx-min-width:466;" + "-fx-max-width:466;" +"-fx-background-color: linear-gradient(from 0% 15% to 0% 70% , slateblue,gray);" +  "-fx-background-radius: 0 0 10 10");
    Image bottomImage = new Image("AppImages/bottom.png");
    ImageView imageVievBottom = new ImageView(bottomImage);
    Image rightimage = new Image("AppImages/right.png");
    Image leftImage = new Image("AppImages/left.png");
    ImageView imageLeft = new ImageView(leftImage);
    ImageView imageRight = new ImageView(rightimage);
    centerStack = new StackPane();
    centerStack.setStyle( "-fx-min-height:82;" + "-fx-max-height:82");
    Rectangle stackPaneClip = new Rectangle (470,84);
    centerStack.setClip(stackPaneClip);
    label = new Label("Unknown hero");
    centerStack.getChildren().add(label);
    label.getStylesheets().add(getClass().getResource("css/heroNameLabel.css").toExternalForm());
    randomisedHeroPanel.setCenter(centerStack);
    randomisedHeroPanel.setBottom(imageVievBottom);
    randomisedHeroPanel.setLeft(imageLeft);
    randomisedHeroPanel.setRight(imageRight);

    //Кнопка рандома
    FlowPane randomBPane = new FlowPane();
    randomBPane.setAlignment(Pos.CENTER);
    Button randomButton = new Button();
    DropShadow buttonShadow = new DropShadow();
    randomButton.setEffect(buttonShadow);
    randomButton.setStyle("-fx-color:transparent;" + "-fx-background-insets: 0;" + "-fx-background-radius: 12;" + "-fx-background-image:url('AppImages/grd.png');"  +
    "-fx-min-height:80;"  + "-fx-min-width:350");
    randomBPane.getChildren().add(randomButton);
    randomBPane.setMargin(randomButton, new Insets(20,0,0,0));
    
    centerVbox.getChildren().addAll(animPane, randomisedHeroPanel, randomBPane);
    randomButton.setOnAction(new EventHandler<ActionEvent>() { 

      @Override
      public void handle (ActionEvent ae) {
        
    	ArrayList<ImageView>  allWorkingIM = allHeroIcons();
    	heroSpin.getChildren().clear();
    	if(allWorkingIM.size()<4) {
    		for(int j = 0; j<allWorkingIM.size(); j++) {
            	heroSpin.getChildren().add(allWorkingIM.get(j));
            	ImageViewCount++;
    		}
    	} else {
    	for(int notMoreThenFive = 0; notMoreThenFive<5; notMoreThenFive++) {
        	heroSpin.getChildren().add(allWorkingIM.get(notMoreThenFive));
        	ImageViewCount++;
    	}}
    	

		Timeline mainTimeline = new Timeline();
		for(int f = 0, t = -150; f<5; f++, t=t-150) {
			KeyFrame key = new KeyFrame(Duration.seconds(f+1),evt -> {
			//пытаюсь в обновление
			heroSpin.getChildren().add(allWorkingIM.get(ImageViewCount));
			TranslateTransition trs = new TranslateTransition(Duration.seconds(5));
			trs.setNode(heroSpin.getChildren().get(heroSpin.getChildren().size()-1));
			trs.setToX(-750);
			trs.play();
			//KeyFrame newKey = new KeyFrame(Duration.seconds(5), new KeyValue(heroSpin.getChildren().get(heroSpin.getChildren().size()-1).translateXProperty(), -750));
			//mainTimeline.getKeyFrames().add(newKey);
			//mainTimeline.play();
			heroSpin.getChildren().remove(0);
			ImageViewCount++;		
			
			
			}
			, new KeyValue(heroSpin.getChildren().get(f).translateXProperty(), t));
			mainTimeline.getKeyFrames().add(key);
		}
		mainTimeline.setRate(1);
        mainTimeline.setCycleCount(1);
		mainTimeline.play();
		
    	  
   	     Image heroIconImage = new Image("AppImages/HeroStack.png");
    	 ImageView heroIconImageView = new ImageView(heroIconImage);
    	 centerStack.getChildren().add(heroIconImageView);
    	 TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(1500), heroIconImageView);
    	 menuTranslation.setFromY(103);
    	 menuTranslation.setToY(-1);
         menuTranslation.setCycleCount(2);
         menuTranslation.setAutoReverse(true);
         menuTranslation.play();
         menuTranslation.setOnFinished(new EventHandler<ActionEvent>(){
        	 public void handle(ActionEvent ae)  {
            	 centerStack.getChildren().remove(heroIconImageView);       
        	 }}); 
         KeyFrame labelKey = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
				label.setText(heroesNames[(int) (Math.random() * 119)]);
			}});
         Timeline labelTimeline = new Timeline(labelKey);
         labelTimeline.setCycleCount(1);
         labelTimeline.play();
         
      }
    });

    AnchorPane.setTopAnchor(listBoxPane, -1.0);
    AnchorPane.setBottomAnchor(listBoxPane, 50.0);
    AnchorPane.setBottomAnchor(listButtonPane, 0.0);
    rightBox.getChildren().add(listBoxPane);
    rightBox.getChildren().add(listButtonPane);

    saveButton.setPrefHeight(40);
    saveButton.setPrefWidth(85);
    loadButton.setPrefHeight(40);
    loadButton.setPrefWidth(85);

    AnchorPane topAnchor = new AnchorPane();
    Pane gradientPane = new Pane();    
    gradientPane.setStyle("-fx-background-color: linear-gradient(from 25% 25% to 70% 70% , steelblue,royalblue);"+ 
     "-fx-border-width: 0 0 2 0;" +
     "-fx-border-color: navy");
    topAnchor.setPrefHeight(40);
    AnchorPane.setLeftAnchor(gradientPane,-120.0);    
    AnchorPane.setBottomAnchor(gradientPane,0.0);
    AnchorPane.setRightAnchor(gradientPane,168.0);
    AnchorPane.setTopAnchor(gradientPane,0.0);

    Pane colorPane = new Pane();
    colorPane.setStyle("-fx-background-color: royalblue;" + "-fx-background-image: url('AppImages/chooseENG.png')");
    DropShadow colorShadow = new DropShadow();
    colorPane.setEffect(colorShadow);  
    colorPane.setMinWidth(168);   
    AnchorPane.setRightAnchor(colorPane, 0.0);   
    AnchorPane.setBottomAnchor(colorPane,1.0);
    AnchorPane.setTopAnchor(colorPane,0.0);
    topAnchor.getChildren().add(gradientPane);     
    topAnchor.getChildren().add(colorPane);  

    borderPane.setRight(rightBox);
    borderPane.setCenter(centerVbox);
    borderPane.setTop(topAnchor);
   
    

    return randomGUI;
  }
  
  private ArrayList<ImageView>  allHeroIcons() {
	  ArrayList<ImageView> allView = new ArrayList<ImageView>();
	  File animationPicturesFile = new File("./bin/heroImages");
	  String[] animatonIconArray = animationPicturesFile.list();
	  
	  for (int x = 2; x<heroObs.size(); x++) {
		  if(heroObs.get(x).isSelected()) {
			ImageView IV = new ImageView( new Image("heroImages/" + animatonIconArray[x-2], 150,84,false,true));
			allView.add(IV);
		  }
	  }
      return allView;
  }
  
  static ImageView[] baseHeroIcons(ObservableList<CheckBox> argCheckList) {
		ImageView[] result =  null;
	    File picFolderFile = new File("./bin/heroImages");
	    String[] heroIconArray = picFolderFile.list();
	    ArrayList<String> namesForView = new ArrayList<String>();
	    for(int c = 2; c<argCheckList.size(); c++) {
	    	if(argCheckList.get(c).isSelected()) {
	    		namesForView.add(heroIconArray[c-2]);
	    	}
	    }
		if(namesForView.size() < 4) {
			int x = 0;
			result =  new ImageView[namesForView.size()];
			for(String tempName : namesForView) {
			    ImageView noName  = new ImageView( new Image("heroImages/" + tempName, 150,84,false,true));
			    result[x] = noName;
			    x++;
			}
		} else {
			ImageView hero0 = new ImageView( new Image("heroImages/" + namesForView.get(0), 150,84,false,true));
			ImageView hero1 = new ImageView(new Image("heroImages/" + namesForView.get(1),150,84,false,true));
			ImageView hero2 = new ImageView(new Image("heroImages/" + namesForView.get(2),150,84,false,true));
			ImageView hero3 = new ImageView(new Image("heroImages/" + namesForView.get(3),150,84,false,true));
			result =  new ImageView[4];
			result[0] = hero0; result[1] = hero1; result[2] = hero2; result[3] = hero3;
		}
	    return result;
  }

  private void shadowM(Button but) {

    DropShadow shadowE = new DropShadow();
    shadowE.setOffsetY(2.7);
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