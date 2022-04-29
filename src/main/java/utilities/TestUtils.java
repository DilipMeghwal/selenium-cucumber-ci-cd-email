package utilities;

import java.util.HashMap;

public class TestUtils extends ExcelUtils {
	private  TestUtils instance = null;

	public String projPath = System.getProperty("user.dir");

	public HashMap<String,String> getTestDataHashMap(String fileName, String sheetName, String record){
		HashMap<String,String> testDataHashMap = readData(fileName, sheetName, record);
		return testDataHashMap;
	}
}
