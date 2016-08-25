package com.ferran.workingliquiditymanager.Model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Transaction {
    private GregorianCalendar time;
    private String owner;
    private String itemName;
    private float amount;
    private boolean haveInvoice;

    public Transaction() {

    }

    public Transaction(GregorianCalendar time, String owner, String itemName,
                       float amount, boolean haveInvoice) {
        this.time = time;
        this.owner = owner;
        this.itemName = itemName;
        this.amount = amount;
        this.haveInvoice = haveInvoice;
    }

    public Transaction(Transaction transaction) {
        this.time = transaction.getTime();
        this.owner = transaction.getOwner();
        this.itemName = transaction.getItemName();
        this.amount = transaction.getAmount();
        this.haveInvoice = transaction.isHaveInvoice();
    }

    public static Transaction newInstance(Transaction transaction) {
        return new Transaction(transaction);
    }

    public GregorianCalendar getTime() {
        return time;
    }

    public void setTime(GregorianCalendar time) {
        this.time = time;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public boolean isHaveInvoice() {
        return haveInvoice;
    }

    public void setHaveInvoice(boolean haveInvoice) {
        this.haveInvoice = haveInvoice;
    }

    @Override
    public String toString() {
        return "Transaction{ " + time.get(Calendar.YEAR) + "-" + time.get(Calendar.MONTH) + "-"
                + time.get(Calendar.DAY_OF_MONTH)
                + ", Owner: " + owner + ", ItemName: " + itemName + ", Amount: " + amount
                + ", Invoice state: " + (haveInvoice ? "have invoice" : "haven't invoice") + "}";
    }
}
