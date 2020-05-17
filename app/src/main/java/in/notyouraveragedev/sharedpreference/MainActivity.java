package in.notyouraveragedev.sharedpreference;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import in.notyouraveragedev.sharedpreference.domain.CustomObject;
import in.notyouraveragedev.sharedpreference.domain.NonSerializable;
import in.notyouraveragedev.sharedpreference.util.PreferenceManager;
import in.notyouraveragedev.simplepreference.SimplePreferenceManager;

public class MainActivity extends AppCompatActivity {

    private SimplePreferenceManager preferenceManager;

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
        preferenceManager = new SimplePreferenceManager.SimplePreferenceManagerBuilder(this)
                .withObjectStorageSupport()
                .build();
        // if data is available in shared preference then load the data from shared preference
        // if its not, the save the data into shared preference
        if (savedDataExists()) {
            fetchAndDisplayData();
        } else {
            saveData();
        }


        Map<String, Integer> intMap = new HashMap<>();
        intMap.put("ONE_I", 1);
        intMap.put("TWO_I", 2);
        intMap.put("THREE_I", 3);
        preferenceManager.putAll(intMap);


        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("ONE_S", "1");
        stringMap.put("TWO_s", "2");
        stringMap.put("THREE_S", "3");
        preferenceManager.putAll(stringMap);


        Map<String, Long> longMap = new HashMap<>();
        longMap.put("ONE_L", 1L);
        longMap.put("TWO_L", 2L);
        longMap.put("THREE_L", 3L);
        preferenceManager.putAll(longMap);


        Map<String, Float> floatMap = new HashMap<>();
        floatMap.put("ONE_F", 1f);
        floatMap.put("TWO_F", 2f);
        floatMap.put("THREE_F", 3f);
        preferenceManager.putAll(floatMap);


        Map<String, Boolean> booleanMap = new HashMap<>();
        booleanMap.put("ONE_B", true);
        booleanMap.put("TWO_B", false);
        booleanMap.put("THREE_B", true);
        preferenceManager.putAll(booleanMap);


        Map<String, CustomObject> objectMap = new HashMap<>();
        objectMap.put("ONE_O", new CustomObject());
        objectMap.put("TWO_O", new CustomObject());
        objectMap.put("THREE_O", new CustomObject());
        preferenceManager.putAll(objectMap);


        Map<String, NonSerializable> objectMap2 = new HashMap<>();
        objectMap2.put("ONE_O", new NonSerializable());
        objectMap2.put("TWO_O", new NonSerializable());
        objectMap2.put("THREE_O", new NonSerializable());
        try {
            preferenceManager.putAll(objectMap2);
        } catch (UnsupportedOperationException e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
        }

        Set<String> stringSet = new HashSet<>();
        stringSet.add("ONE");
        stringSet.add("TWO");
        stringSet.add("THREE");

        Set<String> stringSet2 = new HashSet<>();
        stringSet2.add("ONE");
        stringSet2.add("TWO");
        stringSet2.add("THREE");

        Map<String, Set<String>> stringSetMap = new HashMap<>();
        stringSetMap.put("ONE_SS", stringSet);
        stringSetMap.put("TWO_SS", stringSet2);
        preferenceManager.putAll(stringSetMap);


        Set<Integer> intSet = new HashSet<>();
        intSet.add(1);
        intSet.add(2);
        intSet.add(3);

        Map<String, Set<Integer>> intSetMap = new HashMap<>();
        intSetMap.put("ONE_IS", intSet);
        try {
            preferenceManager.putAll(intSetMap);
        } catch (UnsupportedOperationException e) {
            Log.e("Error", Objects.requireNonNull(e.getMessage()));
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
        preferenceManager.saveInteger("Integer", 10);
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
        tvCustomObject.setText((preferenceManager.fetchObject("Object", CustomObject.class)).toString());
    }


}
