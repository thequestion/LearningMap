/**
 * 
 */
package map;

/**
 * @author wenbyuan
 * Identifier for RTM Bullseye learning map, value should not be NULL
 */
public class RBcLearningMapKey {
	private String placementId;
	
	public RBcLearningMapKey(String placementId) {
		this.placementId = placementId;
	}
	
	public void setKey(String key) {
		this.placementId = key;
	}
	
	public String getKey() {
		return this.placementId;
	}
	
	@Override
	public boolean equals(Object o){
		if( o == null || !(o instanceof RBcLearningMapKey) ) return false;
		if( getKey() == null || ((RBcLearningMapKey)o).getKey() == null ) return false;
		return getKey().compareTo( ((RBcLearningMapKey)o).getKey() ) == 0;
	}
}
