
import java.io.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test; // fixes some compile problems with annotations

public class RequirementBasedTests {
  
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("PersonalSCTests");
  } 
  
  public Recipe recipe;

  public User user;
  public RecipeInformation ri;
  public String recipeInformationFile = "recipe_information.txt";
  File recipeFile = new File (recipeInformationFile);
  
  @BeforeClass
  public void setUp() {
 	  ri = new RecipeInformation();
	  user = new User();
  }
  
  // Test for requirement: User Input of ingredients:
  
  @test
  public void addIngredientTest() {
	  
	  user.currentIngredient.clear();
	  assertTrue(user.currentIngredient.size() == 0);
	  
	  user.addIngredient("Chicken");
	  assertTrue(user.currentIngredient.size() == 1 && user.currentIngredient.contains("Chicken"));
	  
	  user.addIngredient("Beef");
	  user.addIngredient("Rice");
	  assertTrue(user.currentIngredient.size() == 3 && 
			  user.currentIngredient.contains("Chicken") && 
			  user.currentIngredient.contains("Beef") && 
			  user.currentIngredient.contains("Rice"));
	  
	  user.currentIngredient.clear();
	  assertTrue(user.currentIngredient.size() == 0);
	  
  }
    
  // Test for requirement: Search engine that will generate plausible recipes
  @test
  public void searchTest() {
  
	  // Correct answer
	  List<String> t;
      List<String> attribute = new ArrayList<String>();
      attribute.add("id");
      attribute.add("name");
      attribute.add("#lack");
      Table correct = new Table(attribute);
      
	  user.currentIngredient.clear();
	  user.addIngredient("eggs");
	  
	  ri.search(us.currentIngredient);
	  
	  
	  t.add("1");
	  t.add("Oven Scrambled Eggs");
	  t.add("3");
	  correct.insert(t);
	  t.add("2");
	  t.add("Rice Pilaf");
	  t.add("10");
	  correct.insert(t);
	  t.add("3");
	  t.add("Oreo Milkshake");
	  t.add("4");
	  correct.insert(t);
	  t.add("4");
	  t.add("Pancakes");
	  t.add("4");
	  correct.insert(t);
	  t.add("5");
	  t.add("Pasta Salad");
	  t.add("17");
	  correct.insert(t);
	  t.add("6");
	  t.add("Cream of Mushroom Soup");
	  t.add("11");
	  correct.insert(t);
	  
	  assertTrue(tableEqual(correct, ri.searched_table));
	  
  }
  
  private boolean tableEqual(Table t1, Table t2) {
	  
	  if (t1 == null) {
		  return false;
	  }
	  
	  if (t2 == null) {
		  return false;
	  }
	  
	  int columnsize = t1.getAttributeSize();
	  
	  if (columnsize != t2.getAttributeSize()) {
		  return false;
	  }
	  
	  int rowsize = t1.getTableSize();
	  
	  if (rowsize != t2.getTableSize()) {
		  return false;
	  }
	  
	  for (int r = 0; r < rowsize; r++) {
		  for (int c = 0; c < columnsize; c++) {
			  if (!t1.getCell(r, c).equals(t2.getCell(r, c))) {
				  return false;
			  }
		  }
		  
	  }
	  
	  return true;
  }
  
  // Test for requirement: Input/add recipes
  @test
  public void addRecipeTest() {
	  
	  recipe = new Recipe("7", "Fride Fish", "1");
	  recipe.setIngerdient("Fish");
	  recipe.setImageDir("");
	  
	  ri.addRecipe(recipe);
	  
	  assertTrue(ri.getIngredient("7").equals("Fish") &&
			  ri.recipe_ingredient.getCell(7, 2).equals("Fride Fish"));
  }
  
  
}