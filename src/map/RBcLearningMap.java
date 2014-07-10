/**
 * 
 */
package map;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wenbyuan
 * RTM Bullseye client learning map
 */
public class RBcLearningMap {
	private static RBcLearningMap instance = null;
	private static Map<RBcLearningMapKey, Set<BullseyeAttribute>> learningMap = null;
	
	private RBcLearningMap() {
		learningMap = new ConcurrentHashMap<RBcLearningMapKey, Set<BullseyeAttribute>>();
	}
	
	/**
	 * Singleton
	 * @return
	 */
	public static RBcLearningMap getInstance() {
		if(instance == null) instance = new RBcLearningMap();
		return instance;
	}
}
