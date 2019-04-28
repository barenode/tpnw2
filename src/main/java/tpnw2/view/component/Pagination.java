package tpnw2.view.component;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.extensions.markup.html.repeater.data.table.NavigatorLabel;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.list.LoopItem;
import org.apache.wicket.markup.html.navigation.paging.IPageableItems;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationIncrementLink;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigationLink;
import org.apache.wicket.markup.html.panel.Panel;

@SuppressWarnings("serial")
public class Pagination extends Panel {

	public Pagination(String id, IPageableItems pageable) {
		super(id);
		add(new NavigatorLabel("label", pageable));		
		add(new PagingNavigationLink<Pagination>("first", pageable, 0) {		
			@Override
			public boolean isEnabled() {
				return pageable.getCurrentPage()>0;
			}
		}.add(new CSSDisabled()));
		add(new PagingNavigationIncrementLink<Pagination>("prev", pageable, -1) {		
			@Override
			public boolean isEnabled() {
				return pageable.getCurrentPage()>0;
			}
		}.add(new CSSDisabled()));
		add(new PagingNavigationIncrementLink<Pagination>("next", pageable, 1) {		
			@Override
			public boolean isEnabled() {
				return !(pageable.getCurrentPage() >= (pageable.getPageCount()-1));
			}
		}.add(new CSSDisabled()));
		add(new PagingNavigationLink<Pagination>("last", pageable, -1) {
			@Override
			public boolean isEnabled() {
				return !(pageable.getCurrentPage() >= (pageable.getPageCount()-1));
			}
		}.add(new CSSDisabled()));				
		add(new PagingNavigation("navigation", pageable) {
			@Override
			protected LoopItem newItem(int iteration) {			
				final long pageIndex = getStartIndex() + iteration;
				LoopItem item = super.newItem(iteration);
				item.add(new Behavior() {
					@Override
					public void onComponentTag(final Component component, final ComponentTag tag) {
						if (pageable.getCurrentPage()==pageIndex) {
							tag.append("class", " active", "");
						}
					}
				});
				return item;
			}

			@Override
			public int getViewSize() {
				return 5;
			}			
		});
	}
	
	private static class CSSDisabled extends Behavior {
		@Override
		public void onComponentTag(final Component component, final ComponentTag tag) {
			if (!component.isEnabled()) {
				tag.append("class", " disabled", "");
			}		
		}		
	}
}
