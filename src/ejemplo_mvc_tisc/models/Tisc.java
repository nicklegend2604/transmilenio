/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplo_mvc_tisc.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Gabriel Huertas <gabriel970826@gmail.com>
 */
public class Tisc implements Serializable{
    
    private String serial; //17 digits, can start with zero
    private String type;
    private LocalDate expeditionDate;
    private Integer balance;

    public Tisc() {
    }

    public Tisc(String serial, String type, LocalDate expeditionDate, Integer balance) {
        this.serial = serial;
        this.type = type;
        this.expeditionDate = expeditionDate;
        this.balance = balance;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getExpeditionDate() {
        return expeditionDate;
    }

    public void setExpeditionDate(LocalDate expeditionDate) {
        this.expeditionDate = expeditionDate;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return serial+", "+ type + ", " + expeditionDate.format(DateTimeFormatter.ISO_DATE) +", "+ balance + "\n";
    }
    
    
    
}
