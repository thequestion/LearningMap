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
	private static ConcurrentHashMap<RBcLearningMapKey, Set<BullseyeAttribute>> learningMap = new ConcurrentHashMap<RBcLearningMapKey, Set<BullseyeAttribute>>();
	
	private RBcLearningMap() {
//		learningMap = new ConcurrentHashMap<RBcLearningMapKey, Set<BullseyeAttribute>>();
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
	 * Given a placement, return all attributes associated with it
	 * @param key
	 * @return
	 */
	public Set<BullseyeAttribute> getAttributesForPlacement(RBcLearningMapKey key) {
		if( key != null && learningMap.containsKey(key) ){
			return learningMap.get(key);
		} 
		return null;
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
	 * Learn one attribute for all associated placements.
	 * @param keys
	 * @param attribute
	 */
	public void addAttribute(Set<RBcLearningMapKey> keys, BullseyeAttribute attribute) {
		if(keys == null || attribute == null) return; 
		for( RBcLearningMapKey key : keys ) {
			if( key != null ){
				learningMap.putIfAbsent(key, Collections.synchronizedSet( new HashSet<BullseyeAttribute>()  ));
				learningMap.get(key).add(attribute);
			}
		}
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
	
	public synchronized void update() {
		
	}
	
	/**
	 * Clear all the records in the map
	 * 
	 */
	public synchronized void clear() {
		learningMap.clear();
	}
	
	//For debug
	public void printMap() {
		for(RBcLearningMapKey key : learningMap.keySet()) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Placement: " + key);
			buffer.append(" Attributes: " + learningMap.get(key));
			System.out.println(buffer.toString());
		}
	}
}
