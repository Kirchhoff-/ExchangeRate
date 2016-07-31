package com.kirchhoff.exchangerates.currencylist;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kirchhoff.exchangerates.database.CurrencyItem;
import com.kirchhoff.exchangerates.R;

import java.util.ArrayList;

/**
 * @author Kirchhoff-
 */
public class CurrencyListAdapter extends RecyclerView.Adapter<CurrencyListAdapter.CurrencyViewHolder> {

    private ArrayList<CurrencyItem> currencyList;
    private Callback callback;


    public CurrencyListAdapter(ArrayList<CurrencyItem> currencyList) {
        setCurrencyList(currencyList);
    }


    public void replaceData(ArrayList<CurrencyItem> list) {
        setCurrencyList(list);
        notifyDataSetChanged();
    }

    private void setCurrencyList(ArrayList<CurrencyItem> currencyList) {
       this.currencyList = currencyList;
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public CurrencyItem getCurrencyItem(int position) {
        return currencyList.get(position);
    }

    public ArrayList<CurrencyItem> getCurrencyArray() {
        return currencyList;
    }

    public String getCurrencyId(int position){
        return currencyList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    @Override
    public CurrencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.currency_list_item, parent, false);
        final CurrencyViewHolder viewHolder = new CurrencyViewHolder(itemView);


        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onItemClick(viewHolder.getAdapterPosition());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CurrencyViewHolder holder, int position) {
       CurrencyItem item = currencyList.get(position);
        holder.item = item;

        holder.name.setText(item.getName());
        holder.rate.setText(item.getRate());
    }



    public interface Callback {
        void onItemClick(int position);
    }

    public static class CurrencyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView rate;
        CardView mainLayout;
        CurrencyItem item;

        public CurrencyViewHolder(View itemView) {
            super(itemView);
            mainLayout = (CardView) itemView.findViewById(R.id.mainLayout);
            name = (TextView) itemView.findViewById(R.id.name);
            rate = (TextView) itemView.findViewById(R.id.rate);
        }

    }
}
