/*******************************************************************************
 * Copyright (c) 2016 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/

package org.jboss.tools.docker.ui.bot.test.container;

import java.io.IOException;

import org.jboss.ide.eclipse.as.reddeer.server.deploy.DeployOnServer;
import org.jboss.reddeer.common.wait.WaitWhile;
import org.jboss.reddeer.core.condition.JobIsRunning;
import org.jboss.reddeer.eclipse.condition.ConsoleHasNoChange;
import org.jboss.reddeer.eclipse.ui.browser.BrowserView;
import org.jboss.tools.docker.reddeer.core.ui.wizards.ImageRunSelectionPage;
import org.jboss.tools.docker.reddeer.ui.DockerImagesTab;
import org.jboss.tools.docker.ui.bot.test.AbstractDockerBotTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author jkopriva
 *
 */

public class ExposePortTest extends AbstractDockerBotTest {

	private String imageName = "jboss/wildfly";
	private String imageTag = "10.0.0.Final";
	private String containerName = "test_run_wildfly";

	@Before
	public void before() {
		openDockerPerspective();
		createConnection();
	}

	@Test
	public void testExposePort() throws IOException {
		pullImage(imageName, imageTag);
		DockerImagesTab imageTab = new DockerImagesTab();
		imageTab.activate();
		imageTab.refresh();
		new WaitWhile(new JobIsRunning());
		imageTab.runImage(imageName + ":" + imageTag);
		ImageRunSelectionPage firstPage = new ImageRunSelectionPage();
		firstPage.setName(containerName);
		firstPage.setPublishAllExposedPorts(false);
		firstPage.finish();
		new WaitWhile(new JobIsRunning());
		new WaitWhile(new ConsoleHasNoChange());
		BrowserView browserView = new BrowserView();
		browserView.open();
		String url = createURL(":8080");
		DeployOnServer.checkBrowserForErrorPage(browserView, url);
	}

	@After
	public void after() {
		deleteContainer(containerName);
		deleteImage(imageName, imageTag);
		deleteConnection();
	}

}
