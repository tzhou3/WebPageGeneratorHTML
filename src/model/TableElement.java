package model;

public class TableElement extends TagElement {

	private Element[][] table;
	private String attributes;
	private int rows, cols;

	public TableElement(int rows, int cols, String attributes) {
		super("table", true, new TextElement("text"), attributes);
		table = new Element[rows][cols];
		this.attributes = attributes;
		this.rows = rows;
		this.cols = cols;
	}

	public void addItem(int rowIndex, int colIndex, Element item) {
		table[rowIndex][colIndex] = item;
	}

	public String genHTML(int indentation) {
		String returnText = "";
		returnText += Utilities.indent(indentation, 1);

		returnText += getStartTag();

		returnText += "\n";
		for (int rows = 0; rows < table.length; rows++) {
			returnText += Utilities.indent(indentation, 2) + "<tr>";

			for (int cols = 0; cols < table[rows].length; cols++) {
				Element temp = table[rows][cols];
				String html = "";

				if (temp != null) {
					html = temp.genHTML(indentation);
					html = html.substring(indentation);
				}

				returnText += "<td>" + html + "</td>";
			}
			returnText += "</tr>\n";
		}
		returnText += Utilities.indent(indentation, 1) + getEndTag();
		return returnText;
	}

	private int getRows() {
		return rows;
	}

	private int getCols() {
		return cols;
	}

	public double getTableUtilization() {
		double count = 0;
		for (int rows = 0; rows < getRows(); rows++) {

			for (int cols = 0; cols < getCols(); cols++) {
				Element temp = table[rows][cols];
				if (temp != null) {
					count++;
				}
			}
		}
		return count / (getRows() * getCols());
	}

}
