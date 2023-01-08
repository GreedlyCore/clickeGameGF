
package com.greedlycore.clickerGF.shops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.example.blm.R;

public class ClickShop extends Fragment {

    LinearLayoutCompat clickShop;
    Button clickShopBtn;

    public static ClickShop newInstance() {

        ClickShop fragment = new ClickShop();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(
                R.layout.click_shop, container, false);

        clickShop = view.findViewById(R.id.click_shop_layout);
        //clickShopBtn = view.findViewById(R.id.click_shop_btn);

        return view;
    }
}
