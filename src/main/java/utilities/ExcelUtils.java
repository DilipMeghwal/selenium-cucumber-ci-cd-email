package utilities;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private  ExcelUtils instance = null;
	private String projPath = System.getProperty("user.dir");

	/*
	 * This functions is designed to read data from excel and save
	 * that data to a hashmap that can be accessed throughout the script
	 */
	@SuppressWarnings("resource")
	public HashMap<String, String> readData(String fileName, String sheetName, String record) {
		HashMap<String, String> testDataHashMap = new HashMap<String, String>();
		try {
			FileInputStream file = new FileInputStream(projPath + "\\src\\test\\resources\\excel\\" + fileName);
			Workbook workbook = null;
			if (fileName.toLowerCase().endsWith("xlsx")) {
				workbook = new XSSFWorkbook(file);
			} else {
				workbook = new HSSFWorkbook(file);
			}

			// Get target in workbook
			Sheet sheet = workbook.getSheet(sheetName);
			List<String> columnNames = new ArrayList<String>();
			List<String> rowData = new ArrayList<String>();
			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Cell cell = sheet.getRow(i).getCell(0);
				if (sheet.getTopRow() == 0 && columnNames.isEmpty() == true) {
					columnNames = getRowValues(sheet, 0);
				} else if (getCellValueAsString(cell).equalsIgnoreCase(record)) {
					rowData = getRowValues(sheet, cell.getRowIndex());
				}
			}
			for (int i = 0; i < columnNames.size(); i++) {
				//System.out.println("Key " + columnNames.get(i) + " Value " + rowData.get(i));
				testDataHashMap.put(columnNames.get(i), rowData.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testDataHashMap;
	}

	public List<String> getRowValues(Sheet sheet, int rowNum) {
		List<String> rowValues = new ArrayList<String>();
		Row row = sheet.getRow(rowNum);
		for (Cell cell : row) {
			switch (cell.getCellType()) {
				case BOOLEAN:
					rowValues.add(String.valueOf(cell.getBooleanCellValue()));
					break;
				case STRING:
					rowValues.add(cell.getStringCellValue().toString());
					break;
				case NUMERIC:
					if (DateUtil.isCellDateFormatted(cell)) {
						rowValues.add(String.valueOf(cell.getDateCellValue()));
					} else {
						rowValues.add(String.valueOf(cell.getNumericCellValue()));
					}
					break;
				case FORMULA:
					rowValues.add(String.valueOf(cell.getCellFormula()));
					break;
				case BLANK:
					rowValues.add("");
					break;
				default:
					rowValues.add("");
			}
		}
		return rowValues;
	}

	public String getCellValueAsString(Cell cell) {
		String cellValue = null;
		switch (cell.getCellType()) {
			case BOOLEAN:
				cellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case STRING:
				cellValue = cell.getStringCellValue().toString();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					cellValue = String.valueOf(cell.getDateCellValue());
				} else {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case FORMULA:
				cellValue = String.valueOf(cell.getCellFormula());
				break;
			case BLANK:
				cellValue = "";
				break;
			default:
				cellValue = "";
		}
		return cellValue;
	}

}
