import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactServiceTest {

    private Contact make(String id) {
        return new Contact(id, "Jane", "Doe", "1234567890", "123 Main Street");
    }

    @Test
    void addContactStoresById() {
        ContactService svc = new ContactService();
        Contact c = make("ID1");
        svc.addContact(c);
        assertEquals(c, svc.getContact("ID1"));
    }

    @Test
    void addingDuplicateIdThrows() {
        ContactService svc = new ContactService();
        svc.addContact(make("ID1"));
        assertThrows(IllegalArgumentException.class, () -> svc.addContact(make("ID1")));
    }

    @Test
    void deleteByIdRemovesContact() {
        ContactService svc = new ContactService();
        svc.addContact(make("ID1"));
        svc.deleteContact("ID1");
        assertNull(svc.getContact("ID1"));
    }

    @Test
    void deleteWithUnknownIdThrows() {
        ContactService svc = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> svc.deleteContact("MISSING"));
    }

    @Test
    void updateFieldsById() {
        ContactService svc = new ContactService();
        svc.addContact(make("ID1"));

        svc.updateFirstName("ID1", "Ann");
        svc.updateLastName("ID1", "Lee");
        svc.updatePhone("ID1", "0987654321");
        svc.updateAddress("ID1", "456 Oak Ave");

        Contact c = svc.getContact("ID1");
        assertEquals("Ann", c.getFirstName());
        assertEquals("Lee", c.getLastName());
        assertEquals("0987654321", c.getPhone());
        assertEquals("456 Oak Ave", c.getAddress());
    }

    @Test
    void updateUnknownIdThrows() {
        ContactService svc = new ContactService();
        assertThrows(IllegalArgumentException.class, () -> svc.updateFirstName("MISSING", "Ann"));
        assertThrows(IllegalArgumentException.class, () -> svc.updateLastName("MISSING", "Lee"));
        assertThrows(IllegalArgumentException.class, () -> svc.updatePhone("MISSING", "1234567890"));
        assertThrows(IllegalArgumentException.class, () -> svc.updateAddress("MISSING", "123"));
    }
}
