 /* Name : Sushma Lingutla
  * Student ID : s0980233
  * Date : 11/10/2015
  * Class : OilPercentageAndProduct - Provides the get/set methods to access the variables product Id and oil percentage encapsulated. 
 */
package restaurants;

public class RestaurantType {
	String restaurantType = null;
	String itemId = null;
	void setData(String restaurantType, String itemId){
		this.restaurantType = restaurantType;
		this.itemId = itemId;
	}
	String getItemId() {
		return itemId;
	}
	String getRestaurantType() {
		return restaurantType;
	}
}
