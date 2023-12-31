package test;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ReadingZipArhParam {
    private ClassLoader classLoader = ReadingZipArhParam.class.getClassLoader();
    @ParameterizedTest(name = "Для файла {0} контрольная сумма должна быть {1}")
    @CsvSource(value = {
            "English Book.pdf , 3506028550",
            "CheckList XLSX.xlsx , 1705283078",
            "CheckList CSV.csv , 3366467583"
    })
    void zipFileParsingTest(String fileName, Long crc) throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
           classLoader.getResourceAsStream("testArh.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals(fileName)) {
                    assertThat(entry.getCrc()).isEqualTo(crc);
                    return;
                }
            }
            fail("Отсутствует " + fileName);
        }
    }
}

