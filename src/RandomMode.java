package dota;

import javafx.scene.layout.*;
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
import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.util.regex.Pattern;
import java.net.URLDecoder;

class RandomMode { 
	
    ListView<CheckBox> heroListView;
    ObservableList<CheckBox> heroObs;
	HBox heroSpin;
    Label label;
    Button saveButton;
    Button loadButton;
    StackPane centerStack;
    static ArrayList<String> heroIconArray;
    String[] heroesNames = {"Abbadon","Alchemist","Ancient Apparition","Anti-Mage","Arc Warden","Axe","Bane","Batrider","Beastmaster","Bloodseeker","Bounty Hunter","Brewmaster","Bristleback","Broodmother","Centaur Warrunner","Chaos Knight","Chen","Clinkz","Clockwerk","Crystal Maiden","Dark Seer","Dark Willow","Dazzle","Death Prophet","Disruptor","Doom","Dragon Knight","Drow Ranger","Earthshaker","Earth Spirit","Elder Titan","Ember Spirit","Enchantress","Enigma","Faceless Void","Grimstroke","Gyrocopter","Huskar","Invoker","Io","Jakiro","Juggernaut","Keeper of the Light","Kunkka","Legion Commander","Leshrac","Lich","Lifestealer","Lina","Lion","Lone Druid","Luna","Lycan","Magnus","Mars","Medusa","Meepo","Mirana","Monkey King","Morphling","Naga Siren","Nature's Prophet","Necrophos","Night Stalker","Nyx Assassin","Ogre Magi","Omniknight","Oracle","Outworld Devourer","Pangolier","Phantom Assassin","Phantom Lancer","Phoenix","Puck","Pudge","Pugna","Queen of Pain","Razor","Riki","Rubick","Sand King","Shadow Demon","Shadow Fiend","Shadow Shaman","Silencer","Skywrath Mage","Slardar","Slark","Snapfire","Sniper","Spectre","Spirit Breaker","Storm Spirit","Sven","Techies","Templar Assassin","Terrorblade","Tidehunter","Timbersaw","Tinker","Tiny","Treant Protector","Troll Warlord","Tusk","Underlord","Undying","Ursa","Vengeful Spirit","Venomancer","Viper","Visage","Void Spirit","Warlock","Weaver","Windranger","Winter Wyvern","Witch Doctor","Wraith King","Zeus"};

  

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


    String path = RandomMode.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    String decodedPath = null;
      try {
 	decodedPath = URLDecoder.decode(path, "UTF-8");
      } catch (Exception ex) {ex.printStackTrace();}

    heroIconArray = new ArrayList<String>();
    try {
      JarFile jf = new JarFile(new File(decodedPath));
      Enumeration e = jf.entries();
      String element = null;
      while (e.hasMoreElements()) {
        element = ((JarEntry)e.nextElement()).getName();
        if(Pattern.matches("^(heroImages.*)",element)) {
	  String[] arrForSplit = element.split("/", 2);
	  heroIconArray.add(arrForSplit[1]);
	}
      }
    } catch (Exception ex) {ex.printStackTrace();}
    heroIconArray.remove(0);
    
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
	    heroSpin.getChildren().addAll(heroImageActionArr[0],heroImageActionArr[1],heroImageActionArr[2],heroImageActionArr[3],heroImageActionArr[4]);
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
    heroListView.getStylesheets().add(getClass().getResource("/css/heroListStyle.css").toExternalForm());
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
      heroCheck.setOnAction(event -> {
    		 if(!heroCheck.isSelected()) {
    			 allHeroesCheck.setSelected(false);
    		 }
    		    ImageView[] heroImageActionArr = baseHeroIcons(heroObs);
    		    heroSpin.getChildren().clear();
    		    if(heroImageActionArr.length < 5) {
                  for(int f = 0; f<6-heroImageActionArr.length;f++) {
                    	ArrayList<ImageView>  tempAR = allHeroIcons();
   					   for(ImageView tempImageView : tempAR) {
   					     heroSpin.getChildren().add(tempImageView);
    		    	   }
   					 }
    		    } else {
    		    heroSpin.getChildren().addAll(heroImageActionArr[0],heroImageActionArr[1],heroImageActionArr[2],heroImageActionArr[3],heroImageActionArr[4]);
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
    heroSpin = new HBox();
    heroSpin.setAlignment(Pos.BASELINE_LEFT);
    InputStream cursorImageStream = getClass().getResourceAsStream("/AppImages/euclidCursor.png");
    Image cursorImage = new Image(cursorImageStream, 25, 20, false, true);
    try {
      cursorImageStream.close();
    } catch(Exception ex) {ex.printStackTrace();}
    ImageView euclidCursor = new ImageView( cursorImage);
    StackPane.setAlignment(euclidCursor, Pos.BOTTOM_CENTER);
    stackAnim.getChildren().addAll(heroSpin, euclidCursor);
    Rectangle spinRect = new Rectangle(594,84);
    spinRect.setLayoutX(73);
    stackAnim.setClip(spinRect);
    heroSpin.setStyle("-fx-background-color: linear-gradient(to right, slateblue, darkslateblue);" +"-fx-max-width:740;" + "-fx-min-width:740");
    ImageView[] heroImageArr = baseHeroIcons(heroObs);
    heroSpin.getChildren().addAll(heroImageArr[0],heroImageArr[1],heroImageArr[2],heroImageArr[3],heroImageArr[4]);
    InputStream bottomImageStream = getClass().getResourceAsStream("/AppImages/bottomSpin.png");
    InputStream topImageStream = getClass().getResourceAsStream("/AppImages/topSpin.png");
    InputStream leftImageStream = getClass().getResourceAsStream("/AppImages/leftSpin.png");
    InputStream rightImageStream = getClass().getResourceAsStream("/AppImages/rightSpin.png");
    
    Image bottomImageSpin = new Image(bottomImageStream);
    Image topImageSpin = new Image(topImageStream);
    ImageView imageVievBottomSpin = new ImageView(bottomImageSpin);
    ImageView imageVievTopSpin = new ImageView(topImageSpin);
    animPane.setBottom(imageVievBottomSpin);
    animPane.setTop(imageVievTopSpin);
    Image leftImageSpin = new Image(leftImageStream);
    Image rightImageSpin = new Image(rightImageStream);
    try {
      bottomImageStream.close();
      topImageStream.close();
      leftImageStream.close();
      rightImageStream.close();
    } catch(Exception ex) {ex.printStackTrace();}
    ImageView imageVievLeftSpin = new ImageView(leftImageSpin);
    ImageView imageVievRightSpin = new ImageView(rightImageSpin);
    animPane.setLeft(imageVievLeftSpin);
    animPane.setRight(imageVievRightSpin);
    
    animPane.setCenter(stackAnim);
    animPane.setStyle("-fx-background-color: indigo;" + "-fx-max-width:600;" + "-fx-min-width:600;");
    
    //Панель полученного героя
    BorderPane randomisedHeroPanel = new BorderPane();
    randomisedHeroPanel.setStyle("-fx-min-height:80;" + "-fx-min-width:466;" + "-fx-max-width:466;" +"-fx-background-color: linear-gradient(from 0% 15% to 0% 70% , slateblue,gray);" +  "-fx-background-radius: 0 0 10 10");
    InputStream botImageStream = getClass().getResourceAsStream("/AppImages/bottom.png");
    InputStream righImageStream = getClass().getResourceAsStream("/AppImages/right.png");
    InputStream lefImageStream = getClass().getResourceAsStream("/AppImages/left.png");
    Image bottomImage = new Image(botImageStream);
    ImageView imageVievBottom = new ImageView(bottomImage);
    Image rightimage = new Image(righImageStream);
    Image leftImage = new Image(lefImageStream);
    ImageView imageLeft = new ImageView(leftImage);
    ImageView imageRight = new ImageView(rightimage);
    try {
      botImageStream.close();
      righImageStream.close();
      lefImageStream.close();
    } catch(Exception ex) {ex.printStackTrace();}
    centerStack = new StackPane();
    centerStack.setStyle( "-fx-min-height:82;" + "-fx-max-height:82");
    Rectangle stackPaneClip = new Rectangle (470,84);
    centerStack.setClip(stackPaneClip);
    label = new Label("Unknown hero");
    centerStack.getChildren().add(label);
    label.getStylesheets().add(getClass().getResource("/css/heroNameLabel.css").toExternalForm());
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
    randomButton.setStyle("-fx-min-height:80;"  + "-fx-min-width:350");
    InputStream randomImageStream = getClass().getResourceAsStream("/AppImages/grd.png");
    Image randomImage = new Image(randomImageStream);
    try {
      randomImageStream.close();
    } catch(Exception ex) {ex.printStackTrace();}
    BackgroundImage randomBI= new BackgroundImage(randomImage ,BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    randomButton.setBackground(new Background(randomBI));
    
    
    randomBPane.getChildren().add(randomButton);
    randomBPane.setMargin(randomButton, new Insets(20,0,0,0));
    centerVbox.getChildren().addAll(animPane, randomisedHeroPanel, randomBPane);
    randomButton.setOnAction(new EventHandler<ActionEvent>() { 

      @Override
      public void handle (ActionEvent ae) {
        ArrayList<ImageView>  allWorkingIM = allHeroIcons();
    	int numberOfRandomHero = (int)(Math.random() * (allWorkingIM.size()));
		heroSpin.getChildren().clear();
        for (int countOfAddedImages = 0; countOfAddedImages<242;) {
            ArrayList<ImageView>  tempArr = allHeroIcons();
        	for(int tmp = 0; tmp<tempArr.size();tmp++) {
        		if(countOfAddedImages!=118) {
            		heroSpin.getChildren().add(tempArr.get(tmp));
            		countOfAddedImages++;
        		} else {
                    ArrayList<ImageView>  newArr = allHeroIcons();
            		heroSpin.getChildren().add(newArr.get(tmp));
            		tmp=tempArr.size();
            		countOfAddedImages++;
        		}
        		if(countOfAddedImages>=242) {
        			break;
        		}
        	}
        }
        	Timeline mainTimeline = new Timeline();
        	for(int c = 0, translateNum = -150, durationNum = 50; c < heroSpin.getChildren().size(); c++, translateNum = translateNum-150, durationNum = durationNum + 50) {
        		if(c<30) {
                		KeyFrame key = new KeyFrame(new Duration(durationNum),event-> {mainTimeline.setRate(mainTimeline.getRate()+0.2);},  new KeyValue(heroSpin.getChildren().get(c).translateXProperty(), translateNum));	
                		mainTimeline.getKeyFrames().add(key);
        		} else {
        			if(c>118+numberOfRandomHero-30) {
        				if (c==118+numberOfRandomHero-2) {
                    		KeyFrame key = new KeyFrame(new Duration(durationNum),event-> {mainTimeline.stop();},  new KeyValue(heroSpin.getChildren().get(c).translateXProperty(), translateNum));	
                    		mainTimeline.getKeyFrames().add(key);
        				} else {
                	    	KeyFrame key = new KeyFrame(new Duration(durationNum),event-> {mainTimeline.setRate(mainTimeline.getRate()-0.2);},  new KeyValue(heroSpin.getChildren().get(c).translateXProperty(), translateNum));	
                	    	mainTimeline.getKeyFrames().add(key);
        				}
        			} else {
            		KeyFrame key = new KeyFrame(new Duration(durationNum), new KeyValue(heroSpin.getChildren().get(c).translateXProperty(), translateNum));	
            		mainTimeline.getKeyFrames().add(key);
        			}
        		}
        	}
        	mainTimeline.setCycleCount(1);
        	mainTimeline.setRate(0.4);
        	mainTimeline.play();
        	
        	
       	 //Нижняя картинка
         InputStream heroIconeStream = getClass().getResourceAsStream("/AppImages/HeroStack.png");
      	 Image heroIconImage = new Image(heroIconeStream);
         try {
           heroIconeStream.close();
         } catch(Exception ex) {ex.printStackTrace();}
       	 ImageView heroIconImageView = new ImageView(heroIconImage);
       	 centerStack.getChildren().add(heroIconImageView);
       	 TranslateTransition menuTranslation = new TranslateTransition(Duration.millis(1500), heroIconImageView);
       	 menuTranslation.setFromY(103);
       	 menuTranslation.setToY(-1);
         menuTranslation.setCycleCount(1);
         TranslateTransition menuBackTraslation = new TranslateTransition(Duration.millis(1500), heroIconImageView);
       	 menuBackTraslation.setFromY(-1);
       	 menuBackTraslation.setToY(103);
         menuTranslation.setCycleCount(1);
         menuBackTraslation.setOnFinished(new EventHandler<ActionEvent>(){
          public void handle(ActionEvent ae)  {
            centerStack.getChildren().remove(heroIconImageView);       
            }}); 
         SequentialTransition seqTrans = new SequentialTransition(menuTranslation, new PauseTransition(Duration.millis(100)), menuBackTraslation);
       	 seqTrans.play();
       	 
          KeyFrame labelKey = new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
   		     @Override
   			 public void handle(ActionEvent ae) {
   		      ArrayList<String> chosenNames = new ArrayList<String>();
   			  for (int x = 2; x<heroObs.size(); x++) {
   				  if(heroObs.get(x).isSelected()) {
   					  chosenNames.add(heroObs.get(x).getText());
   				  }
   			  }
   				label.setText(chosenNames.get(numberOfRandomHero));
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

    Label colorPane = new Label();
    colorPane.setStyle("-fx-background-color: royalblue;");
    InputStream colorImageStream = getClass().getResourceAsStream("/AppImages/chooseENG.png");
    Image colorImage = new Image(colorImageStream);
    try {
      colorImageStream.close();
    } catch(Exception ex) {ex.printStackTrace();}
    ImageView  colorImageView = new ImageView(colorImage);
    colorPane.setGraphic(colorImageView);

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
	  for (int x = 2; x<heroObs.size(); x++) {
		  if(heroObs.get(x).isSelected()) {
			InputStream imageStream = getClass().getResourceAsStream("/heroImages/" + heroIconArray.get(x-2));
			ImageView IV = new ImageView( new Image(imageStream, 150,84,false,true));
                        try {
                          imageStream.close();
    			} catch(Exception ex) {ex.printStackTrace();}
			allView.add(IV);
		  }
	  }
      return allView;
  }
  
  static ImageView[] baseHeroIcons(ObservableList<CheckBox> argCheckList) {
	    ImageView[] result =  null;

	    ArrayList<String> namesForView = new ArrayList<String>();
	    for(int c = 2; c<argCheckList.size(); c++) {
	    	if(argCheckList.get(c).isSelected()) {
	    		namesForView.add(heroIconArray.get(c-2));
	    	}
	    }
		if(namesForView.size() < 5) {
			int x = 0;
			result =  new ImageView[namesForView.size()];
			for(String tempName : namesForView) {
			    InputStream noNameStream = RandomMode.class.getClassLoader().getResourceAsStream("heroImages/" + tempName);
			    ImageView noName  = new ImageView( new Image(noNameStream, 150,84,false,true));
 			    try {
			      noNameStream.close();
  			    } catch(Exception ex) {ex.printStackTrace();}
			    result[x] = noName;
			    x++;
			}
		} else {
			InputStream imageStream0 = RandomMode.class.getClassLoader().getResourceAsStream("heroImages/" + namesForView.get(0));
			InputStream imageStream1 = RandomMode.class.getClassLoader().getResourceAsStream("heroImages/" + namesForView.get(1));
			InputStream imageStream2 = RandomMode.class.getClassLoader().getResourceAsStream("heroImages/" + namesForView.get(2));
			InputStream imageStream3 = RandomMode.class.getClassLoader().getResourceAsStream("heroImages/" + namesForView.get(3));
			InputStream imageStream4 = RandomMode.class.getClassLoader().getResourceAsStream("heroImages/" + namesForView.get(4));
			
			ImageView hero0 = new ImageView( new Image(imageStream0, 150,84,false,true));
			ImageView hero1 = new ImageView(new Image(imageStream1,150,84,false,true));
			ImageView hero2 = new ImageView(new Image(imageStream2,150,84,false,true));
			ImageView hero3 = new ImageView(new Image(imageStream3,150,84,false,true));
			ImageView hero4 = new ImageView(new Image(imageStream4,150,84,false,true));
			try {
                          imageStream0.close();
                          imageStream1.close();
                          imageStream2.close();
                          imageStream3.close();
                          imageStream4.close();
                        } catch(Exception ex) {ex.printStackTrace();}
			result =  new ImageView[5];
			result[0] = hero0; result[1] = hero1; result[2] = hero2; result[3] = hero3; result[4] = hero4;
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