package test;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

public class ReadingZipArh {
    private ClassLoader classLoader = ReadingZipArh.class.getClassLoader();

    @Test
    void zipFileParsingPDFTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
           classLoader.getResourceAsStream("testArh.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("English Book.pdf")) {
                    PDF pdf = new PDF(zis);
                    assertThat(pdf.text).contains("A crash is when your competitor’s program dies");
                    return;
                }
            }
            fail("Отсутствует " + "English Book.pdf");
        }
    }
    @Test
    void zipFileParsingXLSXTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                classLoader.getResourceAsStream("testArh.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("CheckList XLSX.xlsx")) {
                    XLS xls = new XLS(zis);
                    assertThat(xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue())
                    .isEqualTo("Сайт www.salonsecret.ru");
                    return;
                }
            }
            fail("Отсутствует " + "CheckList XLSX.xlsx");
        }
    }
    @Test
    void zipFileParsingCSVTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                classLoader.getResourceAsStream("testArh.zip")
        )) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("CheckList CSV.csv")) {
                    CSVReader csv = new CSVReader(new InputStreamReader(zis));
                    assertThat(csv.readNext()[0]).contains("shop.salonsecret.ru");
                    return;
                }
            }
            fail("Отсутствует " + "CheckList CSV.csv");
        }
    }
}

