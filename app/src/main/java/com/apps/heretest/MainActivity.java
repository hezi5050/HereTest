package com.apps.heretest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.apps.heretest.adapters.TaxisAdapter;
import com.apps.heretest.data.Taxi;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private static final int MAX_TIME_ETA = 60;
    private static final long DELAY_TIME = 5000;
    private static final long PERIOD_TIME = 5000;
    private Timer mTimer;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView taxisRecyclerView = findViewById(R.id.taxis_recycler_view);
        taxisRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        taxisRecyclerView.setLayoutManager(layoutManager);
        final TaxisAdapter taxisAdapter = new TaxisAdapter(getGenerateTaxis());
        taxisRecyclerView.setAdapter(taxisAdapter);

        mHandler = new Handler(Looper.getMainLooper());
        mRunnable = new Runnable() {
            @Override
            public void run() {
                taxisAdapter.updateTaxisArrayList(getGenerateTaxis());
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        // This timer holds a strong reference to MainActivity and might prevent it from getting GC,
        // or even try to act on it after onPause is called, this is only for demo
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (mHandler != null && mRunnable != null) {
                    mHandler.post(mRunnable);
                }
            }
        }, DELAY_TIME, PERIOD_TIME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    private ArrayList<Taxi> getGenerateTaxis() {
        ArrayList<Taxi> generateTaxis = new ArrayList<>();
        generateTaxis.add(new Taxi("Hameleh Shlomo", "http://eilat.city/images/%D7%9E%D7%95%D7%A0%D7%99%D7%95%D7%AA-%D7%94%D7%9E%D7%9C%D7%9A-%D7%A9%D7%9C%D7%9E%D7%94-%D7%90%D7%99%D7%9C%D7%AA-177-13.jpg", generateEta()));
        generateTaxis.add(new Taxi("Taba", "http://eilat.city/images/%D7%9E%D7%95%D7%A0%D7%99%D7%95%D7%AA-%D7%98%D7%90%D7%91%D7%94-%D7%90%D7%99%D7%9C%D7%AA-176-13.jpg", generateEta()));
        generateTaxis.add(new Taxi("Castle", "http://kastel.co.il/wp-content/uploads/2013/07/top3.jpg", generateEta()));
        generateTaxis.add(new Taxi("Mall Ha Yam", "http://eilat.city/images/%D7%9E%D7%95%D7%A0%D7%99%D7%95%D7%AA-%D7%9E%D7%95%D7%9C-%D7%94%D7%99%D7%9D-%D7%90%D7%99%D7%9C%D7%AA-180-13.jpg", generateEta()));
        generateTaxis.add(new Taxi("The City", "http://eilat.city/images/%D7%9E%D7%95%D7%A0%D7%99%D7%95%D7%AA-%D7%94%D7%A2%D7%99%D7%A8-%D7%90%D7%99%D7%9C%D7%AA-181-13.jpg", generateEta()));
        generateTaxis.add(new Taxi("Shahmon", "http://eilat.city/images/%D7%9E%D7%95%D7%A0%D7%99%D7%95%D7%AA-%D7%A9%D7%97%D7%9E%D7%95%D7%9F-%D7%90%D7%99%D7%9C%D7%AA-178-13.jpg", generateEta()));
        generateTaxis.add(new Taxi("Fatal", "http://eilat.city/images/%D7%9E%D7%95%D7%A0%D7%99%D7%95%D7%AA-%D7%A4%D7%AA%D7%90%D7%9C-%D7%90%D7%99%D7%9C%D7%AA-175-13.jpg", generateEta()));
        return generateTaxis;
    }

    private int generateEta() {
        Random random = new Random();
        return random.nextInt(MAX_TIME_ETA + 1);
    }
}
