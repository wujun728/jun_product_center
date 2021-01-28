package com.doroodo.base.vo.web;

import java.util.List;

public class LeftMenu{
	private String pid;
	private List<Menu> menus;

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	public LeftMenu(String pid) {
		super();
		this.pid = pid;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public class Menu{
		private String menuid;
		private String icon;
		private String menuname;
		private List<Menus> menus;
		private int sort;
		public int getSort() {
			return sort;
		}
		public void setSort(int sort) {
			this.sort = sort;
		}
		public class Menus{
			private String menuid;
			private String icon;
			private String menuname;
			private String url;
			private int sort;
			public int getSort() {
				return sort;
			}
			public void setSort(int sort) {
				this.sort = sort;
			}
			public Menus(String menuid, String icon, String menuname, String url,int sort) {
				//this.menuid = "menus_"+menuid;
				this.menuid = menuid;
				this.icon = icon;
				this.menuname = menuname;
				this.url = url;
				this.sort=sort;
			}
			public String getMenuid() {
				return menuid;
			}
			public void setMenuid(String menuid) {
				this.menuid = menuid;
			}
			public String getIcon() {
				return icon;
			}
			public void setIcon(String icon) {
				this.icon = icon;
			}
			public String getMenuname() {
				return menuname;
			}
			public void setMenuname(String menuname) {
				this.menuname = menuname;
			}
			public String getUrl() {
				return url;
			}
			public void setUrl(String url) {
				this.url = url;
			}
		}
		public Menu(String menuid, String icon, String menuname,int sort) {
			//this.menuid ="menu"+menuid;
			this.menuid =menuid;
			this.icon = icon;
			this.menuname = menuname;
			this.sort=sort;
		}
		public String getMenuid() {
			return menuid;
		}
		public void setMenuid(String menuid) {
			this.menuid = menuid;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getMenuname() {
			return menuname;
		}
		public void setMenuname(String menuname) {
			this.menuname = menuname;
		}
		public List<Menus> getMenus() {
			return menus;
		}
		public void setMenus(List<Menus> menus) {
			this.menus = menus;
		}
	}
}