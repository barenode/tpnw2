package tpnw2.view.component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.HeadersToolbar;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import tpnw2.persistence.Dao;

@SuppressWarnings("serial")
public abstract class MasterDetail<T extends Serializable, C extends Serializable> extends Panel {
	private static final int ROWS_PER_PAGE = 10;
	private final Dao<T, C> dao;
	
	private long currentPage = 0;
	private C criteria;
	private String sortProperty;
	private SortOrder sortOrder;
	
	public MasterDetail(String id, Dao<T, C> dao) {
		super(id);
		this.dao = dao;			
		add(new Feedback("feedback"));
		add(new EmptyPanel("content"));		
		criteria = newCriteria();
		sortProperty = defaultSortProperty();
		sortOrder = defaultSortOrder();
		master();		
	}		
	
	protected abstract List<? extends IColumn<T, String>> getColumns();
	
	protected abstract T newItem();	
	
	protected boolean newItemEnabled() {
		return true;
	}
	
	protected boolean removeEnabled(IModel<T> model) {
		return true;
	}
	
	protected abstract boolean checkBeforePersist(T item);
	
	protected boolean checkBeforeRemove(T item) {
		return true;
	}
	
	protected abstract Panel itemPanel(String id, IModel<T> model);
	
	protected abstract C newCriteria();
	
	protected abstract Panel criteriaPanel(String id, IModel<C> model);
	
	protected abstract String defaultSortProperty();		
	
	protected SortOrder defaultSortOrder() {
		return SortOrder.ASCENDING;
	}			
	
	protected void master() {
		MasterDetail.this.replace(new Master("content", getColumns()));
	}
	
	protected void detail(IModel<T> model) {
		MasterDetail.this.replace(new Detail("content", model));
	}
	
	/**
	 * Generic column with single icon link.
	 */
	public abstract static class LinkColumn<T> extends AbstractColumn<T, String> {

		private final IModel<String> labelModel;
		private final String cssClass;
		
		public LinkColumn(IModel<String> labelModel) {
			this(Model.of(""), labelModel, null);
		}
		
		public LinkColumn(IModel<String> labelModel, String cssClass) {
			this(Model.of(""), labelModel, cssClass);
		}
		
		public LinkColumn(IModel<String> displayModel, IModel<String> labelModel, String cssClass) {
			super(displayModel);
			this.labelModel = labelModel;
			this.cssClass = cssClass;
		}

		@Override
		public void populateItem(Item<ICellPopulator<T>> cellItem, String componentId, IModel<T> rowModel) {
			cellItem.add(new FancyButton(componentId, labelModel, cssClass) {
				@Override
				public void onClick() {
					LinkColumn.this.onClick(rowModel);
				}

				@Override
				public boolean isEnabled() {
					return LinkColumn.this.isEnabled(rowModel);
				}									
			});
		}

		@Override
		public String getCssClass() {
			return "table-column-button";
		}		
		
		protected boolean isEnabled(IModel<T> model) {
			return true;
		}
		
		protected abstract void onClick(IModel<T> model);
	}
	
	
	private final class Master extends Panel {

		public Master(String id, List<? extends IColumn<T, String>> columns) {
			super(id);
			List<IColumn<T, String>> allColumns = new ArrayList<>(columns);		
			allColumns.add(new LinkColumn<T>(new ResourceModel("button.edit"), "fa-edit") {
				@Override
				protected void onClick(IModel<T> model) {
					detail(model);
				}				
			});
			allColumns.add(new LinkColumn<T>(new ResourceModel("button.delete"), "fa-remove"){
				@Override
				protected void onClick(IModel<T> model) {
					if (checkBeforeRemove(model.getObject())) {
						dao.remove(model.getObject());
					}					
				}	
				@Override
				protected boolean isEnabled(IModel<T> model) {
					return removeEnabled(model);
				}	
			});		
			SortableDataProvider<T, String> dataProvider = new DataProvider(sortProperty, sortOrder);
			DataTable<T, String> table = new DataTable<T, String>("table", allColumns, dataProvider, ROWS_PER_PAGE) {
				@Override
				protected void onBeforeRender() {
					super.onBeforeRender();
					currentPage = getCurrentPage();
				}				
			};
			table.setCurrentPage(currentPage);
			table.addTopToolbar(new HeadersToolbar<>(table, dataProvider));
			add(table);
			add(new Pagination("pagination", table));
			add(new Link<Page>("newItem") {
				@Override
				public void onClick() {
					detail(Model.of(newItem()));
				}

				@Override
				public boolean isEnabled() {
					return MasterDetail.this.newItemEnabled();
				}
			});	
			
			//criteria
			Form<C> criteriaForm = new Form<>("criteriaForm");
			criteriaForm.add(new SubmitLink("search"));
			criteriaForm.add(criteriaPanel("criteria", Model.of(criteria)));
			add(criteriaForm);
		}		
	}
	
	private final class Detail extends Panel {

		public Detail(String id, final IModel<T> model) {
			super(id);
			Form<T> form = new Form<>("form");
			add(form);
			form.add(MasterDetail.this.itemPanel("item", model));
			form.add(new SubmitLink("submit") {
				@Override
				public void onSubmit() {
					super.onSubmit();
					if (checkBeforePersist(model.getObject())) {
						dao.save(model.getObject());
						master();
					}					
				}						
			});	
			form.add(new Link<Page>("cancel") {
				@Override
				public void onClick() {
					master();
				}			
			});	
		}		
	}	
	
	private class DataProvider extends SortableDataProvider<T, String> {
		
		public DataProvider(String sortProperty, SortOrder sortOrder) {
			super();
			this.setSort(sortProperty, sortOrder);
		}

		@Override
		public Iterator<? extends T> iterator(long first, long count) {
			return dao.page(first, count, criteria, getSort()).iterator();
		}

		@Override
		public long size() {
			return dao.count(criteria);
		}

		@Override
		public IModel<T> model(T object) {
			return Model.of(object);
		}		
	} 
}
