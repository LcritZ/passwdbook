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
public class Site {
	private String site_name;
	private String site_username;
	private String site_password;
	private Integer java_user_id;
	private Integer java_tag_tag_id;

	public String getsite_name() {
		return site_name;
	}

	public void setsite_name(String site_name) {
		this.site_name = site_name;
	}

	public String getSite_username() {
		return site_username;
	}

	public void setSite_username(String site_username) {
		this.site_username = site_username;
	}

	public String getSite_password() {
		return site_password;
	}

	public void setSite_password(String site_password) {
		this.site_password = site_password;
	}

	public Site() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getJava_user_id() {
		return java_user_id;
	}

	public void setJava_user_id(Integer java_user_id) {
		this.java_user_id = java_user_id;
	}

	public Integer getJava_tag_tag_id() {
		return java_tag_tag_id;
	}

	public void setJava_tag_tag_id(Integer java_tag_tag_id) {
		this.java_tag_tag_id = java_tag_tag_id;
	}

	@Override
	public String toString() {
		return "Site [site_name=" + site_name + ", site_username=" + site_username + ", site_password=" + site_password
				+ ", java_user_id=" + java_user_id + ", java_tag_tag_id=" + java_tag_tag_id + "]";
	}

}
