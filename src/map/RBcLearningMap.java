/**
 * 
 */
package map;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * RTM Bullseye client learning map
 * 
 * @author wenbyuan
 * 
 * 
 */
public class RBcLearningMap {
	private static final RBcLearningMap instance = new RBcLearningMap();
	private static Map<RBcLearningMapKey, Set<BullseyeAttribute>> learningMap = null;
	
	private RBcLearningMap() {
		learningMap = new ConcurrentHashMap<RBcLearningMapKey, Set<BullseyeAttribute>>();
	}
	
	/**
	 * Singleton
	 * @return
	 */
	public static RBcLearningMap getInstance() {
		return instance;
	}
}
