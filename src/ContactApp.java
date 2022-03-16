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
        init();
    }

    private static void init() {
        try {
            System.out.println(Files.readAllLines(contactsPath));
        } catch (IOException e) {
            System.out.println("No file exists");
            e.printStackTrace();
        }
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
