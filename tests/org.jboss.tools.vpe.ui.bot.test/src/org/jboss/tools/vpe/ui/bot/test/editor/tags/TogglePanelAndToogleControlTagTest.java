/*******************************************************************************
 * Copyright (c) 2007-2016 Exadel, Inc. and Red Hat, Inc.
 * Distributed under license by Red Hat, Inc. All rights reserved.
 * This program is made available under the terms of the
 * Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Exadel, Inc. and Red Hat, Inc. - initial API and implementation
 ******************************************************************************/
package org.jboss.tools.vpe.ui.bot.test.editor.tags;

import org.jboss.reddeer.common.wait.AbstractWait;
import org.jboss.reddeer.common.wait.TimePeriod;

/**
 * Tests Rich Faces togglePanel and toogleControl and toolBarGroup Tags behavior
 * 
 * @author vlado pakan
 *
 */
public class TogglePanelAndToogleControlTagTest extends AbstractTagTest {
	private static final String FACET_0_LABEL = "!-* Facet 0 Label";
	private static final String FACET_1_LABEL = "!-* Facet 1 Label";

	@Override
	protected void initTestPage() {
		initTestPage(TestPageType.XHTML,
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
						+ "<html xmlns=\"http://www.w3.org/1999/xhtml\"\n"
						+ "  xmlns:f=\"http://java.sun.com/jsf/core\"\n"
						+ "  xmlns:rich=\"http://richfaces.org/rich\"\n"
						+ "  xmlns:h=\"http://java.sun.com/jsf/html\">\n" + "  <head>\n" + "  </head>\n" + "  <body>\n"
						+ "    <f:view>\n" + "      <rich:togglePanel>\n" + "        <f:facet name=\"facet0\">\n"
						+ "          <rich:toggleControl>\n" + "            <h:outputText value=\""
						+ TogglePanelAndToogleControlTagTest.FACET_0_LABEL + "\" />\n"
						+ "          </rich:toggleControl>\n" + "        </f:facet>\n"
						+ "        <f:facet name=\"facet1\">\n" + "          <h:outputText value=\""
						+ TogglePanelAndToogleControlTagTest.FACET_1_LABEL + "\" />\n" + "        </f:facet>\n"
						+ "      </rich:togglePanel>\n" + "    </f:view>\n" + "  </body>\n" + "</html>");
	}

	@Override
	protected void verifyTag() {
		assertVisualEditorContainsNodeWithValue(getVisualEditor(), TogglePanelAndToogleControlTagTest.FACET_0_LABEL,
				getTestPageFileName());
		assertVisualEditorNotContainNodeWithValue(getVisualEditor(), TogglePanelAndToogleControlTagTest.FACET_1_LABEL,
				getTestPageFileName());
		// check tag selection
		getVisualEditor().selectDomNode(getVisualEditor().getDomNodeByTagName("DIV", 4), 0);
		AbstractWait.sleep(TimePeriod.getCustom(3));
		String selectedText = getSourceEditor().getSelectedText();
		String hasToStartWith = "<rich:togglePanel>";
		assertTrue("Selected text in Source Pane has to start with '" + hasToStartWith + "'" + "\nbut it is '"
				+ selectedText + "'", selectedText.trim().startsWith(hasToStartWith));
		String hasEndWith = "</rich:togglePanel>";
		assertTrue("Selected text in Source Pane has to end with '" + hasEndWith + "'" + "\nbut it is '" + selectedText
				+ "'", selectedText.trim().endsWith(hasEndWith));
		// check rich:toolBarGroup selection
		getVisualEditor().selectDomNode(getVisualEditor().getDomNodeByTagName("SPAN", 0), 0);
		AbstractWait.sleep(TimePeriod.getCustom(3));
		selectedText = getSourceEditor().getSelectedText();
		hasToStartWith = "<rich:toggleControl>";
		assertTrue("Selected text in Source Pane has to start with '" + hasToStartWith + "'" + "\nbut it is '"
				+ selectedText + "'", selectedText.trim().startsWith(hasToStartWith));
		hasEndWith = "</rich:toggleControl>";
		assertTrue("Selected text in Source Pane has to end with '" + hasEndWith + "'" + "\nbut it is '" + selectedText
				+ "'", selectedText.trim().endsWith(hasEndWith));
	}
}
