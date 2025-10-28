package com.jun.plugin.query;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.HibernateException;
import org.hibernate.transform.AliasedTupleSubsetResultTransformer;

public class SmartAliasToBean extends AliasedTupleSubsetResultTransformer {

	// IMPL NOTE : due to the delayed population of setters (setters cached
	// for performance), we really cannot properly define equality for
	// this transformer

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Class resultClass;
	private boolean isInitialized;
	private String[] aliases;

	public SmartAliasToBean(Class resultClass) {
		if (resultClass == null) {
			throw new IllegalArgumentException("resultClass cannot be null");
		}
		isInitialized = false;
		this.resultClass = resultClass;

	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isTransformedValueATupleElement(String[] aliases,
			int tupleLength) {
		return false;
	}

	public Object transformTuple(Object[] tuple, String[] aliases) {
		Object result = null;		
		try {
			if (!isInitialized) {
				initialize(aliases);
			}
			else {
				check(aliases);
			}
			Map<String,Object> resultMap = new HashMap<String,Object>(tuple.length);
			for ( int i=0; i<tuple.length; i++ ) {
				String alias = aliases[i];
				if ( alias!=null ) {
					resultMap.put( alias, tuple[i] );
				}
			}
			result = resultClass.newInstance();
			BeanUtils.copyProperties(result, resultMap);
		} catch (InstantiationException e) {
			throw new HibernateException("Could not instantiate resultclass: "
					+ resultClass.getName());
		} catch (IllegalAccessException e) {
			throw new HibernateException("Could not instantiate resultclass: "
					+ resultClass.getName());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	private void initialize(String[] aliases) {
		this.aliases = Arrays.copyOf(aliases, aliases.length);
		isInitialized = true;
	}

	private void check(String[] aliases) {
		if (!Arrays.equals(aliases, this.aliases)) {
			throw new IllegalStateException(
					"aliases are different from what is cached; aliases="
							+ Arrays.asList(aliases) +
							" cached=" + Arrays.asList(this.aliases));
		}
	}

	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		SmartAliasToBean that = (SmartAliasToBean) o;

		if (!resultClass.equals(that.resultClass)) {
			return false;
		}
		if (!Arrays.equals(aliases, that.aliases)) {
			return false;
		}

		return true;
	}

	public int hashCode() {
		int result = resultClass.hashCode();
		result = 31 * result + (aliases != null ? Arrays.hashCode(aliases) : 0);
		return result;
	}
}
