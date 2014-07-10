/**
 * 
 */
package map;

/**
 * @author wenbyuan
 *
 */
public class BullseyeAttribute {

	private int id;
	private String name;
	private int bucketId;
	
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

	public void setBucketId(int bucketId) {
		this.bucketId = bucketId;
	}

	@Override
	public boolean equals(Object o){
		if( o == null || !(o instanceof BullseyeAttribute)) return false;
		return getId() == ((BullseyeAttribute)o).getId();
	}
}
