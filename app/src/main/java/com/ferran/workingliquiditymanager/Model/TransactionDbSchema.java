package com.ferran.workingliquiditymanager.Model;

public class TransactionDbSchema {
    public static final class TransactionTable {
        public static final String NAME = "transactions";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TIME = "time";
            public static final String OWNER = "owner";
            public static final String ITEM = "item";
            public static final String AMOUT = "amount";
            public static final String HASINVOICE = "hasinvoice";
        }
    }
}
