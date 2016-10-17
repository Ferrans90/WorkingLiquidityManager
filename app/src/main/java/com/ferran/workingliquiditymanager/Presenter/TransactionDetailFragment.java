package com.ferran.workingliquiditymanager.Presenter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.ferran.workingliquiditymanager.Model.Transaction;
import com.ferran.workingliquiditymanager.Model.TransactionLab;
import com.ferran.workingliquiditymanager.R;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionDetailFragment extends Fragment {
    private static final String ARG_TRANSACTION_ID = "transactionid";
    private static final int REQUEST_DATE = 1;
    private EditText mItemNameEditText;
    private EditText mOwnerEditText;
    private EditText mAmountEditText;
    private Button mDateButtton;
    private CheckBox mInvoiceCheckBox;

    private Transaction mTransaction;

    public TransactionDetailFragment() {
        // Required empty public constructor
    }

    public static TransactionDetailFragment newInstance(UUID transactionID) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_TRANSACTION_ID, transactionID);
        TransactionDetailFragment fragment = new TransactionDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID uuid = (UUID) getArguments().getSerializable(ARG_TRANSACTION_ID);
        mTransaction = TransactionLab.get(getActivity()).getTransaction(uuid);
    }

    @Override
    public void onPause() {
        super.onPause();
        TransactionLab.get(getActivity()).updateTransaction(mTransaction);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            GregorianCalendar date = DatePickFragment.getDatePickResult(data);
            mTransaction.setTime(date);
            updateButtonDate(date);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction, container, false);
        mItemNameEditText = (EditText) v.findViewById(R.id.fragment_transaction_name);
        mItemNameEditText.setText(mTransaction.getItemName());
        mItemNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTransaction.setItemName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mOwnerEditText = (EditText) v.findViewById(R.id.fragment_transaction_owner);
        mOwnerEditText.setText(mTransaction.getOwner());
        mOwnerEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTransaction.setOwner(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mAmountEditText = (EditText) v.findViewById(R.id.fragment_transaction_amount);
        mAmountEditText.setText(mTransaction.getAmount().toString());
        mAmountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mTransaction.setAmount(new BigDecimal(s.toString()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mDateButtton = (Button) v.findViewById(R.id.fragment_transaction_date);
        mDateButtton.setText(new SimpleDateFormat("EEEE, MMM dd, yyyy").format(mTransaction.getTime().getTime()));
        mDateButtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DatePickActivity.newIntent(getActivity(), mTransaction.getTime());
                startActivityForResult(intent, REQUEST_DATE);
            }
        });
        mInvoiceCheckBox = (CheckBox) v.findViewById(R.id.fragment_transaction_invoice);
        mInvoiceCheckBox.setChecked(mTransaction.isHasInvoice());
        mInvoiceCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mTransaction.setHasInvoice(isChecked);
            }
        });
        return v;
    }

    private void updateButtonDate(GregorianCalendar date) {
        mDateButtton.setText(new SimpleDateFormat("EEEE, MMM dd, yyyy").format(date.getTime().getTime()));
    }

}
