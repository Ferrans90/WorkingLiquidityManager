package com.ferran.workingliquiditymanager.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TransactionListActivity extends SingleFragmentActivity {

    public static Intent newIntent(Context fromContext) {
        Intent intent = new Intent(fromContext, TransactionListActivity.class);
        return intent;
    }

    @Override
    public Fragment createFragment() {
        return TransactionListFragment.newInstance();
    }
}
