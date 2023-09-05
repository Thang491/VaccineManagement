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
public class Students implements Serializable{
    private String studentID;
    private String studentName;

    public Students(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    
    

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return String.format("|%-15s|%-15s|",studentID, studentName);
    }
}
