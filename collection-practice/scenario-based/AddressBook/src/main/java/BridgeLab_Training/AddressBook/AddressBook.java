package BridgeLab_Training.AddressBook;
import java.util.*;

public class AddressBook {

    static Scanner sc = new Scanner(System.in);

    // ✅ MODEL
    static class Contact {
        String firstName, lastName, city, state, phone, email;

        Contact(String firstName, String lastName, String city,
                String state, String phone, String email) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.city = city;
            this.state = state;
            this.phone = phone;
            this.email = email;
        }

        // Duplicate Check (UC-7)
        @Override
        public boolean equals(Object obj) {
            Contact c = (Contact) obj;
            return this.firstName.equals(c.firstName)
                && this.lastName.equals(c.lastName);
        }

        @Override
        public String toString() {
            return firstName + " " + lastName + " | " + city + " | " + phone;
        }
    }

    // ✅ DATA STORAGE (UC-6)
    static Map<String, List<Contact>> addressBooks = new HashMap<>();

    // ✅ REGEX VALIDATION
    static boolean isValidName(String name) {
        return name.matches("[A-Za-z]+");
    }

    static boolean isValidPhone(String phone) {
        return phone.matches("\\d{10}");
    }

    // ✅ ADD CONTACT (UC-2)
    static void addContact(String bookName) {

        System.out.print("First Name: ");
        String fn = sc.next();
        if (!isValidName(fn)) { System.out.println("Invalid Name"); return; }

        System.out.print("Last Name: ");
        String ln = sc.next();

        System.out.print("City: ");
        String city = sc.next();

        System.out.print("State: ");
        String state = sc.next();

        System.out.print("Phone: ");
        String phone = sc.next();
        if (!isValidPhone(phone)) { System.out.println("Invalid Phone"); return; }

        System.out.print("Email: ");
        String email = sc.next();

        Contact contact = new Contact(fn, ln, city, state, phone, email);

        List<Contact> contacts = addressBooks.get(bookName);

        if (!contacts.contains(contact)) {
            contacts.add(contact);
            System.out.println("Contact Added!");
        } else {
            System.out.println("Duplicate Contact!");
        }
    }

    // ✅ VIEW CONTACTS (UC-5)
    static void viewContacts(String bookName) {
        for (Contact c : addressBooks.get(bookName)) {
            System.out.println(c);
        }
    }

    // ✅ DELETE CONTACT (UC-4)
    static void deleteContact(String bookName) {
        System.out.print("Enter First Name to Delete: ");
        String name = sc.next();

        List<Contact> contacts = addressBooks.get(bookName);

        contacts.removeIf(c -> c.firstName.equals(name));

        System.out.println("Contact Deleted!");
    }

    // ✅ SORT CONTACTS (UC-11)
    static void sortContacts(String bookName) {
        List<Contact> contacts = addressBooks.get(bookName);

        contacts.sort(Comparator.comparing(c -> c.firstName));

        System.out.println("Contacts Sorted!");
    }

    public static void main(String[] args) {

        System.out.println("Welcome to Address Book Program");

        // UC-6 → Multiple Address Books
        addressBooks.put("Friends", new ArrayList<>());
        addressBooks.put("Office", new ArrayList<>());

        String bookName = "Friends";

        int choice;

        do {
            System.out.println("\n1.Add  2.View  3.Delete  4.Sort  0.Exit");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addContact(bookName);
                    break;
                case 2:
                    viewContacts(bookName);
                    break;
                case 3:
                    deleteContact(bookName);
                    break;
                case 4:
                    sortContacts(bookName);
                    break;
            }

        } while (choice != 0);

        System.out.println("Thank You!");
    }
}
