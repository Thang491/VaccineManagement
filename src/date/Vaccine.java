/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

import java.io.Serializable;

/**
 *
 * @author admin
 */
public class Vaccine implements Serializable{
    private String vaccineID;
    private String NamevaccineID;
    
    public Vaccine(){
    }

    public void setNamevaccineID(String NamevaccineID) {
        this.NamevaccineID = NamevaccineID;
    }

    public Vaccine(String vaccineID, String NamevaccineID) {
        this.vaccineID = vaccineID;
        this.NamevaccineID = NamevaccineID;
    }

    public String getVaccineID() {
        return vaccineID;
    }

   
    

    public String getInjectionID() {
        return NamevaccineID;
    }

   

    @Override
    public String toString() {
        return String.format("|%-15s|%-15s|",vaccineID, NamevaccineID);
    }
}