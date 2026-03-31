package generics;
//meal plan interface
interface MealPlan{
	String getMealType();
}
//meal subtypes
class VegetarianMeal implements MealPlan{
	public String getMealType() {
		return "Vegetarian Meal Plan";
	}
}
class VeganMeal implements MealPlan{
	public String getMealType(){
		return "Vegan Meal";
				
	}
}
class KetoMeal implements MealPlan{
	public String getMealType() {
		return "High-protein mela plan";
	}
}
//generic meal class
class Meal<T extends MealPlan>{
	private T plan;
	Meal(T plan){
		this.plan=plan;
	}
	T getPlan() {
		return plan;
	}
}
//utility class with generic method

class MealGenerator{
	//generic method
	static <T extends MealPlan> void generateMealPlan(T plan) {
		System.out.println("Generated: " + plan.getMealType());
	}
}

public class PersonalizedMealPlanGenerator {
	public static void main(String[] args) {
		Meal<VegetarianMeal> vegMeal=new Meal<>(new VegetarianMeal());
		Meal<VeganMeal> veganMeal=new Meal<>(new VeganMeal());
		Meal<KetoMeal> ketoMeal=new Meal<>(new KetoMeal());
		//generate meal plans
		MealGenerator.generateMealPlan(vegMeal.getPlan());
		MealGenerator.generateMealPlan(veganMeal.getPlan());
		MealGenerator.generateMealPlan(ketoMeal.getPlan());
		
		
	}

}
