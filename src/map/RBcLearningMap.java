/**
 * 
 */
package map;

import java.util.HashSet;
import java.util.Map;
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
	private static Map<RBcLearningMapKey, Set<BullseyeAttribute>> learningMap = new ConcurrentHashMap<RBcLearningMapKey, Set<BullseyeAttribute>>();;
	
	private RBcLearningMap() {
//		learningMap = new ConcurrentHashMap<RBcLearningMapKey, Set<BullseyeAttribute>>();
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
				attributes.addAll(learningMap.get(key));
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
				if( learningMap.containsKey(key) ){
					learningMap.get(key).add(attribute);
				} else {
					Set<BullseyeAttribute> newAttributeSet = new HashSet<BullseyeAttribute>();
					newAttributeSet.add(attribute);
					learningMap.put(key, newAttributeSet);
				}
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
				if( learningMap.containsKey(key) ){
					learningMap.get(key).addAll(attributes);
				} else {
					Set<BullseyeAttribute> newAttributeSet = new HashSet<BullseyeAttribute>();
					newAttributeSet.addAll(attributes);
					learningMap.put(key, newAttributeSet);
				}
			}
		}
	}
	
	public synchronized void update() {
		
	}
}
