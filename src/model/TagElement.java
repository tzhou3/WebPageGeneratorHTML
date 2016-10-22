package model;

public class TagElement implements Element {

	private boolean endTag;
	public static boolean enable;
	private String tagName, attributes;
	private Element content;
	private int actualId;
	private static int id;

	public TagElement(String tagName, boolean endTag, Element content, String attributes) {
		this.tagName = tagName;
		this.endTag = endTag;
		this.content = content;
		if (attributes != null) {
			this.attributes = " " + attributes;
		} else {
			this.attributes = attributes;
		}
		this.actualId = id;
		id++;
	}

	public static void enableId(boolean choice) {
		enable = choice;
	}

	@Override
	public String genHTML(int indentation) {
		String returnText = "";
		returnText += Utilities.indent(indentation, 1);

		returnText += getStartTag();

		if (content != null) {
			returnText += content.genHTML(0);
		}

		if (endTag) {
			returnText += getEndTag();
		}
		return returnText;

	}

	public String getEndTag() {
		if (endTag) {
			return "</" + tagName + ">";
		} else {
			return "";
		}
	}

	public int getId() {
		return actualId;
	}

	public String getStartTag() {
		if (attributes == null) {
			if (enable) {
				return "<" + tagName + " id=\"" + getStringId() + "\">";
			} else {
				return "<" + tagName + ">";
			}
		} else {
			if (enable) {
				return "<" + tagName + " id=\"" + getStringId() + "\"" + attributes + ">";
			} else {
				return "<" + tagName + attributes + ">";
			}
		}
	}

	public String getStringId() {
		return tagName + getId();
	}

	public static void resetIds() {
		id = 1;
	}

	public void setAttributes(String attributes) {
		this.attributes = " " + attributes;
	}

}
