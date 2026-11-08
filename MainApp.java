package App;

import java.util.Scanner;

import Exception.EmptySnippetListException;
import Exception.SnippetNotFoundException;
import Service.snippetService;

public class MainApp {
	
	public static void printCentered(String text, int width) {
	    int padding = (width - text.length()) / 2;
	    if (padding < 0) padding = 0;
	    System.out.println(" ".repeat(padding) + text);
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
	
	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		snippetService service = new snippetService();
		service.loadFromFile();  
		
		int choice;
		
		int width = 85;
		
		printCentered("!!~~~~~~~~~~ Code Snippet Organiser ~~~~~~~~~~!!", width);
		do {
			System.out.println("\n\nTotal Snippets: " + service.getTotalSnippets() +"\n");
			System.out.println("1) Add Snippet");
			System.out.println("2) View All Snippets");
			System.out.println("3) Search By Tag");
			System.out.println("4) Delete Snippet");
			System.out.println("5) Edit Snippet");
			System.out.println("6) Exit....");
			typeWriter("\n||===>> Enter your choice : ", 80);
			
			try {
				choice = sc.nextInt();
				sc.nextLine();
				
				switch (choice) {

                case 1:
                    addSnippetUI(sc, service);
                    break; 
                case 2:
                    service.displayAll();
                    break;
                case 3:
                    searchByTagUI(sc, service);
                    break;
                case 4:
                    deleteSnippetUI(sc, service);
                    break;
                case 5:
                    editSnippetUI(sc, service);
                    break;
                case 6:
                    service.saveToFile();   
                    typeWriter("Exiting... Thank you !", 80);
                    break;
                default:
                	typeWriter("Invalid Input! Try again....", 80);
				}
			}
			catch (Exception e) {
				typeWriter("INVALID INPUT !! PLEASE ENTER APPROPRIATE NUMBERS ONLY ...", 80);
				sc.nextLine();
				choice=0;
			}
	}
		while (choice != 6) ;
			sc.close();
		}

		private static void addSnippetUI(Scanner sc, snippetService service) {
		    
		    System.out.print("Enter Title : ");
		    String Title = sc.nextLine();
		    
		    System.out.print("Enter Language : ");
		    String Language = sc.nextLine();
		    
		    System.out.println("Enter Code (type 'END' in a new line to finish):");

		    StringBuilder codeBuilder = new StringBuilder();

		    while (true) {
		        String line = sc.nextLine();

		        if (line.equalsIgnoreCase("END")) {
		            break;
		        }

		        codeBuilder.append(line).append("\n");
		    }

		    String Code = codeBuilder.toString();
		    
		    System.out.print("Enter Tags (seperated by commas) : ");
		    String[] Tags = sc.nextLine().split(",");
		    
		    service.addSnippet(Title, Language, Code, Tags);
		}
		
		private static void searchByTagUI(Scanner sc, snippetService service) {

		    System.out.print("Enter tag to search: ");
		    String tag = sc.nextLine();

		    service.searchByTag(tag);
		}
		
		private static void deleteSnippetUI(Scanner sc, snippetService service) {

		    System.out.print("Enter Snippet ID to delete: ");
		    int id = sc.nextInt();

		    service.deleteSnippet(id);
		}
		
		private static void editSnippetUI(Scanner sc, snippetService service) {

			try {
		    System.out.print("Enter Snippet ID to edit: ");
		    int id = sc.nextInt();
		    sc.nextLine(); 

		    typeWriter("Leave field empty if you don't want to change it", 80);

		    System.out.print("New Title: ");
		    String title = sc.nextLine();

		    System.out.print("New Language: ");
		    String language = sc.nextLine();

		    System.out.print("New Code: ");
		    String code = sc.nextLine();

		    System.out.print("New Tags (comma separated): ");
		    String tagInput = sc.nextLine();

		    String[] tags = null;
		    if (!tagInput.isEmpty()) {
		        tags = tagInput.split(",");
		    }

		        service.editSnippet(id, title, language, code, tags);
		        
		    } catch (EmptySnippetListException e) {
		    	System.out.println("⚠ " + e.getMessage());

		    } catch (SnippetNotFoundException e) {
		    	System.out.println("❌ " + e.getMessage());
		    }
		    catch (Exception e) {
		    	System.out.println(e.getMessage());
		    }
		}
}
