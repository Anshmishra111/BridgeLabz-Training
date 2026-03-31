package BridgeLabz_Training.Junit;

public class DatabaseConnection {
private boolean connected=false;
public void connect() {
	connected=true;
	System.out.println("Database connected");
	
}
public void disconnect() {
	connected=false;
	System.out.println("Dtabase disconnected");
}
public boolean isConnected() {
	return connected;
}
}
