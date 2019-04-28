package tpnw2.report;

import java.util.Map;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.resource.JQueryResourceReference;

@SuppressWarnings("serial")
public class MorrisChart<T extends Record> extends WebMarkupContainer {

	public static JavaScriptResourceReference RAPHAEL_JS = new JavaScriptResourceReference(MorrisChart.class, "raphael.min.js");
	public static JavaScriptResourceReference MORRIS_JS = new JavaScriptResourceReference(MorrisChart.class, "morris.min.js");
	public static CssResourceReference MORRIS_CSS = new CssResourceReference(MorrisChart.class, "morris.css");
	
	private final String chartType;
	private final Dataset<T> dataset;
	
	public MorrisChart(String id, String chartType, Dataset<T> dataset) {
		super(id);
		setOutputMarkupId(true);
		this.chartType = chartType;
		this.dataset = dataset;
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.render(JavaScriptHeaderItem.forReference(JQueryResourceReference.INSTANCE_2));
		response.render(JavaScriptHeaderItem.forReference(RAPHAEL_JS));
		response.render(JavaScriptHeaderItem.forReference(MORRIS_JS));
		response.render(CssHeaderItem.forReference(MORRIS_CSS));
		CharSequence js = getScript();
		response.render(OnDomReadyHeaderItem.forScript(js.toString()));
	}
	
	protected CharSequence getScript() {
		StringBuilder buffer = new StringBuilder();
		//buffer.append("$(function() {\n");
		buffer.append("Morris." + chartType + "({\n");
		buffer.append("element: '" + getMarkupId() + "',\n");
		//data
		buffer.append("data: [\n");
		if (dataset.getRecords()!=null) {
			boolean first = true;
			for (T record : dataset.getRecords()) {
				if (!first) {
					buffer.append(",");
				}
				buffer.append("{\n");
				boolean firstAttr = true;
				for (Map.Entry<String, String> entry : record.map().entrySet()) {
					if (!firstAttr) {
						buffer.append(",");
					}
					buffer.append("'" + entry.getKey() + "':");
					buffer.append(entry.getValue());
					firstAttr = false;
				}
				buffer.append("}\n");
				first = false;
			}
		}
		buffer.append("]\n");
		//
		//options
		//x key
		if (dataset.getXKey()!=null) {
			buffer.append(",xkey: '" + dataset.getXKey() + "'\n");
		}
		//y keys
		if (dataset.getYKeys()!=null) {
			buffer.append(",ykeys: [\n");
			boolean first = true;
			for (String yKey : dataset.getYKeys()) {
				if (!first) {
					buffer.append(",");
				}
				buffer.append("'" + yKey + "'");
				first = false;
			}
			buffer.append("]\n");
		}
		//labels
		if (dataset.getLabels()!=null) {
			buffer.append(",labels: [\n");
			boolean first = true;
			for (String label : dataset.getLabels()) {
				if (!first) {
					buffer.append(",");
				}
				buffer.append("'" + label + "'");
				first = false;
			}
			buffer.append("]\n");
		}
		buffer.append(",hideHover: 'auto'\n");
		buffer.append(",resize: true\n");
		buffer.append("});\n");
		return buffer.toString();
	}	
}
