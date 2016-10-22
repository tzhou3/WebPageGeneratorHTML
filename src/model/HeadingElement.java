package model;

public class HeadingElement extends TagElement {

	private int level;
	Element content;

	public HeadingElement(Element content, int level, String attributes) {

		super("h" + level, true, content, attributes);
		this.level = level;
		this.content = content;
	}

}
