/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package date;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author admin
 */
public class Injection implements Serializable{
    private String studentID;
    private String vaccineID;
    private String injectionID;
    private String injectionPlace1;
    private Date injectionDate1;
    private String injectionPlace2;
    private Date injectionDate2;
    

    public Injection(String studentID, String vaccineID, String injectionID, String injectionPlace1, Date injectionDate1, String injectionPlace2, Date injectionDate2) {
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.injectionID = injectionID;
        this.injectionPlace1 = injectionPlace1;
        this.injectionDate1 = injectionDate1;
        this.injectionPlace2 = injectionPlace2;
        this.injectionDate2 = injectionDate2;
       
    }

   

  

    public String getStudentID() {
        return studentID;
    }

    

    public String getVaccineID() {
        return vaccineID;
    }

   

    public String getInjectionID() {
        return injectionID;
    }

    

    public String getInjectionPlace1() {
        return injectionPlace1;
    }

    public void setInjectionPlace1(String injectionPlace1) {
        this.injectionPlace1 = injectionPlace1;
    }

    public Date getInjectionDate1() {
        return injectionDate1;
    }

    public void setInjectionDate1(Date injectionDate1) {
        this.injectionDate1 = injectionDate1;
    }

    public String getInjectionPlace2() {
        return injectionPlace2;
    }

    public void setInjectionPlace2(String injectionPlace2) {
        this.injectionPlace2 = injectionPlace2;
    }

    public Date getInjectionDate2() {
        return injectionDate2;
    }

    public void setInjectionDate2(Date injectionDate2) {
        this.injectionDate2 = injectionDate2;
    }

    

    @Override
    public String toString() {
       
        return String.format("|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|",studentID, vaccineID, injectionID, injectionPlace1, injectionDate1, injectionPlace2, injectionDate2);
}
}
