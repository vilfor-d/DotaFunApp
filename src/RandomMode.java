import javafx.scene.layout.*;
import javafx.scene.control.*;
//import java.util.Arrays;
import javafx.collections.*;
import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.effect.*;
import javafx.scene.input.*;

class RandomMode { 

    ListView<CheckBox> heroListView;

    Label label;
    Button saveButton;
    Button loadButton;

  String[] heroesNames = {"Abbadon","Alchemist","Ancient Apparition","Anti-Mage","Arc Warden","Axe","Bane","Batrider","Beastmaster","Bloodseeker","Bounty Hunter","Brewmaster","Bristleback","Broodmother","Centaur Warrunner","Chaos Knight","Chen","Clinkz","Clockwerk","Crystal Maiden","Dark Seer","Dark Willow","Dazzle","Death Prophet","Disruptor","Doom","Dragon Knight","Drow Ranger","Earth Spirit","Earthshaker","Elder Titan","Ember Spirit","Enchantress","Enigma","Faceless Void","Grimstroke","Gyrocopter","Huskar","Invoker","Io","Jakiro","Juggernaut","Keeper of the Light","Kunkka","Legion Commander","Leshrac","Lich","Lifestealer","Lina","Lion","Lone Druid","Luna","Lycan","Magnus","Mars","Medusa","Meepo","Mirana","Monkey King","Morphling","Naga Siren","Nature's Prophet","Necrophos","Night Stalker","Nyx Assassin","Ogre Magi","Omniknight","Oracle","Outworld Devourer","Pangolier","Phantom Assassin","Phantom Lancer","Phoenix","Puck","Pudge","Pugna","Queen of Pain","Razor","Riki","Rubick","Sand King","Shadow Demon","Shadow Fiend","Shadow Shaman","Silencer","Skywrath Mage","Slardar","Slark","Snapfire","Sniper","Spectre","Spirit Breaker","Storm Spirit","Sven","Techies","Templar Assassin","Terrorblade","Tidehunter","Timbersaw","Tinker","Tiny","Treant Protector","Troll Warlord","Tusk","Underlord","Undying","Ursa","Vengeful Spirit","Venomancer","Viper","Visage","Void Spirit","Warlock","Weaver","Windranger","Winter Wyvern","Witch Doctor","Wraith King","Zeus"};

  

  public StackPane getRandomGUI() {

    StackPane randomGUI = new StackPane();
    randomGUI.setStyle(" -fx-background-color: lightseagreen");
    BorderPane borderPane = new BorderPane();
    randomGUI.getChildren().add(borderPane);

    ObservableList<CheckBox> heroObs = FXCollections.observableArrayList();
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
    	  
		@SuppressWarnings("unused")
		SaveManager saveDialog = new SaveManager("load", checkBool, heroObs );
      
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
    }}});

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
    }}});

    heroListView.getStylesheets().add(getClass().getResource("css/heroListStyle.css").toExternalForm());

    heroObs.add(allHeroesCheck);
    heroObs.add(clearHeroesCheck);

    for(String hero : heroesNames) {

      CheckBox heroCheck = new CheckBox(hero);
      heroCheck.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent me) {
      clearHeroesCheck.setSelected(false);
      }});
      heroCheck.setStyle("-fx-text-fill: black;" + "-fx-font-size: 13");
      heroObs.add(heroCheck);
    }

    label = new Label("Hero");
    Button lButton = new Button("Random");

    lButton.setOnAction(new EventHandler<ActionEvent>() { 

      @Override
      public void handle (ActionEvent ae) {

        label.setText(heroesNames[(int) (Math.random() * 120)]);
        System.out.println("new");

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
    borderPane.setCenter(label);
    borderPane.setLeft(lButton);
    borderPane.setTop(topAnchor);
   
    

    return randomGUI;
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