package basketSplitter;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import basket.JsonReader;

class JsonReaderTest {
	
	@Test
	@DisplayName("jsonMapToFile() - Incorrect configuration path file")
	void test_JsonFileToMapMethodWithIncorrectInputPath_ReturnsIOException() {
		String incorrectPath = "my-path.json";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	    System.setErr(new PrintStream(outputStream));
		
	    JsonReader.changeJsonFileToMap(incorrectPath);
	    String errorMessage = outputStream.toString();
	    
		assertTrue(errorMessage.contains("Error reading JSON file:"));
		System.setErr(System.err);
	}
	
	
	@Test
	@DisplayName("jsonMapToFile() - Correct configuration path file")
	void test_JsonFileToMapMethodWithCorrectInputPath_ReturnsMapOfStringAndListOfStringsOfCorrectSize() {
		String correctPath = "src/test/resources/testConfig.json";	
		Map<String, List<String>> result = JsonReader.changeJsonFileToMap(correctPath);
		
		assertEquals(3, result.keySet().size());
		assertEquals(3, result.values().size());
	}

}
