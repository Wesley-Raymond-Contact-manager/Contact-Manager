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
                    printListFormat(contactList);
                    break;
                case 2:
                    addContactToList();
                    break;
                case 3:
                    searchContactByName();
                    break;
                case 4:
                   deleteContact();
                default:
                    break;
            }
        } while(input.yesNo("\nDo you want to continue adding/editing contacts?"));
        writeToContacts();
    }

    private static void deleteContact() {
        try {
            String searchName = input.getString("Who would you like to delete from the list?");
            int index = -1;
            for (int i = 0; i < contactList.size(); i++) {
                if (contactList.get(i).toLowerCase().contains(searchName.toLowerCase())){
                    index = i;
                }
            }
            contactList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Could not find a contact with that name");
        }
    }

    private static void searchContactByName() {
        String searchName = input.getString("Which contact would you like to see?");
        contactList.forEach(contact -> {
            if (contact.toLowerCase().contains(searchName.toLowerCase())){
                System.out.println(contact);
            }
        });
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
        long phoneNumber = input.getLong(7, 10, "Enter the contact phone number.");
        String contactInfo = name + " - " + phoneNumber;
        contactList.add(contactInfo);
    }

    private static void printListFormat(List<String> contactList){
        System.out.printf("%-10s | %12s", "Name", "Phone Number");
        System.out.println();
        for (String contact : contactList) {
            String [] contactSplit = contact.split("-");

            System.out.printf("%-10s | %-12s%n", contactSplit[0], formatPhoneNumber(contactSplit[1].trim()));
        }
    }

    private static String formatPhoneNumber(String phoneNum) {
        String [] phoneNums = phoneNum.split("");
        String formattedPhoneNum = "";
        if (phoneNum.length() == 7) {
            phoneNums[3] = "-";
        } else if (phoneNum.length() == 10) {
            phoneNums[3] = "-";
            phoneNums[7] = "-";
        }
        for (String character: phoneNums) {
            formattedPhoneNum += character;
        }
       return formattedPhoneNum;
    }
}
