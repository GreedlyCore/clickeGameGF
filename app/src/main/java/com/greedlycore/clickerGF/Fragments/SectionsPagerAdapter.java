package com.greedlycore.clickerGF.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.greedlycore.clickerGF.shops.AutoShop;
import com.greedlycore.clickerGF.shops.ClickShop;
import com.example.blm.R;
import com.greedlycore.clickerGF.shops.SkinShop;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages
 *
 * адаптер для вашего ViewPager, который контролирует порядок вкладок, заголовков и связанного с ними контента.
 * Наиболее важными методами являются getPageTitle(int position), который используется для получения заголовка нужно вкладки,
 * getItem(int position), который определяет фрагмент для каждой вкладки.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    // создаем массив с именами фрагментов
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3};
    //
    private final Context Context;
    private final SharedPreferences sp;
    TextView balance;
    TextView autoClick;

    // КОНСТРУКТОР
    public SectionsPagerAdapter(Context context, FragmentManager fm, TextView autoClick, TextView balance, SharedPreferences sp) {
        super(fm);
        // context  хранит в себе методы для отображения базовых UI
        Context = context;
        this.autoClick = autoClick;
        this.balance = balance;
        this.sp = sp;

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        switch (position){
            case 1:
                return ClickShop.newInstance();
            case 2:
                return SkinShop.newInstance();
            default:
                return AutoShop.newInstance(autoClick, balance, Context, sp);
        }

        //return PageFragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return Context.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 3;
    }
}