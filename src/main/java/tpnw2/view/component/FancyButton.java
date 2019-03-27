package tpnw2.view.component;

import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

@SuppressWarnings("serial")
public abstract class FancyButton extends Panel {

	public FancyButton(String id, IModel<String> model, String cssClass) {
		super(id);
		Link<FancyButton> link = new Link<FancyButton>("link") {			
			@Override
			public void onClick() {
				FancyButton.this.onClick();
			}
		};
		add(link);
		link.add(new WebComponent("icon") {
			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				tag.append("class",  " " + cssClass, "");
			}
		});
		link.add(new Label("label", model));
	}	
	
	public abstract void onClick();
}
