// todo javadoc documentation
package Dictionary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        // creating the dictionary
        Dictionary dictionary = new Dictionary();

        // welcome message
        System.out.print("""
                -----------------------------
                Welcome to dictionary
                -----------------------------
                """);

        int menuSelection;
        Scanner myKey = new Scanner(System.in);

        while(true) {
            System.out.println("""
                -----------------------------
                Main Menu
                -----------------------------
                1 View all dictionary
                2 Search for word
                3 Search for words beginning with
                4 Add word
                5 Remove word
                6 Edit word definition
                7 Upload dictionary file contents
                8 Save dictionary to file
                0 Exit
                """);

            // main input loop
            while(true) {
                try {
                    System.out.println("""
                            -----------------------------
                            What would you like to do?""");
                    String tryNumber = myKey.nextLine();
                    menuSelection = Integer.parseInt(tryNumber);
                    if (menuSelection < 0 || menuSelection > 8)
                        throw new Exception();

                    break;
                } catch (Exception e) {
                    System.out.println("Please enter a valid integer.");
                }
            }





            // exit
            if (menuSelection==0) {
                System.out.println("Thank you for using the dictionary services.");
                System.exit(0);
            }





            // display all dictionary
            else if (menuSelection==1) {
                // header
                System.out.print("""
                            -----------------------------
                            Displaying entire dictionary
                            -----------------------------
                            """);

                // display
                dictionary.display();
            }




            // search for word
            else if (menuSelection==2) {
                // header
                System.out.print("""
                            -----------------------------
                            Search for word
                            -----------------------------
                            """);

                // get info
                System.out.println("Word to find?");
                String word = myKey.nextLine();

                // display
                dictionary.display(word);
            }



            // display all beginning with
            else if (menuSelection==3) {
                // header
                System.out.print("""
                            -----------------------------
                            Search beginning with
                            -----------------------------
                            """);

                // get info
                System.out.println("Word start?");
                String wordStart = myKey.nextLine();

                // search
                dictionary.search(wordStart);
            }


            // add a word
            // todo add a loop for ease of use
            else if (menuSelection==4) {
                // header
                System.out.print("""
                            -----------------------------
                            Add a word
                            -----------------------------
                            """);

                // get info
                System.out.println("Word to add?");
                String word = myKey.nextLine();

                System.out.println("Its definition? (Keep definition to a single line, others will be ignored.) ");
                String definition = myKey.nextLine();

                // add word
                dictionary.addWord(word, definition);
            }


            // remove word
            else if (menuSelection==5) {
                // header
                System.out.print("""
                            -----------------------------
                            Remove a word
                            -----------------------------
                            """);

                // get info
                System.out.println("Word to remove?");
                String word = myKey.nextLine();

                // remove word
                dictionary.removeWord(word);
            }


            // edit definition
            else if (menuSelection==6) {
                // header
                System.out.print("""
                            -----------------------------
                            Edit a definition
                            -----------------------------
                            """);

                // get info
                System.out.println("Word to edit?");
                String word = myKey.nextLine();

                System.out.println("New definition? (Keep definition to a single line, others will be ignored.) ");
                String definition = myKey.nextLine();

                // edit word
                dictionary.editDefinition(word, definition);
            }

            // upload dictionary file
            else if (menuSelection==7) {

                // header
                System.out.print("""
                            -----------------------------
                            Upload a dictionary file
                            -----------------------------
                            """);

                // get info
                System.out.println("Filepath?");
                String filePath = myKey.nextLine();

                try {
                    // i assume a properly formatted txt file
                    // todo take into account random blank lines
                    // todo add validation: # presence, non-blankness, multi-line definitions, unexpected file end, etc.
                    Scanner inputStream = new Scanner(new FileInputStream(filePath));
                    while (inputStream.hasNextLine()) {
                        String nextWord = inputStream.nextLine().substring(1); // i assume # presence
                        String definition ="";
                        if (inputStream.hasNextLine() ) {
                            definition = inputStream.nextLine();
                        }

                        if (definition.isBlank())
                            definition = "No definition provided";

                        dictionary.addWord(nextWord,definition);
                    }
                    inputStream.close();
                } catch (FileNotFoundException e) {
                    System.out.println("File not found exception. Action cannot proceed.");
                }
            }

            // save to file
            else if (menuSelection==8) {
                // header
                System.out.print("""
                            -----------------------------
                            Save dictionary to file
                            -----------------------------
                            """);

                // get info
                System.out.println("Filepath?");
                String filePath = myKey.nextLine();

                try {
                    PrintWriter outputStream = new PrintWriter(new FileOutputStream(filePath));
                    String toSave = dictionary.fileSaving();

                    outputStream.print(toSave);

                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        }
    }
}