package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
public class ReadingZipArh {
    private ClassLoader classLoader = ReadingZipArh.class.getClassLoader();
    @Test
    void zipFileParsingTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
           classLoader.getResourceAsStream("testArh.zip")
        )) {
            ZipEntry entry;

            entry = zis.getNextEntry();
            Assertions.assertEquals("Java_Head_Firts_Java.pdf", entry.getName());
            Assertions.assertEquals(577943615, entry.getCrc());

            entry = zis.getNextEntry();
            Assertions.assertEquals("CheckList XLSX.xlsx", entry.getName());
            Assertions.assertEquals(3269606048L, entry.getCrc());

            entry = zis.getNextEntry();
            Assertions.assertEquals("CheckList CSV.csv", entry.getName());
            Assertions.assertEquals(3366467583L, entry.getCrc());
        }
    }
}
