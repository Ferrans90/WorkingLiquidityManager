package com.ferran.workingliquiditymanager.Presenter.Presenter;

import android.support.v4.app.Fragment;

public class MainPageActivity extends SingleFragmentActivity {
    @Override
    public Fragment createFragment() {
        return MainPageFragment.newInstance();
    }
}
