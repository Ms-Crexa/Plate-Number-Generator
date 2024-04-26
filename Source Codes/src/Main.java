import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class customer {
    private final String nameplate;
    private final String c_name;
    private final String Date_registered;
    private final String vin_number;
    private final String registration_id;
    private final String gender;
    private final String birthday;
    private final String country;

    public customer(String nameplate, String c_name, String Date_registered, String vin_number, String registration_id, String gender, String birthday, String country){
        this.nameplate = nameplate;
        this.c_name = c_name;
        this.Date_registered = Date_registered;
        this.vin_number = vin_number;
        this.registration_id = registration_id;
        this.gender = gender;
        this.birthday = birthday;
        this.country = country;
    }

    public String toString(){
        System.out.println("----------------------------------");
        return "Registration #: " + registration_id + "\nPlate Number: " + nameplate + "\nName: " + c_name + "\nDate of Birth: "
                + birthday + "\nGender: " + gender + "\nVehicle Identification Number: "  + vin_number + "\nCountry: " + country + "\nDate Registered: " + Date_registered;
    }
}


public class Main {


    public static void main(String[] args) {

        List <customer> c = new ArrayList <>();
        Scanner s = new Scanner(System.in);
        Scanner s1 = new Scanner(System.in);

        c.listIterator();

        System.out.println("########################################");
        System.out.println("#   Welcome to the Car Registration!   #");
        System.out.println("########################################");
        System.out.print("\nIs this your first time registering? ");
        System.out.print("\n\nChoices:");
        System.out.print("\n(1) Yes, I would like to register.");
        System.out.print("\n(2) No, I would like to update my information.");
        System.out.println("\n(3) No, I would like to read my information.");
        System.out.println("\n(4) Exit.");

        int response = 0;

        while ((response < 1) || (response > 4)) {
            System.out.print("\n\nPrompt: ");
            response = s.nextInt();

            switch (response) {

                case 1:

                    System.out.println("\nYou chose 1");
                    String response2;

                    do {
                        String regis_id, nameplate;
                        String fn, mn, ln, c_name;
                        String birthday, gender, Date_registered, country;
                        String vin_number;

                        boolean check, checkGender;

                        c.listIterator();
                        Iterator <customer> i;

                        System.out.println("\nRegistration Id: ");
                        registration_id();
                        System.out.println("\nGenerated Vehicle Plate Number: ");
                        VehiclePlateNumber();

                        System.out.print("\nKindly input the generated registration ID Number: ");
                        regis_id = s1.nextLine();
                        check = checkId(regis_id);
                        while (!check) {
                            System.out.println("Please check if you have inputted the correct ID number and try again: ");
                            regis_id = s1.nextLine();
                            check = checkId(regis_id);
                        }

                        System.out.print("\nKindly input the Generated Vehicle Plate Number: ");
                        nameplate = s1.nextLine();
                        boolean result = match_TheLetters(nameplate);
                        while (!result) {
                            System.out.println("Please check if you have input the correct Plate Number and try again.");
                            nameplate = s1.nextLine();
                            result = match_TheLetters(nameplate);
                        }

                        System.out.print("\nEnter Your First Name: ");
                        fn = s1.nextLine();
                        System.out.print("Enter Your Middle Initial: ");
                        mn = s1.nextLine();
                        System.out.print("Enter Your Last Name: ");
                        ln = s1.nextLine();
                        c_name = (fn + " " + mn + "." + " " + ln);
                        System.out.print("Enter Your Birthday: ");
                        birthday = s1.nextLine();

                        System.out.print("Enter Gender (Male or Female): ");
                        gender = s1.nextLine();
                        checkGender = checking_gender(gender);
                        while (!checkGender) {
                            System.out.println("Please check if you have input the correct gender and try again.");
                            gender = s1.nextLine();
                            checkGender = checking_gender(gender);
                        }

                        System.out.print("Enter Vehicle Identification Number (17 characters): ");
                        vin_number = s1.nextLine();
                        boolean a = checkIfValid(vin_number);
                        while (!a) {
                            System.out.println("Please check if you have input the correct Vehicle Identification Number and try again.");
                            System.out.print("Enter Vehicle Identification Number (17 characters): ");
                            vin_number = s1.nextLine();
                            a = checkIfValid(vin_number);
                        }
                        System.out.print("What country are you from?: ");
                        country = s1.nextLine();
                        System.out.print("Enter Date registered: ");
                        Date_registered = s1.nextLine();

                        c.add(new customer(nameplate, c_name, Date_registered, vin_number, regis_id, gender, birthday, country));

                        System.out.println("\n----------------------------");
                        System.out.println("Registration Information:");
                        i = c.iterator();
                        while (i.hasNext()) {
                            customer e = i.next();
                            System.out.println(e);
                        }
                        System.out.println("----------------------------------");
                        System.out.println("Do you want to save your information? Input Y if yes or N if no.");
                        response2 = s1.nextLine();
                        if (response2.equalsIgnoreCase("Y")){
                            try{
                                File myObj = new File (regis_id);
                                if (myObj.createNewFile()) {
                                    System.out.println("\nRegistration created: " + myObj.getName());
                                }
                                else{
                                    System.out.println("Registration already exists.");
                                }
                            } catch (IOException e){
                                System.out.println("An error occurred");
                                e.printStackTrace();
                            }
                            try{
                                FileWriter myWriter = new FileWriter(regis_id);
                                myWriter.write("Registration ID: " + regis_id);
                                myWriter.write("\nVehicle Plate Number: " + nameplate);
                                myWriter.write("\nName: " + c_name);
                                myWriter.write("\nDate of Birth: " + birthday);
                                myWriter.write("\nGender: " + gender);
                                myWriter.write("\nVehicle Identification Number: " + vin_number);
                                myWriter.write("\nCountry: " + country);
                                myWriter.write("\nDate Registered: " + Date_registered);
                                myWriter.close();
                            } catch (IOException e) {
                                System.out.println("An error occurred");
                                e.printStackTrace();
                            }
                            System.out.println("\nRegistration Successful! Thank you for using the program.");
                            break;
                        }
                    } while (!response2.equals("Y"));
                    break;


                case 2:
                    System.out.println("You chose 2.");
                    System.out.println("Enter registration number: ");
                    String fileName = s1.nextLine();
                    boolean check = checkId(fileName);

                    while (!check) {
                        System.out.println("\nPlease check if you have inputted the correct ID number and try again: ");
                        fileName = s1.nextLine();
                        check = checkId(fileName);
                    }

                    try{
                        BufferedReader reader = new BufferedReader(new FileReader(fileName));
                        String line;
                        while((line = reader.readLine()) != null){
                            System.out.println(line);
                        }
                        reader.close();
                        System.out.println("\nPlease choose which information to edit: ");
                        System.out.println(" (1) Vehicle Plate Number");
                        System.out.println(" (2) Name");
                        System.out.println(" (3) Date of Birth");
                        System.out.println(" (4) Gender");
                        System.out.println(" (5) Country");
                        System.out.println("\nPrompt: ");
                        int prompt = s1.nextInt();
                        s1.nextLine();
                        if (prompt == 1) {
                            System.out.println("Enter new Vehicle Plate Number: ");
                            String plateNumber = s1.nextLine();
                            var newLineContent = "Vehicle Plate Number: " + plateNumber;
                            ChangeLineInFile changeFile = new ChangeLineInFile();
                            int lineToBeEdited = 2;
                            changeFile.changeALineInATextFile(fileName, newLineContent, lineToBeEdited);
                            System.out.println("Updated Vehicle Plate Number: " + plateNumber);
                        }

                        else if (prompt == 2) {
                            System.out.println("Enter Your First Name: ");
                            String fn = s1.nextLine();
                            System.out.println("Enter Your Middle Initial: ");
                            String mn = s1.nextLine();
                            System.out.println("Enter Your Last Name: ");
                            String ln = s1.nextLine();
                            String c_name = fn + " " + mn + "." + " " + ln;
                            var newLineContent = "Name: " + c_name;

                            ChangeLineInFile changeFile = new ChangeLineInFile();
                            int lineToBeEdited = 3;
                            changeFile.changeALineInATextFile(fileName, newLineContent, lineToBeEdited);
                            System.out.println("Updated Name: " + c_name);
                        }

                        else if (prompt == 3){
                            System.out.println("Enter Date of Birth: ");
                            String dateOfBirth = s1.nextLine();
                            var newLineContent = "Date of Birth: " + dateOfBirth;

                            ChangeLineInFile changeFile = new ChangeLineInFile();
                            int lineToBeEdited = 4;
                            changeFile.changeALineInATextFile(fileName, newLineContent, lineToBeEdited);
                            System.out.println("Updated Date of Birth: " + dateOfBirth);
                        }

                        else if (prompt == 4){
                            System.out.println("Enter type of Gender: ");
                            String gender = s1.nextLine();
                            var newLineContent = "Gender: " + gender;

                            ChangeLineInFile changeFile = new ChangeLineInFile();
                            int lineToBeEdited = 5;
                            changeFile.changeALineInATextFile(fileName, newLineContent, lineToBeEdited);
                            System.out.println("Updated Date of Birth: " + gender);
                        }

                        else if (prompt == 5){
                            System.out.println("Enter new name of country: ");
                            String country = s1.nextLine();
                            var newLineContent = "Country: " + country;

                            ChangeLineInFile changeFile = new ChangeLineInFile();
                            int lineToBeEdited = 6;
                            changeFile.changeALineInATextFile(fileName, newLineContent, lineToBeEdited);
                            System.out.println("Updated Country: " + country);
                        }
                        else{
                            System.out.println("Error: Input not found.");
                        }
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                    break;

                case 3:
                    System.out.println("Enter registration number: ");
                    fileName = s1.nextLine();
                    check = checkId(fileName);

                    while (!check) {
                        System.out.println("\nPlease check if you have inputted the correct ID number and try again: ");
                        fileName = s1.nextLine();
                        check = checkId(fileName);
                    }

                    try{
                        BufferedReader reader = new BufferedReader(new FileReader(fileName));
                        String line;
                        while((line = reader.readLine()) != null){
                            System.out.println(line);
                        }
                        reader.close();
                    } catch (IOException e){
                        e.printStackTrace();
                    }

                case 4:
                    System.out.println("\nThank you for using the program.");
                    break;

                default:
                    System.out.println("Error: Input not found. Please try again.");
            }
        }
    }


    public static void VehiclePlateNumber() {

        int firstLetter = 65 + (int) (Math.random() * 24);
        int secondLetter = 65 + (int) (Math.random() * 24);
        int thirdLetter = 65 + (int) (Math.random() * 24);

        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);

        System.out.println("" + (char) firstLetter + (char) secondLetter + (char) thirdLetter +
                num1 + num2 + num3 + num4);
    }

    public static void registration_id(){

        int firstLetter = 65 + (int) (Math.random() * 24);
        int secondLetter = 65 + (int) (Math.random() * 24);

        int num1 = (int) (Math.random() * 10);
        int num2 = (int) (Math.random() * 10);
        int num3 = (int) (Math.random() * 10);
        int num4 = (int) (Math.random() * 10);

        System.out.println("" + num1 + num2 + num3 + num4 + (char) firstLetter + (char) secondLetter);
    }

    private static boolean checking_gender(String gender) {
        String regex = "^M(ale)?$|^F(emale)?$";
        Pattern p = Pattern.compile(regex);

        if (gender == null) {
            return false;
        }

        Matcher m = p.matcher(gender);

        return m.matches();
    }

    private static boolean checkId(String regis_id) {
        String regex = "[0-9]{4}[A-Z]{2}";
        Pattern p = Pattern.compile(regex);

        if (regis_id == null) {
            return false;
        }

        Matcher m = p.matcher(regis_id);

        return m.matches();

    }

    private static boolean checkIfValid(String vin_number) {
        String regex = "[a-zA-Z0-9]{9}[a-zA-Z0-9-]{2}[0-9]{6}";
        Pattern p = Pattern.compile(regex);

        if (vin_number == null) {
            return false;
        }

        Matcher m = p.matcher(vin_number);

        return m.matches();
    }

    private static boolean match_TheLetters(String nameplate) {

        String regex = "[A-Z]{3}[0-9]{4}";

        Pattern p = Pattern.compile(regex);

        if (nameplate == null) {
            return false;
        }

        Matcher m = p.matcher(nameplate);

        return m.matches();
    }

}


