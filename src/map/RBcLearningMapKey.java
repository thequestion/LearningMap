/**
 * 
 */
package map;

/**
 * Identifier for RTM Bullseye learning map, value should not be NULL
 * @author wenbyuan
 * @constructor
 * public RBcLearningMapKey(String placementId)
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((placementId == null) ? 0 : placementId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RBcLearningMapKey other = (RBcLearningMapKey) obj;
		if (placementId == null) {
			if (other.placementId != null)
				return false;
		} else if (!placementId.equals(other.placementId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.placementId;
	}
}
