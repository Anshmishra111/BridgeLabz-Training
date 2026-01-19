package addressbook;

public class ContactUC1 {
	private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;
    // constructor, getters, setters
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ContactUC1)) return false;
        ContactUC1 other = (ContactUC1) obj;
        return firstName.equalsIgnoreCase(other.firstName)
            && lastName.equalsIgnoreCase(other.lastName);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " | " + city + ", " + state;
    }
}

