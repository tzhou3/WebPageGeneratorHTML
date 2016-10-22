package model;

public class AnchorElement extends TagElement {
	private String urlText;
	private String linkText;

	public AnchorElement(String url, String linkText, String attributes) {
		super("a", true, null, attributes);

		this.urlText = url;
		this.linkText = linkText;

		if (attributes != null) {
			setAttributes("href=\"" + urlText + "\" " + attributes);
		} else {
			setAttributes("href=\"" + urlText + "\"");
		}
	}

	@Override
	public String genHTML(int indentation) {

		String returnText = "";
		returnText += Utilities.indent(indentation, 1);

		returnText += getStartTag() + linkText + this.getEndTag();

		return returnText;
	}

	public String getLinkText() {
		return linkText;
	}

	public String getUrlText() {
		return urlText;
	}
}
