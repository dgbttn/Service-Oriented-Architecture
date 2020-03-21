package SocketExample;

import java.io.Serializable;

public class Student {
    public String id, name, studyClass, phoneNumber, email, address;

    public Student(String id, String name, String studyClass, String phoneNumber, String email, String address) {
        this.id = id;
        this.name = name;
        this.studyClass = studyClass;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }

    public String toString() {
        return "Student information: \n" +
                " - id         : " + this.id + '\n' +
                " - name       : " + this.name + '\n' +
                " - studyClass : " + this.studyClass + '\n' +
                " - phoneNumber: " + this.phoneNumber + '\n' +
                " - email      : " + this.email + '\n' +
                " - address    : " + this.address + '\n' +
                "---------------------------------------------";
    }
}
