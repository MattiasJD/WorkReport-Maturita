package com.example.maturitazadani;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkReport {
    private int id;
    private LocalTime from;
    private LocalTime to;
    private LocalDate date;

    public LocalTime getFrom() {
        return from;
    }

    public void setFrom(LocalTime from) {
        this.from = from;
    }

    public LocalTime getTo() {
        return to;
    }

    public void setTo(LocalTime to) {
        this.to = to;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public WorkReport() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "" + id +""+  from + "" + to + "" + date;
    }
}
