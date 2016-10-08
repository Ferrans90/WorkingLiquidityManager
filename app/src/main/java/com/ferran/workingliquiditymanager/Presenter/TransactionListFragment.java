package com.ferran.workingliquiditymanager.Presenter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ferran.workingliquiditymanager.Model.Transaction;
import com.ferran.workingliquiditymanager.Model.TransactionLab;
import com.ferran.workingliquiditymanager.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TransactionListFragment extends Fragment {
    private RecyclerView recyclerView;
    private TransactionAdapter transactionAdapter;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_transaction_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.transaction_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return v;
    }

    private void updateUi() {
        TransactionLab transactionLab = TransactionLab.get(getActivity());
        List<Transaction> transactions = transactionLab.getTransactions();
        transactionAdapter = new TransactionAdapter(transactions);
        recyclerView.setAdapter(transactionAdapter);
    }

    private class TransactionHolder extends RecyclerView.ViewHolder {
        private TextView mTimeTextView;
        private TextView mOwnerTextView;
        private TextView mItemNameTextView;
        private TextView mAmoutTextView;
        private CheckBox mInvoiceCheckBox;

        private Transaction transaction;

        public TransactionHolder(View view) {
            super(view);
            mTimeTextView = (TextView) view.findViewById(R.id.fragment_transaction_list_time);
            mOwnerTextView = (TextView) view.findViewById(R.id.fragment_transaction_list_owner);
            mItemNameTextView = (TextView) view.findViewById(R.id.fragment_transaction_list_item_name);
            mAmoutTextView = (TextView) view.findViewById(R.id.fragment_transaction_list_amount);
            mInvoiceCheckBox=(CheckBox)view.findViewById(R.id.fragment_transaction_list_invoice);
        }

        public void bindTransaction(Transaction t) {
            transaction = t;
            mTimeTextView.setText(transaction.getTime().toString());
            mOwnerTextView.setText(transaction.getOwner());
            mItemNameTextView.setText(transaction.getItemName());
            mAmoutTextView.setText(transaction.getAmount().toString());
            mInvoiceCheckBox.setChecked(transaction.isHasInvoice());
        }

    }

    private class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder> {
        private List<Transaction> transactions;

        public TransactionAdapter(List<Transaction> ts) {
            transactions = ts;
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
