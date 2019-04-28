package tpnw2.view;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

import tpnw2.view.component.Header;
import tpnw2.view.component.Menu;

@SuppressWarnings("serial")
public class PageBase extends WebPage {

	public static JavaScriptResourceReference BOOTSTRAP_JS = new JavaScriptResourceReference(PageBase.class, "js/bootstrap.min.js");
	public static JavaScriptResourceReference METIS_JS = new JavaScriptResourceReference(PageBase.class, "js/metisMenu.min.js");
	public static JavaScriptResourceReference ADMIN_JS = new JavaScriptResourceReference(PageBase.class, "js/sb-admin-2.min.js");
	
	public static CssResourceReference BOOTSTRAP_CSS = new CssResourceReference(PageBase.class, "css/bootstrap/bootstrap.min.css");
	public static CssResourceReference BOOTSTRAP_TABLE_CSS = new CssResourceReference(PageBase.class, "css/bootstrap/dataTables.bootstrap.css");
	public static CssResourceReference CUSTOM_CSS = new CssResourceReference(PageBase.class, "css/styles.css");
	public static CssResourceReference ADMIN_CSS = new CssResourceReference(PageBase.class, "css/sb-admin-2.min.css");
	public static CssResourceReference FONT_CSS = new CssResourceReference(PageBase.class, "css/font/css/font-awesome.min.css");
	public static CssResourceReference METIS_CSS = new CssResourceReference(PageBase.class, "css/metisMenu/metisMenu.min.css");
	
	public PageBase(final PageParameters parameters) {
		super(parameters);
		add(new Label("title", new ResourceModel("title")));
		add(new Header("header"));
		add(new Menu("menu"));
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		//js
		response.render(JavaScriptHeaderItem.forReference(JQueryResourceReference.INSTANCE_2));
		response.render(JavaScriptHeaderItem.forReference(BOOTSTRAP_JS));
		response.render(JavaScriptHeaderItem.forReference(METIS_JS));
		response.render(JavaScriptHeaderItem.forReference(ADMIN_JS));
		//css
		response.render(CssHeaderItem.forReference(FONT_CSS));
		response.render(CssHeaderItem.forReference(BOOTSTRAP_CSS));
		response.render(CssHeaderItem.forReference(BOOTSTRAP_TABLE_CSS));
		response.render(CssHeaderItem.forReference(ADMIN_CSS));
		response.render(CssHeaderItem.forReference(CUSTOM_CSS));
		response.render(CssHeaderItem.forReference(METIS_CSS));
	}
	
//	protected boolean isAdministrator() {
//		return hasRole(Auth.ADMINISTRATOR);
//	}
//	
//	protected boolean isManager() {
//		return hasRole(Auth.MANAGER);
//	}
//	
//	protected boolean hasRole(String role) {
//		return session().getRoles().hasRole(role);
//	}
//	
//	protected SecuredSession session() {
//		return (SecuredSession)Session.get();
//	}
//	
//	protected Employee logged() {
//		return session().getEmployee();
//	}
}
