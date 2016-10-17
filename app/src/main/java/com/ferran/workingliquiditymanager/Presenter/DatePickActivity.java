package com.ferran.workingliquiditymanager.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.GregorianCalendar;

public class DatePickActivity extends SingleFragmentActivity {
    private static final String EXTRA_DATE = "extradate";

    @Override
    public Fragment createFragment() {
        GregorianCalendar gc = (GregorianCalendar) getIntent().getSerializableExtra(EXTRA_DATE);
        return DatePickFragment.newInstance(gc);
    }

    public static Intent newIntent(Context context, GregorianCalendar gc) {
        Intent intent = new Intent(context, DatePickActivity.class);
        intent.putExtra(EXTRA_DATE, gc);
        return intent;
    }
}
