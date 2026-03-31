package addressbook;

import java.util.*;

class Contact {
    String firstName, lastName, address, city, state, zip, phone, email;

    Contact(String fn, String ln, String add, String city,
            String state, String zip, String phone, String email) {
        this.firstName = fn; this.lastName = ln; this.address = add;
        this.city = city; this.state = state; this.zip = zip;
        this.phone = phone; this.email = email;
    }

    // UC7: Duplicate check by name
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact c = (Contact) o;
        return firstName.equalsIgnoreCase(c.firstName)
            && lastName.equalsIgnoreCase(c.lastName);
    }

    @Override
    public int hashCode() {
        return (firstName + lastName).toLowerCase().hashCode();
    }

    String fullName() {
        return firstName + " " + lastName;
    }

    public String toString() {
        return fullName() + " | " + city + " | " + state + " | " + zip;
    }
}

class AddressBook {
    String name;
    List<Contact> contacts = new ArrayList<>();

    AddressBook(String name) {
        this.name = name;
    }

    // UC2, UC5, UC7
    void addContact(Contact c) {
        if (!contacts.contains(c)) {
            contacts.add(c);
            System.out.println("Contact added to " + name);
        } else {
            System.out.println("Duplicate contact not allowed in " + name);
        }
    }

    // UC3
    void editContact(String firstName, Scanner sc) {
        for (Contact c : contacts) {
            if (c.firstName.equalsIgnoreCase(firstName)) {
                System.out.print("New City: ");
                c.city = sc.next();
                System.out.print("New Phone: ");
                c.phone = sc.next();
                System.out.println("Contact updated");
                return;
            }
        }
        System.out.println("Contact not found");
    }

    // UC4
    void deleteContact(String firstName) {
        contacts.removeIf(c -> c.firstName.equalsIgnoreCase(firstName));
    }

    // UC8, UC9
    void searchByCityOrState(String value) {
        for (Contact c : contacts)
            if (c.city.equalsIgnoreCase(value) || c.state.equalsIgnoreCase(value))
                System.out.println(c + " (Book: " + name + ")");
    }

    // UC10
    int countByCityOrState(String value) {
        int count = 0;
        for (Contact c : contacts)
            if (c.city.equalsIgnoreCase(value) || c.state.equalsIgnoreCase(value))
                count++;
        return count;
    }

    // UC11
    void sortByName() {
        contacts.sort(Comparator.comparing(Contact::fullName));
    }

    // UC12
    void sortByCity() { contacts.sort(Comparator.comparing(c -> c.city)); }
    void sortByState(){ contacts.sort(Comparator.comparing(c -> c.state)); }
    void sortByZip()  { contacts.sort(Comparator.comparing(c -> c.zip)); }

    void display() {
        contacts.forEach(System.out::println);
    }
}

public class AddressBookProblem {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        Scanner sc = new Scanner(System.in);

        // UC6: Multiple Address Books
        Map<String, AddressBook> system = new HashMap<>();
        AddressBook personal = new AddressBook("Personal");
        AddressBook office = new AddressBook("Office");
        system.put("Personal", personal);
        system.put("Office", office);

        // Sample data
        personal.addContact(new Contact("Amit","Sharma","A1",
                "Delhi","Delhi","110001","9999","a@gmail.com"));
        personal.addContact(new Contact("Ravi","Kumar","A2",
                "Mumbai","MH","400001","8888","r@gmail.com"));
        office.addContact(new Contact("Neha","Singh","B1",
                "Delhi","Delhi","110010","7777","n@gmail.com"));

        // UC8, UC9
        System.out.println("\nSearch persons in Delhi:");
        system.values().forEach(ab -> ab.searchByCityOrState("Delhi"));

        // UC10
        int total = 0;
        for (AddressBook ab : system.values())
            total += ab.countByCityOrState("Delhi");
        System.out.println("Total persons in Delhi: " + total);

        // UC11 & UC12
        System.out.println("\nSorted by Name:");
        personal.sortByName();
        personal.display();

        System.out.println("\nSorted by City:");
        personal.sortByCity();
        personal.display();
    }
}
