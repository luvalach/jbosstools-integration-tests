package org.jboss.tools.openshift.ui.bot.test.app;

import java.util.Date;

import org.jboss.tools.openshift.ui.bot.test.OpenShiftBotTest;
import org.jboss.tools.openshift.ui.bot.util.OpenShiftLabel;
import org.jboss.tools.openshift.ui.bot.util.TestProperties;
import org.jboss.tools.openshift.ui.bot.util.TestUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CreateDeletePythonApp extends OpenShiftBotTest {

	private final String PYTHON_APP_NAME = TestProperties
			.get("openshift.pythonapp.name") + new Date().getTime();
	
	@Before
	public void cleanUpProject() {
		TestUtils.cleanupGitFolder(TestProperties
				.get("openshift.pythonapp.name"));
	}
	
	@Test
	public void canCreatePythonApp() {
		createOpenShiftApplication(PYTHON_APP_NAME, OpenShiftLabel.AppType.PYTHON);
	}
	
	@After
	public void canDeletePythonApp() {
		deleteOpenShiftApplication(PYTHON_APP_NAME, OpenShiftLabel.AppType.PYTHON_TREE);
	}
}
