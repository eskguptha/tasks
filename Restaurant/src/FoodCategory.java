 /* Name : Sushma Lingutla
  * Student ID : s0980233
  * Date : 11/10/2015
  * Class : SkinTypeAndProducts - Provides the get/set methods to access the variables product Id and skin type encapsulated. 
 */
package restaurants;

public class FoodCategory {
	String foodCategory = null;
	String itemId = null;
	void setData(String foodCategory, String itemId){
		this.foodCategory = foodCategory;
		this.itemId = itemId;
	}
	String getItemId() {
		return itemId;
	}
	String getFoodCategory() {
		return foodCategory;
	}
}
