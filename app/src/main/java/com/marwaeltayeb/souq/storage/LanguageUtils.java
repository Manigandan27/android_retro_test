package com.marwaeltayeb.souq.storage;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class LanguageUtils {

    private LanguageUtils(){}

    public static void setEnglishState(Context context, boolean isEnglishEnabled){
        SharedPreferences sharedpreferences = context.getSharedPreferences("language_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean("language", isEnglishEnabled);
        editor.apply();
    }

    public static boolean getEnglishState(Context context){
        SharedPreferences sharedpreferences = context.getSharedPreferences("language_data", Context.MODE_PRIVATE);
        return sharedpreferences.getBoolean("language", true);
    }

    public static void setLocale(Context context,String language){
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
        // Save data to shared preferences
        SharedPreferences.Editor editor = context.getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_lang",language);
        editor.apply();
    }

    // Load language saved in shared preferences
    public static void loadLocale(Context context){
        SharedPreferences prefs = context.getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("My_lang","");
        setLocale(context,language);
    }
}



/*
public class LocaleHelper {
    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    // the method is used to set the language at runtime
    public static Context setLocale(Context context, String language) {
        persist(context, language);

        // updating the language for devices above android nougat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, language);
        }
        // for devices having lower version of android os
        return updateResourcesLegacy(context, language);
    }

    private static void persist(Context context, String language) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SELECTED_LANGUAGE, language);
        editor.apply();
    }

    // the method is used update the language of application by creating
    // object of inbuilt Locale class and passing language argument to it
    @TargetApi(Build.VERSION_CODES.N)
    private static Context updateResources(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = context.getResources().getConfiguration();
        configuration.setLocale(locale);
        configuration.setLayoutDirection(locale);

        return context.createConfigurationContext(configuration);
    }


    @SuppressWarnings("deprecation")
    private static Context updateResourcesLegacy(Context context, String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Resources resources = context.getResources();

        Configuration configuration = resources.getConfiguration();
        configuration.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale);
        }

        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        return context;
    }
}

 */