/**
 * @Date 2016年11月13日
 *
 * @author 郭  璞
 *
 */
package dao;

import org.junit.Test;

import entity.Site;

/**
 * @author 郭 璞
 *
 */
public class sitedaotest {

	public static SiteDAO sdao = new SiteDAO();
	public static UserDAO udao = new UserDAO();
	public static TagDAO tdao = new TagDAO();

	@Test
	public void addSiteTest() throws Exception {

		Site site = new Site();
		site.setsite_name("软院教务");
		site.setSite_username("201492115");
		site.setSite_password("285514");
		site.setJava_user_id(3);
		site.setJava_tag_tag_id(2);

		boolean isAdded = sdao.addSite(site);
		System.out.println("Is Added: " + isAdded);

	}

	@Test
	public void updateSiteXXTest() throws Exception {
		// boolean isUpdated = sdao.updateSiteUsername("慕课", "GP");
		// boolean isUpdated = sdao.updateSitePassword("慕课", "231456");
		boolean isUpdated = sdao.updateSiteTagID("慕课", 2);
		System.out.println("Is Updated: " + isUpdated);
	}

	@Test
	public void selectSiteTest() throws Exception {
		String site_name = "慕课";
		Site site = sdao.selectSite(site_name);
		System.out.println(site.toString());
	}

	@Test
	public void selectAllSitesTest() throws Exception {
		java.util.List<Site> sites = sdao.selectAllSites("郭璞");
		for (Site site : sites) {
			System.out.println(site.toString());
		}
	}

}
