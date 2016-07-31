package com.kirchhoff.exchangerates.currencylist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.kirchhoff.exchangerates.CurrencyItem;
import com.kirchhoff.exchangerates.R;
import com.kirchhoff.exchangerates.currencydetails.CurrencyDetailsActivity;
import com.kirchhoff.exchangerates.utils.LogUtils;

import java.util.ArrayList;

/**
 * @author Kirchhoff-
 */
public class CurrencyListFragment extends Fragment implements CurrencyListContract.View {


    private CurrencyListContract.Presenter presenter;

    private CurrencyListAdapter adapter;

    private ScrollChildSwipeRefreshLayout refreshLayout;

    private RecyclerView recyclerView;

    private ProgressBar progressBar;


    public CurrencyListFragment() {
        // Requires empty public constructor.
    }

    public static CurrencyListFragment newInstance() {
        return new CurrencyListFragment();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtils.d("onCreate");
        super.onCreate(savedInstanceState);
        adapter = new CurrencyListAdapter(new ArrayList<CurrencyItem>(0));
    }


    @Override
    public void onResume() {
        LogUtils.d("OnResume");
        super.onResume();
        presenter.start();
    }

    @Override
    public void setPresenter(CurrencyListContract.Presenter presenter) {
        this.presenter = presenter;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.currency_list_frag, container, false);


        refreshLayout = (ScrollChildSwipeRefreshLayout) root.findViewById(R.id.refreshLayout);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBar);

        //For improving performance
        recyclerView.setHasFixedSize(true);

        //Create and set LayoutManager for RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        // Set up progress indicator
        refreshLayout.setColorSchemeColors(
                ContextCompat.getColor(getActivity(), R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent),
                ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark)
        );

        // Set the scrolling view in the custom SwipeRefreshLayout.
        refreshLayout.setScrollUpChild(recyclerView);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });


        // Set up listener on list item
        adapter.setCallback(new CurrencyListAdapter.Callback() {
            @Override
            public void onItemClick(int position) {
                presenter.showCurrencyDetails(adapter.getCurrencyId(position));
            }
        });

        setHasOptionsMenu(true);

        return root;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public void showLoadIndicator(boolean active) {

        if (active) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showRefreshIndicator(final boolean active) {

        if (getView() == null) {
            return;
        }

        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refreshLayout);

        // Make sure setRefreshing() is called after the layout is done with
        // everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }


    @Override
    public void showCurrencyList(ArrayList<CurrencyItem> currencyElements) {
        adapter.replaceData(currencyElements);
    }


    @Override
    public void showCurrencyDetails(String id) {
        Intent intent = new Intent(getActivity(), CurrencyDetailsActivity.class);
        intent.putExtra(CurrencyDetailsActivity.CURRENCY_ITEM_ID, id);
        startActivity(intent);
    }


    @Override
    public boolean isActive() {
        return isAdded();
    }
}
