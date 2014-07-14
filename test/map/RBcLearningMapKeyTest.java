/**
 * 
 */
package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

/**
 * @author wenbyuan
 *
 */
public class RBcLearningMapKeyTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEquals() {
		RBcLearningMapKey key1 = new RBcLearningMapKey("222");
		RBcLearningMapKey key2 = new RBcLearningMapKey("223");
		RBcLearningMapKey key3 = new RBcLearningMapKey("222");
		assertEquals(key1, key3);
		assertNotEquals(key1, key2);
		HashMap<RBcLearningMapKey, String> testMap = new HashMap<RBcLearningMapKey, String>();
		testMap.put(key1, "test");
		assertTrue(testMap.containsKey(key3));
	}

}
