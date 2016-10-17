package com.ferran.workingliquiditymanager.Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.ferran.workingliquiditymanager.Model.TransactionDbSchema.*;

public class TransactionBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "transactionBase.db";

    public TransactionBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TransactionTable.NAME + "("
                + TransactionTable.Cols.UUID + " primary key, "
                + TransactionTable.Cols.TIME + ", "
                + TransactionTable.Cols.OWNER + ", "
                + TransactionTable.Cols.ITEM + ", "
                + TransactionTable.Cols.AMOUT + ", "
                + TransactionTable.Cols.HASINVOICE + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
