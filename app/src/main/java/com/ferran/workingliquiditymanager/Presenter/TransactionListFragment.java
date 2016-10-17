package com.ferran.workingliquiditymanager.Presenter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ferran.workingliquiditymanager.Model.Transaction;
import com.ferran.workingliquiditymanager.Model.TransactionLab;
import com.ferran.workingliquiditymanager.R;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionListFragment extends Fragment {
    private static final int REQUEST_TRANSACTION = 0;
    private RecyclerView mRecyclerView;
    private TransactionAdapter mTransactionAdapter;
    private int touchPos;
    private boolean isDeleted;


    public TransactionListFragment() {
        // Required empty public constructor
    }

    public static TransactionListFragment newInstance() {
        Bundle args = new Bundle();

        TransactionListFragment fragment = new TransactionListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction_list, container, false);
        mRecyclerView = (RecyclerView) v.findViewById(R.id.transaction_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUi();
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_transaction_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_fragment_transaction_list_new_transaction:
                touchPos = TransactionLab.get(getActivity()).getTransactions().size();
                Transaction transaction = new Transaction();
                TransactionLab.get(getActivity()).addTransaction(transaction);
                Intent intent = TransactionDetailActivity.newIntent(getActivity(), transaction.getUuid());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUi() {
        // 屏幕选择等时刷新显示
        TransactionLab transactionLab = TransactionLab.get(getActivity());
        List<Transaction> transactions = transactionLab.getTransactions();

        if (mTransactionAdapter == null) {
            mTransactionAdapter = new TransactionAdapter(transactions);
            mRecyclerView.setAdapter(mTransactionAdapter);
        } else if (isDeleted) {
            isDeleted = false;
            mTransactionAdapter.setTransactions(transactions);
            mTransactionAdapter.notifyItemRemoved(touchPos);
            mTransactionAdapter.notifyItemChanged(touchPos, mTransactionAdapter.getItemCount());
        } else {
            mTransactionAdapter.setTransactions(transactions);
            mTransactionAdapter.notifyItemChanged(touchPos);
        }
    }

    private class TransactionHolder extends RecyclerView.ViewHolder {
        private TextView mTimeTextView;
        private TextView mOwnerTextView;
        private TextView mItemNameTextView;
        private TextView mAmountTextView;
        private CheckBox mInvoiceCheckBox;

        private Transaction transaction;

        public TransactionHolder(View view) {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    touchPos = getAdapterPosition();
                    Intent intent = TransactionDetailActivity
                            .newIntent(getActivity(), transaction.getUuid());
                    startActivityForResult(intent, REQUEST_TRANSACTION);
                }
            });
            mTimeTextView = (TextView) view.findViewById(R.id.fragment_transaction_list_time);
            mOwnerTextView = (TextView) view.findViewById(R.id.fragment_transaction_list_owner);
            mItemNameTextView = (TextView) view.findViewById(R.id.fragment_transaction_list_item_name);
            mAmountTextView = (TextView) view.findViewById(R.id.fragment_transaction_list_amount);
            mInvoiceCheckBox = (CheckBox) view.findViewById(R.id.fragment_transaction_list_invoice);
        }

        public void bindTransaction(Transaction t) {
            transaction = t;
            mTimeTextView.setText(new SimpleDateFormat("MMM dd, yyyy").format(transaction.getTime().getTime()));
            mOwnerTextView.setText(transaction.getOwner());
            mItemNameTextView.setText(transaction.getItemName());
            mAmountTextView.setText(transaction.getAmount().toString());
            mInvoiceCheckBox.setChecked(transaction.isHasInvoice());
        }

    }

    private class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder> {
        private List<Transaction> transactions;

        public TransactionAdapter(List<Transaction> ts) {
            transactions = ts;
        }

        public void setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
        }

        @Override
        public TransactionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View v = inflater.inflate(R.layout.list_item_transaction, parent, false);
            return new TransactionHolder(v);
        }

        @Override
        public void onBindViewHolder(TransactionHolder holder, int position) {
            Transaction t = transactions.get(position);
            holder.bindTransaction(t);
        }

        @Override
        public int getItemCount() {
            return transactions.size();
        }
    }
}
