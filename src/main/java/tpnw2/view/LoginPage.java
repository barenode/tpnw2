package tpnw2.view;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;
import org.apache.wicket.util.string.Strings;

import tpnw2.view.component.Feedback;

@SuppressWarnings("serial")
public class LoginPage extends WebPage {

	public static JavaScriptResourceReference BOOTSTRAP_JS = new JavaScriptResourceReference(PageBase.class, "js/bootstrap.min.js");
	public static JavaScriptResourceReference METIS_JS = new JavaScriptResourceReference(PageBase.class, "js/metisMenu.min.js");
	public static JavaScriptResourceReference ADMIN_JS = new JavaScriptResourceReference(PageBase.class, "js/sb-admin-2.min.js");
	
	public static CssResourceReference BOOTSTRAP_CSS = new CssResourceReference(PageBase.class, "css/bootstrap/bootstrap.min.css");
	public static CssResourceReference BOOTSTRAP_TABLE_CSS = new CssResourceReference(PageBase.class, "css/bootstrap/dataTables.bootstrap.css");
	public static CssResourceReference CUSTOM_CSS = new CssResourceReference(PageBase.class, "css/styles.css");
	public static CssResourceReference ADMIN_CSS = new CssResourceReference(PageBase.class, "css/sb-admin-2.min.css");
	public static CssResourceReference FONT_CSS = new CssResourceReference(PageBase.class, "css/font/css/font-awesome.min.css");
	public static CssResourceReference METIS_CSS = new CssResourceReference(PageBase.class, "css/metisMenu/metisMenu.min.css");
	
	private String email;
    private String password;
    
    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new Label("title", new ResourceModel("signin")));
        add(new Feedback("feedback"));
        StatelessForm<LoginPage> form = new StatelessForm<LoginPage>("loginForm") {
            @Override
            protected void onSubmit() {            	
            	if (Strings.isEmpty(email))
                    return;
                boolean authResult = AuthenticatedWebSession.get().signIn(email, password);
                // if authentication succeeds redirect user to the requested page
                if (authResult){
                    setResponsePage(DashboardPage.class);
                } else {
                	this.error(getString("signin.error"));
                    return;
                }
            }
        };
        form.add(new SubmitLink("submit"));
        form.setDefaultModel(new CompoundPropertyModel<>(this));
        form.add(new EmailTextField("email").setRequired(true));
        form.add(new PasswordTextField("password"));
        add(form);
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
}
