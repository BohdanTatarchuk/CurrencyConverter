package com.example.converter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "converts_into", schema = "public")
public class ConvertsInto
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exchange_id")
    private int id;

    @Column(name = "currency_a")
    private String currencyA;

    @Column(name = "currency_b")
    private String currencyB;

    @Column(name = "rate")
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
