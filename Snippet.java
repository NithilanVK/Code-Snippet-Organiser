package Model;

public class Snippet {
	
	private int ID;
	private String Title;
	private String Language;
	private String[] Tags;
	private String Code;
	
	public Snippet(int ID, String Title, String Language, String[] Tags, String Code) {
		this.ID=ID;
		this.Title=Title;
		this.Language=Language;
		this.Tags=Tags;
		this.Code=Code;	
	}
  	public int getID() {
		return ID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String Title) {
		if(Title==null || Title.trim().isEmpty()) 
			System.out.println("Title shuld not be Empty !!");
		this.Title=Title;
	}
	public String getLanguage() {
		return Language;
	}
	public void setLanguage(String Language) {
		if(Language==null || Language.trim().isEmpty())
			System.out.println("Language type cannot be Empty !!");
		this.Language=Language;
	}
	public String[] getTags() {
		return Tags;
	}
	public void setTags(String[] Tags) {
		this.Tags=Tags;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String Code) {
		this.Code=Code;
	}
	@Override
	public String toString() {
		String tagList = "";
		for (String tag : Tags)
			tagList += tag + "";
		return "\nID: " + ID +
	           "\nTitle: " + Title +
	           "\nLanguage: " + Language +
	           "\nTags: " + tagList +
	           "\nCode:\n" + Code;
	}
}
