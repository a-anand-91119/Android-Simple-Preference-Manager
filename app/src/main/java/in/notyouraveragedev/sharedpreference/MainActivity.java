package in.notyouraveragedev.sharedpreference;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import in.notyouraveragedev.sharedpreference.domain.CustomObject;
import in.notyouraveragedev.sharedpreference.util.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    private PreferenceManager preferenceManager;

    private TextView tvIsSaved;
    private TextView tvCustomObject;
    private TextView tvLong;
    private TextView tvString;
    private TextView tvInteger;
    private TextView tvFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvIsSaved = findViewById(R.id.tv_is_saved);
        tvCustomObject = findViewById(R.id.tv_custom);
        tvLong = findViewById(R.id.tv_long);
        tvString = findViewById(R.id.tv_string);
        tvInteger = findViewById(R.id.tv_integer);
        tvFloat = findViewById(R.id.tv_float);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // removing specific entries
                preferenceManager.removeData("isDataSaved");
                // removing all entries
                preferenceManager.removeAll();
            }
        });

        // getting the instance of Preference Manager
        preferenceManager = PreferenceManager.getInstance(this);

        // if data is available in shared preference then load the data from shared preference
        // if its not, the save the data into shared preference
        if (savedDataExists()) {
            fetchAndDisplayData();
        } else {
            saveData();
        }
    }

    private boolean savedDataExists() {
        return preferenceManager.fetchBoolean("isDataSaved");
    }

    /**
     * Method saves a boolean, string, integer, long, float
     * and a custom object into shared preference.
     *
     * Once the data is saved, this value is then fetched and displayed
     */
    private void saveData() {
        preferenceManager.saveString("String", "this is saved String");
        preferenceManager.saveLong("Long", (long) 100);
        preferenceManager.saveFloat("Float", (float) 1000.1);
        preferenceManager.saveInteger("Integer", (int) 10);
        preferenceManager.saveObject("Object", new CustomObject("object name", "99"));

        fetchAndDisplayData();

        preferenceManager.saveBoolean("isDataSaved", true);

    }

    /**
     * Method fetched the data stored in shared preference
     */
    private void fetchAndDisplayData() {
        tvIsSaved.setText(getString(R.string.boolean_text, String.valueOf(preferenceManager.fetchBoolean("isDataSaved"))));
        tvInteger.setText(String.valueOf(preferenceManager.fetchInteger("Integer")));
        tvLong.setText(String.valueOf(preferenceManager.fetchLong("Long")));
        tvFloat.setText(String.valueOf(preferenceManager.fetchFloat("Float")));
        tvString.setText(preferenceManager.fetchString("String"));
        tvCustomObject.setText(((CustomObject) preferenceManager.fetchObject("Object", CustomObject.class)).toString());
    }


}
