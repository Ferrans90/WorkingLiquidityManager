package com.ferran.workingliquiditymanager.Model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.UUID;

public class Transaction {
    private UUID uuid;
    private GregorianCalendar time;
    private String owner;
    private String itemName;
    private BigDecimal amount;
    private boolean hasInvoice;

    public Transaction() {
        this(UUID.randomUUID());
        amount = new BigDecimal(0);
    }

    public Transaction(UUID id) {
        uuid = id;
        time = new GregorianCalendar();
    }

    public Transaction(GregorianCalendar time, String owner, String itemName,
                       BigDecimal amount, boolean hasInvoice) {
        this.uuid = UUID.randomUUID();
        this.time = time;
        this.owner = owner;
        this.itemName = itemName;
        this.amount = amount;
        this.hasInvoice = hasInvoice;
    }

    public Transaction(Transaction transaction) {
        this.uuid = transaction.getUuid();
        this.time = transaction.getTime();
        this.owner = transaction.getOwner();
        this.itemName = transaction.getItemName();
        this.amount = transaction.getAmount();
        this.hasInvoice = transaction.isHasInvoice();
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isHasInvoice() {
        return hasInvoice;
    }

    public void setHasInvoice(boolean hasInvoice) {
        this.hasInvoice = hasInvoice;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Transaction{ " + time.get(Calendar.YEAR) + "-" + time.get(Calendar.MONTH) + "-"
                + time.get(Calendar.DAY_OF_MONTH)
                + ", Owner: " + owner + ", ItemName: " + itemName + ", Amount: " + amount
                + ", Invoice state: " + (hasInvoice ? "have invoice" : "haven't invoice") + "}";
    }
}
