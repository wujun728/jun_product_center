package com.shuogesha.cms.entity;

import java.io.Serializable;

public class Channel implements Serializable {
	
	






		return this.id;
	}

		this.id=id;
	}

		return this.name;
	}

		this.name=name;
	}

	public Channel getParent() {
		return parent;
	}

	public void setParent(Channel parent) {
		this.parent = parent;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	} 
	
}