package scenario_based;
import java.util.*;
class Vessel{
	String vesselId;
    String vesselName;
    double averageSpeed;
    String vesselType;
    Vessel(String vesselId, String vesselName,
            double averageSpeed, String vesselType){
    	this.vesselId = vesselId;
        this.vesselName = vesselName;
        this.averageSpeed = averageSpeed;
        this.vesselType = vesselType;
    }
}
class VesselUtil{
	List<Vessel> vesselList=new ArrayList<>();
	void addVesselPerformance(Vessel v) {
		vesselList.add(v);
	}
	Vessel getVesselById(String vesselId) {
		for(Vessel v:vesselList) {
			if(v.vesselId.equals(vesselId)) {
				return v;
			}
		}
		return null;
	}
	List<Vessel> getHighPerformanceVessels(){
		List<Vessel> result=new ArrayList<>();
		double maxSpeed=0;
		for(Vessel v:vesselList) {
			if(v.averageSpeed>maxSpeed) {
				maxSpeed=v.averageSpeed;
			}
		}
		for(Vessel v:vesselList) {
			if(v.averageSpeed==maxSpeed) {
				result.add(v);
			}
		}
		return result;
	}
}
public class OceanFleet {
	 public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);
	        VesselUtil util=new VesselUtil();
	        System.out.println("Enter the number of vessels to be added");
	        int n=sc.nextInt();
	        sc.nextLine();
	        System.out.println("Enter vessel details");
	        for(int i=0;i<n;i++) {
	        	String input=sc.nextLine();
	        	String[] data=input.split(":");
	        	 Vessel v = new Vessel(
	                     data[0],
	                     data[1],
	                     Double.parseDouble(data[2]),
	                     data[3]
	                 );
	                 util.addVesselPerformance(v);
	             }

	             System.out.println("Enter the Vessel Id to check speed");
	             String id = sc.nextLine();

	             Vessel found = util.getVesselById(id);
	             if (found != null) {
	                 System.out.println(found.vesselId + " | " +
	                     found.vesselName + " | " +
	                     found.vesselType + " | " +
	                     found.averageSpeed + " knots");
	             } else {
	                 System.out.println("Vessel Id " + id + " not found");
	             }

	             System.out.println("High performance vessels are");
	             for (Vessel v : util.getHighPerformanceVessels()) {
	                 System.out.println(v.vesselId + " | " +
	                     v.vesselName + " | " +
	                     v.vesselType + " | " +
	                     v.averageSpeed + " knots");
	        }
	 }

}
