package model;

import java.util.ArrayList;

public class WebPage implements Comparable<WebPage> {

	private ArrayList<Element> elements;
	private String title;

	public WebPage(String title) {
		elements = new ArrayList<Element>();
		this.title = title;
	}

	public int addElement(Element element) {
		elements.add(element);
		if (element instanceof TagElement) {
			return -1;
		} else {
			return ((TagElement) element).getId();
		}
	}

	public String getWebPageHTML(int indentation) {
		String result = "<!doctype html>\n";
		result += "<html>\n";
		result += Utilities.indent(indentation, 1) + "<head lang=\"en\">\n";
		result += Utilities.indent(indentation, 2) + "<meta charset=\"utf-8\"/>\n";
		result += Utilities.indent(indentation, 2) + "<title>" + title + "</title>\n";
		result += Utilities.indent(indentation, 1) + " </head>\n";
		result += Utilities.indent(indentation, 1) + "<body>\n";
		for (Element temp : elements) {
			result += temp.genHTML(indentation) + "\n";
		}
		result += Utilities.indent(indentation, 1) + "</body>\n";
		result += "</html>";
		return result;
	}

	public void writeToFile(String filename, int indentation) {
		Utilities.writeToFile(filename, getWebPageHTML(indentation));
	}

	public Element findElem(int id) {
		if (TagElement.enable) {
			for (Element temp : elements) {
				if (temp instanceof TagElement) {
					int tempId = ((TagElement) temp).getId();
					if (tempId == id)
						return temp;
				}
			}
		}
		return null;
	}

	public String stats() {
		int listCount = 0, paragraphCount = 0, tableCount = 0;
		double tableUtilization = 0;
		for (Element temp : elements) {
			if (temp instanceof ListElement) {
				listCount++;
			} else if (temp instanceof ParagraphElement) {
				paragraphCount++;
			} else if (temp instanceof TableElement) {
				tableCount++;
				tableUtilization += ((TableElement) temp).getTableUtilization();
			}

		}
		tableUtilization /= tableCount;
		tableUtilization *= 100;
		String returnText = "List Count: " + listCount + "\n" + "Paragraph Count: " + paragraphCount + "\n"
				+ "Table Count: " + tableCount + "\n" + "TableElement Utilization: " + tableUtilization;
		return returnText;
	}

	@Override
	public int compareTo(WebPage temp) {
		// TODO Auto-generated method stub
		if (title.compareTo(temp.title) < 0)
			return -1;
		else if (title.compareTo(temp.title) > 0)
			return 1;
		else
			return 0;
	}

	public static void enableId(boolean choice) {
		TagElement.enableId(choice);
	}

}
