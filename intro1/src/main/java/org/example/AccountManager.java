package org.example;

import java.math.BigDecimal;
import java.sql.SQLOutput;

public class AccountManager {

    public double withDraw(Account account, double value){
        if (account.getBalance() > value) {
            account.setBalance(account.getBalance() - value);
            System.out.println("a");
        }
        else{
            System.out.println("Yetersiz Bakiye");
        }
        return account.getBalance();
    }

    public double deposit(Account account, double value){
        account.setBalance(account.getBalance() + value);
        System.out.println("Para yatırıldı.");
        return account.getBalance();
    }
}
