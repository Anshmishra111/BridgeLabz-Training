package BridgeLabz_Training.Junit;

public class UserRegistration {
public boolean registerUser(String username,String email,String password) {
	if(username==null||username.length()<5) {
		throw new IllegalArgumentException("Invalid username");
	}
	if(email==null||!email.contains("@")) {
		throw new IllegalArgumentException("Invalid email");
	}
	if(password==null||password.length()<8) {
		throw new IllegalArgumentException("Invalid password");
	}
	return true;
}

}

