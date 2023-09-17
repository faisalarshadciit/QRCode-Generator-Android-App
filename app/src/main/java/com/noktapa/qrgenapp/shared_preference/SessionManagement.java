package com.noktapa.qrgenapp.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManagement {
    private static final String MyPREFERENCES = "QRCodePreferences";

    final SharedPreferences sharedpreferences;
    final SharedPreferences.Editor editor;
    final Context context;

    public SessionManagement(Context mContext) {
        this.context = mContext;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
    }

    public void setCountry(String country) {
        editor.putString("country", country);
        editor.commit();
    }

    public String getCountry() {
        return sharedpreferences.getString("country", "");
    }

    public void setIsFirstTime(boolean value) {
        editor.putBoolean("is_first_time", value);
        editor.commit();
    }

    public boolean getIsFirstTime() {
        return sharedpreferences.getBoolean("is_first_time", true);
    }

    public void setIsRated(boolean value) {
        editor.putBoolean("is_rated", value);
        editor.commit();
    }

    public boolean getIsRated() {
        return sharedpreferences.getBoolean("is_rated", false);
    }

}
