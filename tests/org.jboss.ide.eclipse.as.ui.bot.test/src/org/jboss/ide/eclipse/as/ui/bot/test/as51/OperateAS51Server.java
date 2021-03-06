package org.jboss.ide.eclipse.as.ui.bot.test.as51;

import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerReqType;
import org.jboss.ide.eclipse.as.reddeer.server.requirement.ServerRequirement.JBossServer;
import org.jboss.ide.eclipse.as.ui.bot.test.template.OperateServerTemplate;
import org.jboss.reddeer.requirements.server.ServerReqState;

/**
 * @see OperateServerTemplate
 * @author Lucia Jelinkova
 *
 */
@JBossServer(state=ServerReqState.STOPPED, type=ServerReqType.AS5_1)
public class OperateAS51Server extends OperateServerTemplate {
	
	@Override
	public String getWelcomePageText() {
		return "Manage this JBoss AS Instance";
	}
	
	@Override
	protected boolean ignoreExceptionInConsole(){
		return true;
	}
}
