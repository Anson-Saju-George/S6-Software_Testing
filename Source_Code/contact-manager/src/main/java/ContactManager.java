import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ContactManager {

    Map<String, Contact> contactList = new ConcurrentHashMap<>();

    public void addContact(String firstName, String lastName, String phoneNumber) {
        Contact contact = new Contact(firstName, lastName, phoneNumber);
        validateContact(contact);
        checkIfContactAlreadyExist(contact);
        contactList.put(generateKey(contact), contact);
    }

    public Collection<Contact> getAllContacts() {
        return contactList.values();
    }

    private void checkIfContactAlreadyExist(Contact contact) {
        if (contactList.containsKey(generateKey(contact))) {
            throw new RuntimeException("Contact Already Exists");
        }
    }

    private void validateContact(Contact contact) {
        contact.validateFirstName();
        contact.validateLastName();
        contact.validatePhoneNumber();
    }

    private String generateKey(Contact contact) {
        return String.format("%s-%s", contact.getFirstName(), contact.getLastName());
    }

    // Main method to run the program
    public static void main(String[] args) {
        ContactManager cm = new ContactManager();

        // Add contacts
        try {
            cm.addContact("boney george", "thomasmvn test", "0123456789");
            cm.addContact("Jerjin", "Anto", "0987654321");
            // Uncomment the following line to see an exception for a duplicate contact
            // cm.addContact("John", "Doe", "0123456789");
        } catch (RuntimeException e) {
            System.err.println("Error adding contact: " + e.getMessage());
        }

        // Display all contacts
        System.out.println("=== Contact List ===");
        for (Contact contact : cm.getAllContacts()) {
            System.out.println(contact.getFirstName() + " " + contact.getLastName() + " - " + contact.getPhoneNumber());
        }
    }
}
