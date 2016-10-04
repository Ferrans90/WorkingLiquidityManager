package com.ferran.workingliquiditymanager.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.ferran.workingliquiditymanager.Model.TransactionDbSchema.*;

public class TransactionLab {
    private static TransactionLab transactionLab;
    private Context mContext;
    private SQLiteDatabase mDb;

    private TransactionLab(Context context) {
        mContext = context.getApplicationContext();
        mDb = new TransactionBaseHelper(mContext).getWritableDatabase();
    }

    public static TransactionLab get(Context context) {
        if (transactionLab == null) {
            transactionLab = new TransactionLab(context);
            return transactionLab;
        }
        return transactionLab;
    }

    public Transaction getTransaction(UUID id) {
        TransactionCursorWrapper cursor = queryTransactions(TransactionTable.Cols.UUID + "=?", new String[]{id.toString()});
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getTransaction();
        } finally {
            cursor.close();
        }
    }

    public void addTransaction(Transaction ts) {
        ContentValues values = getContentValues(ts);
        mDb.insert(TransactionTable.NAME, null, values);
    }

    public void updateTransaction(Transaction ts) {
        String uuidString = ts.getUuid().toString();
        ContentValues values = getContentValues(ts);
        mDb.update(TransactionTable.NAME, values, TransactionTable.Cols.UUID + "=?",
                new String[]{uuidString});
    }

    public void deleteTransaction(Transaction ts) {
        String uuidString = ts.getUuid().toString();
        mDb.delete(TransactionTable.NAME, TransactionTable.Cols.UUID + "=?", new String[]{uuidString});
    }

    public List<Transaction> getTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        TransactionCursorWrapper cursor = queryTransactions(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                transactions.add(cursor.getTransaction());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return transactions;
    }

    private TransactionCursorWrapper queryTransactions(String whereClause, String[] whereArgs) {
        Cursor cursor = mDb.query(TransactionTable.NAME, null,
                whereClause, whereArgs, null, null, null);
        return new TransactionCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Transaction transaction) {
        ContentValues values = new ContentValues();
        values.put(TransactionTable.Cols.UUID, transaction.getUuid().toString());
        values.put(TransactionTable.Cols.TIME, transaction.getTime().getTimeInMillis());
        values.put(TransactionTable.Cols.OWNER, transaction.getOwner());
        values.put(TransactionTable.Cols.ITEM, transaction.getItemName());
        values.put(TransactionTable.Cols.AMOUT, transaction.getAmount().toString());
        values.put(TransactionTable.Cols.HASINVOICE, transaction.isHasInvoice() ? 1 : 0);
        return values;
    }
}
