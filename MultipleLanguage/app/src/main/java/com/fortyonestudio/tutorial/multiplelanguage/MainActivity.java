package com.fortyonestudio.tutorial.multiplelanguage;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Locale;

/**
 * Created by Riris.
 */

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private Button btnDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        btnDisplay = (Button) findViewById(R.id.button);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                Toast.makeText(MainActivity.this,
                        radioButton.getText(), Toast.LENGTH_SHORT).show();
                        
                //detect language by string
                if(radioButton.getText().equals(getResources().getString(R.string.english))){
                    setLocale("en");

                }else if (radioButton.getText().equals(getResources().getString(R.string.indonesia))){
                    setLocale("in");
                }

            }

        });
    }

    private Locale getLocaleFromPref(String langId) {
        Locale locale = Locale.getDefault();

        // String language = prefs.getString(Constants.LANGUAGE, "");
        String language = langId;
        if (!language.isEmpty()) {
            locale = new Locale(language);
            Locale.setDefault(locale);
        }
        return locale;
    }

    private void overwriteConfigurationLocale(Configuration config,
                                              Locale locale) {
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
    }

    public void setLocale(String langId) {
        Locale locale = getLocaleFromPref(langId);
        Configuration config = getBaseContext().getResources()
                .getConfiguration();
        overwriteConfigurationLocale(config, locale);

        recreate();

    }

}
