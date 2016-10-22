package model;

import java.util.ArrayList;

public class ListElement extends TagElement {

	private ArrayList<Element> list;

	public ListElement(boolean ordered, String attributes) {
		super(Utilities.getListTagName(ordered), true, null, attributes);
		list = new ArrayList<Element>();
	}

	public void addItem(Element item) {
		if (item != null)
			list.add(item);
	}

	public String genHTML(int indentation) {
		String returnText = "";
		returnText += Utilities.indent(indentation, 1);

		returnText += getStartTag();

		returnText += "\n";
		for (Element temp : list) {
			returnText += Utilities.indent(indentation, 2) + "<li>\n";
			String tempText = temp.genHTML(indentation);
			String[] tempTextLines = tempText.split("\n");
			for (int i = 0; i < tempTextLines.length; i++) {
				returnText += Utilities.indent(indentation, 2) + tempTextLines[i] + "\n";
			}
			returnText += Utilities.indent(indentation, 2) + "</li>\n";
		}
		returnText += getEndTag();
		return returnText;

	}
}
