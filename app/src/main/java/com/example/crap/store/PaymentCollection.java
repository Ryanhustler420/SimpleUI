package com.example.crap.store;

import com.example.crap.model.Payment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PaymentCollection {

    private ArrayList<Payment> payments = new ArrayList<>();

    public PaymentCollection() {
        // Date date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        payments.add(new Payment(new GregorianCalendar(2019, Calendar.MARCH, 10).getTime(), 199));
        payments.add(new Payment(new GregorianCalendar(2019, Calendar.APRIL, 11).getTime(), 99));
        payments.add(new Payment(new GregorianCalendar(2019, Calendar.MAY, 12).getTime(), 129));
        payments.add(new Payment(new GregorianCalendar(2019, Calendar.JUNE, 13).getTime(), 199));
        payments.add(new Payment(new GregorianCalendar(2019, Calendar.JULY, 13).getTime(), 99));
        payments.add(new Payment(new GregorianCalendar(2019, Calendar.AUGUST, 14).getTime(), 99));
        payments.add(new Payment(new GregorianCalendar(2019, Calendar.SEPTEMBER, 16).getTime(), 99));
    }

    public ArrayList<Payment> getPayemtsCollections() {
        return payments;
    }
}
