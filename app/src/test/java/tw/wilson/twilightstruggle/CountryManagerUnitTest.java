package tw.wilson.twilightstruggle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import tw.wilson.twilightstruggle.country.CountryManager;

import static org.junit.Assert.*;

/**
 * Created by MSI Pro on 2017/8/6.
 */
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class CountryManagerUnitTest {
    @Test
    public void addUSInfluence() throws Exception {
        CountryManager countryManager = CountryManager.getCountryManager();
        assertEquals(0, countryManager.getUSInfluence("Japan"));
        countryManager.addUSInfluence("Japan", 3);
        CountryManager countryManager2 = CountryManager.getCountryManager();
        assertEquals(3, countryManager2.getUSInfluence("Japan"));
        countryManager2.addUSInfluence("Japan", -4);
        assertEquals(0, countryManager2.getUSInfluence("Japan"));
    }

    @Test
    public void addUSSRInfluence() throws Exception {
        CountryManager countryManager = CountryManager.getCountryManager();
        assertEquals(0, countryManager.getUSSRInfluence("Japan"));
        countryManager.addUSSRInfluence("Japan", 3);
        CountryManager countryManager2 = CountryManager.getCountryManager();
        assertEquals(3, countryManager2.getUSSRInfluence("Japan"));
        countryManager2.addUSSRInfluence("Japan", -4);
        assertEquals(0, countryManager2.getUSSRInfluence("Japan"));
    }

    @Test
    public void getStability() throws Exception {
        CountryManager countryManager = CountryManager.getCountryManager();
        assertEquals(3, countryManager.getStability("Taiwan"));
        assertEquals(4, countryManager.getStability("Japan"));
        assertEquals(2, countryManager.getStability("Philippines"));
    }

    @Test
    public void countryControlledState() throws Exception {
        CountryManager countryManager = CountryManager.getCountryManager();
        assertEquals(CountryManager.CONTROLLED_NONE, countryManager.controlledState("Japan"));
        assertEquals(0, countryManager.getUSInfluence("Japan"));
        assertEquals(0, countryManager.getUSSRInfluence("Japan"));

        countryManager.addUSInfluence("Japan", 5);
        assertEquals(CountryManager.CONTROLLED_US, countryManager.controlledState("Japan"));
        assertEquals(5, countryManager.getUSInfluence("Japan"));
        assertEquals(0, countryManager.getUSSRInfluence("Japan"));

        countryManager.addUSSRInfluence("Japan", 4);
        assertEquals(CountryManager.CONTROLLED_NONE, countryManager.controlledState("Japan"));
        assertEquals(5, countryManager.getUSInfluence("Japan"));
        assertEquals(4, countryManager.getUSSRInfluence("Japan"));

        countryManager.addUSInfluence("Japan", -7);
        assertEquals(CountryManager.CONTROLLED_USSR, countryManager.controlledState("Japan"));
        assertEquals(0, countryManager.getUSInfluence("Japan"));
        assertEquals(4, countryManager.getUSSRInfluence("Japan"));
    }
}
