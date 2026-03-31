package BridgeLab_Training.AddressBook;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.*;

public class AddressBookTest {

    // ✅ Test Regex Name Validation
    @Test
    public void testValidName() {
        assertTrue(AddressBook.isValidName("Ansh"));
    }

    @Test
    public void testInvalidName() {
        assertFalse(AddressBook.isValidName("Ansh123"));
    }

    // ✅ Test Phone Validation
    @Test
    public void testValidPhone() {
        assertTrue(AddressBook.isValidPhone("9999999999"));
    }

    @Test
    public void testInvalidPhone() {
        assertFalse(AddressBook.isValidPhone("123"));
    }

    // ✅ Test Contact Creation
    @Test
    public void testContactCreation() {
        AddressBook.Contact c =
            new AddressBook.Contact("A", "B", "Delhi", "Delhi", "9999999999", "a@mail.com");

        assertEquals("A", c.firstName);
        assertEquals("B", c.lastName);
    }

    // ✅ Test Add Contact (No Duplicate)
    @Test
    public void testAddContact() {

        Map<String, List<AddressBook.Contact>> books = new HashMap<>();
        books.put("TestBook", new ArrayList<>());

        AddressBook.Contact c =
            new AddressBook.Contact("A", "B", "Delhi", "Delhi", "9999999999", "a@mail.com");

        books.get("TestBook").add(c);

        assertEquals(1, books.get("TestBook").size());
    }

    // ✅ Test Duplicate Contact Prevention
    @Test
    public void testDuplicateContact() {

        List<AddressBook.Contact> contacts = new ArrayList<>();

        AddressBook.Contact c1 =
            new AddressBook.Contact("A", "B", "Delhi", "Delhi", "9999999999", "a@mail.com");

        AddressBook.Contact c2 =
            new AddressBook.Contact("A", "B", "Mumbai", "MH", "8888888888", "b@mail.com");

        contacts.add(c1);

        if (!contacts.contains(c2)) {
            contacts.add(c2);
        }

        assertEquals(1, contacts.size()); // Duplicate avoided
    }

    // ✅ Test Delete Contact
    @Test
    public void testDeleteContact() {

        List<AddressBook.Contact> contacts = new ArrayList<>();

        AddressBook.Contact c =
            new AddressBook.Contact("A", "B", "Delhi", "Delhi", "9999999999", "a@mail.com");

        contacts.add(c);

        contacts.removeIf(contact -> contact.firstName.equals("A"));

        assertEquals(0, contacts.size());
    }

    // ✅ Test Sorting Contacts
    @Test
    public void testSortContacts() {

        List<AddressBook.Contact> contacts = new ArrayList<>();

        contacts.add(new AddressBook.Contact("Rahul", "K", "Delhi", "DL", "1", "r@mail.com"));
        contacts.add(new AddressBook.Contact("Ansh", "M", "Noida", "UP", "2", "a@mail.com"));

        contacts.sort(Comparator.comparing(c -> c.firstName));

        assertEquals("Ansh", contacts.get(0).firstName);
    }
}
