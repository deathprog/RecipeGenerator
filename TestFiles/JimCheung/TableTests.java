import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.Before;

public class TableTests {
	   Table table;
	
	   @Before
	   public void setUp() {
		   List<String> list = new ArrayList<String>();
		   list.add("1");
		   list.add("2");
		   list.add("3");
		   table = new Table(list);
	   }
	   
	   @Test
	   public void testTableInsert(){
		  List<String> insertion = new ArrayList<String>( );
		  insertion.add("4");
		  insertion.add("5");
		  insertion.add("6");
		  table.insert(insertion);
	      assertEquals(table.getTableSize(), 2);
	   }
	   
	   @Test
	   public void testTableRemove(){
		  table.remove(0);
	      assertEquals(table.getTableSize(), 0);
	   }
	   
	   @Test
	   public void testGetAttributeSize(){
	      assertEquals(table.getAttributeSize(), 3);
	   }
	   
	   @Test
	   public void testGetTableSize(){
	      assertEquals(table.getTableSize(), 1);
	   }
	   
	   @Test
	   public void testGetRow(){
		   List<String> list = new ArrayList<String>();
		   list.add("1");
		   list.add("2");
		   list.add("3");
	      assertEquals(list, table.getRow(0));
	   }
	   
	   @Test
	   public void testGetCell(){
		   assertEquals(table.getCell(0,0),"1");
	   }
}
