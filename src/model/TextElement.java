package model;

public class TextElement implements Element {

	private String text;

	public TextElement(String text) {
		this.text = text;
	}

	@Override
	public String genHTML(int indentation) {
		// TODO Auto-generated method stub
		String returnText = "";
		for (int i = 0; i < indentation; i++) {
			returnText += " ";
		}
		returnText += text;
		return returnText;
	}

}
