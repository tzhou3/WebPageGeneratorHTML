package model;

import java.util.ArrayList;

public class ParagraphElement extends TagElement {

	private ArrayList<Element> content;
	private String attributes;

	public ParagraphElement(String attributes) {
		super("p", true, new TextElement("text"), attributes);
		content = new ArrayList<Element>();
		this.attributes = attributes;
	}

	public void addItem(Element item) {
		content.add(item);
	}

	@Override
	public String genHTML(int indentation) {
		String returnText = "";
		returnText = Utilities.indent(indentation, 1);

		returnText += getStartTag();

		returnText += "\n";
		for (Element temp : content) {

			returnText += Utilities.indent(indentation, 1);
			returnText += temp.genHTML(indentation) + "\n";
		}
		returnText += Utilities.indent(indentation, 1) + getEndTag();
		return returnText;
	}

}
