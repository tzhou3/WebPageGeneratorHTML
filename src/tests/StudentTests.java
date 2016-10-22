package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import model.*;

public class StudentTests {

	public static final String TESTS_TAG = "\n\nEndTest";

	@Test
	public void testAnchor1() {
		int indentation = 3;
		String answer = "", attributes = "type=\"text/html\"";

		TagElement.resetIds();
		TagElement.enableId(true);
		AnchorElement element = new AnchorElement("url", "link text", null);
		answer += element.genHTML(indentation);
		AnchorElement element2 = new AnchorElement("http://www.w3schools.com", "HTML Tutorial", attributes);
		answer += "\n" + element2.genHTML(indentation);
		// System.out.println(answer);
		assertTrue(TestsSupport.isCorrect("studentTestAnchor.txt", answer));
	}

	@Test
	public void testAnchor2() {
		String answer = "", attributes = "type=\"text/html\"";
		TagElement.resetIds();
		TagElement.enableId(true);

		AnchorElement element = new AnchorElement("http://www.w3schools.com", "HTML Tutorial", attributes);
		answer += "Link Text: \n";
		answer += element.getLinkText();
		assertTrue(TestsSupport.isCorrect("studentTestAnchor2.txt", answer));

	}

	@Test
	public void testAnchor3() {
		String answer = "", attributes = "type=\"text/html\"";
		TagElement.resetIds();
		TagElement.enableId(true);

		AnchorElement element = new AnchorElement("http://www.w3schools.com", "HTML Tutorial", attributes);
		answer += "URL Text: \n";
		answer += element.getUrlText();
		assertTrue(TestsSupport.isCorrect("studentTestAnchor3.txt", answer));
	}

	@Test
	public void testHeading() {
		int indentation = 3, level = 1;
		String answer = "";
		String attributes = null;

		TagElement.resetIds();
		TagElement.enableId(false);
		HeadingElement element = new HeadingElement(new TextElement("Test Heading"), level, attributes);
		answer += element.genHTML(indentation);

		answer += "\n";
		HeadingElement element2 = new HeadingElement(new TextElement("Test Heading 2"), level + 1, attributes);
		answer += element2.genHTML(indentation);

		answer += "\n";
		HeadingElement element3 = new HeadingElement(new TextElement("Test Heading 3"), level + 2, attributes);
		answer += element3.genHTML(indentation);

		assertTrue(TestsSupport.isCorrect("studentTestHeading.txt", answer));
	}

	@Test
	public void testImage() {
		int indentation = 3;
		String answer = "", attributes = " align=\"middle\"", alt = "Testudo Image";
		int width = 84, height = 111;

		TagElement.resetIds();
		TagElement.enableId(true);
		ImageElement element = new ImageElement("testudo.jpg", width, height, alt, attributes);
		answer += element.genHTML(indentation);
		answer += "\n";

		indentation = 6;
		element = new ImageElement("testudo.jpg", width, height, alt, null);
		answer += element.genHTML(indentation);
		answer += "\n";

		element = new ImageElement("testudo.jpg", width, height, alt, null);
		answer += element.genHTML(indentation);
		assertTrue(TestsSupport.isCorrect("studentTestImage.txt", answer));
	}

	@Test
	public void testList1() {
		int indentation = 3;
		String answer = "", attributes = null;

		boolean orderedList = false;
		TagElement.resetIds();
		TagElement.enableId(true);
		ListElement element = new ListElement(orderedList, attributes);
		element.addItem(new TextElement("Superman"));
		element.addItem(new AnchorElement("http://www.cs.umd.edu", "UMD", attributes));
		answer += element.genHTML(indentation);
		answer += "\nSecondElement\n";

		indentation = 6;
		orderedList = true;
		String attributesList = "reversed";

		ListElement element2 = new ListElement(orderedList, attributesList);

		ParagraphElement paragraph = new ParagraphElement(attributes);
		paragraph.addItem(new TextElement("Fear the turtle"));
		paragraph.addItem(new ImageElement("testudo.jpg", 84, 111, "Testudo Image", attributes));
		paragraph.addItem(new AnchorElement("http://www.cs.umd.edu", "UMD", attributes));

		element2.addItem(paragraph); // Testing the addItem function in list
										// with genHTML because it has a void
										// return type

		TableElement table1 = new TableElement(2, 2, attributes);
		table1.addItem(0, 1, new TextElement("test"));
		table1.addItem(0, 0, new TextElement("test2"));

		element2.addItem(table1);

		answer += element2.genHTML(indentation);

		answer += TESTS_TAG;
		assertTrue(TestsSupport.isCorrect("studentTestList.txt", answer));

	}

	@Test
	public void testList2() {
		int indentation = 3;
		String answer = "", attributes = "reversed";

		boolean orderedList = true;
		TagElement.resetIds();
		TagElement.enableId(true);
		ListElement element = new ListElement(orderedList, attributes);
		ImageElement img = new ImageElement("testudo.jpg", 80, 111, "Testudo Image", null);
		AnchorElement a = new AnchorElement("http://www.cs.umd.edu", "UMD", null);
		TableElement table1 = new TableElement(5, 2, "attribute");
		table1.addItem(0, 1, new TextElement("test"));
		table1.addItem(0, 0, new TextElement("test2"));
		table1.addItem(3, 1, new TextElement("test3"));
		table1.addItem(1, 0, new TextElement("test4"));
		element.addItem(img);
		element.addItem(a);
		element.addItem(table1);

		answer += element.genHTML(indentation);
		assertTrue(TestsSupport.isCorrect("studentTestList2.txt", answer));

	}

	@Test
	public void testParagraph() {
		int indentation = 3;
		String answer = "", attributes = null;

		TagElement.resetIds();
		TagElement.enableId(true);
		ParagraphElement element = new ParagraphElement(attributes);

		HeadingElement headElement = new HeadingElement(new TextElement("test"), 1, attributes);
		ImageElement imgElement = new ImageElement("testudo.jpg", 145, 150, "Testudo Image", "align=\"middle\"");
		AnchorElement aElement = new AnchorElement("http://www.google.com", "Google", attributes);

		element.addItem(headElement);
		element.addItem(imgElement); // Testing the addItem function in
										// paragraph with genHTML because it has
										// a void return type
		element.addItem(aElement);

		answer += element.genHTML(indentation);
		assertTrue(TestsSupport.isCorrect("studentTestParagraph.txt", answer));
	}

	@Test
	public void testTable() {
		int indentation = 3;
		String attributes = "border=\"1\"", answer = "";

		TagElement.resetIds();
		TagElement.enableId(true);
		TableElement tableElement = new TableElement(2, 10, attributes);

		int testNum = 1;

		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 10; j++) {
				tableElement.addItem(i, j, new TextElement("test" + testNum));
				// Testing the addItem function in table with genHTML beacuse it
				// has a void return type
				testNum++;
			}
		}
		answer += tableElement.genHTML(indentation);
		assertTrue(TestsSupport.isCorrect("studentTestTable.txt", answer));
	}

	@Test
	public void testTag() {
		int indentation = 3;
		String attributes = null, answer = "";

		TagElement.resetIds();
		TagElement.enableId(true);

		TagElement element = new HeadingElement(new TextElement("text"), 1, attributes);
		answer += "ID: " + element.getId() + "\n";
		answer += "EnableID? " + element.enable + "\n";
		answer += "StartTag: " + element.getStartTag() + "\n";
		answer += "EndTag: " + element.getEndTag() + "\n";
		answer += "StringID: " + element.getStringId() + "\n";
		answer += "HTML: " + element.genHTML(indentation) + "\n";
		element.setAttributes("align=\"left\"");
		answer += "HTML with attributes: " + element.genHTML(indentation) + "\n";
		assertTrue(TestsSupport.isCorrect("studentTestTag.txt", answer));

	}
	
	@Test
	public void testTag2(){
		int indentation = 3;
		String attributes = null, answer = "";

		TagElement.resetIds();
		TagElement.enableId(false);

		TagElement element = new HeadingElement(new TextElement("text"), 1, attributes);
		answer += "ID: " + element.getId() + "\n";
		answer += "EnableID? " + element.enable + "\n";
		answer += "StartTag: " + element.getStartTag() + "\n";
		answer += "EndTag: " + element.getEndTag() + "\n";
		answer += "StringID: " + element.getStringId() + "\n";
		answer += "HTML: " + element.genHTML(indentation) + "\n";
		element.setAttributes("align=\"left\"");
		answer += "HTML with attributes: " + element.genHTML(indentation) + "\n";
		assertTrue(TestsSupport.isCorrect("studentTestTag2.txt", answer));
	}
	@Test
	public void testText() {
		int indentation = 3;
		String answer = "";
		TextElement element = new TextElement("testText");
		assertEquals("   testText", element.genHTML(indentation));
	}

	@Test
	public void testUtilities() {
		int indentation = 3;
		String answer = Utilities.indent(indentation, 1);
		assertEquals("   ", answer);
	}

	@Test
	public void testUtilities2() {
		assertEquals("ol", Utilities.getListTagName(true));
		assertEquals("ul", Utilities.getListTagName(false));
	}

	@Test
	public void testWeb() {
		int indentation = 3;
		String answer = "";

		TagElement.resetIds();
		TagElement.enableId(true);
		WebPage webPage = new WebPage("TestTitle");

		TableElement tableElement = new TableElement(2, 10, null);
		int testNum = 1;
		for (int j = 0; j < 10; j++) {
			tableElement.addItem(1, j, new TextElement("test" + testNum));
			testNum++;
		}

		webPage.addElement(tableElement);

		ListElement listElement = new ListElement(true, null);
		ImageElement img = new ImageElement("testudo.jpg", 80, 111, "Testudo Image", null);
		AnchorElement a = new AnchorElement("http://www.cs.umd.edu", "UMD", null);
		listElement.addItem(img);
		listElement.addItem(a);
		webPage.addElement(listElement);

		ParagraphElement paragraphElement = new ParagraphElement(null);
		HeadingElement headElement = new HeadingElement(new TextElement("test"), 1, null);
		ImageElement imgElement = new ImageElement("testudo.jpg", 145, 150, "Testudo Image", "align=\"middle\"");
		AnchorElement aElement = new AnchorElement("http://www.google.com", "Google", null);
		paragraphElement.addItem(headElement);
		paragraphElement.addItem(imgElement);
		paragraphElement.addItem(aElement);
		webPage.addElement(paragraphElement);

		answer = webPage.getWebPageHTML(indentation) + "\n";

		answer += "\nStats: \n" + webPage.stats();

		Element findElem = webPage.findElem(5);

		answer += "\n\nFind Element5: \n" + findElem.genHTML(indentation);
		assertTrue(TestsSupport.isCorrect("studentTestWeb.txt", answer));

	}

	@Test
	public void testWebPage2() {
		int indentation = 3;
		String answer = "";

		TagElement.resetIds();
		TagElement.enableId(true);
		WebPage webPage1 = new WebPage("a");
		WebPage webPage2 = new WebPage("b");
		assertEquals(-1, webPage1.compareTo(webPage2));
	}

	@Test
	public void testHonorsWebPage() {
		String answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(true);
		
		answer += HonorsWebPage.getName("Stephan Loh").genHTML(0);
		assertEquals("Name: Stephan Loh", answer);
	}

	@Test
	public void testHonorsWebPage2() {
		String answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(true);
		
		String info = "Stephan Loh enjoys long walks on the beach. He also enjoys rainy sunday afternoons with a really good book and a cup of chai tea latte.";
		answer += HonorsWebPage.getInfo(info).genHTML(3);
		assertTrue(TestsSupport.isCorrect("studentTestHonors.txt", answer));
	}
	
	@Test
	public void testHonorsWebPage3(){
		String answer = "";
		
		TagElement.resetIds();
		TagElement.enableId(true);
		
		String profileLink="https://www.facebook.com/stephan.loh.35?fref=ts";
		answer+=HonorsWebPage.getProfileLink(profileLink).genHTML(3);
		assertTrue(TestsSupport.isCorrect("studentTestHonors2.txt", answer));
	}
	
	@Test
	public void testHonorsWebPage4(){
		String answer="";
		
		TagElement.resetIds();
		TagElement.enableId(true);
		
		String url1="www.google.com";
		String url2="www.microsoft.com";
		String url3="www.facebook.com";
		
		answer+=HonorsWebPage.getFavWebsites(url1, url2, url3).genHTML(3);
		assertTrue(TestsSupport.isCorrect("studentTestHonors3.txt", answer));
		
	}
	
	@Test
	public void testHonorsSurprise(){
		String answer="";
		
		TagElement.resetIds();
		TagElement.enableId(true);
		
		answer+=HonorsWebPage.surprise().genHTML(3);
		assertTrue(TestsSupport.isCorrect("studentTestHonors4.txt", answer));
	}

}
