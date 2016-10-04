package com.ferran.workingliquiditymanager.Model;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.math.BigDecimal;
import java.util.GregorianCalendar;
import java.util.UUID;

import static com.ferran.workingliquiditymanager.Model.TransactionDbSchema.*;


public class TransactionCursorWrapper extends CursorWrapper {
    public TransactionCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Transaction getTransaction() {
        String uuidString = getString(getColumnIndex(TransactionTable.Cols.UUID));
        GregorianCalendar time = new GregorianCalendar();
        time.setTimeInMillis(getLong(getColumnIndex(TransactionTable.Cols.TIME)));
        String owner = getString(getColumnIndex(TransactionTable.Cols.OWNER));
        String item = getString(getColumnIndex(TransactionTable.Cols.ITEM));
        BigDecimal amout = new BigDecimal(getString(getColumnIndex(TransactionTable.Cols.AMOUT)));
        int hasInvoice = getInt(getColumnIndex(TransactionTable.Cols.HASINVOICE));

        Transaction ts = new Transaction(UUID.fromString(uuidString));
        ts.setTime(time);
        ts.setOwner(owner);
        ts.setItemName(item);
        ts.setAmount(amout);
        ts.setHasInvoice(hasInvoice != 0);

        return  ts;
    }
}
