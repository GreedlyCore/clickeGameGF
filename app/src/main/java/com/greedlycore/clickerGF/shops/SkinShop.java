
package com.greedlycore.clickerGF.shops;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.example.blm.R;

public class SkinShop extends Fragment {

    Button skinShopBtn;
    LinearLayoutCompat skinShop;

    public static SkinShop newInstance() {
        SkinShop fragment = new SkinShop();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.skin_shop, container, false);


        skinShop = view.findViewById(R.id.skin_shop_layout);
        //skinShopBtn = view.findViewById(R.id.skin_shop_btn);


        return view;
    }
}
