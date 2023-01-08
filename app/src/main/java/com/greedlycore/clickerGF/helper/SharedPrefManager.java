package com.greedlycore.clickerGF.helper;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;


public class SharedPrefManager {
        //final String SAVED_AUTO_COUNT = "count";
        public static void saveValue(Context context, SharedPreferences sp, String name, String value){
            sp = context.getSharedPreferences("SP", MODE_PRIVATE);
            SharedPreferences.Editor ed = sp.edit();
            ed.putString(name, value);
            ed.commit();
        }

        public static String loadValue(Context context, SharedPreferences shared_p, String name){
            shared_p = context.getSharedPreferences("SP", MODE_PRIVATE);
            return shared_p.getString(name, "1000");
    }

}

