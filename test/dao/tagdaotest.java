/**
 * @Date 2016年11月13日
 *
 * @author 郭  璞
 *
 */
package dao;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import entity.Site;
import entity.Tag;

/**
 * @author 郭 璞
 *
 */
public class tagdaotest {

	@Test
	public void addtagtest() throws Exception {
		String tag_name = "教育";
		Tag tag = new Tag();
		tag.settag_name(tag_name);

		TagDAO dao = new TagDAO();
		boolean issuccess = dao.addTag(tag);
		System.out.println("Is Success: " + issuccess);
	}

	@Test
	public void updatetagtest() throws Exception {
		String old_tag_name = "教育";
		String new_tag_name = "娱乐";

		TagDAO dao = new TagDAO();
		boolean isSuccess = dao.updateTag(old_tag_name, new_tag_name);
		System.out.println("Is SUccess: " + isSuccess);
	}

	@Test
	public void deleteTagTest() throws Exception {
		String tag_name = "娱乐";
		Tag tag = new Tag();
		tag.settag_name(tag_name);
		TagDAO dao = new TagDAO();
		boolean isDeleted = dao.deleteTag(tag);
		System.out.println("Is Deleted: " + isDeleted);
	}

	@Test
	public void selectTagTest() throws Exception {
		String tag_name = "娱乐";
		TagDAO dao = new TagDAO();
		Tag tag = dao.selectTag(tag_name);
		System.out.println(tag.toString());
	}

	@Test
	public void selectAllTagsTest() throws Exception {
		TagDAO dao = new TagDAO();
		java.util.List<Tag> tags = dao.selectAllTags();
		System.out.println(tags.toString());
	}

	@Test
	public void getTagIdTest() throws Exception {
		TagDAO dao = new TagDAO();
		int tag_id = dao.getTagId("教育");
		Assert.assertEquals(2, tag_id);
	}

	@Test
	public void getSitesEachTagTest() throws Exception {
		String tag_name = "教育";
		TagDAO dao = new TagDAO();
		Map<String, Integer> map = dao.getSitesEachTag(tag_name);
		System.out.println(map.toString());
	}

	@Test
	public void getSitesByTagnameTest() throws Exception {
		String tag_name = "娱乐";
		TagDAO dao = new TagDAO();
		List<Site> sites = dao.getSitesByTagname(tag_name);
		System.out.println(sites.toString());
	}

	@Test ////////////////////////////////////////////////////// 待测有异常
	public void sitesOfTags() throws Exception {
		TagDAO dao = new TagDAO();
		List<Tag> tags = dao.selectAllTags();
		String[] tag_names = new String[tags.size()];
		for (int i = 0; i < tags.size(); i++) {
			tag_names[i] = tags.get(i).gettag_name().toString().trim();
		}

		for (int index = 0; index < tag_names.length; index++) {
			System.out.println(dao.getSitesEachTag(tag_names[index]));
		}
		/**
		 * java.lang.RuntimeException: :
		 * com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException:
		 * No operations allowed after connection closed. at
		 * dao.TagDAO.getSitesEachTag(TagDAO.java:152) at
		 * dao.tagdaotest.sitesOfTags(tagdaotest.java:97) at
		 * sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at
		 * sun.reflect.NativeMethodAccessorImpl.invoke(Unknown Source) at
		 * sun.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source) at
		 * java.lang.reflect.Method.invoke(Unknown Source) at
		 * org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(
		 * FrameworkMethod.java:50) at
		 * org.junit.internal.runners.model.ReflectiveCallable.run(
		 * ReflectiveCallable.java:12) at
		 * org.junit.runners.model.FrameworkMethod.invokeExplosively(
		 * FrameworkMethod.java:47) at
		 * org.junit.internal.runners.statements.InvokeMethod.evaluate(
		 * InvokeMethod.java:17) at
		 * org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:325) at
		 * org.junit.runners.BlockJUnit4ClassRunner.runChild(
		 * BlockJUnit4ClassRunner.java:78) at
		 * org.junit.runners.BlockJUnit4ClassRunner.runChild(
		 * BlockJUnit4ClassRunner.java:57) at
		 * org.junit.runners.ParentRunner$3.run(ParentRunner.java:290) at
		 * org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71) at
		 * org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288) at
		 * org.junit.runners.ParentRunner.access$000(ParentRunner.java:58) at
		 * org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268) at
		 * org.junit.runners.ParentRunner.run(ParentRunner.java:363) at
		 * org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(
		 * JUnit4TestReference.java:86) at
		 * org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution
		 * .java:38) at
		 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(
		 * RemoteTestRunner.java:459) at
		 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(
		 * RemoteTestRunner.java:675) at
		 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(
		 * RemoteTestRunner.java:382) at
		 * org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(
		 * RemoteTestRunner.java:192)
		 * 
		 * 
		 */
	}

}
