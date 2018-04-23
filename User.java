
import java.util.*;

public class User {

 public List<String> currentIngredient;
public List<String> favorite;

 public User() {
  this.currentIngredient = new ArrayList<String>();
  this.favorite = new ArrayList<String>();
 }

 public boolean addIngredient(String s) {
   if (currentIngredient.contains(s))
     return false;
   return currentIngredient.add(s);
 }
 
 public boolean addFavorite(String ID) {
   return this.favorite.add(ID);
 }
 
 public boolean removeFavorite(String ID) {
   return this.favorite.remove(ID);
 }
 
 public List<String> getFavorite() {
   return this.favorite;
 }
 
 public boolean isFavorite (String ID) {
   return this.favorite.contains(ID);
 }
 
 public boolean isFavourite (Recipe recipe) {
   return isFavorite (recipe.getId());
 }
 
}