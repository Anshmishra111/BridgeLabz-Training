package generics;
import java.util.*;
abstract class JobRole{
	String roleName;
	JobRole(String roleName){
		this.roleName=roleName;
	}
	abstract void evaluate();
}
//different jobRoles
class SoftwareEngineer extends JobRole{
	SoftwareEngineer(){
		super("Software Engineer ");
	}
	void evaluate() {
		System.out.println(roleName + " : evaluated coding and system design skills");
		
	}
}
class DataScientist extends JobRole{
	DataScientist(){
		super("Data Scientist");
	}
	void evaluate() {
		System.out.println(roleName + " : evaluated ml and statistics skills");
	}
}
class ProductManager extends JobRole{
	ProductManager(){
		super("Product Manager");
	}
	void evaluate() {
		System.out.println(roleName + " : evaluated product and leadership skills");
	}
}
//generic resume class
class Resume<T extends JobRole>{
	private T role;
	
	Resume(T role){
		this.role=role;
	}
	T getRole() {
		return role;
	}
}
//utility class with generic method
class ScreeningPipeline{
	//generic method
	static<T extends JobRole> void screenResume(T role) {
			role.evaluate();
		}
	//wildcard method
	static void screenAll(List<? extends JobRole> roles) {
		for(JobRole role: roles) {
			role.evaluate();
		}
	}
}


public class AIDrivenResumeScreenningSystem {
	public static void main(String[] args) {
		Resume<SoftwareEngineer> seResume=new Resume<>(new SoftwareEngineer());
		Resume<DataScientist> dsResume=new Resume<>(new DataScientist());
		Resume<ProductManager> pmResume=new Resume<>(new ProductManager());
		
		//individual screening
		ScreeningPipeline.screenResume(seResume.getRole());
		ScreeningPipeline.screenResume(dsResume.getRole());
		//Screening pipeline using wildcard
		List<JobRole> pipeline=new ArrayList<>();
		pipeline.add(seResume.getRole());
		pipeline.add(dsResume.getRole());
		pipeline.add(pmResume.getRole());
		
		System.out.println("------Screening pipeline----");
		ScreeningPipeline.screenAll(pipeline);
		
	}
	

}
