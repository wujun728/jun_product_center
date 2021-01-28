package sy.model.base;

/**
 * sessionInfo模型，只要登录成功，就需要设置到session里面，便于系统使用
 * 
 * @author Wujun
 * 
 */
public class SessionInfo implements java.io.Serializable {

	private Syuser user;

	public Syuser getUser() {
		return user;
	}

	public void setUser(Syuser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return user.getLoginname();
	}
	
	public String getUserId() {
		return getUser().getId();
	}

}
