package com.kodlama.io.rentacar.adapters;

import java.util.Random;

public class FakePostServiceAdapter implements PosService{
    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if (!isPaymentSuccessful){
            throw new RuntimeException("Payment failed.");
        }
    }
}
