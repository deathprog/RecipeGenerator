
import java.util.*;
import java.io.*;

public class RecipeInformation {

	// For load
   public String recipeInformationFile = "recipe_information.txt";
   public Table recipe_ingredient;
   public Table recipe_dir;
   public List<String> all_ingredients;

	// For search
   public Table searched_table;

   public void search(List<String> all_ingredient) {
   
      List<String> attribute = new ArrayList<String>();
      attribute.add("id");
      attribute.add("name");
      attribute.add("#lack");
      this.searched_table = new Table(attribute);
   
      for (int i = 0; i < recipe_ingredient.getTableSize(); i++) {
         int n_lack = this.compare_material(all_ingredient, recipe_ingredient.getCell(i, 2));
         if (n_lack < 3) {
            List<String> tuple = new ArrayList<String>();
            tuple.add(recipe_ingredient.getCell(i, 0));
            tuple.add(recipe_ingredient.getCell(i, 1));
            tuple.add(Integer.toString(n_lack));
            searched_table.insert(tuple);
         }
      }
   
   }

   private int compare_material(List<String> user, String data) {
      Scanner sc = new Scanner(data);
      int n_lack = 0;
      while (sc.hasNext()) {
         String s = sc.next();
         boolean check = false;
         for (int i = 0; i < user.size(); i++) {
            if (user.get(i).equals(s)) {
               check = true;
               break;
            }
         }
         if (check == false)
            n_lack++;
      }
      sc.close();
      return n_lack;
   }

   public RecipeInformation() {
      this.loadFile();
   }

   public boolean loadFile() {
   
   	// File.
      File f = new File(recipeInformationFile);
   	
      Scanner sc = null;
      try {
         sc = new Scanner(f);
      } 
      catch (Exception e) {
         return false;
      }
   
   	// Initialize recipe_ingredient.
      List<String> attribute = new ArrayList<String>();
      attribute.add("id");
      attribute.add("name");
      attribute.add("material");
      this.recipe_ingredient = new Table(attribute);
   
   	// Initialize recipe_dir.
      attribute = new ArrayList<String>();
      attribute.add("id");
      attribute.add("dir");
      this.recipe_dir = new Table(attribute);
   
   	// Initialize all_ingredients.
      all_ingredients = new ArrayList<String>();
   
   	// Read
      String line = null;
      String ID = null;
      while (sc.hasNextLine()) {
         line = sc.nextLine();
         if (line.equals("info")) { // Get recipe_ingredient.
            List<String> tuple = new ArrayList<String>();
            ID = sc.nextLine();
            tuple.add(ID); // ID
            tuple.add(sc.nextLine()); // Name
            line = sc.nextLine(); // Ingredient
            tuple.add(line); 
            addIngredient(line);
            recipe_ingredient.insert(tuple);
         } 
         else { // Get recipe_dir
            List<String> tuple = new ArrayList<String>();
            tuple.add(ID);
            tuple.add(line);
            recipe_dir.insert(tuple);
         }
      }
      sc.close();
      return true;
   }

   private void addIngredient(String ings) {
      Scanner sc = new Scanner(ings);
      while (sc.hasNext()) {
         String ing = sc.next();
         if (!all_ingredients.contains(ing)) {
            all_ingredients.add(ing);
         }
      }
      sc.close();
   }

   public void printTables() {
      System.out.println("recipe_ingredient");
      recipe_ingredient.print();
      System.out.println();
      System.out.println("recipe_dir");
      recipe_dir.print();
      System.out.println();
      System.out.println("all_ingredients");
      System.out.println(all_ingredients.toString());
   }
   
   public String  getDirByID(String ID) {
      for (int i = 0; i < this.recipe_dir.getTableSize(); i++) {
         if (this.recipe_dir.getCell(i, 0).equals(ID)) {
            return this.recipe_dir.getCell(i, 1);
         }
      }
      System.out.println("ID not found");
      return null;
   }

   public String getIngredient(String ID) {
      for (int i = 0; i < this.recipe_ingredient.getTableSize(); i++) {
         if (this.recipe_ingredient.getCell(i, 0).equals(ID)) {
            return this.recipe_ingredient.getCell(i, 2);
         }
      }
      System.out.println("ID not found");
      return null;
   }

   public int getIngredientNum(String ID) {
      String ingredient = getIngredient(ID);
      if (ingredient == null) {
         return 0;
      }
   
      int n = 0;
      Scanner sc = new Scanner(ingredient);
      while (sc.hasNext()) {
         sc.next();
         n++;
      }
      sc.close();
      return n;
   }
   
   // Add a new recipe
   public void addRecipe(Recipe recipe){
   
        // Write file
      String fileName = recipe.getID()+recipe.getName();
      String fileDir = "dat/" + fileName + ".txt"; // dat/fileIDfileName.txt
   
      File f = new File(fileDir);
      while (f.exists()) {
         f.delete(); 
         System.out.println("Recipe have been modified");
      }
   
      try {
         f.createNewFile();
         FileOutputStream out = new FileOutputStream(f);
         String temp = "";
      
         temp += "ID\n";
         temp += recipe.getID() + "\n";
         temp += "Name\n";
         temp += recipe.getName() + "\n";
         temp += "Image directory\n";
         temp += recipe.getImageDir() + "\n";
         temp += "Description\n";
         temp += recipe.getDescription() + "\n";
      
         out.write(temp.getBytes());
         out.close();
      } 
      catch (Exception e) {
         System.out.println("Something wrong with RecipeInformation.addRecipe lol");
         return;
      }
   
   
      List<String> tuple = new ArrayList<String>();
      tuple.add(recipe.getID());
      tuple.add(recipe.getName());
      tuple.add(recipe.getIngredientString());
      this.recipe_ingredient.insert(tuple);
   
      tuple = new ArrayList<String>();
      tuple.add(recipe.getID());
      tuple.add(fileDir);
      this.recipe_dir.insert(tuple);
   }
   
   // Save two table
    public void save() {

        File f = new File(recipeInformationFile);
        if (f.exists()) {
            f.delete(); 
            System.out.println("Recipe information saved.");
        }

        String buffer = "";

        for (int i = 0; i < this.recipe_ingredient.getTableSize(); i++) {
            String ID = this.recipe_ingredient.getCell(i, 0);
            buffer += "info\n";
            buffer += ID + "\n";
            buffer += this.recipe_ingredient.getCell(i, 1) + "\n";
            buffer += this.recipe_ingredient.getCell(i, 2) + "\n";
            buffer += this.getDirByID(ID) + "\n";
        }

        try {
            f.createNewFile();
            FileOutputStream out = new FileOutputStream(f);
            out.write(buffer.getBytes());
            out.close();
        } catch (Exception e) {
            System.out.println("Something wrong with RecipeInformation.save()");
            return;
        }

    }
}
