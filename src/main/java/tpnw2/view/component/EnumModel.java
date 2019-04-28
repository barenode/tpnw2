package tpnw2.view.component;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

@SuppressWarnings("serial")
public class EnumModel<T extends Enum<?>> implements IModel<String> {
	
	private final IModel<T> model;
	
	public EnumModel(IModel<T> model) {
		super();
		this.model = model;
	}

	@Override
	public String getObject() {
		T e = model.getObject();
		String resourceKey = e.getClass().getSimpleName() + "." + e.name();
		return new ResourceModel(resourceKey).getObject();
	}
}
