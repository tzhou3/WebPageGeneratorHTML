package model;

public class ImageElement extends TagElement {

	public ImageElement(String imageURL, int width, int height, String alt, String attributes) {
		super("img", false, null, attributes);
		if (attributes != null) {
			setAttributes("src=\"" + imageURL + "\"" + " width=\"" + width + "\"" + " height=\"" + height + "\""
					+ " alt=\"" + alt + "\"" + " " + attributes);
		} else {
			setAttributes("src=\"" + imageURL + "\"" + " width=\"" + width + "\"" + " height=\"" + height + "\""
					+ " alt=\"" + alt + "\"");
		}
	}

}
