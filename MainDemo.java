
public class MainDemo {

	public static void main(String[] a) {

		RecipeInformation ri = new RecipeInformation();
		User us = new User();
		ri.printTables();

		System.out.println("\nCurrent Ingredient of the user: ");
		System.out.println(us.currentIngredient.toString());

		us.addIngredient("ingredient1");
		System.out.println("\nCurrent ingredient of the user after add: ");
		System.out.println(us.currentIngredient.toString());

		System.out.println("\nResult of search: ");
		ri.search(us.currentIngredient);
		ri.searched_table.print();

	}

}