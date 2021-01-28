package org.myframework.ioc;

import org.springframework.beans.factory.BeanNameAware;

public class FXNewsProvider implements BeanNameAware{
	DowJonesNewsListener newsListener ;
	DowJonesNewsPersister newPersistener ;
	public void getAndPersistNews() {
		// TODO Auto-generated method stub
		System.out.println("111111111");
	}
	public FXNewsProvider() {
		super();
	}
	public FXNewsProvider(DowJonesNewsListener newsListener,
			DowJonesNewsPersister newsPersister) {
		super();
		this.newsListener = newsListener;
		this.newPersistener = newsPersister;
	}
	public DowJonesNewsListener getNewsListener() {
		return newsListener;
	}
	public void setNewsListener(DowJonesNewsListener newsListener) {
		this.newsListener = newsListener;
	}
	public DowJonesNewsPersister getNewPersistener() {
		return newPersistener;
	}
	public void setNewPersistener(DowJonesNewsPersister newPersistener) {
		this.newPersistener = newPersistener;
	}
	@Override
	public void setBeanName(String name) {
		System.out.println("beanName " + name);
		
	}
	 
	 

}
