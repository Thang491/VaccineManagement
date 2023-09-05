/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author admin
 */
public class ListVaccine extends ArrayList<Injection> {

    ArrayList<Injection> list = new ArrayList<>();
    ArrayList<Vaccine> listv = new ArrayList<>();
    ArrayList<Students> liststd = new ArrayList<>();
    ArrayList<String> list1 = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void showallStudent() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
        }

    }

    public void addStudent() {
        System.out.println("\nThis student has no injection information yet! What do you want to do?");
        System.out.println("1. Add 1st injection information.");
        System.out.println("2. Add all injection information.");
        System.out.println("3. Cancel - Do not add injection information for this student.");
        int menu = Utils.getInt("Your Choice: ");
        switch (menu) {
            case 1:
                FirstInjection();
                break;
            case 2:
                allInjection();
                break;
            case 3:
                System.out.println("\nCancel! Do not add this student's injection information!\n");
                break;
        }
    }

    public void allInjection() {
        String studentID, vaccineID, injectionID, injectionPlace1, injectionPlace2;
        Date injectionDate1, injectionDate2, updateDate2;

        String choicee, updatePlace2;

        while (true) {
            studentID = Utils.getString("Input studentID:");
            Students xxx = searchStudentsID(studentID);

            if (xxx != null) {
                break;
            } else {
                System.out.println("Student does not exist! Try again!");
            }
        }
        Injection xx = searchStudentID(studentID);
        if (xx != null) {
            if (xx.getInjectionDate2() != null && xx.getInjectionPlace2() != null) {
                System.out.println("This student has completed 2 injections. No more input required.!");
                System.out.println("----------------------------------------------------------------------");
            } else {
                String choice;
                do {
                    System.out.println("1 injection has been given");
                    System.out.print("Do you want to add Injection 2 ? (Yes/No):  ");
                    choice = sc.nextLine().trim();
                    if (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No"))) {
                        System.out.println("Please enter correct (Yes/No!)");
                    }
                    // choice đánh từ bàn phím vào nếu khác sẽ do again
                } while (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No")));
                if (choice.equalsIgnoreCase("Yes")) {
                    updatePlace2 = Utils.getString("Input injection place 2:");
                    xx.setInjectionPlace2(updatePlace2);
                    while (true) {
                        updateDate2 = Utils.getDate("Please input new date 2: ");
                        if (Utils.getDifferenceDays(xx.getInjectionDate1(), updateDate2) >= 28) {
                            break;
                        } else {
                            System.out.println("The date of the 2nd injection must be after 28 days count from the 1st day.");
                        }
                        System.out.println("-----------------------------------------------------------------------------");
                    }

                    xx.setInjectionDate2(updateDate2);
                    System.out.println(xx.toString());
                    System.out.println("Student has completed 2 injections ");

                } else {
                    System.out.println("That student's injection information remains the same!");
                }
            }
        } else {
            do {
                vaccineID = Utils.getString("Input vaccineID:");
                Vaccine xxx = searchVaccineID(vaccineID);
                if (xxx == null) {
                    System.out.println("This vaccineID not exists!!");
                } else {
                    break;
                }
            } while (true);
            do {
                injectionID = Utils.getString("Input injectionID:");
                Injection xxx = searchInjectionID(injectionID);
                if (xxx == null) {
                    break;
                } else {
                    System.out.println("Injection ID already exists, please enter another ID");
                }
            } while (true);
            injectionPlace1 = Utils.getString("Input injection place 1: ");
            injectionDate1 = Utils.getDate("Input injection date 1: ");
            injectionPlace2 = Utils.getString("Input injection place 2: ");

            while (true) {
                injectionDate2 = Utils.getDate("Input injection Date 2: ");
                if (injectionDate2 == null) {
                    break;
                } else {
                    if (Utils.getDifferenceDays(injectionDate1, injectionDate2) >= 28) {
                        break;
                    } else {
                        System.out.println("The date of the 2nd injection must be after 28 days count from the 1st day.");
                    }
                    System.out.println("-----------------------------------------------------------------------------");
                }
            }

            list.add(new Injection(studentID, vaccineID, injectionID, injectionPlace1, injectionDate1, injectionPlace2, injectionDate2));
            System.out.println("Add a new students successfully ");
            writeInjection();
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Input (Yes) to continue adding another students || Input (No) to return to the menu");

            String choice;
            do {
                System.out.print("(Yes/No): ");
                choice = sc.nextLine().trim();
                if (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No"))) {
                    System.out.println("Please enter correct (Yes/No!)");
                }
                // choice đánh từ bàn phím vào nếu khác sẽ do again
            } while (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No")));
            if (choice.equalsIgnoreCase("Yes")) {
                this.addStudent();
            }
        }
    }

    public void FirstInjection() {

        String studentID, vaccineID, injectionID, injectionPlace1;
        Date injectionDate1, updateDate2;

        String choicee, updatePlace2;

        while (true) {
            studentID = Utils.getString("Input studentID:");
            Students xxx = searchStudentsID(studentID);

            if (xxx != null) {
                break;
            } else {
                System.out.println("Student does not exist! Try again!");
            }
        }
        Injection xx = searchStudentID(studentID);
        if (xx != null) {
            if (xx.getInjectionDate2() != null && xx.getInjectionPlace2() != null) {
                System.out.println("This student has completed 2 injections. No more input required.!");
            } else {
                String choice;
                do {
                    System.out.println("1 injection has been given!");
                    System.out.print("Do you want to add Injection 2 ? (Yes/No):  ");
                    choice = sc.nextLine().trim();
                    if (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No"))) {
                        System.out.println("Please enter correct (Yes/No!)");
                    }
                    // choice đánh từ bàn phím vào nếu khác sẽ do again
                } while (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No")));
                if (choice.equalsIgnoreCase("Yes")) {
                    updatePlace2 = Utils.getString("Input injection place 2:");
                    xx.setInjectionPlace2(updatePlace2);
                    while (true) {
                        updateDate2 = Utils.getDate("Input new date2: ");
                        if (Utils.getDifferenceDays(xx.getInjectionDate1(), updateDate2) >= 28) {
                            break;
                        } else {
                            System.out.println("The date of the 2nd injection must be after 28 days count from the 1st day.");
                        }
                        System.out.println("-----------------------------------------------------------------------------");
                    }

                    xx.setInjectionDate2(updateDate2);
                    System.out.println(xx.toString());
                    System.out.println("Student has completed 2 injections ");

                } else {
                    System.out.println("That student's injection information remains the same!");
                }
            }
        } else {
            do {
                vaccineID = Utils.getString("Input vaccineID:");
                Vaccine xxx = searchVaccineID(vaccineID);
                if (xxx == null) {
                    System.out.println("This vaccineID not exists!!");
                } else {
                    break;
                }
            } while (true);
            do {
                injectionID = Utils.getString("Input injectionID:");
                Injection xxx = searchInjectionID(injectionID);
                if (xxx == null) {
                    break;
                } else {
                    System.out.println("Injection ID already exists, please enter another ID");
                }
            } while (true);
            injectionPlace1 = Utils.getString("Input injection place 1: ");
            injectionDate1 = Utils.getDate("Input injection date 1: ");

            list.add(new Injection(studentID, vaccineID, injectionID, injectionPlace1, injectionDate1, null, null));
            System.out.println("Add a new students successfully ");
            writeInjection();
            System.out.println("----------------------------------------------------------------------------");
            System.out.println("Input (Yes) to continue adding another students || Input (No) to return to the menu");
            String choice;
            do {
                System.out.print("(Yes/No): ");
                choice = sc.nextLine().trim();
                if (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No"))) {
                    System.out.println("Please enter correct (Yes/No!)");
                }
                // choice đánh từ bàn phím vào nếu khác sẽ do again
            } while (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No")));
            if (choice.equalsIgnoreCase("Yes")) {
                this.addStudent();
            }
        }
    }

    public void updateStudents() {
        String injectionID, updatePlace2;
        Date updateDate2;
        injectionID = Utils.getString("Please enter injection Id:");
        Injection xxx = searchInjectionID(injectionID);
        if (xxx == null) {
            System.out.println("This injection ID not exists!!");
        } else {
            System.out.println(xxx.toString());
            updatePlace2 = Utils.getString("Input injection place 2:");
            xxx.setInjectionPlace2(updatePlace2);
            while (true) {
                updateDate2 = Utils.getDate("Input new date2: ");
                if (Utils.getDifferenceDays(xxx.getInjectionDate1(), updateDate2) >= 28) {
                    break;
                } else {
                    System.out.println("The date of the 2nd injection must be after 28 days count from the 1st day.");
                }
                System.out.println("-----------------------------------------------------------------------------");
            }

            xxx.setInjectionDate2(updateDate2);
            System.out.println(xxx.toString());
            System.out.println("Student has completed 2 injections ");
            writeInjection();

        }

    }

    public Injection searchInjectionID(String InjectionID) {
        if (list.isEmpty()) {
            return null;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getInjectionID().equalsIgnoreCase(InjectionID)) {
                return list.get(i);
            }
        }
        return null;

    }

    public void deleteStudent() {
        String injectionId;
        String choice;
        injectionId = Utils.getString("Injection Id you want to delete: ");
        Injection xxx = searchInjectionID(injectionId);
        if (xxx == null) {
            System.out.println("Injection does not exist");
        } else {
            do {
                System.out.println(xxx.toString());
                System.out.print("Do you sure delete this InjectionID(Yes/No): ");
                choice = sc.nextLine().trim();
                if (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No"))) {
                    System.out.println("Please enter correct (Yes/No!)");
                }
            } while (!(choice.equalsIgnoreCase("Yes") || choice.equalsIgnoreCase("No")));
            if (choice.equalsIgnoreCase("Yes")) {
                list.remove(xxx);
                System.out.println("Remove succesfully!");
                writeInjection();
            }
            if (choice.equalsIgnoreCase("No")) {
                System.out.println("Remove failed!");
            }
        }

    }

    public Injection searchStudentID(String studentID) {
        if (list.isEmpty()) {
            return null;
        }

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStudentID().equalsIgnoreCase(studentID)) {
                return list.get(i);
            }
        }
        return null;

    }
    
    public void searchPlace(){
        String place = Utils.getString("Input Place: ");
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getInjectionPlace1().contains(place)){
                System.out.println(list.get(i).toString());
            }
        }
    }
    public void searchinforbyStudentId() {
        System.out.println("1. Search injection by ID.");
        System.out.println("2. Search injection by place.");
        System.out.println("3. Cancel - Do not add injection information for this student.");
        int menu = Utils.getInt("Your Choice: ");
        switch (menu) {
            case 1:
                searchStudentbyID();
                break;
            case 2:
                searchStudentbyName();
                break;
            case 3:
                System.out.println("\nCancel! Do not add this student's injection information!\n");
                break;
        }
    }

    public void searchStudentbyName() {
        int c = 0;
        String place = Utils.getString("Input place1 to search: ");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getInjectionPlace1().contains(place)) {
                System.out.println(list.get(i).toString());
                c++;
            }
            
        }
        if(c == 0){
            System.out.println("Place1 not exists!....");
        }

    }

    public void searchStudentbyID() {
        String studentId;
        studentId = Utils.getString("Student Id you want to find: ");
        Injection xxx = searchStudentID(studentId);
        if (xxx == null) {
            System.out.println("Injection does not exist");
            System.out.println("-----------------------------------------");
        } else {
            System.out.println("Injection you want to search!");
            System.out.println(xxx.toString());
            System.out.println("---------------------------------------");

        }
    }
    
    

    public void writeVaccine() {
        try {
            String fileName = "vaccine.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            listv.add(new Vaccine("Covid-V001", "AstraZeneca"));
            listv.add(new Vaccine("Covid-V002", "SPUTNIK V"));
            listv.add(new Vaccine("Covid-V003", "Vero Cell"));
            listv.add(new Vaccine("Covid-V004", "Pfiezer"));
            listv.add(new Vaccine("Covid-V005", "Moderna"));
            oos.writeObject(listv);

            oos.close();
            fos.close();
        } catch (Exception e) {
        }
        System.out.println("Loading file vaccine.dat succcesfully!");
    }

    public void readFileVaccine() {
        try {
            File f = new File("vaccine.dat");
            if (!f.exists()) {
                System.out.println("Empty list.");
                return;
            }
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);

            ArrayList<Vaccine> vc = (ArrayList<Vaccine>) ois.readObject();
            for (Vaccine vcid : vc) {
                System.out.println(vcid.toString());
                System.out.println("----------------------------");
                //listv.add(vcid);
            }
            ois.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("IOException occured :" + e.getMessage());
        }

    }

    public void writeStudent() {
        try {
            String fileName = "students.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            liststd.add(new Students("SE15000", "HOA HUNG"));
            liststd.add(new Students("SE15001", "TRUONG PHI"));
            liststd.add(new Students("SE15002", "QUAN VU"));
            liststd.add(new Students("SE15003", "LUU BI"));
            liststd.add(new Students("SE15004", "TAO THAO"));
            liststd.add(new Students("SE15005", "TON QUYEN"));
            liststd.add(new Students("SE15006", "CHU DU"));
            liststd.add(new Students("SE15007", "GIA CAT LUONG"));
            liststd.add(new Students("SE15008", "TAO PHI"));
            liststd.add(new Students("SE15009", "TRIEU VAN"));

            oos.writeObject(liststd);

            oos.close();
            fos.close();
        } catch (Exception e) {
        }
        System.out.println("Loading file student.dat successfully!");
    }

    public void readFileStudent() {
        try {
            File f = new File("students.dat");
            if (!f.exists()) {
                System.out.println("Empty list.");
                return;
            }
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Students> vc = (ArrayList<Students>) ois.readObject();
            for (Students sd : vc) {
                System.out.println(sd.toString());
                System.out.println("------------------------------");
                //liststd.add(sd);
            }
            ois.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("IOException occured :" + e.getMessage());
        }

    }

    public void writeInjection() {
        try {
            String fileName = "injection.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);

            oos.close();
            fos.close();
        } catch (Exception e) {
        }
        System.out.println("Loading file injection.dat successfully!");

    }

    public void readFileInjection() {
        try {
            File f = new File("injection.dat");
            if (!f.exists()) {
                System.out.println("Empty list.");
                return;
            }
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Injection> it = (ArrayList<Injection>) ois.readObject();
            for (Injection sd : it) {
                System.out.println(sd.toString());
                System.out.println("-----------------------------------------------------");
                list.add(sd);
            }
            ois.close();
            fis.close();

        } catch (Exception e) {
            System.out.println("IOException occured :" + e.getMessage());
        }

    }

    public void importFile() {
        this.writeVaccine();
        this.writeStudent();
        System.out.println("Information student!");
        this.readFileStudent();
        System.out.println("Information vaccine!");
        this.readFileVaccine();
        System.out.println("Information injection!");
        readFileInjection();
    }

    public Vaccine searchVaccineID(String vaccineID) {
        if (listv.isEmpty()) {
            return null;
        }

        for (int i = 0; i < listv.size(); i++) {
            if (listv.get(i).getVaccineID().equalsIgnoreCase(vaccineID)) {
                return listv.get(i);
            }
        }
        return null;
    }

    public Students searchStudentsID(String studentID) {
        if (liststd.isEmpty()) {
            return null;
        }

        for (int i = 0; i < liststd.size(); i++) {
            if (liststd.get(i).getStudentID().equalsIgnoreCase(studentID)) {
                return liststd.get(i);
            }
        }
        return null;

    }

   

    public void updateStudentsbyID() {
        String studentID, updatePlace2;
        Date updateDate2;
        studentID = Utils.getString("Please enter injection Id:");
        Injection xxx = searchStudentID(studentID);
        if (xxx == null) {
            System.out.println("This injection ID not exists!!");
        } else {
            updatePlace2 = Utils.getString("Input injection place 2:");
            xxx.setInjectionPlace2(updatePlace2);
            while (true) {
                updateDate2 = Utils.getDate("Please enter new date2: ");
                if (Utils.getDifferenceDays(xxx.getInjectionDate1(), updateDate2) >= 28) {
                    break;
                } else {
                    System.out.println("The date of the 2nd injection must be after 28 days count from the 1st day.");
                }
                System.out.println("-----------------------------------------------------------------------------");
            }

            xxx.setInjectionDate2(updateDate2);
            System.out.println(xxx.toString());
            System.out.println("Student has completed 2 injections ");
            writeInjection();

        }

    }
    
    public void createKey() {
        try {
            SecureRandom sr = new SecureRandom();
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024, sr);
            KeyPair kp = kpg.genKeyPair();
            PublicKey publicKey = kp.getPublic();
            PrivateKey privateKey = kp.getPrivate();
            File publicKeyFile = createKeyFile(new File("publicKey.rsa"));
            File privateKeyFile = createKeyFile(new File("privateKey.rsa"));
            FileOutputStream fos = new FileOutputStream(publicKeyFile);
            fos.write(publicKey.getEncoded());
            fos.close();
            fos = new FileOutputStream(privateKeyFile);
            fos.write(privateKey.getEncoded());
            fos.close();
            System.out.println("Generate key successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File createKeyFile(File file) throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        } else {
            file.delete();
            file.createNewFile();
        }
        return file;
    }

    public void security() throws Exception {
        try {

            byte[] b;
            try (FileInputStream fis = new FileInputStream("publicKey.rsa")) {
                b = new byte[fis.available()];
                fis.read(b);
            }
            X509EncodedKeySpec spec = new X509EncodedKeySpec(b);
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = factory.generatePublic(spec);
            Cipher c = Cipher.getInstance("RSA");
            c.init(Cipher.ENCRYPT_MODE, pubKey);
            for (Injection d : list) {
                byte encryptOut[] = c.doFinal(d.toString().getBytes());
                String strEncrypt = Base64.getEncoder().encodeToString(encryptOut);
                System.out.println("ID Student" + d.getStudentID() + "code hash " + strEncrypt);

            }

        } catch (IOException | InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
        }
        writeInjection();
    }

}
