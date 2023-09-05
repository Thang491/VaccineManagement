/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Utils {
    public static String getStringName(String inputMsg) {
        String s;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(inputMsg);
            s = sc.nextLine().trim().toUpperCase();
            if (s.length() == 0 || s.isEmpty()) {
                System.out.println("Please re-enter!!!");
            } else {
                return s;
            }

        }
    }
    public static Date getDate(String inputMsg){

        int day, month, year;
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
        String dateFormat = "\\d{2}-\\d{2}-\\d{4}";
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        Date date = null;
        d.setLenient(false); 
        do {
            System.out.print(inputMsg);
            String data = sc.nextLine();
            if (data.matches(dateFormat)) {
               
                try {
                    date = d.parse(data);
                    check = true;
                } catch (Exception e) {
                    System.out.println("Please Enter again");;
                    check = false;
                }
            } else {
                System.out.println("DateFormat invalid.");
                check = false;
            }
            
        } while (check != true); 
        return date;
    
        
    }
     public static long getDifferenceDays(Date d1, Date d2){
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        long diff = (c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000);
        return diff;
    }
            
    public static String getString(String message) {
        String result = "";
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        do {
            System.out.print(message);
            String tmp = sc.nextLine().trim().toUpperCase();
            if (!tmp.isEmpty()) {
                result = tmp;
                check = false;
            }
        } while (check);
        return result;
    }
   
    public static double getDouble(String message){
        Double result = 0.0;
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        do {
            try {
                System.out.print(message);
                result = Double.parseDouble(sc.nextLine());
                check = false;
            } catch (Exception e) {
                System.out.println("Please re-enter the Weght of food!");
            }
        } while (check);
        return result;
    }
    public static int getInt(String message) {
        int result = 0;
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        do {
            try {
                System.out.print(message);
                result = Integer.parseInt(sc.nextLine());
                check = false;
            } catch (Exception e) {
            }
        } while (check);
        return result;
    }
    public static boolean getBoolean(String message) {
        boolean result = true;
        Scanner sc = new Scanner(System.in);
        String choice;
        boolean check = true;
        do {

            System.out.print(message);
            //Nhap Y/N thay vi nhap True or False
            do {
                System.out.print("(Y/N): ");
                choice = sc.nextLine();
            } while (!(choice.equals("Y") || choice.equals("N")));
            if (choice.equals("Y")) {
                result = true;
            } else {
                result = false;
            }
            //result = Boolean.parseBoolean(sc.nextLine());
            check = false;

        } while (check);
        return result;
    }
    

}
