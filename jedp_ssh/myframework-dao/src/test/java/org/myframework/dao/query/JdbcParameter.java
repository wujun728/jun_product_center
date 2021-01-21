package org.myframework.dao.query;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class JdbcParameter<K, V> implements Map<K, V> {

	public static final int DEFAULT_NUMBER = 0;

	public static final String EMPTY_STRING = "";

	private Map<K, V> map;

	/**
	 *
	 * @param map
	 */
	public JdbcParameter(Map<K, V> map) {
		this.map = map;
	}

	public JdbcParameter() {
		super();
	}

	public void setMap(Map<K, V> map) {
		this.map = map;
	}

	 /**
     *
     * @param key
     * @return
     */
    public boolean isValid(Object key) {
        return getString(key).length() > 0;
    }
    
    
	/**
	 *
	 * @param keys
	 * @return
	 */
	public Object[] removes(Object[] keys) {
		List<Object> removedKeys = new ArrayList<Object>();
		for (int i = 0; i < keys.length; i++) {
			Object key = keys[i];
			if (this.map.containsKey(key)) {
				removedKeys.add(this.map.remove(key));
			}
		}
		return removedKeys.toArray();
	}

	/**
	 *
	 * @return
	 */
	public HashMap<K, V> toHashMap() {
		return new HashMap<K, V>(this.map);
	}
	
	/**
	 *
	 * @return
	 */
	public Map<String, Object> toSimpleMap() {
		Map<String, Object> newMap = new HashMap<String, Object>( );
		Set<java.util.Map.Entry<K, V>> sEntries = this.map.entrySet();
		for (Entry<K, V> entry : sEntries) {
			K key = entry.getKey();
			V val = entry.getValue();
			newMap.put((String)key, val);
		}
		return newMap;
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public String getString(Object key) {
		return hasKey(key) ? map.get(key).toString().trim() : EMPTY_STRING;
	}

	/**
	 * @param key
	 * @return
	 */
	public boolean hasKey(Object key) {
		return map.containsKey(key);
	}


	/**
	 *
	 * @param key
	 * @return
	 */
	public void valueToNumber(K key) {
		this.put(key, (V) Long.valueOf(this.getLong(key)));
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public void valueToString(K key) {
		this.put(key, (V) this.getString(key));
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public int getInt(K key) {
		Object value = get(key);
		if (value instanceof String) {
			String stringValue = getString(key);
			return Integer.parseInt(stringValue);
		} else if (value instanceof Integer) {
			return ((Integer) value).intValue();
		} else {
			return DEFAULT_NUMBER;
		}
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public int getInt(K key,int defInt) {
		Object value = get(key);
		if (value instanceof String) {
			String stringValue = getString(key);
			return Integer.parseInt(stringValue);
		} else if (value instanceof Integer) {
			return ((Integer) value).intValue();
		} else {
			return defInt;
		}
	}

	public long getLong(K key) {
		Object value = get(key);
		if (value instanceof String) {
			String stringValue = getString(key);
			return Long.parseLong(stringValue);
		} else if (value instanceof Long) {
			return ((Long) value).longValue();
		} else if (value instanceof BigDecimal) {
			return ((BigDecimal) value).longValue();
		} else {
			return DEFAULT_NUMBER;
		}
	}

	public double getDouble(K key) {
		Object value = get(key);
		if (value instanceof String) {
			String stringValue = getString(key);
			return Double.parseDouble(stringValue);
		} else if (value instanceof Double) {
			return ((Double) value).doubleValue();
		} else {
			return DEFAULT_NUMBER;
		}
	}

	public BigDecimal getBigDecimal(K key) {
		Object value = get(key);
		if (value instanceof String) {
			String stringValue = getString(key);
			return new BigDecimal(stringValue);
		} else if (value instanceof BigDecimal) {
			return (BigDecimal) value;
		} else {
			return null;
		}
	}

	public boolean getBoolean(K key) {
		return getString(key).equals(Boolean.TRUE.toString());
	}

	/**
	 *
	 * @param key
	 * @return
	 */
	public String getDateString(K key, String pattern) {
		DateFormat dateFormat = new SimpleDateFormat(pattern);
		Object value = this.map.get(key);
		return dateFormat.format((Date) value);
	}

	public void clear() {
		this.map.clear();
	}

	public boolean containsKey(Object key) {
		return this.map.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return this.map.containsKey(value);
	}

	public Set<Map.Entry<K, V>> entrySet() {
		return this.map.entrySet();
	}

	@Override
	public boolean equals(Object obj) {
		return this.map.equals(obj);
	}

	public V get(Object key) {
		return this.map.get(key);
	}

	@Override
	public int hashCode() {
		return this.map.hashCode();
	}

	public boolean isEmpty() {
		return this.map.isEmpty();
	}

	public Set<K> keySet() {
		return this.map.keySet();
	}

	public V put(K key, V value) {
		return this.map.put(key, value);
	}

	public void putAll(Map<? extends K, ? extends V> t) {
		this.map.putAll(t);
	}

	public V remove(Object key) {
		return this.map.remove(key);
	}

	public int size() {
		return this.map.size();
	}

	@Override
	public String toString() {
		return this.map.toString();
	}

	public Collection<V> values() {
		return this.map.values();
	}

}
