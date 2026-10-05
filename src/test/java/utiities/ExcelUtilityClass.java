package utiities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilityClass {

    private String path;

    public void ExcelUtility(String path) {
        this.path = path;
    }

    // Get row count
    public int getRowCount(String sheetName) throws IOException {

        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fi);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();

        workbook.close();
        fi.close();

        return rowCount;
    }

    // Get column count
    public int getCellCount(String sheetName, int rownum) throws IOException {

        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fi);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        XSSFRow row = sheet.getRow(rownum);

        if (row == null) {
            workbook.close();
            fi.close();
            return 0;
        }

        int cellCount = row.getLastCellNum();

        workbook.close();
        fi.close();

        return cellCount;
    }

    // Get cell data
    public String getCellData(String sheetName, int rownum, int colnum)
            throws IOException {

        FileInputStream fi = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fi);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        XSSFRow row = sheet.getRow(rownum);

        if (row == null) {
            workbook.close();
            fi.close();
            return "";
        }

        XSSFCell cell = row.getCell(colnum);

        if (cell == null) {
            workbook.close();
            fi.close();
            return "";
        }

        DataFormatter formatter = new DataFormatter();

        String data = formatter.formatCellValue(cell);

        workbook.close();
        fi.close();

        return data;
    }

    // Set cell data
    public void setCellData(String sheetName, int rownum, int colnum,
            String data) throws IOException {

        File xlFile = new File(path);

        XSSFWorkbook workbook;

        // If Excel file does not exist
        if (!xlFile.exists()) {

            workbook = new XSSFWorkbook();

            XSSFSheet sheet = workbook.createSheet(sheetName);

            XSSFRow row = sheet.createRow(rownum);

            XSSFCell cell = row.createCell(colnum);

            cell.setCellValue(data);

            FileOutputStream fo = new FileOutputStream(path);

            workbook.write(fo);

            fo.close();
            workbook.close();

            return;
        }

        // Open existing Excel file
        FileInputStream fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        // Create sheet if it doesn't exist
        XSSFSheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        // Create row if it doesn't exist
        XSSFRow row = sheet.getRow(rownum);

        if (row == null) {
            row = sheet.createRow(rownum);
        }

        // Create cell
        XSSFCell cell = row.getCell(colnum);

        if (cell == null) {
            cell = row.createCell(colnum);
        }

        cell.setCellValue(data);

        fi.close();

        FileOutputStream fo = new FileOutputStream(path);

        workbook.write(fo);

        fo.close();
        workbook.close();
    }

    // Fill cell Green
    public void fillGreenColor(String sheetName, int rownum, int colnum)
            throws IOException {

        FileInputStream fi = new FileInputStream(path);

        XSSFWorkbook workbook = new XSSFWorkbook(fi);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        XSSFRow row = sheet.getRow(rownum);

        XSSFCell cell = row.getCell(colnum);

        CellStyle style = workbook.createCellStyle();

        style.setFillForegroundColor(
                IndexedColors.GREEN.getIndex());

        style.setFillPattern(
                FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fi.close();

        FileOutputStream fo = new FileOutputStream(path);

        workbook.write(fo);

        fo.close();
        workbook.close();
    }

    // Fill cell Red
    public void fillRedColor(String sheetName, int rownum, int colnum)
            throws IOException {

        FileInputStream fi = new FileInputStream(path);

        XSSFWorkbook workbook = new XSSFWorkbook(fi);

        XSSFSheet sheet = workbook.getSheet(sheetName);

        XSSFRow row = sheet.getRow(rownum);

        XSSFCell cell = row.getCell(colnum);

        CellStyle style = workbook.createCellStyle();

        style.setFillForegroundColor(
                IndexedColors.RED.getIndex());

        style.setFillPattern(
                FillPatternType.SOLID_FOREGROUND);

        cell.setCellStyle(style);

        fi.close();

        FileOutputStream fo = new FileOutputStream(path);

        workbook.write(fo);

        fo.close();
        workbook.close();
    }
}