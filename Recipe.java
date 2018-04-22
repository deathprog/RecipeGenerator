import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.ArrayList;
import javafx.scene.image.ImageView;
import java.util.List;

public class Recipe{

   private List<String> ingredients = new ArrayList<String>();
   private StringProperty id;
   private StringProperty name;
   private StringProperty lack;
   private String description;
   private ImageView image;
   private String imageDir;
   
   public void setID(String value) { IDProperty().set(value); }
   public String getID() { 
      return IDProperty().get(); }
   public StringProperty IDProperty() { 
      if (id == null) id = new SimpleStringProperty(this, "id");
      return id; 
   }
 
   
   public void setName(String value) { nameProperty().set(value); }
   public String getName() { 
      return nameProperty().get(); }
   public StringProperty nameProperty() { 
      if (name == null) name = new SimpleStringProperty(this, "name");
      return name; 
   } 
     
   
   public void setLack(String value) { lackProperty().set(value); }
   public String getLack() { 
      return lackProperty().get(); }
   public StringProperty lackProperty() { 
      if (lack == null) lack = new SimpleStringProperty(this, "lack");
      return lack; 
   } 
   
   public String getIngredientString() {
      String result = "";
      for (int i = 0; i < ingredients.size(); i++) {
         result += ingredients.get(i) + " ";
      }
      return result;
   }
     
   public String getDescription() {
      return this.description;
   }

   public String getImageDir() {
      return imageDir;
   }
   
   public Recipe(String id, String name, String lack){
      setID(id);
      setName(name);
      setLack(lack);
   }
   
   public Recipe(String name, String desc, String imgurl, List<String> ingredients)
   {
      setName(name);
      this.description = desc;
      this.imageDir = imgurl;
      this.ingredients = ingredients;
   }
}