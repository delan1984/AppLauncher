package com.demo.androidlauncherapp;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Delan.S.Patel
 */

public class AppsDrawerFragment extends Fragment implements SearchView.OnQueryTextListener,
        SearchView.OnCloseListener{

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    SearchView searchView;
    RecyclerView.LayoutManager layoutManager;


    public AppsDrawerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_apps_drawer, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.appDrawer_recylerView);
        adapter = new AppsDrawerAdapter(getContext());
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        searchView = (SearchView) view.findViewById(R.id.searchView);
        SearchManager searchManager = (SearchManager) getActivity()
                .getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setIconifiedByDefault(true);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

    }
    @Override
    public boolean onClose() {
        if (adapter != null) {
            AppsDrawerAdapter.filterData("");
            adapter.notifyDataSetChanged();
        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (adapter != null) {
            AppsDrawerAdapter.filterData(s);
            adapter.notifyDataSetChanged();
        }
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        if (adapter != null) {
            AppsDrawerAdapter.filterData(s);
            adapter.notifyDataSetChanged();
        }
        return false;
    }
}
