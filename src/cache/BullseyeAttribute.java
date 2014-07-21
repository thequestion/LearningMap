/**
 * 
 */
package cache;

/**
 * @author wenbyuan
 *
 */
/**
 * 
 * Bullseye Attribute:
 * Two attributes are regarded as equals as long as their names are identical.
 * 
 * @constructor 
 * public BullseyeAttribute(String name)
 * 
 * @author wenbyuan
 * 
 */
public class BullseyeAttribute {

	private String name;
	
	public BullseyeAttribute(String name) {
		this.name = name;
	}
	public BullseyeAttribute(int id, String name, int bucketId) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + name.hashCode();
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
		if( this.name == null ){
			if(other.name != null)
				return false;
		}
		if (this.name.compareTo(other.name) != 0)
			return false;
		return true;
	}



	
	@Override
	public String toString() {
		return "" + this.name;
	}
}

