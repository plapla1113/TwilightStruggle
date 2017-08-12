package tw.wilson.twilightstruggle.country;

import android.support.v4.util.ArrayMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by MSI Pro on 2017/8/2.
 */

class CountryData {
    protected static String mJsonText = "{\n" +
            "\"JAPAN\":{\"countryName\":\"Japan\",\"countryContinent\":\"Asia\",\"countrySubContinent\":\"\",\"countryStability\":4,\"neighborCountry\":[\"SouthKorea\",\"Taiwan\",\"US\",\"Philippines\"],\"isBattleground\":true},\n" +
            "\"TAIWAN\":{\"countryName\":\"Taiwan\",\"countryContinent\":\"Asia\",\"countrySubContinent\":\"\",\"countryStability\":3,\"neighborCountry\":[\"SouthKorea\",\"Japan\"],\"isBattleground\":false},\n" +
            "\"PHILIPPINES\":{\"countryName\":\"Philippines\",\"countryContinent\":\"Asia\",\"countrySubContinent\":\"SoutheastAsia\",\"countryStability\":2,\"neighborCountry\":[\"Japan\",\"Indonesia\"],\"isBattleground\":false},\n" +
            "\"SOUTHKOREA\":{\"countryName\":\"SouthKorea\",\"countryContinent\":\"Asia\",\"countrySubContinent\":\"\",\"countryStability\":3,\"neighborCountry\":[\"Japan\",\"NorthKorea\"],\"isBattleground\":true}\n" +
            "}";

    protected static ArrayMap<String, ArrayList<String>> countryMap = new ArrayMap<>();


    public static int getCountryStability(String countryName) {
        int stability = 0;
        try {
            stability = new JSONObject(mJsonText).getJSONObject(countryName).getInt("countryStability");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return stability;
    }

    public static boolean isCountryBattleground(String countryName) {
        boolean isBattleground = false;
        try {
            isBattleground = new JSONObject(mJsonText).getJSONObject(countryName).getBoolean("isBattleground");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return isBattleground;
    }

    public static ArrayList<String> getNeighbors(String countryName) {
        ArrayList<String> neighbors = new ArrayList();
        try {
            JSONArray neighborJSONArray = new JSONObject(mJsonText).getJSONObject(countryName).getJSONArray("neighborCountry");
            for(int i=0;i<neighborJSONArray.length();i++){
                neighbors.add(neighborJSONArray.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return neighbors;
    }

    public static String getContinent(String countryName) {
        String continent = "";
        try {
            continent = new JSONObject(mJsonText).getJSONObject(countryName).getString("countryContinent");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return continent;
    }

    public static String getSubContinent(String countryName) {
        String subContinent = "";
        try {
            subContinent = new JSONObject(mJsonText).getJSONObject(countryName).getString("countrySubContinent");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return subContinent;
    }

    public static ArrayList<String> getCountryList(String continent) {
        ArrayList<String> countryList = countryMap.get(continent);
        if(countryList == null) {
            countryList = new ArrayList<>();
            try {
                JSONObject jsonObject = new JSONObject(mJsonText);
                Iterator<String> iter = jsonObject.keys();
                while (iter.hasNext()) {
                    String key = iter.next();
                    String countryName = jsonObject.getJSONObject(key).getString("countryName");
                    String countryContinent = jsonObject.getJSONObject(key).getString("countryContinent");
                    String countrySubContinent = jsonObject.getJSONObject(key).getString("countrySubContinent");
                    if (continent.equals(countryContinent) || continent.equals(countrySubContinent)) {
                        countryList.add(countryName);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            countryMap.put(continent, countryList);
        }

        return countryList;
    }
}
