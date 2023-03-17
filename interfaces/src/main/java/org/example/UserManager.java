package org.example;

public class UserManager {
    public void add(User user){
        System.out.println(user.getFirstName() +" Kayıt edildi.");
    }

    public void addMultiple(User[] users){
        for (User user : users) {
            add(user);
        }
    }
}
