/**
 * 
 */
package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Before;
import org.junit.Test;

/**
 * @author wenbyuan
 *
 */
public class RBcLearningMapTest{

	ExecutorService service = Executors.newCachedThreadPool();
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertTrue(true);
	}
	
	@Test
	public void testSingleton() {
		Object dummy = new String("s");
		//Single thread
		assertEquals(RBcLearningMap.getInstance(), RBcLearningMap.getInstance());
		assertNotEquals(RBcLearningMap.getInstance(), dummy);
	}
	
	@Test
	public void testSingletonMultThread() throws InterruptedException, ExecutionException {
		Future<RBcLearningMap> future1 = getFutureRBcLearningMap();
		//Multi thread
		for(int i = 0; i < 100; i++) {
		    Future<RBcLearningMap> future2 = getFutureRBcLearningMap();
		    assertEquals(future1.get(), future2.get());
		}
	    
	    Future<Object> future3 = getFutureDummy();
	    assertNotEquals(future1.get(), future3.get());
	}

	private Future<RBcLearningMap> getFutureRBcLearningMap() {
		return service.submit((new Callable<RBcLearningMap>() {
		      public RBcLearningMap call() {
		        return RBcLearningMap.getInstance();
		      }
		    }));
	}
	
	private Future<Object> getFutureDummy() {
		return service.submit((new Callable<Object>() {
		      public Object call() {
		        return (Object)new String("S");
		      }
		    }));
	}
	

}
