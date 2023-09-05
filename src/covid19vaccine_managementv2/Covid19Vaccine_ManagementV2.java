/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package covid19vaccine_managementv2;

import date.I_Menu;
import date.ListVaccine;
import date.Menu;

/**
 *
 * @author admin
 */
public class Covid19Vaccine_ManagementV2 {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        I_Menu menu = new Menu();
        menu.addItem("WELL COME TO COVID-19 VACCINE MANEGEMENT");
        menu.addItem("       <SE151060 - ĐỖ HỒNG THẮNG>");
        menu.addItem("        Select the options below");
        menu.addItem("*******************************************");
        menu.addItem(
                     "1. Show information all students have been injected\n" +
                     "2. Add student's vaccine injection information\n" +
                     "3. Updating information of students' vaccine injection\n" +
                     "4. Delete student vaccine injection information\n" +
                     "5. Search for injection information by studentID\n" +
                     "6. Store data to file\n" +
                     "7. Information Encryption\n"+
                     "8. Others - Quit");
              
               ListVaccine a = new ListVaccine();
               a.importFile();
               a.createKey();
        int choice;
        do {
            menu.showMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    a.showallStudent();
                    break;
                case 2:
                    a.addStudent();
                    
                    break;
                case 3:
                    a.updateStudents();
                    
                    break;

                case 4:
                    a.deleteStudent();
                    
                    
                    break;
                case 5:
                    a.searchinforbyStudentId();
                    break;

                case 6:
                    a.writeInjection();
                    

                    break;
                case 7 : 
                    a.security();
                    break;
                case 8:
                    System.out.println("          THANKS FOR USING MY APP!");
                    System.out.println("!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@!");
                    System.out.println("---------------------------------------------------");
                    break;
            }
        } while (choice != 8);
    
    }
}
