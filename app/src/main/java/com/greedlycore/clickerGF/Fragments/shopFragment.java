package com.greedlycore.clickerGF.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.blm.R;
import com.google.android.material.tabs.TabLayout;


public class shopFragment extends Fragment {
    View view;
    TextView autoClick;
    TextView balance;
    private SharedPreferences sp;

    public static shopFragment newInstance(Context context, TextView autoClick, TextView balance, SharedPreferences sPref) {

        Bundle args = new Bundle();
        shopFragment fragment = new shopFragment();
        //fragment.mainActivity = mainActivity;
        fragment.autoClick = autoClick;
        fragment.balance = balance;
        fragment.sp = sPref;
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.shop_fragment, container, false);
        view.setVisibility(View.GONE);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getContext(), getFragmentManager(), autoClick, balance, sp);
        ViewPager viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = view.findViewById(R.id.tabs);

        tabs.setupWithViewPager(viewPager);
        //ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        //адаптер - это набор инструментов для выполнения необходимых методов старой либы, которую уже здесь ты не можешь использовать


        return view;
    }


    public void visible() {
        //mainActivity.main_btn.setVisibility(view.GONE);
        view.setVisibility(View.VISIBLE);

    }
    public void notVisible() {
        //mainActivity.main_btn.setVisibility(view.VISIBLE);
        view.setVisibility(View.GONE);

    }
}


