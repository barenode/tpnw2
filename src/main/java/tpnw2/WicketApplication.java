package tpnw2;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import tpnw2.view.DashboardPage;
import tpnw2.view.LoginPage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see tpw2.Start#main(String[])
 */
public class WicketApplication extends WebApplication { //AuthenticatedWebApplication {//extends WebApplication {//

	@Autowired
	private ApplicationContext applicationContext;
	   
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return DashboardPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext, true));
	}

//	@Override
//	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
//		return SecuredSession.class;
//	}
//
//	@Override
//	protected Class<? extends WebPage> getSignInPageClass() {
//		return LoginPage.class;
//	}
}
