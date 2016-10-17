package com.ferran.workingliquiditymanager.Presenter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class TransactionDetailActivity extends SingleFragmentActivity {
    private static final String EXTRA_UUID = "extrauuid";

    @Override
    public Fragment createFragment() {
        UUID uuid = (UUID) getIntent().getSerializableExtra(EXTRA_UUID);
        return TransactionDetailFragment.newInstance(uuid);
    }

    public static Intent newIntent(Context context, UUID uuid) {
        Intent intent = new Intent(context, TransactionDetailActivity.class);
        intent.putExtra(EXTRA_UUID, uuid);
        return intent;
    }

}
