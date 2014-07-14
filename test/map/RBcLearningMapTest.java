/**
 * 
 */
package map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author wenbyuan
 *
 */
public class RBcLearningMapTest{

	ExecutorService service = Executors.newCachedThreadPool();
	private volatile static int count = 0;
	private volatile static int thread = 0;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
//		service =  Executors.newCachedThreadPool();
	}
	
	@After
	public void tearsDown() throws Exception {
		service.shutdown();
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
	
	@Test
	public void testAddAttribute() {
		synchronized(this){
			System.out.println();
			System.out.println("Test add attribute single thread.");
			Set<BullseyeAttribute> attributes = generateAttributes(count++, 1);
			Set<RBcLearningMapKey> placements = generateKeys(count++, 5);
			RBcLearningMap.getInstance().addAttribute(placements, (BullseyeAttribute)attributes.toArray()[0]);
			System.out.println("Placements: " + placements
					+ " Attributes: " +RBcLearningMap.getInstance().getAttributesForPlacements(placements));
			System.out.println("Placement: " + placements.toArray()[0]
					+ " Attributes: " +RBcLearningMap.getInstance().getAttributesForPlacement((RBcLearningMapKey)placements.toArray()[0]));
			System.out.println("Current map: ");
			RBcLearningMap.getInstance().printMap();
			RBcLearningMap.getInstance().clear();		
		}
	}
	
	@Test
	public void testAddAttributes() {
		synchronized(this){
			System.out.println();
			System.out.println("Test add attributes single thread.");
			Set<BullseyeAttribute> attributes = generateAttributes(count++, 10);
			Set<RBcLearningMapKey> placements = generateKeys(count++, 5);
			RBcLearningMap.getInstance().addAttributes(placements, attributes);
			System.out.println("Placements: " + placements
					+ " Attributes: " +RBcLearningMap.getInstance().getAttributesForPlacements(placements));
			System.out.println("Placement: " + placements.toArray()[0]
					+ " Attributes: " +RBcLearningMap.getInstance().getAttributesForPlacement((RBcLearningMapKey)placements.toArray()[0]));
			System.out.println("Current map: ");
			RBcLearningMap.getInstance().printMap();
			RBcLearningMap.getInstance().clear();		
		}
	}

	@Test
	public void testAddAttributeMultiThread() throws InterruptedException {
		System.out.println();
		System.out.println("Test add attribute multi thread.");
		ExecutorService service2 = Executors.newCachedThreadPool();
		Set<RBcLearningMapKey> testPlacements = generateKeys(17, 5);
		int THREAD = 100;
		for(int i = 0; i < THREAD; i++){
			service2.execute(new Runnable(){
				@Override
				public void run() {
					StringBuffer buffer = new StringBuffer();
					buffer.append("Thread: " + ++thread);
					Set<BullseyeAttribute> attributes = generateAttributes(++count, 1);
					Set<RBcLearningMapKey> placements = generateKeys(17, 5);
					
					buffer.append(" Attribute: " + ((BullseyeAttribute)attributes.toArray()[0]).getId());
					buffer.append(" Placements: " + placements);
					RBcLearningMap.getInstance().addAttribute(placements, (BullseyeAttribute)attributes.toArray()[0]);
					buffer.append(" Attributes in Map: " + RBcLearningMap.getInstance().getAttributesForPlacements(placements));
					System.out.println(buffer.toString());	
					}
			});		
		}
		service2.shutdown();
		while(!service2.isTerminated())
			service.awaitTermination( 1, TimeUnit.NANOSECONDS );
//		for(RBcLearningMapKey placement : testPlacements){
//			assertTrue( RBcLearningMap.getInstance().getAttributesForPlacement(placement).size() == THREAD );
//		}
		RBcLearningMap.getInstance().getAttributesForPlacements(testPlacements);
		System.out.println("Current map: ");
		RBcLearningMap.getInstance().printMap();
		RBcLearningMap.getInstance().clear();
	}

	@Test
	public void testAddAttributesMultiThread() throws InterruptedException {
		System.out.println();
		System.out.println("Test add attributes multi thread.");
		ExecutorService service2 = Executors.newCachedThreadPool();
		Set<RBcLearningMapKey> testPlacements = generateKeys(17, 5);
		int THREAD = 100;
		for(int i = 0; i < THREAD; i++){
			service2.execute(new Runnable(){
				@Override
				public void run() {
					StringBuffer buffer = new StringBuffer();
					buffer.append("Thread: " + ++thread);
					Set<BullseyeAttribute> attributes = generateAttributes(++count, count);
					Set<RBcLearningMapKey> placements = generateKeys(17, 5);
					
					buffer.append(" Attribute: " + attributes);
					buffer.append(" Placements: " + placements);
					RBcLearningMap.getInstance().addAttributes(placements, attributes);
					buffer.append(" Attributes in Map: " + RBcLearningMap.getInstance().getAttributesForPlacements(placements));
					System.out.println(buffer.toString());	
					}
			});		
		}
		service2.shutdown();
		while(!service2.isTerminated())
			service.awaitTermination( 1, TimeUnit.NANOSECONDS );
//		for(RBcLearningMapKey placement : testPlacements){
//			assertTrue( RBcLearningMap.getInstance().getAttributesForPlacement(placement).size() == THREAD );
//		}
		RBcLearningMap.getInstance().getAttributesForPlacements(testPlacements);
		System.out.println("Current map: ");
		RBcLearningMap.getInstance().printMap();
		RBcLearningMap.getInstance().clear();
	}
	
	public void testGetAttributesForPlacement() {
		
	}
	

	private Set<RBcLearningMapKey> generateKeys(long seed, int max) {
		Random generator = new Random(seed);
		Set<RBcLearningMapKey> keys =  new HashSet<RBcLearningMapKey>();
		for(int i = 0; i < max; i++){
			int id = generator.nextInt(1000);
			RBcLearningMapKey key = new RBcLearningMapKey(""+id);
			keys.add(key);
		}
		return keys;
	}
	
	private Set<BullseyeAttribute> generateAttributes(long seed, int max) {
		Random generator = new Random(seed);
		Set<BullseyeAttribute> attributes = new HashSet<BullseyeAttribute>();
		for(int i = 0; i < max; i++){
			int id = generator.nextInt(100);
			BullseyeAttribute attribute = new BullseyeAttribute(id,"dummy", 0);
			attributes.add(attribute);
		}
		return attributes;
	}
	
	private Future<RBcLearningMap> getFutureRBcLearningMap() {
		return service.submit((new Callable<RBcLearningMap>() {
		      public RBcLearningMap call() {
//		    	System.out.println("Thread " + count++);
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
