package model;

import java.util.Scanner;

public class HonorsWebPage {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("What is your name?");
		String name = scan.nextLine();

		System.out.println("Please enter your profile picture URL");
		String url = scan.nextLine();

		System.out.println("Tell us about yourself");
		String info = scan.nextLine();

		System.out.println("What are your three favorite websites? (Provide URL)");
		String url1 = scan.nextLine();
		String url2 = scan.nextLine();
		String url3 = scan.nextLine();

		WebPage HonorsWebPage = new WebPage(name + "'s Profile");
		HonorsWebPage.addElement(getName(name));
		HonorsWebPage.addElement(getProfileLink(url));
		HonorsWebPage.addElement(getInfo(info));
		HonorsWebPage.addElement(getFavWebsites(url1, url2, url3));
		HonorsWebPage.addElement(surprise());
		System.out.println(HonorsWebPage.getWebPageHTML(3));

	}
	
	public static TextElement getName(String name){
		return new TextElement("Name: " + name);
	}
	
	public static AnchorElement getProfileLink(String propicUrl) {
		AnchorElement proPic = new AnchorElement(propicUrl, "Profile Picture", null);
		return proPic;
	}

	public static ParagraphElement getInfo(String info) {
		ParagraphElement paragraph = new ParagraphElement(null);
		paragraph.addItem(new TextElement(info));
		return paragraph;
	}

	public static ListElement getFavWebsites(String website1, String website2, String website3) {
		ListElement favWebSites = new ListElement(true, null);
		favWebSites.addItem(new AnchorElement(website1, "Favorite Website 1", null));
		favWebSites.addItem(new AnchorElement(website2, "Favorite Website 2", null));
		favWebSites.addItem(new AnchorElement(website3, "Favorite Website 3", null));
		return favWebSites;
	}

	public static AnchorElement surprise() {
		AnchorElement surprise = new AnchorElement("http://gph.is/29KxfmX", "Surprise!!!", null);
		return surprise;
	}
}
