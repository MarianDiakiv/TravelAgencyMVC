package com.marian.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class DateSearch {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date1;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date2;
    public DateSearch() {
    }
    public Date getDate1() {
        return date1;
    }
    public void setDate1(Date date1) {
        this.date1 = date1;
    }
    public Date getDate2() {
        return date2;
    }
    public void setDate2(Date date2) {
        this.date2 = date2;
    }


}
