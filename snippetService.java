package Service;

import Model.Snippet;
import Exception.EmptySnippetListException;
import Exception.SnippetNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

interface SnippetOperations {
   public void addSnippet(String Title, String Language, String Code, String[] Tags);
   public void displayAll();
   public void searchByTag(String Tags);
   public void deleteSnippet(int id);
   public void editSnippet(int id, String title, String language, String code, String[] tags);
}

public class snippetService implements SnippetOperations {
	private Snippet[] snippets;
	private int count;
	
	public Snippet findSnippetById(int id) {
	    for (int i = 0; i < count; i++) {
	        if (snippets[i].getID() == id) {
	            return snippets[i];
	        }
	    }
	    throw new SnippetNotFoundException("Snippet ID not found: " + id);
	}
	
	 public static void typeWriter(String text, int delay) {
		    for (char c : text.toCharArray()) {
		        System.out.print(c);
		        try {
		            Thread.sleep(delay); 
		        } catch (InterruptedException e) {
		            Thread.currentThread().interrupt();
		        }
		    }
		    System.out.println();
		}
	 
	 public int getTotalSnippets() {
		    return count;
		}
	
	public snippetService() {
		snippets = new Snippet[100];
		count=0;
	}
	
	private int generateId() {
		return count+1;
	}

	@Override
	public void addSnippet(String Title, String Language, String Code, String[] Tags) {
		if(count>= snippets.length) {
			  typeWriter("Storage Full.....!", 80);
			  return;
		}
		Snippet snippet = new Snippet(generateId(), Title, Language, Tags, Code);
		snippets[count] = snippet;
		count++;
		typeWriter("Snippet added successfully.....!", 80);
	}
	
	@Override
	public void displayAll() {
		if(count == 0) {
			typeWriter("No snippets available.....!", 80);
			return;
		}
		for (int i=0; i<count; i++) {
			if(snippets[i]!=null)
     			System.out.println(snippets[i]);
		}
	}
	
	@Override
	public void searchByTag(String searchTags) {
		boolean found = false;
		for (int i=0; i<count; i++) {
			String[] tags = snippets[i].getTags();
			for (String tag : tags) {
				if (tag.trim().equalsIgnoreCase(searchTags)) {
					System.out.println(snippets[i]);
					found=true;
				}
			}
		}
		if(!found) 
			typeWriter("No snippet found with that tag.....!", 80);
	}
	
	public void checkIfEmpty() {
	    if (snippets.length==0) {
	        throw new EmptySnippetListException("No snippets available!");
	    }
	}
	@Override
	public void deleteSnippet(int id) {
		
		checkIfEmpty();
		boolean found = false;
		for (int i=0; i<count; i++) {
			if(snippets[i].getID()==id) {
				for (int j=i; j<count-1; j++) 
					snippets[j]=snippets[j+1];
				snippets[count-1]=null;
				count--;
				found=true;
				typeWriter("LOADING.... -> Snippet deleted successfully.....!", 80);
				break;
			}
		}
		if (!found) 
			typeWriter("Snippet ID not found.....!", 80);
	}
	
	@Override
	public void editSnippet(int id, String title, String language, String code, String[] tags) {

		checkIfEmpty();
	    boolean found = false;

	    for (int i = 0; i < count; i++) {

	        if (snippets[i].getID() == id) {

	            if (title != null && !title.isEmpty()) {
	                snippets[i].setTitle(title);
	            }

	            if (language != null && !language.isEmpty()) {
	                snippets[i].setLanguage(language);
	            }

	            if (code != null && !code.isEmpty()) {
	                snippets[i].setCode(code);
	            }

	            if (tags != null && tags.length > 0) {
	                snippets[i].setTags(tags);
	            }

	            found = true;
	            typeWriter("Snippet updated successfully!", 80);
	            break;
	        }
	    }

	    if (!found) {
	        throw new SnippetNotFoundException("Snippet ID not found: " + id);
	    }
	}
	public void saveToFile() {

	    try {
	        FileWriter writer = new FileWriter("snippets.txt");

	        for (int i = 0; i < count; i++) {

	            Snippet s = snippets[i];

	            writer.write(s.getID() + ";" +
	                         s.getTitle() + ";" +
	                         s.getLanguage() + ";" +
	                         s.getCode().replace("\n", "<nl>") + ";" +
	                         String.join(",", s.getTags()));

	            writer.write("\n");
	        }

	        writer.close();
	        typeWriter("Data saved successfully!", 80);

	    } catch (IOException e) {
	        typeWriter("Error saving file!", 80);
	    }
	}

	
	public void loadFromFile() {
	    try {
	        File file = new File("snippets.txt");
	        if (!file.exists()) return;

	        Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) {
	            String line = sc.nextLine().trim();

	            if (line.isEmpty()) continue;

	            String[] parts = line.split(";");

	            if (parts.length < 5) { 
	                typeWriter("Skipping corrupted line: " + line, 80);
	                continue;
	            }

	            int id = Integer.parseInt(parts[0]);
	            String title = parts[1];
	            String language = parts[2];
	            String code = parts[3].replace("<nl>", "\n");
	            String[] tags = parts[4].split(",");

	            snippets[count++] = new Snippet(id, title, language, tags, code);
	        }

	        sc.close();

	    } catch (Exception e) {
	        typeWriter("Error loading file: " + e.getMessage(), 80); 
	    }
	}
}
