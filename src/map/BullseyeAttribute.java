/**
 * 
 */
package map;

/**
 * 
 * Bullseye Attribute:
 * Two attributes are regarded as equals as long as their ids are identical.
 * 
 * @constructor 
 * public BullseyeAttribute(int id, String name, int bucketId)
 * 
 * @author wenbyuan
 * 
 */
public class BullseyeAttribute {

	private int id;
	private String name;
	private int bucketId;
	
	public BullseyeAttribute(int id, String name, int bucketId) {
		this.id = id;
		this.name = name;
		this.bucketId = bucketId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBucketId() {
		return bucketId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		BullseyeAttribute other = (BullseyeAttribute) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void setBucketId(int bucketId) {
		this.bucketId = bucketId;
	}


	
	@Override
	public String toString() {
		return "" + this.id;
	}
}
