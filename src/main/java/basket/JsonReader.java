package basket;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JsonReader reads a JSON file and transform its contents to a selected format. 
 */

public class JsonReader {

	/**
	 * Reads a JSON file from the given path and transforms its content into a map, where the keys are of type 
	 * String and the values are lists of Strings. 
	 * 
	 * @param path - Absolute path to the JSON file. 
	 * @return - A map where keys are of type String and the values are list of String. 
	 */
	public static Map<String, List<String>> changeJsonFileToMap(String path) {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, List<String>> result = null;
		try {
			result = mapper.readValue(new File(path), new TypeReference<Map<String, List<String>>>() {});
		} catch (IOException e) {
			 System.err.println("Error reading JSON file: " + e.getMessage());
		}
		
		if (result == null || result.isEmpty()) {
			return Collections.emptyMap();
		}
		
		return result;
	}

}