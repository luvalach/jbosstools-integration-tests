package org.jboss.tools.cdi.bot.test.weld.cdi10;

import org.eclipse.reddeer.eclipse.ui.perspectives.JavaEEPerspective;
import org.eclipse.reddeer.junit.annotation.RequirementRestriction;
import org.eclipse.reddeer.junit.requirement.matcher.RequirementMatcher;
import org.eclipse.reddeer.requirements.openperspective.OpenPerspectiveRequirement.OpenPerspective;
import org.eclipse.reddeer.requirements.server.ServerRequirementState;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerRequirement.JBossServer;
import org.jboss.tools.cdi.bot.test.weld.template.WeldBuiltInContextsTemplate;
import org.jboss.tools.cdi.reddeer.matcher.ServerMatcher;

@JBossServer(state=ServerRequirementState.PRESENT, cleanup=false)
@OpenPerspective(JavaEEPerspective.class)
public class WeldBuiltInContextsTestCDI10 extends WeldBuiltInContextsTemplate{

	@RequirementRestriction
	public static RequirementMatcher getRestrictionMatcher() {
	  return new RequirementMatcher(JBossServer.class, "family", ServerMatcher.AS());
	}
	
	public WeldBuiltInContextsTestCDI10() {
		CDIVersion = "1.0";
	}

}
