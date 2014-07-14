/**
 * 
 */
package map;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RTM Bullseye client learning map
 * 
 * @author wenbyuan
 * 
 */
public class RBcLearningMap {
	private static final RBcLearningMap instance = new RBcLearningMap();
	private static ConcurrentHashMap<RBcLearningMapKey, Set<BullseyeAttribute>> learningMap =
			new ConcurrentHashMap<RBcLearningMapKey, Set<BullseyeAttribute>>();
	
	private RBcLearningMap() {
		System.out.println("Constructor called.");
	}
	
	/**
	 * Singleton
	 * @return
	 */
	public static RBcLearningMap getInstance() {
		return instance;
	}
	
	/**
	 * Given a set of placements, return all attributes associated with them
	 * @param keys
	 * @return
	 */
	public Set<BullseyeAttribute> getAttributesForPlacements(Set<RBcLearningMapKey> keys) {
		if(keys == null) return null;
		Set<BullseyeAttribute> attributes = new HashSet<BullseyeAttribute>();
		for(RBcLearningMapKey key : keys) {
			if(key != null && learningMap.containsKey(key)) {
				Set<BullseyeAttribute> tempAttributes = learningMap.get(key);
				//Synchronized needed because tempAttributes is accessed by multiple thread
				synchronized(tempAttributes){
					attributes.addAll(tempAttributes);
				}
			}
		}
		return attributes;
	}

	/**
	 * Learn a set of attributes for all associated placements.
	 * @param keys
	 * @param attributes
	 */
	public void addAttributes(Set<RBcLearningMapKey> keys, Set<BullseyeAttribute> attributes) {
		if(keys == null || attributes == null) return; 
		for( RBcLearningMapKey key : keys ) {
			if( key != null ){
				learningMap.putIfAbsent(key, Collections.synchronizedSet( new HashSet<BullseyeAttribute>() ));
				learningMap.get(key).addAll(attributes);
			}
		}
	}
	
	/**
	 * Remove a set of given attributes given a set of given keys.
	 * @param keys
	 * @param attributes
	 */
	public void removeAttributes(Set<RBcLearningMapKey> keys, Set<BullseyeAttribute> attributes) {
		if(keys == null || attributes == null) return;
		for(RBcLearningMapKey key : keys)
			if(key != null)
				for(BullseyeAttribute attribute : attributes) {
					if(attribute != null)
						learningMap.get(key).remove(attribute);
				}
	}
	
	/**
	 * Clear all the records in the map
	 * 
	 */
	public synchronized void clear() {
		learningMap.clear();
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for(RBcLearningMapKey key : learningMap.keySet()) {
			buffer.append("Placement: " + key);
			buffer.append(" Attributes: " + learningMap.get(key));
			buffer.append(System.lineSeparator());
		}		
		return buffer.toString();
	}
	
	//For debugging
	public void printMap() {
		System.out.println(toString());
	}
}
