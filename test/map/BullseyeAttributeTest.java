package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BullseyeAttributeTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(true);
	}
	
	@Test
	public void testEquals() {
		BullseyeAttribute attr1 = new BullseyeAttribute(1, "MaritalStatus", 1);
		BullseyeAttribute attr2 = new BullseyeAttribute(1, "Jack", 2);
		BullseyeAttribute attr3 = new BullseyeAttribute(2, "Other", 3);
		
		assertEquals(attr1, attr2);
		assertNotEquals(attr1, attr3);
	}

}
