package tpnw2.view;

import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.EmailTextField;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.StatelessForm;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.util.string.Strings;

import tpnw2.view.component.Feedback;

@SuppressWarnings("serial")
public class LoginPage extends WebPage {

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
                System.out.println("SUBMIT");
            	
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
}
