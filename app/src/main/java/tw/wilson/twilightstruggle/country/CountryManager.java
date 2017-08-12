package tw.wilson.twilightstruggle.country;

import android.support.v4.util.ArrayMap;

import java.util.ArrayList;

/**
 * Created by MSI Pro on 2017/8/6.
 */

public class CountryManager {
    public static final int CONTROLLED_NONE = 0;
    public static final int CONTROLLED_US = 1;
    public static final int CONTROLLED_USSR = 2;

    public static final int SIDE_US = 101;
    public static final int SIDE_USSR = 102;

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

    private Country getCountryFromArrayMapWithoutPutBack(String country) {
        Country countryObj = countryArrayMap.get(country);
        if(countryObj == null){
            countryObj = new Country(country);
            return countryObj;
        }
        return countryObj;
    }

    private void overrideCountryToArrayMap(Country countryObj){
        countryArrayMap.put(countryObj.getCountryName(), countryObj);
    }

    public int getStability(String country) {
        Country countryObj = getCountryFromArrayMapWithoutPutBack(country);
        return countryObj.getCountryStability();
    }

    private void addUSInfluence(String country, int influence) {
        Country countryObj = getCountryFromArrayMap(country);
        int oldInfluence = countryObj.getUSInfluence();
        if(oldInfluence + influence < 0) {
            countryObj.setUSInfluence(0);
        } else {
            countryObj.setUSInfluence(oldInfluence + influence);
        }
        overrideCountryToArrayMap(countryObj);
    }

    private int getUSInfluence(String country) {
        Country countryObj = getCountryFromArrayMapWithoutPutBack(country);
        return countryObj.getUSInfluence();
    }

    private void addUSSRInfluence(String country, int influence) {
        Country countryObj = getCountryFromArrayMap(country);
        int oldInfluence = countryObj.getUSSRInfluence();
        if(oldInfluence + influence < 0) {
            countryObj.setUSSRInfluence(0);
        } else {
            countryObj.setUSSRInfluence(oldInfluence + influence);
        }
        overrideCountryToArrayMap(countryObj);
    }

    private int getUSSRInfluence(String country) {
        Country countryObj = getCountryFromArrayMapWithoutPutBack(country);
        return countryObj.getUSSRInfluence();
    }

    public int getControlledState(String country) {
        Country countryObj = getCountryFromArrayMapWithoutPutBack(country);
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

    public int getInfluence(int side, String country) {
        if (side == SIDE_US) {
            return getUSInfluence(country);
        } else if (side == SIDE_USSR) {
            return getUSSRInfluence(country);
        }
        //Should not be here
        return 0;
    }

    public void addInfluence(int side, String country, int influence) {
        if (side == SIDE_US) {
            addUSInfluence(country, influence);
        } else if (side == SIDE_USSR) {
            addUSSRInfluence(country, influence);
        }
    }

    public int getNumOfControlledNeighbors(int state, String country) {
        int numOfControlledNeighbors = 0;
        ArrayList<String> neighborsList = getNeighbors(country);
        for (int i=0;i<neighborsList.size();i++) {
            String nCountry = neighborsList.get(i);
            if(nCountry.equals("US") || nCountry.equals("USSR")){
                if(state == CONTROLLED_US && nCountry.equals("US")){
                    numOfControlledNeighbors++;
                }else if(state == CONTROLLED_USSR && nCountry.equals("USSR")){
                    numOfControlledNeighbors++;
                }
            } else {
                if (state == getControlledState(nCountry)) {
                    numOfControlledNeighbors++;
                }
            }
        }
        return numOfControlledNeighbors;
    }

    public ArrayList<String> getNeighbors(String country) {
        Country countryObj = getCountryFromArrayMapWithoutPutBack(country);
        return countryObj.getNeighbors();
    }

    public ArrayList<String> getCountryList(String continent) {
        ArrayList<String> countryList = CountryData.getCountryList(continent);
        return countryList;
    }
}
