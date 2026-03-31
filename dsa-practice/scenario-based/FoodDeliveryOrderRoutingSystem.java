package scenario_based;
import java.util.*;

class NoAgentAvailableException extends Exception{
	public NoAgentAvailableException(String message) {
		super(message);
	}
}
class Order{
	int orderId;
	int location;
	Order(int orderId,int location){
		this.orderId=orderId;
		this.location=location;
	}
}
class Agent{
	int agentId;
	int location;
	boolean available=true;
	Agent(int agentId,int location){
		this.agentId=agentId;
		this.location=location;
	
	}
}
class DeliveryService{
	Queue<Order> orders=new LinkedList<>();
	List<Agent> agents=new ArrayList<>();
	void addOrder(Order o) {
	 orders.add(o);
	}
	void addAgent(Agent a) {
		agents.add(a);
	}
	void assignOrder() throws NoAgentAvailableException {
		if(orders.isEmpty()) return;
		
		Order o=orders.poll();
		Agent selected=null;
		for(Agent a:agents) {
			if(a.available) {
				selected=a;
				break;
			}
		}
		if(selected==null) 
			throw new NoAgentAvailableException("No agent free");
			selected.available=false;
			 System.out.println("Order " + o.orderId + " assigned to Agent " + selected.agentId);
		}
		void viewActiveAgents() {
	        for (Agent a : agents) {
	            System.out.println("Agent " + a.agentId +
	                    " Available: " + a.available);
	        }
	}
}
public class FoodDeliveryOrderRoutingSystem {
	public static void main(String[] args) {

        DeliveryService ds = new DeliveryService();

        ds.addAgent(new Agent(1, 5));
        ds.addAgent(new Agent(2, 10));

        ds.addOrder(new Order(101, 6));
        ds.addOrder(new Order(102, 9));

        try {
            ds.assignOrder();
            ds.assignOrder();
            ds.assignOrder(); // Exception
        } catch (NoAgentAvailableException e) {
            System.out.println(e.getMessage());
        }

        ds.viewActiveAgents();
}
}
