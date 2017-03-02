/**
 * @Date 2016年11月12日
 *
 * @author 郭  璞
 *
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dbhelper.DbHelper;
import dbhelper.QueryRunner;
import entity.Site;
import entity.Tag;
import handlers.BeanHandler;
import handlers.BeanListHandler;

/**
 * @author 郭 璞
 *
 */
public class TagDAO extends BaseDAO {

	public boolean addTag(Tag tag) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "insert into java_tag(tag_name) values('" + tag.gettag_name() + "')";
			PreparedStatement ps = conn.prepareStatement(sql);
			int effectedrows = ps.executeUpdate();
			DbHelper.release(conn, ps);
			return effectedrows == 1 ? true : false;

		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	public boolean deleteTag(Tag tag) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "delete from java_tag where tag_name='" + tag.gettag_name() + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			int effectedrows = ps.executeUpdate();
			DbHelper.release(conn, ps);
			return effectedrows == 1 ? true : false;

		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	public boolean updateTag(String old_tag_name, String new_tag_name) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "update java_tag set tag_name='" + new_tag_name + "' where tag_name='" + old_tag_name + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			int effectedrows = ps.executeUpdate();
			DbHelper.release(conn, ps);
			return effectedrows == 1 ? true : false;

		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	public Tag selectTag(String tag_name) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "select * from java_tag where tag_name=?";
			QueryRunner queryRunner = new QueryRunner();
			Tag tag = queryRunner.query(conn, sql, new BeanHandler<Tag>(Tag.class), tag_name);

			DbHelper.release(conn);

			return tag != null ? tag : null;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	public List<Tag> selectAllTags() {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "select * from java_tag";
			QueryRunner queryRunner = new QueryRunner();
			List<Tag> tags = queryRunner.query(conn, sql, new BeanListHandler<Tag>(Tag.class));

			DbHelper.release(conn);

			return tags != null ? tags : null;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 根据tag_name获取 tag在数据库中对应的id， 为多表联结查询打下基础。
	 * 
	 * @param tag_name
	 * @return
	 */
	public int getTagId(String tag_name) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "select tag_id from java_tag where tag_name='" + tag_name + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int tag_id = 0;
			while (rs.next()) {
				tag_id = (int) rs.getObject("tag_id");
			}
			DbHelper.release(conn, ps, rs);

			return tag_id != 0 ? tag_id : 0;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 根据指定的标签 tag_name ，查询数据库中对应的标签下的Site的个数
	 * 
	 * @param tag_name
	 *            标签名
	 * @return 标签名对应的site的个数
	 */
	public Map<String, Integer> getSitesEachTag(String tag_name) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			Map<String, Integer> tag_site_counts = new HashMap<String, Integer>();
			boolean tagHasSites = false;

			String sql = "select count(java_site.site_id) as counts from java_site where java_site.java_tag_tag_id=(select java_tag.tag_id from java_tag where java_tag.tag_name='"
					+ tag_name + "')";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int numbers = rs.getInt("counts");
				tag_site_counts.put(tag_name, numbers);
				tagHasSites = true;
			}

			DbHelper.release(conn, ps);

			return tagHasSites ? tag_site_counts : null;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}

	}

	/**
	 * 根据 指定的标签名称，获取到 该标签下所有的site对象的详细信息
	 * 
	 * @param tag_name
	 *            指定的标签的标签名
	 * @return 对应于该标签名下的所有的site对象的详细信息
	 */
	public List<Site> getSitesByTagname(String tag_name) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "select * from java_site where java_site.java_tag_tag_id=(select java_tag.tag_id from java_tag where java_tag.tag_name='"+tag_name+"')";
			QueryRunner queryRunner = new QueryRunner();
			List<Site> sites = queryRunner.query(conn, sql, new BeanListHandler<Site>(Site.class));

			DbHelper.release(conn);
			return sites.size() != 0 ? sites : null;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}

	}
}
