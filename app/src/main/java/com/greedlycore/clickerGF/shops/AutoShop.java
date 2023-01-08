package com.greedlycore.clickerGF.shops;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.greedlycore.clickerGF.Item;
import com.example.blm.R;
import com.greedlycore.clickerGF.helper.SharedPrefManager;
import com.greedlycore.clickerGF.helper.short_num;

public class AutoShop extends Fragment {
    TextView batCountText;
    TextView batPower;
    Button batButton;
    Item bat;
    Item pistol;
    Item grenade;

    Button pistolButton;
    TextView pistolCountText;
    TextView pistolPower;

    private TextView autoPowerText;
    private TextView balanceText;
    private Context context;

    private long balance;
    private long autoPower;
    private long batCount;
    private long pistolCount;
    private long price;

    final String SAVED_BALANCE = "BALANCE";
    final String SAVED_AUTO_ATTACK_COUNT = "AUTO_ATTACK_COUNT";
    final String SAVED_CLICK_ATTACK_COUNT = "CLICK_ATTACK_COUNT";

    final String SAVED_BAT_COUNT = "CLICK_BAT_COUNT";
    final String SAVED_LATEST_BAT_PRICE = "LATEST_BAT_PRICE";
    final String SAVED_LATEST_PISTOL_PRICE = "LATEST_PISTOL_PRICE";
    final String SAVED_PISTOL_COUNT = "CLICK_PISTOL_COUNT";

    private SharedPreferences sp;

    public static AutoShop newInstance(TextView autoClick, TextView balance, Context context, SharedPreferences sp) {

        AutoShop fragment = new AutoShop();
        fragment.autoPowerText = autoClick;
        fragment.balanceText = balance;
        fragment.context = context;
        fragment.sp = sp;


        return fragment;
    }
//    @Nullable
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//
//        SharedPrefManager.saveValue(context, sp, SAVED_LATEST_BAT_PRICE, String.valueOf(bat.getPrice()));
//        SharedPrefManager.saveValue(context, sp, SAVED_LATEST_PISTOL_PRICE, String.valueOf(pistol.getPrice()));
//
//
//
//    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.auto_shop, container, false);

        bat = new Item("bat", 1, 1.00005f, Integer.parseInt(SharedPrefManager.loadValue(context, sp, "SAVED_BATS_COUNT")));
        pistol = new Item("pistol", 2, 1.00075f, Integer.parseInt(SharedPrefManager.loadValue(context, sp, "SAVED_PISTOL_COUNT")));
        grenade = new Item("grenade", 5, 1.0001f, 0);

        batCountText = view.findViewById(R.id.bat_count);
        batPower = view.findViewById(R.id.bat_power);
        batButton = view.findViewById(R.id.bat_btn);
        batButton.setText(SharedPrefManager.loadValue(context, sp, SAVED_LATEST_PISTOL_PRICE));
        batButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                balance = Long.parseLong(SharedPrefManager.loadValue(context, sp, SAVED_BALANCE));
                price = bat.getPrice();
                if (balance >= price) {
                    bat.buy();

                    autoPower = Long.parseLong(SharedPrefManager.loadValue(context, sp, SAVED_AUTO_ATTACK_COUNT));
                    SharedPrefManager.saveValue(context, sp, SAVED_AUTO_ATTACK_COUNT, String.valueOf(autoPower+bat.getDamage()));
                    autoPowerText.setText(short_num.to_text(autoPower+bat.getDamage()));

                    batCount = Long.parseLong(SharedPrefManager.loadValue(context, sp, SAVED_BAT_COUNT));
                    SharedPrefManager.saveValue(context, sp, SAVED_BAT_COUNT, String.valueOf(bat.getCount()));
                    batCountText.setText(short_num.to_text(batCount));


                    balance -= price;
                    SharedPrefManager.saveValue(context, sp, SAVED_BALANCE, String.valueOf(balance));
                    balanceText.setText(short_num.to_text(balance));

                    batButton.setText(short_num.to_text(price));

                } else {
                    Toast.makeText(getContext(), "Not enough money", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pistolCountText = view.findViewById(R.id.pistol_count);
        pistolPower = view.findViewById(R.id.pistol_power);
        pistolButton = view.findViewById(R.id.pistol_btn);
        pistolButton.setText(SharedPrefManager.loadValue(context, sp, SAVED_LATEST_PISTOL_PRICE));

        pistolButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                balance = Long.parseLong(SharedPrefManager.loadValue(context, sp, SAVED_BALANCE));
                price = pistol.getPrice();
                if (balance >= price) {
                    pistol.buy();
                    ///////////////////
                    autoPower = Long.parseLong(SharedPrefManager.loadValue(context, sp, SAVED_AUTO_ATTACK_COUNT));
                    SharedPrefManager.saveValue(context, sp, SAVED_AUTO_ATTACK_COUNT, String.valueOf(autoPower+pistol.getDamage()));
                    autoPowerText.setText(short_num.to_text(autoPower+pistol.getDamage()));
                    ///////////////////
                    pistolCount = Long.parseLong(SharedPrefManager.loadValue(context, sp, SAVED_PISTOL_COUNT));
                    SharedPrefManager.saveValue(context, sp, SAVED_PISTOL_COUNT, String.valueOf(pistol.getCount()));
                    pistolCountText.setText(String.valueOf(pistol.getCount()));
                    ///////////////////
                    balance -= price;
                    SharedPrefManager.saveValue(context, sp, SAVED_BALANCE, String.valueOf(balance));
                    balanceText.setText(short_num.to_text(balance));

                    pistolButton.setText(short_num.to_text(price));
                } else {
                    Toast.makeText(getContext(), "Not enough money", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }


}
