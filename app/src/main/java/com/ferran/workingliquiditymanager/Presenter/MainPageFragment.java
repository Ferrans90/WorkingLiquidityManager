package com.ferran.workingliquiditymanager.Presenter;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import com.ferran.workingliquiditymanager.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainPageFragment extends Fragment {
    private Button mOpenAccountButton;

    public static MainPageFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainPageFragment fragment = new MainPageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public MainPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_main_page,container,false);
        mOpenAccountButton =(Button)v.findViewById(R.id.fragment_main_page_open_account);
        mOpenAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=TransactionListActivity.newIntent(getActivity());
                startActivity(intent);
            }
        });
        return v;
    }

}
