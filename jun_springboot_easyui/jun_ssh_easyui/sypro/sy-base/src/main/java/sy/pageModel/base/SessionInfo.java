package sy.pageModel.base;

import java.io.Serializable;
import java.util.List;

/**
 * 为了统一管理session中，用户自定义变量，所以定义这个类，将所有用户要存放在session中的信息，全都存放在这里，便于管理
 * 
 * @author 孙宇
 *
 */
public class SessionInfo implements Serializable {

	private List<String> permissionUrls = null;// 这个列表记录用户可以访问的url集合

	public List<String> getPermissionUrls() {
		return permissionUrls;
	}

	public void setPermissionUrls(List<String> permissionUrls) {
		this.permissionUrls = permissionUrls;
	}

}
