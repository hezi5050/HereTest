package com.apps.heretest.data;

import android.support.annotation.NonNull;

public class Taxi implements Comparable<Taxi>{

    private String mName;
    private String mIconUrl;
    private int mEta;

    public Taxi(String name, String iconUrl, int eta) {
        mName = name;
        mIconUrl = iconUrl;
        mEta = eta;
    }

    public String getName() {
        return mName;
    }

    public String getIconUrl() {
        return mIconUrl;
    }

    public int getEta() {
        return mEta;
    }

    @Override
    public int compareTo(@NonNull Taxi taxi) {
        return mEta - taxi.getEta();
    }

}
