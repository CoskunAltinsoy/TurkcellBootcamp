package com.example.ecommerce.adapters;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FakePostServiceAdapter implements PosService{
    @Override
    public void pay() {
        boolean isPaymentSuccessful = new Random().nextBoolean();
        if (!isPaymentSuccessful){
            throw new RuntimeException("Payment failed.");
        }
    }
}