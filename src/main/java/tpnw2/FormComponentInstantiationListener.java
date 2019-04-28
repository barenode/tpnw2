package tpnw2;

import java.io.Serializable;

import org.apache.wicket.Component;
import org.apache.wicket.application.IComponentInstantiationListener;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.FormComponent;

@SuppressWarnings("serial")
public class FormComponentInstantiationListener implements IComponentInstantiationListener, Serializable {

	@Override
	public void onInstantiation(Component component) {
		if (component instanceof FormComponent<?>) {
			component.add(new Behavior() {
				@Override
				public void onComponentTag(Component component, ComponentTag tag) {				
					super.onComponentTag(component, tag);
					if (!((FormComponent<?>) component).isValid()) {
						tag.append("class",  " has-error-control", "");
					}					
				}				
			});
		}
	}
}
