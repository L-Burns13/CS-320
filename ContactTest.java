import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void createsValidContact() {
        Contact c = new Contact("ID1234567", "Jane", "Doe", "1234567890", "123 Main Street");
        assertEquals("ID1234567", c.getContactId());
        assertEquals("Jane", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main Street", c.getAddress());
    }

    @Test
    void contactIdMustBeNonNullAndMax10() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact(null, "Jane", "Doe", "1234567890", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID-TOO-LONG", "Jane", "Doe", "1234567890", "123 Main Street")); // 11+
    }

    @Test
    void firstNameRules() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID1", null, "Doe", "1234567890", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID1", "ABCDEFGHIJK", "Doe", "1234567890", "123 Main Street")); // 11
    }

    @Test
    void lastNameRules() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID1", "Jane", null, "1234567890", "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID1", "Jane", "ABCDEFGHIJK", "1234567890", "123 Main Street")); // 11
    }

    @Test
    void phoneMustBeExactly10Digits() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID1", "Jane", "Doe", null, "123 Main Street"));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID1", "Jane", "Doe", "123456789", "123 Main Street"));   // 9
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID1", "Jane", "Doe", "12345678901", "123 Main Street")); // 11
    }

    @Test
    void addressMustBeNonNullAndMax30() {
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID1", "Jane", "Doe", "1234567890", null));
        assertThrows(IllegalArgumentException.class,
                () -> new Contact("ID1", "Jane", "Doe", "1234567890",
                        "1234567890123456789012345678901")); // 31
    }

    @Test
    void settersEnforceRules() {
        Contact c = new Contact("ID1", "Jane", "Doe", "1234567890", "123 Main Street");
        c.setFirstName("Ann");
        c.setLastName("Lee");
        c.setPhone("0987654321");
        c.setAddress("456 Oak Ave");
        assertEquals("Ann", c.getFirstName());
        assertEquals("Lee", c.getLastName());
        assertEquals("0987654321", c.getPhone());
        assertEquals("456 Oak Ave", c.getAddress());

        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setPhone("123"));     // not 10
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
    }
}
