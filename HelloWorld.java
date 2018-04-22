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
 
public class HelloWorld extends Application {

//Stores the path of the image chosen when adding a new recipe
   String imagePath = "";

   public static void main(String[] args) {
      launch(args);
   }
   
   @Override
   public void start(Stage stage) throws Exception, MalformedURLException {
      
      //Load FXML
      Parent homePage = FXMLLoader.load(getClass().getResource("gui.fxml"));
      Parent addPage = FXMLLoader.load(getClass().getResource("add_screen.fxml"));
      Parent searchPage = FXMLLoader.load(getClass().getResource("user.fxml"));
      
      Scene homeScene = new Scene(homePage, 650, 400);
      Scene addScene = new Scene(addPage, 650, 400);
      Scene searchScene = new Scene(searchPage, 650, 400);
   
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
                  if(recipeInfo.getIngredientNum(recipeInfo.searched_table.getCell(r, 0)) > Integer.parseInt(recipeInfo.searched_table.getCell(r, 2)))
                     recipes.add(new Recipe(recipeInfo.searched_table.getCell(r, 0), recipeInfo.searched_table.getCell(r, 1), recipeInfo.searched_table.getCell(r, 2)));
               }
               searchPageTableView.setItems(recipes);
               
               TableColumn<Recipe,String> idCol = new TableColumn<Recipe,String>("ID");
               idCol.setCellValueFactory(new PropertyValueFactory("id"));
               TableColumn<Recipe,String> nameCol = new TableColumn<Recipe,String>("Name");
               nameCol.setCellValueFactory(new PropertyValueFactory("name"));
               TableColumn<Recipe,String> lackCol = new TableColumn<Recipe,String>("#Lack");
               lackCol.setCellValueFactory(new PropertyValueFactory("lack"));   
                           
               searchPageTableView.getColumns().setAll(idCol, nameCol, lackCol);
               
               //Clear the text in the textfield when you search
               searchPageTextField.clear( );
               
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
               ingredients.addAll(searchPageListView.getItems());
               Recipe recipe = new Recipe(name, desc, imgDir, ingredients);
               recipe.setID(recipeInfo.recipe_dir.getTableSize()+"");
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

   }
}