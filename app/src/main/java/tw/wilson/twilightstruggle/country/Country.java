package tw.wilson.twilightstruggle.country;

import java.util.ArrayList;

/**
 * Created by MSI Pro on 2017/8/1.
 */

public class Country {
    private String mCountryName;
    private int mCountryStability;
    private int mUSInfluence;
    private int mUSSRInfluence;
    private boolean mIsBattleground;
    private ArrayList<String> mNeighborsArrayList;
    private String mContinent;
    private String mSubContinent;

    public Country(String countryName) {
        this.mCountryName = countryName;
        initCountryStatus();
    }

    private void initCountryStatus() {
        setCountryStability(CountryData.getCountryStability(getCountryName()));
        setUSInfluence(0);
        setUSSRInfluence(0);
        setBattleground(CountryData.isCountryBattleground(getCountryName()));
        setNeighbors(CountryData.getNeighbors(getCountryName()));
        setContinent(CountryData.getContinent(getCountryName()));
        setSubContinent(CountryData.getSubContinent(getCountryName()));
    }

    public String getCountryName() {
        return mCountryName;
    }

    public int getCountryStability() {
        return mCountryStability;
    }

    private void setCountryStability(int stability) {
        this.mCountryStability = stability;
    }

    public int getUSInfluence() {
        return mUSInfluence;
    }

    public void setUSInfluence(int influence) {
        this.mUSInfluence = influence;
    }

    public int getUSSRInfluence() {
        return mUSSRInfluence;
    }

    public void setUSSRInfluence(int influence) {
        this.mUSSRInfluence = influence;
    }

    public boolean isBattleground() {
        return mIsBattleground;
    }

    public void setBattleground(boolean battleground) {
        this.mIsBattleground = battleground;
    }

    public ArrayList<String> getNeighbors() {
        return mNeighborsArrayList;
    }

    private void setNeighbors(ArrayList<String> arrayList) {
        this.mNeighborsArrayList = arrayList;
    }

    public String getContinent() {
        return mContinent;
    }

    private void setContinent(String continent) {
        this.mContinent = continent;
    }

    public String getSubContinent() {
        return mSubContinent;
    }

    private void setSubContinent(String subContinent) {
        this.mSubContinent = subContinent;
    }
}
