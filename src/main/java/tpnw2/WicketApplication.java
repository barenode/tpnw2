package tpnw2;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import tpnw2.view.CarPage;
import tpnw2.view.CompanyPage;
import tpnw2.view.EmployeePage;
import tpnw2.view.OfficePage;
import tpnw2.view.OrderPage;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 * 
 * @see tpw2.Start#main(String[])
 */
public class WicketApplication extends WebApplication {//AuthenticatedWebApplication {

	@Autowired
	private ApplicationContext applicationContext;
	   
	/**
	 * @see org.apache.wicket.Application#getHomePage()
	 */
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return OrderPage.class;
	}

	/**
	 * @see org.apache.wicket.Application#init()
	 */
	@Override
	public void init()
	{
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContext, true));
		// add your configuration here
	}

//	@Override
//	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	protected Class<? extends WebPage> getSignInPageClass() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
