package tpnw2.view.component;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.extensions.ajax.markup.html.tabs.AjaxTabbedPanel;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.panel.EmptyPanel;

public class TabsPanel<T extends ITab> extends AjaxTabbedPanel<T> {
	private static final long serialVersionUID = 1L;

	public TabsPanel(String id, List<T> tabs) {
		super(id, tabs);
	}

	@Override
	protected LoopItem newTabContainer(final int tabIndex)
	{
		return new LoopItem(tabIndex) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void onComponentTag(ComponentTag tag) {
				super.onComponentTag(tag);
				String cssClass = (String)tag.getAttribute("class");
				if (cssClass == null) {
					cssClass = " ";
				}
				if (getIndex() == getSelectedTab()) {
					cssClass += " active";
				}
				tag.put("class", cssClass.trim());
			}

			@Override
			public boolean isVisible() {
				return ((ITab)getTabs().get(tabIndex)).isVisible();
			}
		};
	}

	@Override
	protected WebMarkupContainer newTabsContainer(String id) {
		WebMarkupContainer tabsContainer = super.newTabsContainer(id);
		//tabsContainer.add(newAlternateHeaderContent("alternateHeaderContent"));
		return tabsContainer;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getTabContainerCssClass() {
		return "tab-row ui-tabs-nav ui-widget-header ui-corner-all";
	}

	protected Component newAlternateHeaderContent(String id) {
		return new EmptyPanel(id).setRenderBodyOnly(true);
	}
}
