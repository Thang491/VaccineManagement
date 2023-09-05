/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author admin
 */
public class Menu extends ArrayList<String> implements I_Menu{
    ArrayList<String> list = new ArrayList<>();
    public Menu(){
        super();
    }

    @Override
    public void addItem(String s) {
       list.add(s);
    }

    @Override
    public int getChoice() {
        Scanner sc = new Scanner(System.in);
        int result = 0;
        boolean check = true;
        do {            
            try {
                System.out.print("Input your choice:");
                result = Integer.parseInt(sc.nextLine().trim());
                check = false;
            } catch (Exception e) {
            }
        } while (check);
        return result;

    }

    @Override
    public void showMenu() {
        for (String Menu : list) {
            System.out.println(Menu);
        }
    }
    
}
