package scenariobased;

	import java.util.ArrayList;
	import java.util.List;

	// Custom Exception
	class InvalidPhoneNumberException extends Exception {
	    public InvalidPhoneNumberException(String message) {
	        super(message);
	    }
	}

	// Contact class
	class Contact {
	    String name;
	    String phoneNumber;

	    Contact(String name, String phoneNumber) {
	        this.name = name;
	        this.phoneNumber = phoneNumber;
	    }
	}

	// Contact Organizer class
	public class PhoneContactOrganiser {

	    static List<Contact> contacts = new ArrayList<>();

	    // Method to add contact
	    static void addContact(String name, String phoneNumber)
	            throws InvalidPhoneNumberException {

	        // Validate phone number
	        if (!phoneNumber.matches("\\d{10}")) {
	            throw new InvalidPhoneNumberException(
	                    "Phone number must be exactly 10 digits");
	        }

	        // Check duplicate
	        for (Contact c : contacts) {
	            if (c.phoneNumber.equals(phoneNumber)) {
	                System.out.println("Duplicate contact not allowed.");
	                return;
	            }
	        }

	        contacts.add(new Contact(name, phoneNumber));
	        System.out.println("Contact added successfully.");
	    }

	    // Method to delete contact
	    static void deleteContact(String phoneNumber) {
	        for (Contact c : contacts) {
	            if (c.phoneNumber.equals(phoneNumber)) {
	                contacts.remove(c);
	                System.out.println("Contact deleted successfully.");
	                return;
	            }
	        }
	        System.out.println("Contact not found.");
	    }

	    // Method to search contact
	    static void searchContact(String phoneNumber) {
	        for (Contact c : contacts) {
	            if (c.phoneNumber.equals(phoneNumber)) {
	                System.out.println("Contact Found:");
	                System.out.println("Name: " + c.name);
	                System.out.println("Phone: " + c.phoneNumber);
	                return;
	            }
	        }
	        System.out.println("Contact not found.");
	    }

	    public static void main(String[] args) {

	        try {
	            addContact("Ansh", "9876543210");
	            addContact("Ravi", "9123456789");
	            addContact("Duplicate", "9876543210"); // duplicate
	            addContact("Invalid", "12345"); // invalid number
	        } catch (InvalidPhoneNumberException e) {
	            System.out.println("Error: " + e.getMessage());
	        }

	        searchContact("9876543210");
	        deleteContact("9123456789");
	        searchContact("9123456789");
	    }
	}


