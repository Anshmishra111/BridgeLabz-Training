package scenario_based;

public class SandeepFitnessChallengeTracker {
	public static void main(String[] args) {
	int[] pushUps= {30,0,25,35,0,35,20};
	int totalPushups=0;
	int workOutDays=0;
	for(int count:pushUps) {
		if(count==0) {
			continue;
		}
		totalPushups+=count;
		workOutDays++;
	}
	double averagePushups=(double)totalPushups/workOutDays;
	System.out.println("Total pushups in the week: " + totalPushups);
	System.out.println("Workout Days: " + workOutDays);
	System.out.println("Average pushups per workoutday: " + averagePushups);

}
}
