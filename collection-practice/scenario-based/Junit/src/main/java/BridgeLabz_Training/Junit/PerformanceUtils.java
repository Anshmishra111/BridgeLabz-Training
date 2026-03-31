package BridgeLabz_Training.Junit;

public class PerformanceUtils {
public String longRunningTask() throws InterruptedException{
	Thread.sleep(3000);
	return "Task Completed";
}
}
