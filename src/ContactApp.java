import util.Input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ContactApp {
    private static List<String> contactList = new ArrayList<>();
    private static Path contactsPath = Paths.get("src", "contacts.txt");
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
        } while (input.yesNo("\nDo you want to continue adding/editing contacts?"));
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

    private static void printMenu() {
        System.out.println("1. View contacts.\n" +
                "2. Add a new contact.\n" +
                "3. Search a contact by name.\n" +
                "4. Delete an existing contact.\n" +
                "5. Exit.\n");
    }

    private static int searchContactList(String name) {
        try {
            int index = -1;
            for (int i = 0; i < contactList.size(); i++) {
                String[] contactSplit = contactList.get(i).split(" -");
                if (contactSplit[0].equalsIgnoreCase(name)) {
                    System.out.println();
                    index = i;
                }
            }
            return index;
        } catch (IndexOutOfBoundsException e) {
            return -1;
        }
    }

    private static void addContactToList() {
        String name = input.getString("Enter the contact name.");
        long phoneNumber = validatePhoneNumber(input.getString("Enter the contact phone number."));
        int index = searchContactList(name);
        Contact contact = new Contact(name, phoneNumber);
        if (index == -1) {
            contactList.add(contact.toString());
        } else if (index != 1 && input.yesNo("Contact exists. Do you want to overwrite the contact? (y/n)")) {
            contactList.remove(index);
            contactList.add(contact.toString());
        }
    }

    private static void deleteContact() {
        int index = searchContactList(input.getString("Enter the contact name."));
        if (index != -1) {
            contactList.remove(index);
            System.out.println("Contact has been deleted. Thank you, come again");
        } else {
            System.out.println("Contact either does not exist or is spelled incorrectly");
        }

    }

    private static void searchContactByName() {
        String searchName = input.getString("Which contact would you like to see?");
        for (int i = 0; i < contactList.size(); i++) {
            if (i == 0) {
                System.out.printf("%-20s | %12s\n", "Name", "Phone Number");
            }
            if (contactList.get(i).toLowerCase().contains(searchName.toLowerCase())) {
                printListFormat(contactList.get(i));
            }
        }
    }

    private static void writeToContacts() {
        try {
            Files.write(contactsPath, contactList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printListFormat(List<String> contactList) {
        System.out.printf("%-20s | %12s\n", "Name", "Phone Number");
        for (String contact : contactList) {
            String[] contactSplit = contact.split("-");

            System.out.printf("%-20s | %-12s%n", contactSplit[0], formatPhoneNumber(contactSplit[1].trim()));
        }
    }

    private static void printListFormat(String contact) {
        String[] contactSplit = contact.split("-");

        System.out.printf("%-20s | %-12s%n", contactSplit[0], formatPhoneNumber(contactSplit[1].trim()));
    }

    private static String formatPhoneNumber(String phoneNum) {
        String[] phoneNums = phoneNum.split("");
        StringBuilder formattedPhoneNum = new StringBuilder();
        if (phoneNums.length == 10) {
            for (int i = 0; i < phoneNums.length; i++) {
                if (i == 2 || i == 5) {
                    formattedPhoneNum.append(phoneNums[i]).append("-");
                } else {
                    formattedPhoneNum.append(phoneNums[i]);
                }
            }
        } else {
            for (int i = 0; i < phoneNums.length; i++) {
                if (i == 2) {
                    formattedPhoneNum.append(phoneNums[i]).append("-");
                } else {
                    formattedPhoneNum.append(phoneNums[i]);
                }
            }
        }
        return formattedPhoneNum.toString();
    }

    private static long validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() == 7 || phoneNumber.length() == 10) {
            return Long.parseLong(phoneNumber);
        }
        String userInput = input.getString("Phone numbers must be either 7 or 10 digits.");
        return validatePhoneNumber(userInput);
    }
}
