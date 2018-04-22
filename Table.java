
import java.util.ArrayList;
import java.util.List;

public class Table {

	private List<String> attribute;
	private List<List<String>> alltuples;
	private int[] keyIndex;

	public Table(List<String> attribute) {
		this.attribute = attribute;
		this.alltuples = new ArrayList<List<String>>();
		keyIndex = null;
	}

	/******************************** Done ************************************/

	// Check whether the newly added tuple satisfies the key constrain. Return true
	// if satisfies, otherwise false.
	private boolean checkKeyConstrain(List<String> tuple) {
		if (keyIndex == null)
			return true; // No key constrain.

		for (int r = 0; r < this.getTableSize(); r++) {
			boolean check = true;
			for (int i = 0; i < keyIndex.length; i++) {
				if (alltuples.get(r).get(keyIndex[i]).equals(tuple.get(keyIndex[i])))
					check = false;
			}
			if (check == true)
				return false;
		}

		return true;
	}

	// Insert a new tuple
	public boolean insert(List<String> tuple) {
		if (!this.checkKeyConstrain(tuple)) {
			return false;
		}
		if (this.alltuples.add(tuple)) {
			return true;
		}
		return false;
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

	// Set the key
	public boolean setKey(List<String> key) {

		keyIndex = new int[key.size()];
		int count = 0;
		for (int c = 0; c < this.getAttributeSize(); c++) {
			for (int i = 0; i < key.size(); i++) {
				if (attribute.get(c).equals(key.get(i))) {
					keyIndex[count++] = c;
				}
			}
		}

		if (count != key.size()) {
			keyIndex = null;
			return false;
		}

		return true;
	}

	// Select when attribute equals condition
	public Table select_0(String conditions, String attribute) {
		int a_index = -1;
		int i = 0;

		// Get a_index
		for (i = 0; i < this.attribute.size(); i++) {
			if (this.attribute.get(i).equals(attribute)) {
				a_index = i;
				break;
			}
		}

		if (a_index == -1) {// Attribute not exist
			return null;
		}

		Table result = new Table(this.attribute); // Domain not implemented
		for (i = 0; i < this.getTableSize(); i++) {
			if (alltuples.get(i).get(a_index).equals(conditions)) {
				result.alltuples.add(this.alltuples.get(i));
			}
		}
		return result;
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
