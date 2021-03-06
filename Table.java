
import java.util.ArrayList;
import java.util.List;

public class Table {

 private List<String> attribute;
 private List<List<String>> alltuples;

 public Table(List<String> attribute) {
  this.attribute = attribute;
  this.alltuples = new ArrayList<List<String>>();
 }

 /******************************** Done ************************************/

 // Insert a new tuple
 public boolean insert(List<String> tuple) {
  if (this.alltuples.add(tuple)) {
   return true;
  }
  return false;
 }

 // Remove a row
 public void remove(int row) {
  alltuples.remove(row);
 }
 
 // Print the table.
  public void print() {

  for (int c = 0; c < this.getAttributeSize(); c++) {
   System.out.printf(attribute.get(c));
   System.out.printf("\t");
  }
  System.out.println();

  for (int r = 0; r < this.getTableSize(); r++) {
   for (int c = 0; c < this.getAttributeSize(); c++) {
    System.out.printf(alltuples.get(r).get(c));
    System.out.printf("\t");
   }
   System.out.println();
  }

 }

 /********************** Getter &... **********************/

 public int getAttributeSize() {
  return this.attribute.size();
 }

 public int getTableSize() {
  return this.alltuples.size();
 }

 public List<String> getRow(int index) {
  return this.alltuples.get(index);
 }

 public String getCell(int row, int column) {
  return this.alltuples.get(row).get(column);
 }

}
