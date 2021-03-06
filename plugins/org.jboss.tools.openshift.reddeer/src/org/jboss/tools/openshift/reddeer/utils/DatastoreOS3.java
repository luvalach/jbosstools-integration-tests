/*******************************************************************************
 * Copyright (c) 2007-2016 Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v 1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributor:
 *     Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.openshift.reddeer.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.commons.lang.StringUtils;
import org.jboss.tools.openshift.reddeer.view.OpenShiftExplorerView.AuthenticationMethod;

/**
 * Storage for OpenShift v3 credentials.
 * 
 * @author mlabuda@redhat.com
 * @author adietish@redhat.com
 *
 */
public class DatastoreOS3 {
	
	private static final String KEY_SERVER = "openshift.server";
	public static final String KEY_AUTHMETHOD = "openshift.authmethod";
	private static final String KEY_TOKEN = "openshift.token";
	public static final String KEY_PASSWORD = "openshift.password";
	
	static {
		assertTrue("Please add '-D" + KEY_SERVER + "=[host]' to your launch arguments", StringUtils.isNotBlank(System.getProperty(KEY_SERVER)));
		AuthenticationMethod authMethod = AuthenticationMethod.safeValueOf(System.getProperty(KEY_AUTHMETHOD));
		assertNotNull("Please add '-D" + KEY_AUTHMETHOD + "=[basic|oauth]' to your launch arguments", authMethod);
		if (AuthenticationMethod.BASIC.equals(authMethod)) { 
			assertTrue("Please add '-D" + KEY_PASSWORD + "=[password]'", 
					StringUtils.isNotBlank(System.getProperty(KEY_PASSWORD)));
		} else if (AuthenticationMethod.OAUTH.equals(authMethod)) {
			assertTrue("Please add '-D" + KEY_TOKEN + "=[token]' to your launch arguments", 
					StringUtils.isNotBlank(System.getProperty(KEY_TOKEN)));
		}
	}

	// used for basic authentization
	public static String SERVER = System.getProperty(KEY_SERVER);
	public static String USERNAME = System.getProperty("openshift.username");
	public static String PASSWORD = System.getProperty(KEY_PASSWORD);
	public static String TOKEN = System.getProperty(KEY_TOKEN);
	public static String PUBLIC_OS3_SERVER = "https://console.preview.openshift.com";
	public static AuthenticationMethod AUTH_METHOD = AuthenticationMethod.valueOfIgnoreCase(System.getProperty(KEY_AUTHMETHOD));
	
	// github credentials
	public static final String GIT_USERNAME = System.getProperty("github.username", "openshift-tools-testing-account");
	public static final String GIT_PASSWORD = System.getProperty("github.password");
	
	public static String PROJECT1 = "project-name" + System.currentTimeMillis();
	public static String PROJECT1_DISPLAYED_NAME = "displayedName-" + System.currentTimeMillis();
	public static final String PROJECT2 = "project-name02";
	public static final String TEST_PROJECT = "test-project";

	
	public static String TEMPLATE_PATH = new File("").getAbsolutePath() + File.separator + 
			"resources" + File.separator + "eap64-basic-s2i.json";
	
	/**
	 * Generates a new project name for DatastoreOS3.PROJECT1 variable.
	 */
	public static void generateProjectName() {
		long seed = System.currentTimeMillis();
		PROJECT1 = "generated-name" + seed;
		PROJECT1_DISPLAYED_NAME = "displayedName-" + seed;
	}

	public static String getValueOrDefault(String value, String defaultValue) {
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		return value;
	}

}
