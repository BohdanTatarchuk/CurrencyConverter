package com.example.converter.model;

import jakarta.persistence.*;

@Entity
@Table
public class ConvertsInto
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String currencyA;

    private String currencyB;

    private double exchangeRate;

    public ConvertsInto() {}

    public ConvertsInto(String currencyA, String currencyB, double exchangeRate) {
        this.currencyA = currencyA;
        this.currencyB = currencyB;
        this.exchangeRate = exchangeRate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrencyB() {
        return currencyB;
    }

    public void setCurrencyB(String currencyB) {
        this.currencyB = currencyB;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getCurrencyA() {
        return currencyA;
    }

    public void setCurrencyA(String currencyA) {
        this.currencyA = currencyA;
    }
}
