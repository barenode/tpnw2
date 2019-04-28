package tpnw2;

import org.apache.wicket.Component;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.metadata.MetaDataRoleAuthorizationStrategy;

import tpnw2.domain.Employee;
import tpnw2.domain.Office;

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
	
	static boolean isAdministrator() {
		return hasRole(Auth.ADMINISTRATOR);
	}
	
	static boolean isManager() {
		return hasRole(Auth.MANAGER);
	}
	
	static boolean isDriver() {
		return !hasRole(Auth.MANAGER) && !hasRole(Auth.ADMINISTRATOR);
	}
	
	static boolean hasRole(String role) {
		return session().getRoles().hasRole(role);
	}
	
	static SecuredSession session() {
		return (SecuredSession)Session.get();
	}
	
	static Employee logged() {
		return session().getEmployee();
	}
	
	static Office office() {
		return session().getOffice();
	}
}
