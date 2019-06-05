package org.jresearch.tekies.resources.api;

import org.jresearch.tekies.test.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;


public class CustomerDaoTest extends BaseTest {

	@Autowired
	private CustomerDao dao;

//	@Test
//	public void testTutorial() {
//		assertEquals(0, dao.count());
//		Tutorial tutorial = dao.save(new Tutorial(null, "name", "videoId",null)); //$NON-NLS-1$ //$NON-NLS-2$
//		assertNotNull(tutorial.getId());
//		assertEquals(1, dao.count());
//		dao.delete(tutorial);
//		assertEquals(0, dao.count());
//	}
//
//	@Test
//	public void testTutorialFragment() {
//		assertEquals(0, dao.count());
//		Tutorial tutorial = dao.save(new Tutorial(null, "name", "videoId",null)); //$NON-NLS-1$ //$NON-NLS-2$
//		assertNotNull(tutorial.getId());
//		assertEquals(1, dao.count());
//		List<TutorialFragment> tutorialFragments = tutorial.getTutorialFragments();
//		assertNotNull(tutorialFragments);
//		assertEquals(0, tutorialFragments.size());
//		tutorialFragments.add(new TutorialFragment("name", 0, 1)); //$NON-NLS-1$
//
//		dao.saveAndFlush(tutorial);
//
//		Tutorial newOne = dao.findOne(tutorial.getId());
//		List<TutorialFragment> fragments = newOne.getTutorialFragments();
//		assertNotNull(fragments);
//		assertEquals(1, fragments.size());
//		assertEquals("name", fragments.get(0).getName()); //$NON-NLS-1$
//
//		dao.delete(tutorial);
//		assertEquals(0, dao.count());
//	}

}
