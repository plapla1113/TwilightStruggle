package tw.wilson.twilightstruggle.country;

import android.support.v4.util.ArrayMap;

/**
 * Created by MSI Pro on 2017/8/6.
 */

public class CountryManager {
    public static final int CONTROLLED_NONE = 0;
    public static final int CONTROLLED_US = 1;
    public static final int CONTROLLED_USSR = 2;

    private static CountryManager countryManager = new CountryManager();
    private ArrayMap<String, Country> countryArrayMap = new ArrayMap();

    private CountryManager() {}

    public static CountryManager getCountryManager() {
        return countryManager;
    }

    private Country getCountryFromArrayMap(String country) {
        Country countryObj = countryArrayMap.get(country);
        if(countryObj == null){
            countryObj = new Country(country);
            countryArrayMap.put(country, countryObj);
            return countryObj;
        }
        return countryObj;
    }

    private void overrideCountryToArrayMap(Country countryObj){
        countryArrayMap.put(countryObj.getCountryName(), countryObj);
    }

    public int getStability(String country) {
        Country countryObj = getCountryFromArrayMap(country);
        return countryObj.getCountryStability();
    }

    public void addUSInfluence(String country, int influence) {
        Country countryObj = getCountryFromArrayMap(country);
        int oldInfluence = countryObj.getUSInfluence();
        if(oldInfluence + influence < 0) {
            countryObj.setUSInfluence(0);
        } else {
            countryObj.setUSInfluence(oldInfluence + influence);
        }
        overrideCountryToArrayMap(countryObj);
    }

    public int getUSInfluence(String country) {
        Country countryObj = getCountryFromArrayMap(country);
        return countryObj.getUSInfluence();
    }

    public void addUSSRInfluence(String country, int influence) {
        Country countryObj = getCountryFromArrayMap(country);
        int oldInfluence = countryObj.getUSSRInfluence();
        if(oldInfluence + influence < 0) {
            countryObj.setUSSRInfluence(0);
        } else {
            countryObj.setUSSRInfluence(oldInfluence + influence);
        }
        overrideCountryToArrayMap(countryObj);
    }

    public int getUSSRInfluence(String country) {
        Country countryObj = getCountryFromArrayMap(country);
        return countryObj.getUSSRInfluence();
    }

    public int controlledState(String country) {
        Country countryObj = getCountryFromArrayMap(country);
        int stability = countryObj.getCountryStability();
        int USInfluence = countryObj.getUSInfluence();
        int USSRInfluence = countryObj.getUSSRInfluence();

        if(USInfluence > USSRInfluence && (USInfluence-USSRInfluence) >= stability){
            return CONTROLLED_US;
        }else if(USSRInfluence > USInfluence && (USSRInfluence-USInfluence) >= stability){
            return CONTROLLED_USSR;
        }
        return CONTROLLED_NONE;
    }
}
