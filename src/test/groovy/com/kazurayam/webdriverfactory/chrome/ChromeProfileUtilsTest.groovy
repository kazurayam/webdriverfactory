package com.kazurayam.webdriverfactory.chrome

import com.kazurayam.webdriverfactory.UserProfile

import java.nio.file.Files
import java.nio.file.Path

import static org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4.class)
class ChromeProfileUtilsTest {

	@Test
	void test_findUserDataDirectory() {
		// when:
		Path userDataDirectory= ChromeProfileUtils.getDefaultUserDataDirectory()
		// then:
		assertNotNull(userDataDirectory)
		assertTrue(Files.exists(userDataDirectory))
	}

	@Test
	void test_getUserProfiles() {
		List<ChromeUserProfile> userProfiles = ChromeProfileUtils.getChromeUserProfileList()
		assertTrue(userProfiles.size() > 0)
	}

	@Test
	void test_listAllUserProfiles() {
		String text = ChromeProfileUtils.allChromeUserProfilesAsString()
		//println text
		assertTrue( text.length() > 0)
	}

	/**
	 * This test requires you to have a custom profile 'Picaso' defined in your Google Chrome browser
	 */
	@Test
	void test_getUserProfile() {
		ChromeUserProfile userProfile = ChromeProfileUtils.findChromeUserProfile(new UserProfile('Picaso'))
		assertNotNull(userProfile)
		assertEquals(userProfile.getUserProfileName(), new UserProfile('Picaso'))
	}

	@Test
	void test_getChromeProfileNameByDirectoryName() {
		String profileName =
				ChromeProfileUtils.findUserProfileByProfileDirectoryName(
						new ProfileDirectoryName('Default'))
		assertNotNull(profileName)
		println("DirectoryName \'Default\' is associated with the Profile \'${profileName}\'")
	}
}