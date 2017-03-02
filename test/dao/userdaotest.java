/**
 * @Date 2016年11月13日
 *
 * @author 郭  璞
 *
 */
package dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import entity.Tag;
import entity.User;

/**
 * @author 郭 璞
 *
 */
public class userdaotest {

	@BeforeClass
	public static void init() {
		System.out.println("Before Class Run");
	}

	@Test
	public void addUserTest() throws Exception {
		String username = "guopu";
		String password = "123456";
		String email = "1064319632@qq.com";

		User user = new User();
		user.setuser_name(username);
		user.setuser_password(password);
		user.setuser_email(email);

		UserDAO dao = new UserDAO();
		boolean issuccess = dao.addUser(user);
		System.out.println("IS Success: " + issuccess);
	}

	@Test
	public void updateUserTest() throws Exception {
		String username = "guopu";
		String password = "654321";
		String email = "1064319632@qq.com";

		User user = new User();
		user.setuser_name(username);
		user.setuser_password(password);
		user.setuser_email(email);

		UserDAO dao = new UserDAO();
		boolean issuccess = dao.updateUser(user);
		System.out.println("IS Success: " + issuccess);
	}

	@Test
	public void deleteUserTest() throws Exception {
		String username = "guopu";

		UserDAO dao = new UserDAO();
		boolean isSuccess = dao.deleteUser(username);
		System.out.println("Is Success: " + isSuccess);
	}

	@Test
	public void selectUserTest() throws Exception {
		String user_name = "郭璞";
		UserDAO dao = new UserDAO();
		User user = dao.selectUser(user_name);
		System.out.println(user.toString());
	}

	@Test
	public void selectAllTest() throws Exception {
		UserDAO dao = new UserDAO();
		List<User> users = dao.selectAllUsers();
		System.out.println(users);
	}

	@Test
	public void getUserIdTest() throws Exception {
		UserDAO dao = new UserDAO();
		int result = dao.getUserId("guopu");
		System.out.println(result);
	}

	@Test
	public void isExists() throws Exception {
		String user_name = "郭璞";
		UserDAO dao = new UserDAO();
		boolean flag = dao.isNameExists(user_name);
		System.out.println("Is This Name existed: " + flag);
	}
	
	
	@Test
	public void validateTest() throws Exception {
		String user_name = "郭璞";
		String user_password = "123456";
		UserDAO dao = new UserDAO();
		boolean flag = dao.validate(user_name, user_password);
		System.out.println("Is legel: " + flag);
	}
	
	
	@Test
	public void getTagsByUsernameTest() throws Exception {
		String user_name = "郭璞";
		UserDAO dao = new UserDAO();
		List<Tag> tags = dao.getTagsByUsername(user_name);
		System.out.println(tags);
	}
	
	@Test
	public void removeRepeatElementsTest() throws Exception {
		ArrayList list = new ArrayList();
		list.add("1");
		list.add(2);
		list.add(2);
		list.add(2);
		list.add(3);
		System.out.println(list);
		
		UserDAO dao = new UserDAO();
		list = (ArrayList) dao.removeRepeatElements(list);
		System.out.println(list);
		
	}

}
