package com.ferran.workingliquiditymanager.Presenter;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TransactionDetailActivity extends SingleFragmentActivity {

    @Override
    public Fragment createFragment() {
        return TransactionDetailFragment.newInstance();
    }

}
