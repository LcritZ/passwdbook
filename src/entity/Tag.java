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
public class Tag {

	private String tag_name;

	public String gettag_name() {
		return tag_name;
	}

	public void settag_name(String tag_name) {
		this.tag_name = tag_name;
	}

	@Override
	public String toString() {
		return "Tag [tag_name=" + tag_name + "]";
	}

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(String tag_name) {
		super();
		this.tag_name = tag_name;
	}

}
