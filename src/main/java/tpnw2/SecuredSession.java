package tpnw2;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.request.Request;

@SuppressWarnings("serial")
public class SecuredSession extends AuthenticatedWebSession {
    
    private String username;
    
    public SecuredSession(Request request) {
        super(request);
    }
    
    @Override
    public boolean authenticate(String username, String password) {
        System.out.println("username: " + username + ", password: " + password); 
    	//user is authenticated if both username and password are equal to 'test'
          this.username = username;
          
          if(password.equals("driver"))
              return true;
          else if(username.equals("manager"))
              return true;
          else if(username.equals("admin"))
              return true;
          
          return false;
    }
    
    @Override
    public Roles getRoles() {
        Roles resultRoles = new Roles();
    
        //if user is signed in add the relative role
        if(isSignedIn()){
            resultRoles.add(Auth.SIGNEDIN);
        }
     
        //if username is equal to 'superuser' add the ADMIN role
        if(username!= null && username.equals("admin")){
            resultRoles.add(Auth.ADMINISTRATOR);
        }
        if(username!= null && username.equals("manager")){
            resultRoles.add(Auth.MANAGER);
        }
        return resultRoles;
    }
    
    @Override
    public void signOut() {
        super.signOut();
        username = null;
    }
}
