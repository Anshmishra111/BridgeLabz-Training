package addressbook;
import java.util.*;

class Contact {
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String zip;
    String phone;
    String email;

    Contact(String firstName, String lastName, String address,
            String city, String state, String zip,
            String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return firstName + " " + lastName + ", " + city + ", " + state +
                ", Phone: " + phone + ", Email: " + email;
    }
}
class AddressBook {
    List<Contact> contactList = new ArrayList<>();

    // UC2 + UC5
    void addContact(Contact contact) {
        contactList.add(contact);
        System.out.println("Contact added successfully");
    }

    // UC3
    void editContact(String firstName, Scanner sc) {
        for (Contact c : contactList) {
            if (c.firstName.equalsIgnoreCase(firstName)) {
                System.out.print("Enter new city: ");
                c.city = sc.next();
                System.out.print("Enter new phone: ");
                c.phone = sc.next();
                System.out.println("Contact updated");
                return;
            }
        }
        System.out.println("Contact not found");
    }

    // UC4
    void deleteContact(String firstName) {
        Iterator<Contact> it = contactList.iterator();
        while (it.hasNext()) {
            if (it.next().firstName.equalsIgnoreCase(firstName)) {
                it.remove();
                System.out.println("Contact deleted");
                return;
            }
        }
        System.out.println("Contact not found");
    }

    void displayContacts() {
        for (Contact c : contactList) {
            System.out.println(c);
        }
    }
    public class AddressBookMain {
        public static void main(String[] args) {
            System.out.println("Welcome to Address Book Program");

            Scanner sc = new Scanner(System.in);
            AddressBook addressBook = new AddressBook();

            while (true) {
                System.out.println("\n1.Add Contact  2.Edit Contact  3.Delete Contact  4.Display  5.Exit");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("First Name: ");
                        String fn = sc.next();
                        System.out.print("Last Name: ");
                        String ln = sc.next();
                        System.out.print("Address: ");
                        String add = sc.next();
                        System.out.print("City: ");
                        String city = sc.next();
                        System.out.print("State: ");
                        String state = sc.next();
                        System.out.print("Zip: ");
                        String zip = sc.next();
                        System.out.print("Phone: ");
                        String phone = sc.next();
                        System.out.print("Email: ");
                        String email = sc.next();

                        Contact c = new Contact(fn, ln, add, city, state, zip, phone, email);
                        addressBook.addContact(c);
                        break;

                    case 2:
                        System.out.print("Enter First Name to Edit: ");
                        addressBook.editContact(sc.next(), sc);
                        break;

                    case 3:
                        System.out.print("Enter First Name to Delete: ");
                        addressBook.deleteContact(sc.next());
                        break;

                    case 4:
                        addressBook.displayContacts();
                        break;

                    case 5:
                        System.out.println("Thank You!");
                        return;
                }
            }
        }
    }
}

