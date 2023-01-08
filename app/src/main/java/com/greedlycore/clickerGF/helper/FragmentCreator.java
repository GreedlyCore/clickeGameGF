package com.greedlycore.clickerGF.helper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentCreator {
    public static void showFragment(androidx.fragment.app.FragmentManager fragmentManager, Fragment fragment, int ViewFragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(ViewFragment, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
