import java.util.HashMap;
import java.util.Map;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    //add new contact
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null");
        }
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("Contact already exists");
        }
        contacts.put(id, contact);
    }

    //delete by ID
    public void deleteContact(String contactId) {
        require(contactId); // will throw if not found
        contacts.remove(contactId);
    }

    //updates
    public void updateFirstName(String contactId, String newFirstName) {
        require(contactId).setFirstName(newFirstName);
    }

    public void updateLastName(String contactId, String lastName) {
        require(contactId).setLastName(lastName);
    }

    public void updatePhone(String contactId, String phone) {
        require(contactId).setPhone(phone);
    }

    public void updateAddress(String contactId, String newAddress) {
        require(contactId).setAddress(newAddress);
    }

    //used by tests
    public Contact getContact(String contactId) {
        return contacts.get(contactId);
    }

    //helper to fetch or throw
    private Contact require(String contactId) {
        Contact c = contacts.get(contactId);
        if (c == null) {
            throw new IllegalArgumentException("Contact does not exist");
        }
        return c;
    }
}
