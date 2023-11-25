package test;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.InputStream;
import model.PhoneBook;

public class PhoneBookReader {
    ObjectMapper mapper = new ObjectMapper();
    ClassLoader classLoader = PhoneBookReader.class.getClassLoader();
    @Test
    void jsonFileParsingTest() throws Exception {
        try (InputStream is = classLoader.getResourceAsStream("PhoneBook.json")) {
            PhoneBook phoneBook = mapper.readValue(is, PhoneBook.class);
            Assertions.assertEquals("My phone book", phoneBook.getBookName());
            PhoneBook.Contact cg = phoneBook.getChapter().get('A').get(0);
            Assertions.assertEquals("Ambulance", cg.getName());
            Assertions.assertEquals("123456789", cg.getPhoneNum());
            Assertions.assertEquals("АААААААА", cg.getComment());
        }
    }
}
