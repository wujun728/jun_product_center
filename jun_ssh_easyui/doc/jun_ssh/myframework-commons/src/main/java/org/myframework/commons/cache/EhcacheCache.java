package org.myframework.commons.cache;

import java.io.IOException;
import java.io.InputStream;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;


/**
 * Cache adapter for Ehcache.
 *
 * @version $Id: EhcacheCache.java
 */
//@Service("ehcacheCache")
public final class EhcacheCache implements ICacheService {

	/**
	 * The cache manager reference.
	 */
	private static final CacheManager CACHE_MANAGER = createCacheManager();

	/**
	 * Looks for "/ehcache.xml" classpath resource and builds the relative
	 * {@code CacheManager}; if it's no found or it is impossible to load it,
	 * returns the default manager.
	 *
	 * @return the application cache manager.
	 */
	private static CacheManager createCacheManager() {
		CacheManager cacheManager;
		InputStream input = EhcacheCache.class.getResourceAsStream("/ehcache.xml");

		if (input != null) {
			try {
				cacheManager = CacheManager.create(input);
			} catch (Throwable t) {
				cacheManager = CacheManager.create();
			} finally {
				try {
					input.close();
				} catch (IOException e) {
				}
			}
		} else {
			cacheManager = CacheManager.create();
		}

		return cacheManager;
	}

	/**
	 * The cache id.
	 */
	private final String id;

	public EhcacheCache(){
		this.id = this.getClass().getName();
		if (!CACHE_MANAGER.cacheExists(this.id)) {
			CACHE_MANAGER.addCache(this.id);
		}
	}

	/**
	 *
	 *
	 * @param id
	 */
	public EhcacheCache( String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
		if (!CACHE_MANAGER.cacheExists(this.id)) {
			CACHE_MANAGER.addCache(this.id);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	public void clear() {
		this.getCache().removeAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getObject(Object key) {
		try {
			Element cachedElement = this.getCache().get(key);
			if (cachedElement == null) {
				return null;
			}
			return cachedElement.getObjectValue();
		} catch (Exception t) {
			throw new CacheException(t.getMessage(),t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int getSize() {
		try {
			return this.getCache().getSize();
		} catch (Throwable t) {
			throw new CacheException(t.getMessage(),t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void putObject(Object key, Object value) {
		try {
			this.getCache().put(new Element(key, value));
		} catch (Throwable t) {
			throw new CacheException(t.getMessage(),t);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Object removeObject(Object key) {
		try {
			Object obj = this.getObject(key);
			this.getCache().remove(key);
			return obj;
		} catch (Throwable t) {
			  throw new CacheException(t.getMessage(),t);
		}
	}

	/**
	 * Returns the ehcache manager for this cache.
	 *
	 * @return the ehcache manager for this cache.
	 */
	private Ehcache getCache() {
		return CACHE_MANAGER.getCache(this.id);
	}




	/**
	 * {@inheritDoc}
	 */

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "EHCache {" + this.id + "}";
	}

	public void putObject(Object key, Object obj1, int expiry) {
		Element element = new Element(key, obj1);
		element.setTimeToLive(expiry);
		try {
			this.getCache().put(element);
		} catch (Throwable t) {
			throw new CacheException(t.getMessage(),t);
		}
	}

	@Override
	public void setExpiredTime(long time) {
		//getCache().gettgetTimeToIdleSeconds();

	}

	@Override
	public Object get(String key) {
		return this.getObject(key);
	}

	@Override
	public void put(String key, Object value) {
		this.putObject(key, value);
	}

	@Override
	public void delete(String key) {
		this.removeObject(key);
	}

	@Override
	public void shutDown() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

}
