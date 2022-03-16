import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ContactApp {
    private static List<String> contactList = new ArrayList<>();
    private static Path contactsPath = Paths.get("src","contacts.txt");
    private static final Input input = new Input();

    public static void main(String[] args) {
        contactList = init();
        do {
            printMenu();
            switch (input.getInt("Enter an option (1, 2, 3, 4 or 5):")) {
                case 1:
                    System.out.println(contactList);
                case 2:
                    addContactToList();
                case 3:
                    searchContactByName();
                case 4:
                    deleteContact();
                default:
                    break;
            }
        } while(input.yesNo("Do you want to continue adding/editing contacts?"));
        writeToContacts();
    }

    private static List<String> init() {
        try {
            return Files.readAllLines(contactsPath);
        } catch (IOException e) {
            System.out.println("No file exists");
            e.printStackTrace();
        }
        return null;
    }

    private static void printMenu(){
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n");
    }

    private static void writeToContacts(){
        try {
            Files.write(contactsPath, contactList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void addContactToList(){
        String name = input.getString("Enter the contact name.");
        long phoneNumber = input.getInt("Enter the contact phone number.");
        String contactInfo = name + " - " + phoneNumber;
        contactList.add(contactInfo);
    }

}
