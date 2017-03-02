/**
 * @Date 2016年11月12日
 *
 * @author 郭  璞
 *
 */
package entity;

/**
 * @author 郭 璞
 *
 */
public class User {
	private String user_name;
	private String user_password;
	private String user_email;

	public String getuser_name() {
		return user_name;
	}

	public void setuser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getuser_password() {
		return user_password;
	}

	public void setuser_password(String user_password) {
		this.user_password = user_password;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [user_name=" + user_name + ", user_password=" + user_password + ", user_email=" + user_email + "]";
	}

	public String getuser_email() {
		return user_email;
	}

	public void setuser_email(String user_email) {
		this.user_email = user_email;
	}

}
