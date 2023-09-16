package com.noktapa.qrgenapp.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class SessionManagement {
    private static final String MyPREFERENCES = "QRCodePreferences";

    final Gson gson;
    final SharedPreferences sharedpreferences;
    final SharedPreferences.Editor editor;
    final Context context;

    public SessionManagement(Context mContext) {
        this.context = mContext;
        sharedpreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        gson = new Gson();
    }

    public void setCountry(String country) {
        editor.putString("country", country);
        editor.commit();
    }

    public String getCountry() {
        return sharedpreferences.getString("country", "");
    }

}
