package basketSplitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import basket.BasketSplitter;

class BasketSplitterTest {
	
	private BasketSplitter basketSplitter; 
	
	@BeforeEach
	void setUp() {
		/*Due to the inability to change the BasketSplitter constructor (project requirements), I use the static JsonReader.jsonFileToMap() in the constructor, 
		 * which makes it difficult to mock the JsonReader. Hence I use an actual class instead of mock.*/
		String path = "src/test/resources/testConfig.json";
		basketSplitter = new BasketSplitter(path);
	}

	@Test
	@DisplayName("split() - Empty list of items")
	void test_emptyListOfItemsAsParameterToSplitMethod_returnsEmptyMap() {
		List<String> items = new ArrayList<>();
		Map<String, List<String>> result = basketSplitter.split(items);
		assertTrue(result.isEmpty());
	}
	
	@Test
	@DisplayName("split() - Null list of items")
	void test_nullListOfItemsAsParameterToSplitMethod_returnsEmptyMap() {
		List<String> items = null;
		Map<String, List<String>> result = basketSplitter.split(items);
		assertTrue(result.isEmpty());
	}
	
	@Test
	@DisplayName("split() - Correct list of items")
	void test_correctListOfItemsAsParameterToSplitMethod_returnsExpectedMap()  {
	    List<String> items = new ArrayList<>(List.of("ProductOne", "ProductTwo", "ProductThree"));
		Set<String> deliverySet = new HashSet<>(new ArrayList<>(List.of("Delivery 2", "Delivery 4")));
		
		Map<String, List<String>> result = basketSplitter.split(items);
		assertEquals(deliverySet, result.keySet());
	}

	@Test
	@DisplayName("split() - Incorrect list of items")
	void test_incorrectListOfItemsAsParameterToSplitMethod_returnsMapWithOnlyCorrectProducts()  {
		List<String> items = new ArrayList<>(List.of("ProductOne", "ProductTwo", "ProductFour"));
		Set<String> deliverySet = new HashSet<>(new ArrayList<>(List.of("Delivery 2")));
		
		Map<String, List<String>> result = basketSplitter.split(items);
		assertEquals(deliverySet, result.keySet());
	}

}
