package tpnw2.view.component;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.protocol.http.WebApplication;

@SuppressWarnings("serial")
public class Header extends Panel {

	public Header(String id) {
		super(id);
		add(new Link<String>("index") {
			@Override
            public void onClick() {
            	setResponsePage(WebApplication.get().getHomePage());
            }
		});
		add(new Link<String>("signout") {
            @Override
            public void onClick() {
            	AuthenticatedWebSession.get().invalidate();
                setResponsePage(getApplication().getHomePage());
            }
        });
	}	
}
