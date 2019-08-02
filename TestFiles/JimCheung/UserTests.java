import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Before;

public class UserTests {
	   User user;
	
	   @Before
	   public void setUp() {
		   user = new User();
	   }
	   
	   @Test
	   public void testAddIngredient(){
	      assertEquals(user.addIngredient("pepper"), true);
	   }
	   
	   @Test
	   public void testAddFavorite(){
		   user.addFavorite("1");
	      assertEquals(user.getFavorite().size(), 1);
	   }
	   
	   @Test
	   public void testRemoveFavorite(){
         user.addFavorite("1");
         user.removeFavorite("1");
	      assertEquals(user.getFavorite().size(), 0);
	   }
	   
	   @Test
	   public void testGetFavorite(){
         List<String> list = new ArrayList<String>();
         list.add("1");
         user.getFavorite().add("1");
	      assertEquals(user.getFavorite(),list);
	   }
	   
	   @Test
	   public void testIsFavorite(){
		   user.addFavorite("1");
	      assertEquals(user.isFavorite("1"), true);
	   }
	   
}
