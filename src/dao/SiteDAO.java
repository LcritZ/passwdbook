/**
 * @Date 2016年11月12日
 *
 * @author 郭  璞
 *
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import dbhelper.DbHelper;
import dbhelper.QueryRunner;
import entity.Site;
import handlers.BeanHandler;
import handlers.BeanListHandler;
import util.EncyptUtil;

/**
 * @author 郭 璞
 *
 */
public class SiteDAO extends BaseDAO {

	public boolean addSite(Site site) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String cipherpassword = EncyptUtil.encode(site.getSite_password());
			String sql = "insert into java_site(site_name, site_username, site_password, java_user_id, java_tag_tag_id)"
					+ "values('" + site.getsite_name() + "', '" + site.getSite_username() + "', '" + cipherpassword
					+ "'," + site.getJava_user_id() + " , " + site.getJava_tag_tag_id() + ")";
			PreparedStatement ps = conn.prepareCall(sql);
			int isAdded = ps.executeUpdate();
			DbHelper.release(conn, ps);
			return isAdded == 1 ? true : false;

		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 未测试的 删除site条目
	 * 
	 * @param sitename
	 * @return
	 */
	public boolean deleteSite(String sitename) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "delete from java_site where site_name = '" + sitename + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			int isDeleted = ps.executeUpdate();
			DbHelper.release(conn, ps);
			return isDeleted == 1 ? true : false;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 更新 网站对应的用户名
	 * 
	 * @param site_name
	 *            网站名称
	 * @param new_site_username
	 *            新的用户名
	 * @return
	 */
	public boolean updateSiteUsername(String site_name, String new_site_username) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "update java_site set site_username=? where site_name=?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = { new_site_username, site_name };
			// 在完成更新操作后，底层会自动的断开与数据库的链接
			queryRunner.update(conn, sql, params);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}

	}

	/**
	 * 更新网站对应的密码
	 * 
	 * @param site_name
	 *            网站名称
	 * @param new_site_password
	 *            新的密码
	 * @return
	 */
	public boolean updateSitePassword(String site_name, String new_site_password) {
		Connection conn = null;
		String cipherpassword = EncyptUtil.encode(new_site_password);
		try {
			conn = DbHelper.getConn();
			String sql = "update java_site set site_password=? where site_name=?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = { cipherpassword, site_name };
			// 在完成更新操作后，底层会自动的断开与数据库的链接
			queryRunner.update(conn, sql, params);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}

	}

	/**
	 * 更新 网站对应的标签信息
	 * 
	 * @param site_name
	 *            网站名称
	 * @param new_java_tag_tag_id
	 *            新的标签信息
	 * @return
	 */
	public boolean updateSiteTagID(String site_name, Integer new_java_tag_tag_id) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "update java_site set java_tag_tag_id=? where site_name=?";
			QueryRunner queryRunner = new QueryRunner();
			Object[] params = { new_java_tag_tag_id, site_name };
			// 在完成更新操作后，底层会自动的断开与数据库的链接
			queryRunner.update(conn, sql, params);
			return true;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}

	}

	/**
	 * 根据 site_name 获取到 该site项对应的对象的详细的信息
	 * 
	 * @param site_name
	 *            给定的site_name名称
	 * @return 完整的site对象的详细信息
	 */
	public Site selectSite(String site_name) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "select * from java_site where site_name = ?";
			QueryRunner queryRunner = new QueryRunner();
			Site site = queryRunner.query(conn, sql, new BeanHandler<Site>(Site.class), site_name);
			DbHelper.release(conn);

			return site != null ? site : null;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	public List<Site> selectAllSites(String user_name) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "select * from java_site where java_site.java_user_id=(select java_user.id from java_user where java_user.user_name='"
					+ user_name + "')";
			QueryRunner queryRunner = new QueryRunner();
			//////////////////////////////////////////////////
			////// 这里感觉还是有点问题，使用不定参数来进行处理的话，会导致赋值不成功！！！！！！
			//////////////////////////////////////////////////
			List<Site> sites = queryRunner.query(conn, sql, new BeanListHandler<Site>(Site.class));
			DbHelper.release(conn);

			return sites != null ? sites : null;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

}
