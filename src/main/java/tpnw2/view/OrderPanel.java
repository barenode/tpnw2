package tpnw2.view;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.markup.html.tabs.ITab;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.validation.validator.StringValidator;

import tpnw2.Auth;
import tpnw2.domain.Client;
import tpnw2.domain.Company;
import tpnw2.domain.Individual;
import tpnw2.domain.Order;
import tpnw2.domain.OrderStatus;
import tpnw2.view.component.EnumModel;
import tpnw2.view.component.TabsPanel;

@SuppressWarnings("serial")
public class OrderPanel extends Panel {

	private final IModel<Order> model;
	
	public OrderPanel(String id, IModel<Order> model) {
		super(id);
		this.model = model;
		add(new TextField<Integer>("id", new PropertyModel<>(model, "id")).setEnabled(false));
		EnumModel<OrderStatus> statusModel = new EnumModel<OrderStatus>(new PropertyModel<>(model, "status"));
		add(new TextField<String>("status", statusModel).setEnabled(false));
		add(new TextField<>("administrator", new PropertyModel<>(model, "administrator")).setEnabled(false));
		add(new TextField<>("distance", new PropertyModel<>(model, "distance")).setEnabled(false));
		add(new TextField<BigDecimal>("price", new PropertyModel<>(model, "price")).setEnabled(false));
		add(new OfficeChoice("office", new PropertyModel<>(model, "office")).setRequired(true).setEnabled(Auth.isAdministrator()));
		add(new TextField<>("car", new PropertyModel<>(model, "car")).setEnabled(false));
		add(new TextField<String>("departureAddress", new PropertyModel<>(model, "departureAddress")).setRequired(true)
			.add(StringValidator.maximumLength(255)));
		add(new DateTextField("departureDate", new PropertyModel<>(model, "departureDate"), "dd.MM.yyyy HH:mm").setRequired(true));
		add(new TextField<String>("destinationAddress", new PropertyModel<>(model, "destinationAddress")).setRequired(true)
			.add(StringValidator.maximumLength(255)));
		add(new EmployeeChoice("driver", new PropertyModel<>(model, "driver")).setRequired(true));
		
		final Client client = model.getObject().getClient();		
		List<ITab> tabs = Arrays.asList(
			new ITab() {
				@Override
				public IModel<String> getTitle() {
					return new ResourceModel("individual");
				}
				@Override
				public WebMarkupContainer getPanel(String containerId) {
					Individual individual = null;					
					if (client instanceof Individual) {
						individual = (Individual)client;
					} else {
						individual = new Individual();
					}							
					model.getObject().setClient(individual);
					return new OrderIndividual(containerId, Model.of(individual));
				}
				@Override
				public boolean isVisible() {
					return true;
				}				
			},
			new ITab() {
				@Override
				public IModel<String> getTitle() {
					return new ResourceModel("company");
				}
				@Override
				public WebMarkupContainer getPanel(String containerId) {
					if (client instanceof Individual) {
						model.getObject().setClient(null);
					}
					return new OrderCompany(containerId, new PropertyModel<>(model, "client"));
				}
				@Override
				public boolean isVisible() {
					return true;
				}				
			}			
		);
		add(new TabsPanel<>("client", tabs).setSelectedTab(client instanceof Individual?0:1).setEnabled(model.getObject().getId()==null));
	}	
	
	@Override
	public boolean isEnabled() {
		Order order = model.getObject();
		return order.getStatus()==OrderStatus.Assigned || order.getStatus()==OrderStatus.Pending;
	}

	private static class OrderCompany extends Panel {		

		public OrderCompany(String id, IModel<Company> model) {
			super(id);
			add(new CompanyChoice("client", model));
		}		
	}
	
	private static class OrderIndividual extends Panel {

		public OrderIndividual(String id, IModel<Individual> model) {
			super(id);
			add(new TextField<String>("phone", new PropertyModel<>(model, "phone")).setRequired(true)
				.add(StringValidator.maximumLength(16)));
			add(new TextField<String>("firstname", new PropertyModel<>(model, "firstname")).setRequired(true)
				.add(StringValidator.maximumLength(50)));
			add(new TextField<String>("lastname", new PropertyModel<>(model, "lastname")).setRequired(true)
				.add(StringValidator.maximumLength(50)));
		}		
	}
}
