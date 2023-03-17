package org.example;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        Instructor instructor =
                new Instructor(1,"Mücahit","Altınsoy","Turkcell");

        Applicant applicant
                = new Applicant(2,"Coskun","Altinsoy", "Turkcell");

        userManager.add(instructor);
        userManager.add(applicant);

        User[] users = {instructor, applicant};

        userManager.addMultiple(users);

    }
}