package com.example.converter.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "exchange_history", schema = "public")
public class ExchangeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "history_id")
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exchange_id", referencedColumnName = "exchange_id")
    private ConvertsInto convertsInto;

    @Column(name = "date")
    private LocalDateTime time;

    public ExchangeHistory(User user, ConvertsInto convertsInto, LocalDateTime time) {
        this.user = user;
        this.convertsInto = convertsInto;
        this.time = time;
    }

    public ExchangeHistory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ConvertsInto getConvertsInto() {
        return convertsInto;
    }

    public void setConvertsInto(ConvertsInto convertsInto) {
        this.convertsInto = convertsInto;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
