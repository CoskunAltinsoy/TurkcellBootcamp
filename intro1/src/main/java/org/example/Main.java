package org.example;

import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        Account account = new Account(1,1000,"description");

        AccountManager accountManager = new AccountManager();

        accountManager.withDraw(account,1200);
        System.out.println(accountManager.deposit(account, 1000));
    }


}