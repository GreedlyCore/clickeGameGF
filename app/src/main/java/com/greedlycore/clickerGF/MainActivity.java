package com.greedlycore.clickerGF;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import com.greedlycore.clickerGF.Fragments.shopFragment;
import com.example.blm.R;
import com.greedlycore.clickerGF.helper.FragmentCreator;
import com.greedlycore.clickerGF.helper.SharedPrefManager;
import com.greedlycore.clickerGF.helper.short_num;


public class MainActivity extends AppCompatActivity {

    boolean is_shop_visible = true;
    private long balance;
    private long autoPower;
    private long clickPower;

    private int  delay = 1000; //One second = 1000 milliseconds.
    private boolean isMusicOn;
    Handler handler = new Handler();

    TextView balanceText;
    TextView autoPowerText;
    TextView clickPowerText;
    TextView EnemyHP;
    TextView killsCount;
    TextView damageNow;

    Button ShopBTN;
    Button SkillDieBTN;

    ImageButton VolumeBTN;
    Button rebootprogressFBtn;
    ImageView BLM_LOGO;
    ImageView EnemyLogo;

    Enemy enemy;

    LinearLayoutCompat StatsLinear;
    SoundService music;

    SharedPreferences sPref;

    final String SAVED_BALANCE = "BALANCE";
    final String SAVED_AUTO_ATTACK_COUNT = "AUTO_ATTACK_COUNT";
    final String SAVED_CLICK_ATTACK_COUNT = "CLICK_ATTACK_COUNT";
    final String SAVED_BATS_COUNT = "SAVED_BATS_COUNT";
    final String SAVED_BATS_PRICE = "SAVED_BATS_PRICE";


    @Override
    protected void onPause() {
        super.onPause();
        music.stopMusic();
        isMusicOn = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        music.startMusic();
        isMusicOn = true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        music.stopMusic();
        isMusicOn = false;

        SharedPrefManager.saveValue(getApplicationContext(), sPref, SAVED_BALANCE, String.valueOf(balance));
        SharedPrefManager.saveValue(getApplicationContext(), sPref, SAVED_AUTO_ATTACK_COUNT, String.valueOf(autoPower));
        SharedPrefManager.saveValue(getApplicationContext(), sPref, SAVED_CLICK_ATTACK_COUNT, String.valueOf(clickPower));

    }

    private void declaration() {

        balanceText = findViewById(R.id.main_balance);
        autoPowerText = findViewById(R.id.auto_count);
        clickPowerText = findViewById(R.id.click_count);


        music = new SoundService(getApplicationContext());
        isMusicOn = true;

        ShopBTN = findViewById(R.id.shop_btn);
        VolumeBTN = findViewById(R.id.volume_button);
        SkillDieBTN = findViewById(R.id.skill_die);

        //RebootProgressBTN = findViewById(R.id.reboot_button);

        BLM_LOGO = findViewById(R.id.blm);
        EnemyLogo = findViewById(R.id.iv_enemy);
        EnemyHP = findViewById(R.id.enemy_health);


        StatsLinear = findViewById(R.id.StatsFragment);

        killsCount = findViewById(R.id.kills);
        killsCount.setText("0");
        damageNow = findViewById(R.id.damage);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        declaration();
        final shopFragment fragment = shopFragment.newInstance(getApplicationContext(), autoPowerText, balanceText, sPref);
        FragmentCreator.showFragment(getSupportFragmentManager(), fragment, R.id.ShopFragment);

        balance = Long.parseLong(SharedPrefManager.loadValue(getApplicationContext(), sPref, SAVED_BALANCE));
        balanceText.setText(short_num.to_text(balance));

        autoPower = Long.parseLong(SharedPrefManager.loadValue(getApplicationContext(), sPref, SAVED_AUTO_ATTACK_COUNT));
        autoPowerText.setText(short_num.to_text(autoPower));

        clickPower = Long.parseLong(SharedPrefManager.loadValue(getApplicationContext(), sPref, SAVED_CLICK_ATTACK_COUNT));
        clickPowerText.setText(short_num.to_text(clickPower));

        enemy = new Enemy(50, 10);

        handler.postDelayed(new Runnable() {
            public void run() {
                autoPower = Long.parseLong(SharedPrefManager.loadValue(getApplicationContext(), sPref, SAVED_AUTO_ATTACK_COUNT));
                enemy.attack(autoPower);

                damageNow.setText(String.valueOf(autoPower));
                if (enemy.is_dead()) {

                    //EnemyHP.setText("dead!");
                    killsCount.setText(String.valueOf(Integer.parseInt(killsCount.getText().toString()) + 1));

                    balance = Long.parseLong(SharedPrefManager.loadValue(getApplicationContext(), sPref, SAVED_BALANCE));
                    balance += enemy.getReward();

                    SharedPrefManager.saveValue(getApplicationContext(), sPref, SAVED_BALANCE, String.valueOf(balance));
                    balanceText.setText(short_num.to_text(balance));

                    enemy.revive();
                    EnemyHP.setText(short_num.to_text((long) enemy.getHP()));
                }
                EnemyHP.setText(short_num.to_text((long) enemy.getHP()));


                handler.postDelayed(this, delay);
            }
        }, delay);

//        RebootProgressBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPrefManager.saveValue(getApplicationContext(), sPref, SAVED_BALANCE, "1000");
//                SharedPrefManager.saveValue(getApplicationContext(), sPref, SAVED_AUTO_ATTACK_COUNT, "0");
//                SharedPrefManager.saveValue(getApplicationContext(), sPref, SAVED_CLICK_ATTACK_COUNT, "0");
//                SharedPrefManager.saveValue(getApplicationContext(), sPref, SAVED_BATS_COUNT, "0");
//                balanceText.setText("1k");
//                clickPowerText.setText("0");
//                autoPowerText.setText("0");
//
//            }
//        });

        VolumeBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMusicOn) {
                    music.stopMusic();
                    isMusicOn = false;
                } else {
                    music.startMusic();
                    isMusicOn = true;
                }
            }
        });

        ShopBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (is_shop_visible) {
                    fragment.visible();
                    StatsLinear.setVisibility(LinearLayoutCompat.GONE);
                    is_shop_visible = false;
                } else {
                    fragment.notVisible();
                    StatsLinear.setVisibility(LinearLayoutCompat.VISIBLE);
                    is_shop_visible = true;
                }
            }
        });

//        SkillDieBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ShopBTN.setText("1");
//            }
//        });

        EnemyLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clickPower = Long.parseLong(SharedPrefManager.loadValue(getApplicationContext(), sPref, SAVED_CLICK_ATTACK_COUNT));
                enemy.attack(clickPower);
                damageNow.setText(String.valueOf(clickPower));

                if (enemy.is_dead()) {
                    EnemyHP.setText("dead!");

                    killsCount.setText(String.valueOf(Integer.parseInt(killsCount.getText().toString()) + 1));
                    balance = Long.parseLong(SharedPrefManager.loadValue(getApplicationContext(), sPref, SAVED_BALANCE));
                    balance += enemy.getReward();
                    SharedPrefManager.saveValue(getApplicationContext(), sPref, SAVED_BALANCE, String.valueOf(balance));
                    balanceText.setText(short_num.to_text(balance));

                    enemy.revive();
                }
                EnemyHP.setText(short_num.to_text((long) enemy.getHP()));


            }
        });

    }

}
