package tpnw2;

import org.apache.wicket.Component;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;

public interface Auth {

	String SIGNEDIN 		= "SIGNEDIN";
	String MANAGER 			= "MANAGER";
	String ADMINISTRATOR 	= "ADMINISTRATOR";
	
	Action RENDER_ACTION = new Action(Action.RENDER);
	
	static Component render(Component component, String... roles) {
		for (String role : roles) {
			MetaDataRoleAuthorizationStrategy.authorize(component, RENDER_ACTION, role);
		}		
		return component;
	}		
}
