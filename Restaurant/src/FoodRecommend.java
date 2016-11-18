 /* Name : Sushma Lingutla
  * Student ID : s0980233
  * Date : 11/10/2015
  * Project Description : To create the list of skin products recommended for a given input. 
 */
package restaurants;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class FoodRecommend {

	public static void main(String[] args) throws IOException {
		// Load FoodItemIdNames from File
		String filePath = new File("").getAbsolutePath();
		String[] recommendedItemId = new String[20];
		FoodItemIdNames[] itemNames = new FoodItemIdNames[20];
		int noOfItemRecs = 0;
		String FoodItemIdNamesFile = "/FoodItemIdNames.csv";
		noOfItemRecs = FoodRecommend.loadFoodItemIdNamesClass(itemNames,filePath+FoodItemIdNamesFile);

		// Load RestaurantType from File
		RestaurantType[] restaurantRecs = new RestaurantType[30];
		int noOfRestaurantTypes = 0;
		String restaurantTypeFile = "/RestaurantType.csv";
		noOfRestaurantTypes = FoodRecommend.loadRestaurantTypeClass(restaurantRecs,filePath+restaurantTypeFile);

		// Load FoodType from File
		FoodType[] foodTypeRecs = new FoodType[20];
		int noOfFoodTypes = 0;
		String foodTypeFile = "/FoodType.csv";
		noOfFoodTypes = FoodRecommend.loadFoodTypeClass(foodTypeRecs,filePath+foodTypeFile);

		// Load FoodCategory from File
		FoodCategory[] categoryRecs = new FoodCategory[20];
		int noOfFoodCategoryRecs = 0;
		String foodCategoryFile = "/FoodCategory.csv";
		noOfFoodCategoryRecs = FoodRecommend.loadFoodCategoryClass(categoryRecs,filePath+foodCategoryFile);

		int option = 0;
		String restaurantType = null;
		String foodType = null;
		String foodCategory = null;
		int itemNum =0;
		boolean matchFound = false;
		Scanner input = new Scanner(System.in);
		BufferedWriter bw = new BufferedWriter(new FileWriter("FoodRecommend.csv", true));
		System.out.println("Welcome to Food Recommendation Customer Service");
		while (option != 5){
			System.out.println("===========================================================");
			System.out.println("Please choose one of the below options");
			System.out.println("===========================================================");
			System.out.println("option 1: To Enter RestaurantType \n");
			System.out.println("option 2: To Enter Food Type \n");
			System.out.println("option 3: To Enter Food Category \n");
			System.out.println("option 4: To See your Recommended Food \n");
			System.out.println("option 5: To Exit");
			System.out.println("===========================================================");
			option = input.nextInt();
			switch (option) {
				case 1:
					System.out.println("Enter Your Restaurant Type(Enter FastFood/FineDining/Barbecue/CasualDining) : ");
					restaurantType = input.next();	
					// Input validation for Restaurant Type				
					while (!restaurantType.equalsIgnoreCase("FastFood") && !restaurantType.equalsIgnoreCase("FineDining") && !restaurantType.equalsIgnoreCase("Barbecue") && !restaurantType.equalsIgnoreCase("CasualDining")) {
							System.out.println("ERROR : Please enter valid Restaurant Type");
							restaurantType = input.next();
					}
					break;
				case 2: 
					System.out.println("Enter Your Food Type(Enter Veg/NonVeg) : ");
					foodType = input.next();
					// Input validation for Food Type
					while (!foodType.equalsIgnoreCase("Veg") && !foodType.equalsIgnoreCase("NonVeg")) 
					{
							System.out.println("ERROR : Please enter valid Food Type");
							foodType = input.next();
					}
					break;
				case 3:
					System.out.println("Enter Food Category(Enter Indian/Chinese/Italian) : ");
					foodCategory = input.next();
					// Input validation for Temperature range
					while (!foodCategory.equalsIgnoreCase("Indian") && !foodCategory.equalsIgnoreCase("Chinese") && !foodCategory.equalsIgnoreCase("Italian")) 
					{
							System.out.println("ERROR : Please enter valid Food Category");
							foodCategory = input.next();
					}
					break;
				case 4:
					// Printing the output against database
					System.out.println("Entered Restaurant Type : "+ restaurantType);
					System.out.println("Entered Food Type : " + foodType);
					System.out.println("Entered Food Category : " + foodCategory);
					if ((restaurantType == null) && (foodType == null) && (foodCategory == null)) {
						System.out.println("You have not entered any preferences, sorry we can't recommend any product until then");
						matchFound = false;
					}
					else {
						itemNum = 0;
						// checking the combinations of entered inputs
						String MatchAgainst = null;
						if ((restaurantType != null) && (foodType != null) && (foodCategory != null))
							MatchAgainst = "RtFtFc";
						else if ((restaurantType == null) && (foodType != null) && (foodCategory != null))
							MatchAgainst = "FtFc";
						else if ((restaurantType != null) && (foodType == null) && (foodCategory != null))
							MatchAgainst = "RtFc";
						else if ((restaurantType != null) && (foodType != null) && (foodCategory == null))
							MatchAgainst = "RtFt";
						else if ((restaurantType == null) && (foodType == null) && (foodCategory != null))
							MatchAgainst = "foodCategory";
						else if ((restaurantType != null) && (foodType == null) && (foodCategory == null))
							MatchAgainst = "restaurantType";
						else if ((restaurantType == null) && (foodType != null) && (foodCategory == null))
							MatchAgainst = "foodType";
						switch (MatchAgainst) {
							// checking the combinations of entered inputs and store the matched product found
							case "restaurantType":
								int p = 0;
								while (p<noOfRestaurantTypes){
									if(restaurantRecs[p].getRestaurantType().equalsIgnoreCase(restaurantType)) {
										matchFound = true;
										recommendedItemId[itemNum++]=restaurantRecs[p].getItemId();
										bw.write(restaurantRecs[p].getRestaurantType() + ",,," + restaurantRecs[p].getItemId());
										bw.newLine();
										bw.flush();
									}
									p++;
								}
								break;
							case "foodType":
								int q = 0;
								while (q<noOfFoodTypes){
									if(foodTypeRecs[q].getFoodType().equalsIgnoreCase(foodType)) {
										matchFound = true;
										recommendedItemId[itemNum++]=foodTypeRecs[q].getItemId();
										bw.write("," + foodTypeRecs[q].getFoodType() + ",," + foodTypeRecs[q].getItemId());
										bw.newLine();
										bw.flush();
									}
									q++;
								}
								break;
							case "foodCategory":
								int r = 0;
								while (r<noOfFoodCategoryRecs){
									if(categoryRecs[r].getFoodCategory().equalsIgnoreCase(foodCategory)) {
										matchFound = true;
										recommendedItemId[itemNum++]=restaurantRecs[r].getItemId();
										bw.write(",," + categoryRecs[r].getFoodCategory() + "," + categoryRecs[r].getItemId());
										bw.newLine();
										bw.flush();
									}
									r++;
								}
								break;
							case "RtFt":
									for (int s=0; s<noOfFoodTypes; s++) {
										for (int o=0; o<noOfRestaurantTypes; o++) {
											if(foodTypeRecs[s].getFoodType().equalsIgnoreCase(foodType) 
													&& restaurantRecs[o].getRestaurantType().equalsIgnoreCase(restaurantType)) {
												if (foodTypeRecs[s].getItemId().equalsIgnoreCase(restaurantRecs[o].getItemId())) {
													matchFound = true;
													recommendedItemId[itemNum++]=restaurantRecs[o].getItemId();
													bw.write(restaurantRecs[o].getRestaurantType() + "," + foodTypeRecs[s].getFoodType() + ",," + restaurantRecs[o].getItemId());
													bw.newLine();
													bw.flush();
												}
											}
										}
									}
								break;
							case "RtFc":
									for (int s=0; s<noOfFoodCategoryRecs; s++)
										for (int o=0; 0<noOfRestaurantTypes; o++) {
											if(categoryRecs[s].getFoodCategory().equalsIgnoreCase(foodCategory) 
													&& restaurantRecs[o].getRestaurantType().equalsIgnoreCase(restaurantType)) {
												if (categoryRecs[s].getItemId().equalsIgnoreCase(restaurantRecs[o].getItemId())) {
													matchFound = true;
													recommendedItemId[itemNum++]=restaurantRecs[o].getItemId();
													bw.write(restaurantRecs[o].getRestaurantType() + ",," + categoryRecs[s].getFoodCategory() + "," + restaurantRecs[o].getItemId());
													bw.newLine();
													bw.flush();
												}
											}
										}
								break;
							case "FtFc":
								for (int s=0; s<noOfFoodCategoryRecs; s++)
									for (int o=0; 0<noOfFoodTypes; o++) {
										if(categoryRecs[s].getFoodCategory().equalsIgnoreCase(foodCategory) 
												&& foodTypeRecs[o].getFoodType().equalsIgnoreCase(foodType)) {
											if (categoryRecs[s].getItemId().equalsIgnoreCase(foodTypeRecs[o].getItemId())) {
												matchFound = true;
												recommendedItemId[itemNum++]=foodTypeRecs[o].getItemId();
												bw.write("," + foodTypeRecs[o].getFoodType() + "," + categoryRecs[s].getFoodCategory() + "," + categoryRecs[s].getItemId());
												bw.newLine();
												bw.flush();
											}
										}
									}
								break;
							case "RtFtFc":
									for (int s=0; s<noOfFoodCategoryRecs; s++)
										for (int o=0; o<noOfFoodTypes; o++)
											for (int c=0; c<noOfRestaurantTypes; c++) {
												if(categoryRecs[s].getFoodCategory().equalsIgnoreCase(foodCategory) 
														&& foodTypeRecs[o].getFoodType().equalsIgnoreCase(foodType)
														&& restaurantRecs[c].getRestaurantType().equalsIgnoreCase(restaurantType)) {
													if (categoryRecs[s].getItemId().equalsIgnoreCase(foodTypeRecs[o].getItemId()) 
															&& categoryRecs[s].getItemId().equalsIgnoreCase(restaurantRecs[c].getItemId())) {
														matchFound = true;
														recommendedItemId[itemNum++]=restaurantRecs[c].getItemId();
														bw.write(restaurantRecs[c].getRestaurantType() + "," + foodTypeRecs[o].getFoodType() + "," + categoryRecs[s].getFoodCategory() + "," + categoryRecs[s].getItemId());
														bw.newLine();
														bw.flush();
													}
												}
											}
								break;
							default:
									System.out.println("No Match Found, sorry we cannot recommend any product for you");
								break;
						}
						if(!matchFound)
							System.out.println("No Match Found, sorry we cannot recommend any product for you");
						else {
							System.out.println("===============================================");
							System.out.println("##Recommended food is Successfully Found##");
							System.out.println("List of Products Recommended for you:");
							for (int k=0;k<itemNum; k++) {
								for (int n=0;n<noOfItemRecs; n++) {
									if (recommendedItemId[k].equalsIgnoreCase(itemNames[n].getItemId())) {
										//System.out.println("=>" + itemNames[n].getItemName());
										if (MatchAgainst == "RtFtFc") {
											System.out.println(restaurantType+", "+foodType+", "+foodCategory+", "+itemNames[n].getItemName());
										}
										else if (MatchAgainst == "FtFc") {
											System.out.println(foodType+", "+foodCategory+", "+itemNames[n].getItemName());
										}
										else if (MatchAgainst == "RtFt") {
											System.out.println(restaurantType+", "+foodType+", "+itemNames[n].getItemName());
										}
										else if (MatchAgainst == "RtFc") {
											System.out.println(restaurantType+", "+foodCategory+", "+itemNames[n].getItemName());
										}
										else if (MatchAgainst == "foodCategory") {
											System.out.println(foodCategory+", "+itemNames[n].getItemName());
										}
										else if (MatchAgainst == "foodType") {
											System.out.println(foodType+", "+itemNames[n].getItemName());
										}
										else if (MatchAgainst == "restaurantType") {
											System.out.println(restaurantType+", "+itemNames[n].getItemName());
										}
									}
								}
							}
							System.out.println("===============================================");
						}
						matchFound = false;
					}
					break;
				case 5:
						System.out.println("Exiting the program...");
						break;
				default:
						System.out.println("ERROR : Please select a valid option \n");
			}
		}
		input.close();
	}
	/* FoodItemIdNames       */
	public static int loadFoodItemIdNamesClass(FoodItemIdNames[] itemNames, String filename) {
		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		int noOfItemRecs = 0;

		try {
			int i = 0;
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
			        // use comma as separator
				String[] records = line.split(splitBy);
				itemNames[i] = new FoodItemIdNames();
				itemNames[i].setData(records[0], records[1]);
				i++;
			}
			noOfItemRecs = i;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return noOfItemRecs;
	}
	/* RestaurantType       */
	public static int loadRestaurantTypeClass(RestaurantType[] restaurantRecs, String filename) {

		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		int noOfRestaurantTypes = 0;

		try {
			int i = 0;
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
				// use comma as separator
				String[] records = line.split(splitBy);
				restaurantRecs[i] = new RestaurantType();
				restaurantRecs[i].setData(records[0], records[1]);
				i++;
			}
			noOfRestaurantTypes = i;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return noOfRestaurantTypes;
	}
	/* FoodType       */
	public static int loadFoodTypeClass(FoodType[] foodTypeRecs, String filename) {

		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		int noOfFoodTypes = 0;

		try {
			int i = 0;
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
			        // use comma as separator
				String[] records = line.split(splitBy);
				foodTypeRecs[i] = new FoodType();
				foodTypeRecs[i].setData(records[0], records[1]);
				i++;
			}
			noOfFoodTypes = i;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return noOfFoodTypes;
	}
	/* TemperAndProducts       */
	public static int loadFoodCategoryClass(FoodCategory[] categoryRecs, String filename) {

		BufferedReader br = null;
		String line = "";
		String splitBy = ",";
		int noOfFoodCategoryRecs = 0;

		try {
			int i = 0;
			br = new BufferedReader(new FileReader(filename));
			while ((line = br.readLine()) != null) {
			        // use comma as separator
				String[] records = line.split(splitBy);
				categoryRecs[i] = new FoodCategory();
				categoryRecs[i].setData(records[0], records[1]);
				i++;
			}
			noOfFoodCategoryRecs = i;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return noOfFoodCategoryRecs;
	}
}
