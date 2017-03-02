/**
 * @Date 2016年11月13日
 *
 * @author 郭  璞
 *
 */
package dao;

import dbhelper.DbHelper;

/**
 * @author 郭 璞
 *
 */
public class BaseDAO {

	/**
	 * 使用静态代码块的方式，在程序的DAO层运行之前就注册好 DbHelper ，做好对数据源的注册
	 */
	static {
		try {
			DbHelper.register();
		} catch (Exception e) {
			throw new RuntimeException(e + "\n 数据源未注册成功");
		}
	}

}
