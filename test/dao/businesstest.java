/**
 * @Date 2016年11月13日
 *
 * @author 郭  璞
 *
 */
package dao;

import org.junit.Test;

import biz.Business;

/**
 * @author 郭  璞
 *
 */
public class businesstest {
	
	@Test
	public void getSiteBySitenameTest() throws Exception {
		String site_name = "哔哩哔哩";
		System.out.println(Business.getSiteBySitename(site_name).toString());
	}

	
	@Test
	public void getSitesByUsername() throws Exception {
		String user_name = "郭璞";
		System.out.println(Business.getAllSites(user_name).toString());
	}

	
	@Test
	public void showTagsTest() throws Exception {
		System.out.println(Business.showTags());
	}
	
	@Test
	public void showStringTagTest() throws Exception {
		System.out.println(Business.showStringTags()[0].toString());
	}
	
	@Test
	public void getSiteNumbersByTagTest() throws Exception {
		String tag_name = "教育";
		System.out.println(Business.getSiteNumbersByTag(tag_name));
	}
	
	
	@Test
	public void getSitesByTagTest() throws Exception {
		String tag_name = "教育";
		System.out.println(Business.getSitesByTag(tag_name).toString());
	}
	
	@Test
	public void showSitesByTagnameTest() throws Exception {
		String tag_name = "教育";
		System.out.println(Business.showSitesByTagname(tag_name));
	}
	
	@Test
	public void eachTagWithSitesTest() throws Exception {
		System.out.println(Business.eachTagWithSites().toString());
	}
	
}
