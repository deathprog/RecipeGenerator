import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Before;

public class RecipeTests {

   Recipe recipe;

   @Before
      public void setUp() {
      String name = "Food";
      String desc = "Description";
      String imgurl = "void";
      List<String> ingredients = new ArrayList<String>();
      ingredients.add("Banana");
      recipe.setID("1000");
      recipe.setLack("0");
      recipe.setMatchPercentage(100);
         
      recipe = new Recipe(name, desc, imgurl, ingredients);
   }
	   
   @Test
      public void testSetID(){
      recipe.setID("900");
      assertEquals(recipe.getId(),"900");
   }
      
   @Test
      public void testGetID(){
      assertEquals(recipe.getId(),"1000");
   }
      
   @Test
      public void testSetName(){
      recipe.setName("Food1");
      assertEquals(recipe.getName(),"Food1");
   }
      
   @Test
      public void testGetName(){
      assertEquals(recipe.getName(),"Food");
   }
      
   @Test
      public void testSetLack(){
      recipe.setLack("1");
      assertEquals(recipe.getLack(),"1");
   }
      
   @Test
      public void testGetLack(){
      assertEquals(recipe.getLack(),"0");
   }
	   
   @Test
      public void testSetMatchPercentage(){
      recipe.setMatchPercentage(new Integer(90));
      assertEquals(recipe.getMatchPercentageProperty(),new Integer(90));
   }
      
   @Test
      public void testGetMatchPercentage(){
      assertEquals(recipe.getMatchPercentageProperty(), new Integer(100));
   }
      
   @Test
      public void testSetDescription(){
      recipe.setDescription("Hi");
      assertEquals(recipe.getDescription(),"Hi");
   }
      
   @Test
      public void testGetDescription(){
      assertEquals(recipe.getDescription(),"Description");
   }
      
   @Test
      public void testGetIngredients(){
      List<String> list = new ArrayList<String>();
      list.add("Banana");
      assertEquals(recipe.getIngredients(),list);
   }
	
}
