 /* Name : Sushma Lingutla
  * Student ID : s0980233
  * Date : 11/10/2015
  * Class : TemprAndProducts - Provides the get/set methods to access the variables product Id and temperature range encapsulated. 
 */
package restaurants;

public class FoodType {
	String foodType = null;
	String itemId = null;
	void setData(String foodType, String itemId){
		this.foodType = foodType;
		this.itemId = itemId;
	}
	String getItemId() {
		return itemId;
	}
	String getFoodType() {
		return foodType;
	}
}
