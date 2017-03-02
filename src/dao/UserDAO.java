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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dbhelper.DbHelper;
import dbhelper.QueryRunner;
import entity.Tag;
import entity.User;
import handlers.BeanHandler;
import handlers.BeanListHandler;
import util.EncyptUtil;

/**
 * @author 郭 璞
 * 
 *         默认一个用户的用户名是不能被更改。
 *
 */
public class UserDAO extends BaseDAO {

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	public boolean addUser(User user) {
		Connection conn = null;
		try {
			// 加密前台传过来的 user对象数据
			String cipherpassword = EncyptUtil.encode(user.getuser_password());
			// 获取到数据库连接对象
			conn = DbHelper.getConn();
			// 构造SQL语句
			String sql = "insert into java_user(user_name, user_password, user_email)" + " values('"
					+ user.getuser_name() + "', '" + cipherpassword + "', '" + user.getuser_email() + "')";
			PreparedStatement ps = conn.prepareStatement(sql);
			// 获取 数据库的返回结果, 1 说明成功差入一条记录，否则则插入失败
			int effectedrows = ps.executeUpdate();
			// 释放资源链接
			DbHelper.release(conn, ps);
			// 返回 程序处理结果
			return effectedrows == 1 ? true : false;
		} catch (Exception e) {
			// 抛出 运行时异常信息
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 根据用户名删除与该用户相关的所有的记录信息
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	public boolean deleteUser(String username) {
		Connection conn = null;
		try {

			// 获取到数据库连接对象
			conn = DbHelper.getConn();
			// 构造SQL语句
			String sql = "delete from java_user where user_name='" + username + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			// 获取 数据库的返回结果, 1 说明成功差入一条记录，否则则插入失败
			int effectedrows = ps.executeUpdate();
			// 释放资源链接
			DbHelper.release(conn, ps);
			// 返回 程序处理结果
			return effectedrows == 1 ? true : false;
		} catch (Exception e) {
			// 抛出 运行时异常信息
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 更新用户的密码和邮箱等信息
	 * 
	 * @param username
	 * @return
	 */
	public boolean updateUser(User user) {

		Connection conn = null;
		try {
			// 加密前台传过来的 user对象数据
			String cipherpassword = EncyptUtil.encode(user.getuser_password());
			// 获取到数据库连接对象
			conn = DbHelper.getConn();
			// 构造SQL语句
			String sql = "update java_user set user_password = '" + cipherpassword + "', user_email='"
					+ user.getuser_email() + "' where user_name='" + user.getuser_name() + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			// 获取 数据库的返回结果, 1 说明成功差入一条记录，否则则插入失败
			int effectedrows = ps.executeUpdate();
			// 释放资源链接
			DbHelper.release(conn, ps);
			// 返回 程序处理结果
			return effectedrows == 1 ? true : false;
		} catch (Exception e) {
			// 抛出 运行时异常信息
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 根据用户名查找其个人的详细的信息
	 * 
	 * @param user_name
	 * @return
	 */
	public User selectUser(String user_name) {
		// 声明数据库连接对象
		Connection conn = null;
		try {
			// 初始化 数据库连接对象，避免出现空指针调用异常问题
			conn = DbHelper.getConn();
			// 组装 SQL 查询语句
			String sql = "select * from java_user where user_name= ?";
			// 实例化数据库查询对象
			QueryRunner queryRunner = new QueryRunner();
			// 采用 泛型编程技术 ，从底层开始直接获取数据库行记录到对象的自动转化流程
			User user = queryRunner.query(conn, sql, new BeanHandler<User>(User.class), user_name);
			// 释放数据库链接资源
			DbHelper.release(conn);
			return user != null ? user : null;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 获取数据库user表中 所有用户的详细的信息， 原理可以参照
	 * 上面的selectUser方法，只不过这里对其进行了进一步的包装，直接转化成了包含了对象的集合来进行返回
	 * 
	 * @return
	 */
	public List<User> selectAllUsers() {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "select * from java_user";
			QueryRunner queryRunner = new QueryRunner();
			List<User> users = queryRunner.query(conn, sql, new BeanListHandler<User>(User.class));
			DbHelper.release(conn);
			return users != null ? users : null;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 根据用户名获取到 用户名在数据库中对应的id。藉此可以使用多表查询 查找任何数据
	 * 
	 * @param username
	 * @return
	 */
	public int getUserId(String username) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			String sql = "select id from java_user where user_name='" + username + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int userid = 0;
			while (rs.next()) {
				userid = (int) rs.getObject("id");
			}
			// 释放数据库链接资源
			DbHelper.release(conn, ps, rs);

			return userid != 0 ? userid : 0;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 检查 系统中是否已经存在了这个名字
	 * 
	 * @param user_name
	 *            待查询系统用户的用户名
	 * @return
	 */
	public boolean isNameExists(String user_name) {
		Connection conn = null;
		boolean isExists = false;
		try {
			conn = DbHelper.getConn();
			String sql = "select count(*) as isExisted from java_user where user_name='" + user_name + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				int countname = rs.getInt("isExisted");
				isExists = (countname == 1 ? true : false);
			}

			DbHelper.release(conn, ps, rs);
			return isExists;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 判断给定用户名密码 的用户是否合法
	 * 
	 * @param user_name
	 * @param user_password
	 * @return
	 */
	public boolean validate(String user_name, String user_password) {
		Connection conn = null;
		try {
			conn = DbHelper.getConn();
			user_password = EncyptUtil.encode(user_password);
			String sql = "select count(*) as ishere from java_user where user_name='" + user_name
					+ "' and user_password='" + user_password + "'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int islegel = 0;
			if (rs.next()) {
				islegel = rs.getInt("ishere");
			}
			DbHelper.release(conn, ps, rs);
			return islegel == 1 ? true : false;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 获取 用户名对应用户名的所有的tag对象信息
	 * 
	 * @param user_name
	 *            指定的用户名
	 * @return List集合包裹的Tag对象信息
	 */
	public List<Tag> getTagsByUsername(String user_name) {
		Connection conn = null;
		List<Tag> tags = new ArrayList<Tag>();
		try {
			conn = DbHelper.getConn();
			String sql = "select java_tag.tag_name as tagname from java_tag, java_site, java_user where java_user.user_name='"
					+ user_name
					+ "' and java_site.java_user_id= java_user.id and java_site.java_tag_tag_id=java_tag.tag_id;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				// 获取 当前用户的所有的标签tag，(再根据每一个tag求出每个tag下的site 的个数, 当然了，这会交给
				// Business层来整合，因为根据tag名称计算 site的个数为TagDAO的功能！)
				Tag tag = new Tag();
				tag.settag_name(rs.getString("tagname"));
				tags.add(tag);
			}
			DbHelper.release(conn, ps, rs);

			// 从数据库中拿到的数据中有重复，要么使用下面的代码解决重复，要么在数据库中使用 distinct来去重(推荐第二个方式)
			tags = removeRepeatElements(tags);
			return tags.size() != 0 ? tags : null;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/**
	 * 根据指定的用户名，将获取到的Tag对象中包含的tag_name 拿出来然后转换为数组，返回
	 * 
	 * @param user_name
	 *            给定的用户名
	 * @return 返回一个无重复的 字符串数组
	 */
	public String[] getStringTagnamesByUsername(String user_name) {
		List<Tag> tags = new ArrayList<Tag>();
		tags = this.getTagsByUsername(user_name);
		String[] result = new String[tags.size()];
		for (int index = 0; index < tags.size(); index++) {
			result[index] = tags.get(index).gettag_name().toString().trim();
		}

		return result;
	}

	/**
	 * 去除 标签中重复的 标签名。由于 List中盛装的是标签对象，所以需要对每一个对象单独拿出来进行判断，这一点设计的不好，增加了额外的负担。
	 * 
	 * @param list
	 * @return
	 */
	public List<Tag> removeRepeatElements(List<Tag> list) {
		try {

			Set<String> set = new HashSet<String>();
			for (Tag tag : list) {
				set.add(tag.gettag_name());
			}

			List<Tag> result = new ArrayList<Tag>();
			for (String tag_name : set) {
				Tag tag = new Tag();
				tag.settag_name(tag_name);
				result.add(tag);
			}
			return result;
		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

}
