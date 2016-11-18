 /* Name : Sushma Lingutla
  * Student ID : s0980233
  * Date : 11/10/2015
  * Class : ProductIdAndNames - Provides the get/set methods to access the variables product Id and names encapsulated. 
 */
package restaurants;

public class FoodItemIdNames {
	String itemId = null;
	String itemName = null;
	void setData(String itemId, String itemName){
		this.itemId = itemId;
		this.itemName = itemName;
	}
	String getItemId() {
		return itemId;
	}
	String getItemName() {
		return itemName;
	}
}