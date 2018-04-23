import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.ListProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.util.List;
import java.net.URL;
import java.net.MalformedURLException;
import javafx.scene.control.MenuItem;
import javafx.application.Platform;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.input.MouseEvent;
 
public class HelloWorld extends Application {

//Stores the path of the image chosen when adding a new recipe
   String imagePath = "";
   String viewingID = "";
   Scene previousSceneFromFavoritesPage = null;
   Scene previousSceneFromViewPage = null;

   public static void main(String[] args) {
      launch(args);
   }
   
   @Override
   public void start(Stage stage) throws Exception, MalformedURLException {
      
      //Load FXML
      Parent homePage = FXMLLoader.load(getClass().getResource("gui.fxml"));
      Parent addPage = FXMLLoader.load(getClass().getResource("add_screen.fxml"));
      Parent searchPage = FXMLLoader.load(getClass().getResource("user.fxml"));
      Parent viewPage = FXMLLoader.load(getClass().getResource("view_screen.fxml"));
      Parent favoritesPage = FXMLLoader.load(getClass().getResource("favorites_screen.fxml"));
      
      Scene homeScene = new Scene(homePage, 650, 400);
      Scene addScene = new Scene(addPage, 650, 400);
      Scene searchScene = new Scene(searchPage, 650, 400);
      Scene viewScene = new Scene(viewPage, 650, 400);
      Scene favoritesScene = new Scene(favoritesPage, 650, 400);
      
      
         //Menu items
      /*Node searchPageQuit = searchPage.lookup("#searchPage_quit");
      Node addPageQuit = addPage.lookup("#addPage_quit");
      Node searchPageAbout = searchPage.lookup("#searchPage_about");
      Node addPageAbout = addPage.lookup("#addPage_about");
      */
      MenuBar searchPageMenuBar = new MenuBar();
      Menu searchPageFileMenu = new Menu("File");
      MenuItem searchPageQuitMenuItem = new MenuItem("Quit");
      MenuItem searchPageViewFavoritesMenuItem = new MenuItem("View Favorites");
      searchPageFileMenu.getItems().add(searchPageQuitMenuItem);
      searchPageFileMenu.getItems().add(searchPageViewFavoritesMenuItem);
      Menu searchPageAboutMenu = new Menu("About");
      MenuItem searchPageAboutMenuItem = new MenuItem("About RecipeGenerator");
      searchPageAboutMenu.getItems().add(searchPageAboutMenuItem);
      searchPageMenuBar.getMenus().add(searchPageFileMenu);
      searchPageMenuBar.getMenus().add(searchPageAboutMenu);
      
      MenuBar addPageMenuBar = new MenuBar();
      Menu addPageFileMenu = new Menu("File");
      MenuItem addPageQuitMenuItem = new MenuItem("Quit");
      MenuItem addPageViewFavoritesMenuItem = new MenuItem("View Favorites");
      addPageFileMenu.getItems().add(addPageQuitMenuItem);
      addPageFileMenu.getItems().add(addPageViewFavoritesMenuItem);
      Menu addPageAboutMenu = new Menu("About");
      MenuItem addPageAboutMenuItem = new MenuItem("About RecipeGenerator");
      addPageAboutMenu.getItems().add(addPageAboutMenuItem);
      addPageMenuBar.getMenus().add(addPageFileMenu);
      addPageMenuBar.getMenus().add(addPageAboutMenu);
      
      MenuBar viewPageMenuBar = new MenuBar();
      Menu viewPageFileMenu = new Menu("File");
      MenuItem viewPageQuitMenuItem = new MenuItem("Quit");
      viewPageFileMenu.getItems().add(viewPageQuitMenuItem);
      Menu viewPageAboutMenu = new Menu("About");
      MenuItem viewPageAboutMenuItem = new MenuItem("About RecipeGenerator");
      viewPageAboutMenu.getItems().add(viewPageAboutMenuItem);
      viewPageMenuBar.getMenus().add(viewPageFileMenu);
      viewPageMenuBar.getMenus().add(viewPageAboutMenu);
      
      MenuBar favoritesPageMenuBar = new MenuBar();
      Menu favoritesPageFileMenu = new Menu("File");
      MenuItem favoritesPageQuitMenuItem = new MenuItem("Quit");
      favoritesPageFileMenu.getItems().add(favoritesPageQuitMenuItem);
      Menu favoritesPageAboutMenu = new Menu("About");
      MenuItem favoritesPageAboutMenuItem = new MenuItem("About RecipeGenerator");
      favoritesPageAboutMenu.getItems().add(favoritesPageAboutMenuItem);
      favoritesPageMenuBar.getMenus().add(favoritesPageFileMenu);
      favoritesPageMenuBar.getMenus().add(favoritesPageAboutMenu);
      
      //searchPageVBox.getChildren().add(menuBar);
     // addPageVBox.getChildren().add(menuBar);
      ((VBox) addScene.getRoot()).getChildren().add(0,addPageMenuBar);
      ((VBox) searchScene.getRoot()).getChildren().add(0,searchPageMenuBar);
      ((VBox) viewScene.getRoot()).getChildren().add(0,viewPageMenuBar);
      ((VBox) favoritesScene.getRoot()).getChildren().add(0,favoritesPageMenuBar);
      
      
      
      stage.getIcons().add(new Image("plate.png"));
      stage.setTitle("Recipe Generator");
      stage.setScene(searchScene);
      stage.show();
      
      
     
      //Load database of recipes and foods
      //
      //---
      
      //Load GUI elements
      Button searchButton = (Button)homePage.lookup("#search_button");
      GridPane searchResults = (GridPane)homePage.lookup("#search_results");
      ScrollPane scrollPane = (ScrollPane)homePage.lookup("#scroll_pane");
      scrollPane.setContent(searchResults);
      TextField searchBar = (TextField)homePage.lookup("#search_bar");
      
      Button addPageCancelButton = (Button)addPage.lookup("#addPage_cancel_button");
      Button addPageAddButton = (Button)addPage.lookup("#addPage_add_button");
      TextField addPageNameTextField = (TextField)addPage.lookup("#addPage_name_textField");
      TextArea addPageTextArea = (TextArea)addPage.lookup("#addPage_textArea");
      ListView addPageListView = (ListView)addPage.lookup("#addPage_listView");
      ImageView addPageImageView = (ImageView)addPage.lookup("#addPage_imageView");
      Button addPageUploadButton = (Button)addPage.lookup("#addPage_upload_button");
      TextField addPageIngredientTextField = (TextField)addPage.lookup("#addPage_ingredient_textField");
      Button addPageAddIngredientButton = (Button)addPage.lookup("#addPage_addIngredient_button");
      
      ListView searchPageListView = (ListView)searchPage.lookup("#searchPage_listView");
      Button searchPageAddButton = (Button)searchPage.lookup("#searchPage_add_button");
      Button searchPageSearchButton = (Button)searchPage.lookup("#searchPage_search_button");
      TextField searchPageTextField = (TextField)searchPage.lookup("#searchPage_textField");
      TableView searchPageTableView = (TableView)searchPage.lookup("#searchPage_tableView");
      Button searchPageAddRecipeButton = (Button)searchPage.lookup("#searchPage_addRecipe_button");
      Button searchPageClearIngredientsButton = (Button)searchPage.lookup("#searchPage_clearIngredients_button");
      Label searchPageResultLabel = (Label)searchPage.lookup("#searchPage_result_label");
      
      ListView viewPageIngredients = (ListView)viewPage.lookup("#viewPage_ingredients");
      TextArea viewPageDescription = (TextArea)viewPage.lookup("#viewPage_description");
      Button viewPageBackButton = (Button)viewPage.lookup("#viewPage_back_button");
      ImageView viewPageFavorite = (ImageView)viewPage.lookup("#viewPage_favorite");
      Label viewPageRecipeLabel = (Label)viewPage.lookup("#viewPage_recipe_label");
      
      TableView favoritesPageTableView = (TableView)favoritesPage.lookup("#favoritesPage_tableView");
      Button favoritesPageBackButton = (Button)favoritesPage.lookup("#favoritesPage_back_button");
      
      
   
      
      User user = new User( );
      RecipeInformation recipeInfo = new RecipeInformation();
   
      
      searchButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               //Display search results here in GridPane
               searchButton.setText("Searched");
               stage.setScene(addScene);
               //Clear current results before retrieving new ones
               /*searchResults.getChildren().clear();
               searchResults.add(new Label(searchBar.getText() + " image"), 0, 0);
               searchResults.add(new Label(searchBar.getText() + " description"), 1, 0);
               searchResults.add(new ImageView(new Image("Rice2.jpg", 32, 32, false, true)), 0, 1);
               searchResults.add(new Label("Desc 2"), 1, 1);
               searchResults.add(new Label("Image 3"), 0, 2);
               searchResults.add(new Label("Desc 3"), 1, 2);
               searchResults.add(new Label("Image 4"), 0, 3);
               searchResults.add(new Label("Desc 4"), 1, 3);
               searchResults.add(new Label("Image 5"), 0, 4);
               searchResults.add(new Label("Desc 5"), 1, 4);
               searchResults.add(new Label("Image 6"), 0, 5);
               searchResults.add(new Label("Desc 6"), 1, 5);
               searchResults.add(new Label("Image 7"), 0, 6);
               searchResults.add(new Label("Desc 7"), 1, 6);
               searchResults.add(new Label("Image 8"), 0, 7);
               searchResults.add(new Label("Desc 8"), 1, 7);
               searchResults.add(new Label("Image 9"), 0, 8);
               searchResults.add(new Label("Desc 9"), 1, 8);
               searchResults.add(new Label("Image 10"), 0, 9);
               searchResults.add(new Label("Desc 10"), 1, 9);
               searchResults.add(new Label("Image 11"), 0, 10);
               searchResults.add(new Label("Desc 11"), 1, 10);
               searchResults.add(new Label("Image 12"), 0, 11);
               searchResults.add(new Label("Desc 12"), 1, 11);
               searchResults.add(new Label("Image 13"), 0, 12);
               searchResults.add(new Label("Desc 13"), 1, 12);*/
            } 
         });
         
      addPageCancelButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               stage.setScene(searchScene);
            } 
         });  
         
      searchPageAddButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               user.addIngredient(searchPageTextField.getText());
               searchPageListView.getItems().add(searchPageTextField.getText());
               searchPageTextField.clear( );
            } 
         });    
         
      searchPageSearchButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               recipeInfo.search(user.currentIngredient);
               ObservableList<Recipe> recipes = FXCollections.observableArrayList();
               for (int r = 0; r < recipeInfo.searched_table.getTableSize(); r++) {
                  //Add a recipe to the list only if you have at least 1 common ingredient
                  //System.out.println("Total # of ingredients: "+recipeInfo.getIngredientNum(recipeInfo.searched_table.getCell(r, 0)));
                  //System.out.println("# of ingredients you lack: "+recipeInfo.searched_table.getCell(r, 2));
                  if(recipeInfo.getIngredientNum(recipeInfo.searched_table.getCell(r, 0)) > Integer.parseInt(recipeInfo.searched_table.getCell(r, 2)))
                     recipes.add(new Recipe(recipeInfo.searched_table.getCell(r, 0), recipeInfo.searched_table.getCell(r, 1), recipeInfo.searched_table.getCell(r, 2), 
                        100 - ((int)(Integer.parseInt(recipeInfo.searched_table.getCell(r, 2)) / 1.0 / recipeInfo.getIngredientNum(recipeInfo.searched_table.getCell(r, 0)) * 100))));
               }
               searchPageTableView.setItems(recipes);
               
               TableColumn<Recipe,String> idCol = new TableColumn<Recipe,String>("ID");
               idCol.setCellValueFactory(new PropertyValueFactory("id"));
               TableColumn<Recipe,String> nameCol = new TableColumn<Recipe,String>("Name");
               nameCol.setCellValueFactory(new PropertyValueFactory("name"));
               TableColumn<Recipe,Integer> lackCol = new TableColumn<Recipe,Integer>("% Match");
               lackCol.setCellValueFactory(new PropertyValueFactory("matchPercentage"));   
                           
               searchPageTableView.getColumns().setAll(idCol, nameCol, lackCol);
               
               //Clear the text in the textfield when you search
               searchPageTextField.clear( );
               
               //Show user how many search results were returned
               searchPageResultLabel.setText("Search returned " +searchPageTableView.getItems().size()+ " out of " +recipeInfo.recipe_dir.getTableSize()+ " results.");
               
               //Offer to add a new recipe if no matching recipes are found
               if(recipes.size() == 0)
               {
                  Alert alert = new Alert(AlertType.CONFIRMATION);
                  alert.setTitle("No Recipes Found");
                  alert.setHeaderText("No Matching Recipes Were Found");
                  alert.setContentText("Would you like to add one?");
               
                  Optional<ButtonType> result = alert.showAndWait();
                  if (result.get() == ButtonType.OK){
                     stage.setScene(addScene);
                  } 
                  else {
                  
                  }
               }
            } 
         });  
         
      searchPageAddRecipeButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               stage.setScene(addScene);
            } 
         });
         
      addPageAddButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               
               String name = addPageNameTextField.getText( );
               if(name == null)
                  name = "";
               String desc = addPageTextArea.getText( );
               if(desc == null)
                  desc = "";
               String imgDir  = imagePath;
               if(imgDir == null)
                  desc = "";
               List<String> ingredients = FXCollections.observableArrayList();
               ingredients.addAll(addPageListView.getItems());
               Recipe recipe = new Recipe(name, desc, imgDir, ingredients);
               recipe.setID(recipeInfo.recipe_dir.getTableSize()+1+"");
               addPageImageView.setImage(new Image("plate.png"));
               
               
               //Clear all textfield before switching out
               addPageNameTextField.clear( );
               addPageTextArea.clear( );
               addPageListView.getItems( ).clear( );
               addPageIngredientTextField.clear( );
               
               //save recipes changes
               recipeInfo.addRecipe(recipe);
               recipeInfo.save( );
               recipeInfo.loadFile( );
               
               stage.setScene(searchScene);
            } 
         });  
         
      addPageUploadButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               FileChooser fileChooser = new FileChooser();
               fileChooser.setTitle("Select an Image");
               fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
               File selectedFile = fileChooser.showOpenDialog(stage);
               //URL imageURL = selectedFile.toURI().toURL();
               if (selectedFile != null) {
                  System.out.println(selectedFile.getPath());
                  addPageImageView.setImage(new Image(selectedFile.getPath()));
                  imagePath = selectedFile.getPath();
               }
            } 
         }); 
   
      addPageAddIngredientButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               user.addIngredient(addPageIngredientTextField.getText());
               addPageListView.getItems().add(addPageIngredientTextField.getText());
               
               //NOTE, after adding a new recipe, the search feature no longer works
            } 
         });
         
      searchPageClearIngredientsButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               searchPageListView.getItems().clear();
               user.currentIngredient.clear( );
            } 
         });
         
         //Menu item events
      searchPageQuitMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               Platform.exit();
            } 
         });
      searchPageAboutMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               Alert alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("About RecipeGenerator");
               alert.setHeaderText("About RecipeGenerator");
               alert.setContentText("RecipeGenerator is a program developed by Abdullah Samad, Huajun Wang, Anish Kandumalla, and Jim Cheung for a CS321 project.");
               alert.showAndWait();
            } 
         });
      searchPageViewFavoritesMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               ObservableList<Recipe> recipes = FXCollections.observableArrayList();
               for (int r = 0; r < user.getFavorite().size(); r++) {
                  recipes.add(recipeInfo.getRecipe(user.getFavorite().get(r)));
               }
               favoritesPageTableView.setItems(recipes);
               
               TableColumn<Recipe,String> idCol = new TableColumn<Recipe,String>("ID");
               idCol.setCellValueFactory(new PropertyValueFactory("id"));
               TableColumn<Recipe,String> nameCol = new TableColumn<Recipe,String>("Name");
               nameCol.setCellValueFactory(new PropertyValueFactory("name"));
               TableColumn<Recipe,Integer> lackCol = new TableColumn<Recipe,Integer>("% Match");
               lackCol.setCellValueFactory(new PropertyValueFactory("matchPercentage"));   
                           
               favoritesPageTableView.getColumns().setAll(idCol, nameCol, lackCol);
               
               previousSceneFromFavoritesPage = searchScene;
            
               stage.setScene(favoritesScene);
            } 
         });
      addPageQuitMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               Platform.exit();
            } 
         });
      addPageAboutMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               Alert alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("About RecipeGenerator");
               alert.setHeaderText("About RecipeGenerator");
               alert.setContentText("RecipeGenerator is a program developed by Abdullah Samad, Huajun Wang, Anish Kandumalla, and Jim Cheung for a CS321 project.");
               alert.showAndWait();
            } 
         });
      addPageViewFavoritesMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               ObservableList<Recipe> recipes = FXCollections.observableArrayList();
               for (int r = 0; r < user.getFavorite().size(); r++) {
                  recipes.add(recipeInfo.getRecipe(user.getFavorite().get(r)));
               }
               favoritesPageTableView.setItems(recipes);
               
               TableColumn<Recipe,String> idCol = new TableColumn<Recipe,String>("ID");
               idCol.setCellValueFactory(new PropertyValueFactory("id"));
               TableColumn<Recipe,String> nameCol = new TableColumn<Recipe,String>("Name");
               nameCol.setCellValueFactory(new PropertyValueFactory("name"));
               TableColumn<Recipe,Integer> lackCol = new TableColumn<Recipe,Integer>("% Match");
               lackCol.setCellValueFactory(new PropertyValueFactory("matchPercentage"));   
                           
               favoritesPageTableView.getColumns().setAll(idCol, nameCol, lackCol);
               
               previousSceneFromFavoritesPage = addScene;
            
               stage.setScene(favoritesScene);
            
            } 
         });
      viewPageQuitMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               Platform.exit();
            } 
         });
      viewPageAboutMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               Alert alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("About RecipeGenerator");
               alert.setHeaderText("About RecipeGenerator");
               alert.setContentText("RecipeGenerator is a program developed by Abdullah Samad, Huajun Wang, Anish Kandumalla, and Jim Cheung for a CS321 project.");
               alert.showAndWait();
            } 
         });
      /*viewPageViewFavoritesMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               ObservableList<Recipe> recipes = FXCollections.observableArrayList();
               for (int r = 0; r < user.getFavorite().size(); r++) {
                  recipes.add(recipeInfo.getRecipe(user.getFavorite().get(r)));
               }
               favoritesPageTableView.setItems(recipes);
               
               TableColumn<Recipe,String> idCol = new TableColumn<Recipe,String>("ID");
               idCol.setCellValueFactory(new PropertyValueFactory("id"));
               TableColumn<Recipe,String> nameCol = new TableColumn<Recipe,String>("Name");
               nameCol.setCellValueFactory(new PropertyValueFactory("name"));
               TableColumn<Recipe,Integer> lackCol = new TableColumn<Recipe,Integer>("% Match");
               lackCol.setCellValueFactory(new PropertyValueFactory("matchPercentage"));   
                           
               favoritesPageTableView.getColumns().setAll(idCol, nameCol, lackCol);
            
               previousSceneFromFavoritesPage = viewScene;
            
               stage.setScene(favoritesScene);
            
            } 
         });*/
      favoritesPageQuitMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               Platform.exit();
            } 
         });
      favoritesPageAboutMenuItem.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               Alert alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("About RecipeGenerator");
               alert.setHeaderText("About RecipeGenerator");
               alert.setContentText("RecipeGenerator is a program developed by Abdullah Samad, Huajun Wang, Anish Kandumalla, and Jim Cheung for a CS321 project.");
               alert.showAndWait();
            } 
         });
   
         
         //show viewRecipe page when you click on a recipe in search results
      searchPageTableView.setOnMousePressed(
         new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
               if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                  Recipe tempSelectedRecipe = (Recipe)searchPageTableView.getSelectionModel().getSelectedItem();
                  Recipe selectedRecipe = recipeInfo.getRecipe(tempSelectedRecipe.getId());
                  
                  //prep the view page
                  viewPageIngredients.getItems().clear();
                  viewPageRecipeLabel.setText(selectedRecipe.getName());
                  for(String s:selectedRecipe.getIngredients())
                  {
                     viewPageIngredients.getItems().add(s);
                  }
                  viewPageDescription.setText(selectedRecipe.getDescription());
                  if(user.isFavorite(selectedRecipe.getId()))
                  {
                     viewPageFavorite.setImage(new Image("gold_star.png", 54, 51, false, true));
                  }
                  else
                  {
                     viewPageFavorite.setImage(new Image("star.png", 54, 51, false, true));
                  }
                  
                  viewingID = selectedRecipe.getId();
                  
                  previousSceneFromViewPage = searchScene;
                   
                  stage.setScene(viewScene);                 
               }
            }
         });
         
      viewPageBackButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               stage.setScene(previousSceneFromViewPage);
            } 
         });
      
      viewPageFavorite.setOnMousePressed(
         new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
               if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                  if(user.isFavorite(viewingID))
                  {
                     user.removeFavorite(viewingID);
                     viewPageFavorite.setImage(new Image("star.png", 54, 51, false, true));
                  }
                  else
                  {
                     user.addFavorite(viewingID);
                     viewPageFavorite.setImage(new Image("gold_star.png", 54, 51, false, true));
                  }    
               }
            }
         });
         
      favoritesPageTableView.setOnMousePressed(
         new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
               if (e.isPrimaryButtonDown() && e.getClickCount() == 2) {
                  Recipe tempSelectedRecipe = (Recipe)searchPageTableView.getSelectionModel().getSelectedItem();
                  Recipe selectedRecipe = recipeInfo.getRecipe(tempSelectedRecipe.getId());
                  
                  //prep the view page
                  viewPageIngredients.getItems().clear();
                  viewPageRecipeLabel.setText(selectedRecipe.getName());
                  for(String s:selectedRecipe.getIngredients())
                  {
                     viewPageIngredients.getItems().add(s);
                  }
                  viewPageDescription.setText(selectedRecipe.getDescription());
                  if(user.isFavorite(selectedRecipe.getId()))
                  {
                     viewPageFavorite.setImage(new Image("gold_star.png", 54, 51, false, true));
                  }
                  else
                  {
                     viewPageFavorite.setImage(new Image("star.png", 54, 51, false, true));
                  }
                  
                  viewingID = selectedRecipe.getId();
                  
                  previousSceneFromViewPage = favoritesScene;
                   
                  stage.setScene(viewScene);                 
               }
            }
         });
      favoritesPageBackButton.setOnAction(
         new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
               stage.setScene(previousSceneFromFavoritesPage);
            } 
         });
   
   }
}

/*
TableView favoritesPageTableView = (TableView)favoritesPage.lookup("#favoritesPage_tableView");
      Button favoritesPageBackButton = (Button)favoritesPage.lookup("#favoritesPage_back_button");
      */