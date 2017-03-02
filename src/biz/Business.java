/**
 * @Date 2016年11月13日
 *
 * @author 郭  璞
 *
 */
package biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.SiteDAO;
import dao.TagDAO;
import dao.UserDAO;
import entity.Site;
import entity.Tag;
import entity.User;
import util.EncyptUtil;

/**
 * @author 郭 璞
 *
 */
public class Business {
	
	

	/**
	 * 参与 用户相关数据操作的DAO对象实例
	 */
	public static final UserDAO udao = new UserDAO();

	/**
	 * 相关于标签数据处理的DAO对象实例
	 */
	public static final TagDAO tdao = new TagDAO();

	/**
	 * 相关于Site对象的一系列操作的数据DAO对象
	 */
	public static final SiteDAO sdao = new SiteDAO();

	
	/**
	 * 业务解码方法
	 * @param cipherdata
	 */
	public static String decode(String cipherdata) {
		return EncyptUtil.decode(cipherdata);
	}
	
	
	/**
	 * 处理用户登录 事件， 全部是明文即可，无需做过多的处理，后台会自动的完成加密校验过程。对用户透明
	 * 
	 * @param user_name
	 *            用户输入的用户名
	 * @param user_password
	 *            用户输入的密码
	 * @return 返回true表示为合法用户，false则表示用户名或者密码有错误。
	 */
	public static boolean login(String user_name, String user_password) {
		if (user_name != null && user_password != null) {
			return udao.validate(user_name, user_password);
		}
		return false;
	}

	/**
	 * 提供用户注册请求，默认会先检查所输入的用户名 是否已经存在。
	 * 
	 * @param user
	 *            前台组装好的用户对象， 密码无需加密处理，底层自动处理。
	 * @return 返回true表明注册的用户名合法，返回false则表明此用户名已经存在，应该换个名字继续。
	 */
	public static boolean register(User user) {
		boolean isNameExists = udao.isNameExists(user.getuser_name());
		if (user != null && isNameExists) {
			return udao.addUser(user);
		}
		return false;
	}

	/**
	 * 检测输入的用户名是否已经存在
	 * 
	 * @param user_name
	 *            输入的用户名
	 * @return 返回true表示发现同名用户，不可以使用； 返回false则代表此用户名可以被正常的使用。
	 */
	public static boolean isUsernameExists(String user_name) {
		if (user_name != null && !user_name.equals("")) {
			return udao.isNameExists(user_name);
		}
		return false;
	}

	/**
	 * 修改 登陆用户密码
	 * 
	 * @param user
	 *            前台封装好的User对象实例
	 * @return 返回true表示成功修改， 返回false则代表出错了。
	 */
	public static boolean modifyUserPassword(User user) {
		if (user != null) {
			udao.updateUser(user);
		}
		return false;
	}

	/**
	 * 获取 指定用户在数据库中的ID信息，此方法，应该对外部透明，但是为了更好的拓展，可以不设置为private而暴露出来。
	 * 
	 * @param user_name
	 *            指定的用户名。
	 * @return 返回指定的用户名在数据库中实际存在的主键ID号码
	 */
	public static int getAdminId(String user_name) {
		if (user_name != null && !user_name.equals("")) {
			return udao.getUserId(user_name);
		}
		return -1;
	}

	/**
	 * 擦除指定用户名下的所有的信息，此为危险选项，慎重操作。一旦擦除，数据将无法恢复。<br>
	 * 默认会先检测用户的合法性，防止他人恶意操作。
	 * 
	 * @param user_name
	 *            用户登录名称
	 * @param user_password
	 *            登陆密码
	 * @return 返回true代表成功擦除用户数据，返回false表示擦除操作未成功执行。
	 */
	public static boolean eraseUserData(String user_name, String user_password) {
		boolean islegel = udao.validate(user_name, user_password);
		if (islegel) {
			return udao.deleteUser(user_name);
		}
		return false;
	}

	/**
	 * 添加 标签。 默认任何用户都可以添加标签，所以未加入身份验证。为用户分配标签的操作在addSite方法中进行细化控制。
	 * 
	 * @param tag_name
	 *            标签名称
	 * @return 返回true代表成功添加标签，返回false代表未能成功添加标签。
	 */
	public static boolean addTag(String tag_name) {
		Tag tag = new Tag();
		tag.settag_name(tag_name);
		return tdao.addTag(tag);
	}

	/**
	 * 删除标签， 数据库会自动的进行reference的控制
	 * 
	 * @param tag_name
	 *            标签名称
	 * @return 返回true代表成功删除无外链引用的标签，返回false代表该标签未能成功的删除，原因在于与Site有相关联系，不能直接删除。
	 */
	public static boolean deleteTag(String tag_name) {
		Tag tag = new Tag();
		tag.settag_name(tag_name);
		return tdao.deleteTag(tag);
	}

	/**
	 * 更换标签名称，用户可以进行对标签的更名操作。慎重操作，因为更改一个标签的名称会关联到其他的Site与之相关的标签的隶属。
	 * 
	 * @param old_tag_name
	 *            原来的标签名
	 * @param new_tag_name
	 *            新的标签名称。
	 * @return 返回true代表标签名被成功更改，返回false表示未能成功的更改标签名。
	 */
	public static boolean renameTag(String old_tag_name, String new_tag_name) {
		return tdao.updateTag(old_tag_name, new_tag_name);
	}

	/**
	 * 以列表包裹的Tag对象的集合形式 返回数据库内 所有用户添加过的 标签
	 * 
	 * @return 返回以列表包裹的Tag对象的集合。
	 */
	public static List<Tag> showTags() {
		return tdao.selectAllTags();
	}

	/**
	 * 以字符串数组的形式返回数据库内所有用户添加过的tag的信息，全部为字符串表示。
	 * 
	 * @return 字符串数组的形式表示的全部标签的信息
	 */
	public static String[] showStringTags() {
		List<Tag> tags = tdao.selectAllTags();
		String[] result = new String[tags.size()];
		for (int index = 0; index < tags.size(); index++) {
			result[index] = tags.get(index).gettag_name();
		}
		return result;
	}

	/**
	 * 添加Site到数据库
	 * 
	 * @param site_name
	 *            网站的名称
	 * @param site_username
	 *            网站上的登录用户名
	 * @param site_password
	 *            网站上的登陆密码
	 * @param user_name
	 *            当前登陆用户的用户名
	 * @param tag_name
	 *            把该site添加到那个标签上，如慕课隶属于教育标签。
	 * @return 返回true表示添加条目成功，返回false代表添加条目失败
	 */
	public static boolean addSite(String site_name, String site_username, String site_password, String user_name,
			String tag_name) {
		int user_id = udao.getUserId(user_name);
		int tag_id = tdao.getTagId(tag_name);
		Site site = new Site();

		site.setsite_name(site_name);
		site.setSite_username(site_username);
		site.setSite_password(site_password);
		site.setJava_user_id(user_id);
		site.setJava_tag_tag_id(tag_id);

		return sdao.addSite(site);

	}

	/**
	 * 根据给定的标签名， 获取到 该标签名下对应的详细的site对象的多有的信息
	 * 
	 * @param tag_name
	 *            指定的标签名称
	 * @return 对应于标签名的数据库中的site对象的详细的信息
	 */
	public static List<Site> showSitesByTagname(String tag_name) {
		return tdao.getSitesByTagname(tag_name);
	}

	/**
	 * 这里放松了权限，如果有必要的话，可以以后加上对当前用户有效的删除权限 <br>
	 * 删除 site对象上的全部的信息
	 * 
	 * @param site_name
	 *            网站名称
	 * @return 返回true表示删除成功，返回false代表删除操作失败。
	 */
	public static boolean deleteSite(String site_name) {
		if (site_name != null && !site_name.equals("")) {
			return sdao.deleteSite(site_name);
		}
		return false;
	}

	/**
	 * 更换网站上的登录用户名
	 * 
	 * @param site_name
	 *            网站名称
	 * @param site_username
	 *            新的网站登录的用户名
	 * @return 返回true代表更名操作成功，返回false代表更名操作失败
	 */
	public static boolean changeSiteUsername(String site_name, String site_username) {
		if (site_name != null && site_username != null && !site_name.equals("") && !site_username.equals("")) {
			return sdao.updateSiteUsername(site_name, site_username);
		}
		return false;
	}

	/**
	 * 更换网站上的登陆密码(默认当前操作用户拥有更换条目信息的权限，如果有必要可以进行权限控制模块的添加)
	 * 
	 * @param site_name
	 *            网站名称
	 * @param new_site_userpassword
	 *            新的网站的登陆密码(无需手动加密， 底层自动会进行加密操作处理。)
	 * @return 返回true代表更换密码操作成功，返回false代表更换密码操作失败。
	 */
	public static boolean changeSiteUserpassword(String site_name, String new_site_userpassword) {
		if (site_name != null && !site_name.equals("") && new_site_userpassword != null
				&& !new_site_userpassword.equals("")) {
			return sdao.updateSitePassword(site_name, new_site_userpassword);
		}
		return false;
	}

	/**
	 * 更换网站所属标签， 如腾讯视频原有标签为视频，先可以更换为娱乐。
	 * 
	 * @param site_name
	 *            网站名称
	 * @param tag_name
	 *            要进行更换的新的表亲啊的名称
	 * @return 返回true代表更换标签成功，返回false代表更换隶属标签操作失败
	 */
	public static boolean changeSiteTag(String site_name, String tag_name) {
		if (site_name != null && !site_name.equals("") && tag_name != null && !tag_name.equals("")) {
			int new_java_tag_tag_id = tdao.getTagId(tag_name);
			return sdao.updateSiteTagID(site_name, new_java_tag_tag_id);
		}
		return false;
	}

	/**
	 * 根据网站名称获取该条目的所有的信息
	 * 
	 * @param site_name
	 *            网站名称
	 * @return 返回为null代表没有此网站的条目信息； 返回非null代表该网站名下的详细的信息。
	 */
	public static Site getSiteBySitename(String site_name) {
		if (site_name != null && !site_name.equals("")) {
			return sdao.selectSite(site_name);
		}
		return null;
	}

	/**
	 * 获取当前管理员的添加过的所有的网站site对象的详细的信息。 默认不加权限控制，如有需要进行添加即可。
	 * 
	 * @param user_name
	 *            管理员的名称。
	 * @return 返回指定管理员添加过的所有的标签，以及网站site的详细的信息。
	 */
	public static List<Site> getAllSites(String user_name) {
		return sdao.selectAllSites(user_name);
	}

	/**
	 * 获取 标签名 下对应的site对象的个数
	 * 
	 * @param tag_name
	 *            给定的标签名
	 * @return 返回 标签名下对应的site的对象的个数
	 */
	public static int getSiteNumbersByTag(String tag_name) {
		return tdao.getSitesEachTag(tag_name).get(tag_name);
	}

	/**
	 * 根据指定的标签名称获取 隶属于该标签的site对象的全部信息
	 * 
	 * @param tag_name
	 *            指定的标签名
	 * @return 隶属于标签名参数的site列表的详细的信息
	 */
	public static List<Site> getSitesByTag(String tag_name) {
		return tdao.getSitesByTagname(tag_name);
	}

	/**
	 * 获取所有标签下的对应的site的对象的详细信息 <br>
	 * 然而 貌似结果不能正常的获取
	 * 
	 * @return
	 */
	@Deprecated
	public static List<Map<String, List<Site>>> eachTagWithSites() {
		List<Map<String, List<Site>>> result = new ArrayList<Map<String, List<Site>>>();
		try {
			String[] tag_names = showStringTags();
			for (String tag_name : tag_names) {
				List<Site> sites = getSitesByTag(tag_name);
				Map<String, List<Site>> map = new HashMap<String, List<Site>>();
				map.put(tag_name, sites);
				result.add(map);
			}
			return result;

		} catch (Exception e) {
			throw new RuntimeException(" :\n" + e);
		}
	}

	/////////////////////////////////////////////////////////////////////////////////// 数据库连接提前释放了
	/*
	 * public static List<Map<String, Integer>> eachTagSiteNumbers(String
	 * user_name) { String[] tag_names =
	 * udao.getStringTagnamesByUsername(user_name);
	 * 
	 * for (String tag_name : tag_names) { System.out.println(tag_name); }
	 * 
	 * List<Map<String, Integer>> result = new ArrayList<Map<String,
	 * Integer>>(); for (String tag_name : tag_names) { Map<String, Integer>
	 * eachTagNumbers = tdao.getSitesEachTag(tag_name);
	 * result.add(eachTagNumbers); } System.out.println(result.toString());
	 * 
	 * return result;
	 * 
	 * }
	 */

}
