package in.notyouraveragedev.simplepreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Simple Preference Manager provides method to modify and retrieve data from
 * Android SharedPreferences
 * <p>
 * Created by A Anand on 17-05-2020
 */
public class SimplePreferenceManager {

    /**
     * Builder class to configure the SimplePreferenceManager according to the user needs
     */
    public static class SimplePreferenceManagerBuilder {
        private static final String DEFAULT_PREFERENCE_FILE_NAME = "DEFAULT_SIMPLE_PREFERENCE";
        private Context context;
        private String fileName;
        private int mode;
        private boolean isObjectSupportNeeded;

        /**
         * Constructor to initialize the SimplePreferenceManagerBuilder.
         *
         * @param context the current context
         */
        public SimplePreferenceManagerBuilder(Context context) {
            this.context = context;
            fileName = DEFAULT_PREFERENCE_FILE_NAME;
            mode = Context.MODE_PRIVATE;
            isObjectSupportNeeded = false;
        }

        /**
         * Creates / Opens the Shared Preference with the specified file name
         *
         * @param fileName the name of the Shared Preference File
         * @return the SimplePreferenceManagerBuilder
         */
        public SimplePreferenceManagerBuilder havingFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        /**
         * Specifies the operation mode of the Shared Preference file
         *
         * @param mode the operation mode
         * @return the SimplePreferenceManagerBuilder
         */
        public SimplePreferenceManagerBuilder usingOperationMode(int mode) {
            this.mode = mode;
            return this;
        }

        /**
         * Enables the capability to store and retrieve Custom Objects to and from a Shared Preference File
         *
         * @return the SimplePreferenceManagerBuilder
         */
        public SimplePreferenceManagerBuilder withObjectStorageSupport() {
            this.isObjectSupportNeeded = true;
            return this;
        }

        /**
         * Builds the SimplePreferenceManager using the configurations specified
         *
         * @return an instance of SimplePreferenceManager
         */
        public SimplePreferenceManager build() {
            SimplePreferenceManager simplePreferenceManager;

            if (this.isObjectSupportNeeded)
                simplePreferenceManager = new SimplePreferenceManager(new Gson());
            else
                simplePreferenceManager = new SimplePreferenceManager(null);

            simplePreferenceManager.setSharedPreferences(getSharedPreferences(this.context, this.fileName, this.mode));
            return simplePreferenceManager;
        }

        private static SharedPreferences getSharedPreferences(Context context, String preferenceFileName, int mode) {
            return context.getSharedPreferences(preferenceFileName, mode);
        }

    }

    private SharedPreferences sharedPreferences;
    private Gson gson;

    private void setSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private SimplePreferenceManager(Gson gson) {
        this.gson = gson;
    }

    private void checkObjectSupport() {
        if (gson == null)
            throw new UnsupportedOperationException("Object storage support not enabled for SimplePreferenceManager");
    }

    /**
     * Method to save objects into shared preference.
     * The object is serialize into json and is stored in shared preference.
     *
     * @param key   the key against which data needs to be stored
     * @param value the object to be saved
     * @return true is object was saved successfully, false if object could not be saved
     */
    public boolean saveObject(String key, Object value) throws UnsupportedOperationException {
        checkObjectSupport();
        if (value instanceof Serializable)
            return sharedPreferences.edit().putString(key, gson.toJson(value)).commit();
        else
            throw new UnsupportedOperationException("Only Serializable objects can be saved in SharedPreferences");
    }

    /**
     * Fetched the object stored against the key from shared preference.
     * If object is not available, then null will be returned
     *
     * @param key         the key against which object needs to be fetched
     * @param targetClass the Class to which the object belongs to
     * @return the object stored in the shared preference
     */
    public Object fetchObject(String key, Class targetClass) throws UnsupportedOperationException {
        checkObjectSupport();
        Object storedData = sharedPreferences.getString(key, null);
        if (storedData != null)
            storedData = gson.fromJson((String) storedData, targetClass);
        return storedData;
    }

    /**
     * Method to store String data into shared preference against the specified key.
     *
     * @param key   the key against which data needs to be stored
     * @param value the data to be stored
     * @return true, if data is stored successfully, otherwise returns false
     */
    public boolean saveString(String key, String value) {
        return sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * method to fetch String data stored in shared preference against the specified key.
     *
     * @param key the key against which data needs to be fetched
     * @return the data stored against the specified key
     */
    public String fetchString(String key) {
        return sharedPreferences.getString(key, null);
    }

    /**
     * Method to store Integer data into shared preference against the specified key.
     *
     * @param key   the key against which data needs to be stored
     * @param value the data to be stored
     * @return true, if data is stored successfully, otherwise returns false
     */
    public boolean saveInteger(String key, Integer value) {
        return sharedPreferences.edit().putInt(key, value).commit();
    }

    /**
     * method to fetch Integer data stored in shared preference against the specified key.
     *
     * @param key the key against which data needs to be fetched
     * @return the data stored against the specified key
     */
    public Integer fetchInteger(String key) {
        return sharedPreferences.getInt(key, Integer.MIN_VALUE);
    }

    /**
     * Method to store Long data into shared preference against the specified key.
     *
     * @param key   the key against which data needs to be stored
     * @param value the data to be stored
     * @return true, if data is stored successfully, otherwise returns false
     */
    public boolean saveLong(String key, Long value) {
        return sharedPreferences.edit().putLong(key, value).commit();
    }

    /**
     * method to fetch Long data stored in shared preference against the specified key.
     *
     * @param key the key against which data needs to be fetched
     * @return the data stored against the specified key
     */
    public Long fetchLong(String key) {
        return sharedPreferences.getLong(key, Long.MIN_VALUE);
    }

    /**
     * Method to store Boolean data into shared preference against the specified key.
     *
     * @param key   the key against which data needs to be stored
     * @param value the data to be stored
     * @return true, if data is stored successfully, otherwise returns false
     */
    public boolean saveBoolean(String key, Boolean value) {
        return sharedPreferences.edit().putBoolean(key, value).commit();
    }

    /**
     * method to fetch Boolean data stored in shared preference against the specified key.
     *
     * @param key the key against which data needs to be fetched
     * @return the data stored against the specified key
     */
    public Boolean fetchBoolean(String key) {
        return sharedPreferences.getBoolean(key, false);
    }

    /**
     * Method to store Float data into shared preference against the specified key.
     *
     * @param key   the key against which data needs to be stored
     * @param value the data to be stored
     * @return true, if data is stored successfully, otherwise returns false
     */
    public boolean saveFloat(String key, Float value) {
        return sharedPreferences.edit().putFloat(key, value).commit();
    }

    /**
     * Method to fetch Set of Strings data stored in shared preference against the specified key.
     * If no data is found then an empty HashSet<String> will be returned.
     *
     * @param key the key against which data needs to be fetched
     * @return the data stored against the specified key
     */
    public Set<String> fetchStringSet(String key) {
        return sharedPreferences.getStringSet(key, new HashSet<String>());
    }

    /**
     * Method to store Set of Strings data into shared preference against the specified key.
     *
     * @param key   the key against which data needs to be stored
     * @param value the data to be stored
     * @return true, if data is stored successfully, otherwise returns false
     */
    public boolean saveStringSet(String key, Set<String> value) {
        return sharedPreferences.edit().putStringSet(key, value).commit();
    }

    /**
     * method to fetch Float data stored in shared preference against the specified key.
     *
     * @param key the key against which data needs to be fetched
     * @return the data stored against the specified key
     */
    public Float fetchFloat(String key) {
        return sharedPreferences.getFloat(key, Float.MIN_VALUE);
    }

    /**
     * Removes the data present in the shared preference against the specified key
     *
     * @param key the key against which data needs to be removed
     * @return true if data was removed successfully, otherwise returns false
     */
    public Boolean removeData(String key) {
        return sharedPreferences.edit().remove(key).commit();
    }


    /**
     * Method to check whether there is any data stored against the specified key
     *
     * @param key the key to be checked
     * @return true if some data is present against the specified key, otherwise returns false
     */
    public Boolean contains(String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * Method to remove all the data stored in shared preference.
     * Using apply instead of commit here since
     * apply perform the operations in background while commit perform synchronously.
     * <p>
     * If there are lots of data in shared preference using apply is better
     * since deletion will be performed in background
     */
    public void removeAll() {
        sharedPreferences.edit().clear().apply();
    }

    @SuppressWarnings({"unchecked"})
    public boolean putAll(Map<String, ?> keyValuePairs) {
        Object toCheckType = keyValuePairs.entrySet().iterator().next().getValue();

        if (toCheckType instanceof Long) {
            return putAllLong((Map<String, Long>) keyValuePairs);
        } else if (toCheckType instanceof String) {
            return putAllString((Map<String, String>) keyValuePairs);
        } else if (toCheckType instanceof Integer) {
            return putAllInteger((Map<String, Integer>) keyValuePairs);
        } else if (toCheckType instanceof Boolean) {
            return putAllBoolean((Map<String, Boolean>) keyValuePairs);
        } else if (toCheckType instanceof Float) {
            return putAllFloat((Map<String, Float>) keyValuePairs);
        } else if (toCheckType instanceof Set) {
            Object value = ((Set<?>) toCheckType).iterator().next();
            if (value instanceof String) {
                return putAllStringSet((Map<String, Set<String>>) keyValuePairs);
            } else {
                throw new UnsupportedOperationException("Only Set<String> can be stored in SharedPreferences");
            }
        } else if (toCheckType instanceof Serializable) {
            return putAllSerializable((Map<String, ? extends Serializable>) keyValuePairs);
        } else {
            throw new UnsupportedOperationException("Only Serializable objects can be stored in SharedPreferences");
        }
    }

    private boolean putAllLong(Map<String, Long> keyValuePairs) {
        Iterator<? extends Map.Entry<String, Long>> iterator = keyValuePairs.entrySet().iterator();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        while (iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();
            editor.putLong(entry.getKey(), entry.getValue());
        }
        return editor.commit();
    }

    private boolean putAllString(Map<String, String> keyValuePairs) {
        Iterator<? extends Map.Entry<String, String>> iterator = keyValuePairs.entrySet().iterator();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            editor.putString(entry.getKey(), entry.getValue());
        }
        return editor.commit();
    }

    private boolean putAllFloat(Map<String, Float> keyValuePairs) {
        Iterator<? extends Map.Entry<String, Float>> iterator = keyValuePairs.entrySet().iterator();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        while (iterator.hasNext()) {
            Map.Entry<String, Float> entry = iterator.next();
            editor.putFloat(entry.getKey(), entry.getValue());
        }
        return editor.commit();
    }

    private boolean putAllInteger(Map<String, Integer> keyValuePairs) {
        Iterator<? extends Map.Entry<String, Integer>> iterator = keyValuePairs.entrySet().iterator();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> entry = iterator.next();
            editor.putInt(entry.getKey(), entry.getValue());
        }
        return editor.commit();
    }

    private boolean putAllBoolean(Map<String, Boolean> keyValuePairs) {
        Iterator<? extends Map.Entry<String, Boolean>> iterator = keyValuePairs.entrySet().iterator();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        while (iterator.hasNext()) {
            Map.Entry<String, Boolean> entry = iterator.next();
            editor.putBoolean(entry.getKey(), entry.getValue());
        }
        return editor.commit();
    }

    private boolean putAllStringSet(Map<String, Set<String>> keyValuePairs) {
        Iterator<? extends Map.Entry<String, Set<String>>> iterator = keyValuePairs.entrySet().iterator();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        while (iterator.hasNext()) {
            Map.Entry<String, Set<String>> entry = iterator.next();
            editor.putStringSet(entry.getKey(), entry.getValue());
        }
        return editor.commit();
    }

    private boolean putAllSerializable(Map<String, ? extends Serializable> keyValuePairs) {
        Iterator<? extends Map.Entry<String, ? extends Serializable>> iterator = keyValuePairs.entrySet().iterator();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        while (iterator.hasNext()) {
            Map.Entry<String, ? extends Serializable> entry = iterator.next();
            editor.putString(entry.getKey(), gson.toJson(entry.getValue()));
        }
        return editor.commit();
    }

}
