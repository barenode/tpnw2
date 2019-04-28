package tpnw2;

import java.util.List;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;
import org.apache.wicket.spring.injection.annot.SpringBean;

import tpnw2.domain.Employee;
import tpnw2.domain.Office;
import tpnw2.persistence.EmployeeDao;
import tpnw2.persistence.OfficeDao;

@SuppressWarnings("serial")
public class SecuredSession extends AuthenticatedWebSession {
    
	private static final Roles UNSIGNED = new Roles();
	
	@SpringBean private EmployeeDao employeeDao;
	@SpringBean private OfficeDao officeDao;	
	
    private Employee employee;
    private Roles roles;
    private Office office;
    
    public SecuredSession(Request request) {
        super(request);
    }
    
    @Override
    public boolean authenticate(String email, String password) {   
        employee = employeeDao.findByEmail(email);
        if (employee!=null) {
        	if (employee.getPassword().equals(password)) {
        		roles = new Roles();    
        		roles.add(Auth.SIGNEDIN);
                 if (employee.isAdministrator()) {
                	 roles.add(Auth.ADMINISTRATOR);
                }
                List<Office> managedOffices = officeDao.findByManager(employee.getId());
                if (!managedOffices.isEmpty()) {
                	roles.add(Auth.MANAGER);
                	office = managedOffices.get(0);
                } else {
                	office = employee.getOffice();
                }                
        		return true;
        	}
        }          
        return false;
    }
    
    @Override
    public Roles getRoles() {
        if(isSignedIn()){
        	return roles;
        } else {
        	return UNSIGNED;
        }        
    }
    
    @Override
    public void signOut() {
        super.signOut();
        employee = null;
        roles = null;
        office = null;
    }

	public Employee getEmployee() {
		return employee;
	}

	public Office getOffice() {
		return office;
	} 		
}
