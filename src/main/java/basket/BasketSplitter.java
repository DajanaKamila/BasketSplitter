package basket;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BasketSplitter handles the division of items between deliveries based on the available configuration. 
 * The optimal result is the one containing as few deliveries as possible, and the common delivery for the 
 * largest number of products contains as many of these products as possible.
 */
public class BasketSplitter {

	private Map<String, List<String>> configMap;
	private Map<String, List<String>> optimalDelivery;

	/**
	 * Constructor of the BasketSplitter class. 
	 * 
	 * @param absolutePathToConfigFile - Absolute path to the JSON configuration file with products and their available delivery methods.
	 */
	public BasketSplitter(String absolutePathToConfigFile) {
		this.configMap = JsonReader.changeJsonFileToMap(absolutePathToConfigFile);
		this.optimalDelivery = new HashMap<>();
	}

	/**
	 * Divides the product list into optimal deliveries.
	 * 
	 * @param items - List of products to be divided.
	 * @return - A map of optimal deliveries, where the key is the delivery method and the value is the list of products in a given delivery. 
	 *           If items list is empty or null, method returns empty map. 
	 */
	public Map<String, List<String>> split(List<String> items) {
		if (items == null || items.isEmpty() || configMap.isEmpty()) {
			return optimalDelivery;
		}
		Map<String, List<String>> possibleDeliveriesMap = generateMapOfPossibleDeliveries(items);
		groupProducts(items, possibleDeliveriesMap);
		return optimalDelivery;
	}

	/**
	 * Generates a map of all possible deliveries based on the list of items.  
	 * 
	 * @param items - List of products. 
	 * @return - A map of possible deliveries, where the key is the delivery method and the value is the list of products that can be delivered this way.
	 * 			If the product wasn't available in the config file, method ignores it.		 
	 */
	private Map<String, List<String>> generateMapOfPossibleDeliveries(List<String> items) {
		Map<String, List<String>> possibleDeliveriesMap = new HashMap<>();
		for (String item : items) {
			if (configMap.containsKey(item)) {
				for (String delivery : configMap.get(item)) {
					if (possibleDeliveriesMap.keySet().contains(delivery)) {
						possibleDeliveriesMap.get(delivery).add(item);
					} else {
						possibleDeliveriesMap.put(delivery, new ArrayList<>(List.of(item)));
					}
				}
			}
		}
		return possibleDeliveriesMap;
	}

	/**
	 * Groups products into optimal deliveries based on a list of possible deliveries. 
	 * 
	 * @param items - List of products to be grouped into delivery groups.  
	 * @param possibleDeliveriesMap - A map of all possible deliveries based on the list of items. Deliveries without any products are ignored. 
	 */
	private void groupProducts(List<String> items, Map<String, List<String>> possibleDeliveriesMap) {
		//Find the delivery with the most products
		while (!items.isEmpty() && !possibleDeliveriesMap.isEmpty()) {
			String largestDelivery = possibleDeliveriesMap.entrySet().stream()
					.max(Comparator.comparingInt(entry -> entry.getValue().size())).map(entry -> entry.getKey())
					.orElse(null);

			if (!possibleDeliveriesMap.get(largestDelivery).isEmpty()) {
				// Add the largest delivery to the optimal delivery map
				optimalDelivery.put(largestDelivery, possibleDeliveriesMap.get(largestDelivery));
	
				// Remove products from the list of items and the corresponding values from the map of possible deliveries
				items.removeAll(possibleDeliveriesMap.get(largestDelivery));
				possibleDeliveriesMap.remove(largestDelivery);
				possibleDeliveriesMap.forEach((key, value) -> value.removeAll(optimalDelivery.get(largestDelivery)));
			} else {
				break;
			}
		}
	}

}

