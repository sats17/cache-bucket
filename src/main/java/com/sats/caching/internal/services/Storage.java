package com.sats.caching.internal.services;

/**
 * @version 1.0.0
 * @author sats17
 *
 */
class Storage {

	/**
	 * Timestamp set in milliseconds when Storage creates
	 */
	private long createdTimeStamp = System.currentTimeMillis();

	/**
	 * Cache value
	 */
	private Object value;

	/**
	 * This method returns cache created time
	 * @return createdTimeStamp
	 */
	public long getCreatedTimeStamp() {
		return createdTimeStamp;
	}

	/**
	 * This method cache created timestamp.
	 * @param createdTimeStamp
	 * @return void
	 */
	public void setCreatedTimeStamp(long createdTimeStamp) {
		this.createdTimeStamp = createdTimeStamp;
	}

	/**
	 * Constructor that stores cache value.
	 * @param value
	 */
	public Storage(Object value) {
		this.value = value;
	}

	/**
	 * This method return cache value
	 * @return value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * This method set cache value.
	 * @param value
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Storage hashcode
	 * @return int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (createdTimeStamp ^ (createdTimeStamp >>> 32));
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * Storage equals
	 * @param obj
	 * @return boolean
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Storage other = (Storage) obj;
		if (createdTimeStamp != other.createdTimeStamp)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/**
	 * Storage toString.
	 * @return String
	 */
	@Override
	public String toString() {
		return "Storage [createdTimeStamp=" + createdTimeStamp + ", value=" + value + "]";
	}

}
