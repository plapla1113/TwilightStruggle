package tw.wilson.twilightstruggle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

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
        int side = CountryManager.SIDE_US;
        CountryManager countryManager = CountryManager.getCountryManager();
        assertEquals(0, countryManager.getInfluence(side, "Japan"));
        countryManager.addInfluence(side, "Japan", 3);
        CountryManager countryManager2 = CountryManager.getCountryManager();
        assertEquals(3, countryManager2.getInfluence(side, "Japan"));
        countryManager2.addInfluence(side, "Japan", -4);
        assertEquals(0, countryManager.getInfluence(side, "Japan"));
    }

    @Test
    public void addUSSRInfluence() throws Exception {
        int side = CountryManager.SIDE_USSR;
        CountryManager countryManager = CountryManager.getCountryManager();
        assertEquals(0, countryManager.getInfluence(side, "Japan"));
        countryManager.addInfluence(side, "Japan", 3);
        CountryManager countryManager2 = CountryManager.getCountryManager();
        assertEquals(3, countryManager2.getInfluence(side, "Japan"));
        countryManager2.addInfluence(side, "Japan", -4);
        assertEquals(0, countryManager.getInfluence(side, "Japan"));
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
        assertEquals(CountryManager.CONTROLLED_NONE, countryManager.getControlledState("Japan"));
        assertEquals(0, countryManager.getInfluence(CountryManager.SIDE_US,"Japan"));
        assertEquals(0, countryManager.getInfluence(CountryManager.SIDE_USSR,"Japan"));

        countryManager.addInfluence(CountryManager.SIDE_US, "Japan", 5);
        assertEquals(CountryManager.CONTROLLED_US, countryManager.getControlledState("Japan"));
        assertEquals(5, countryManager.getInfluence(CountryManager.SIDE_US, "Japan"));
        assertEquals(0, countryManager.getInfluence(CountryManager.SIDE_USSR, "Japan"));

        countryManager.addInfluence(CountryManager.SIDE_USSR, "Japan", 4);
        assertEquals(CountryManager.CONTROLLED_NONE, countryManager.getControlledState("Japan"));
        assertEquals(5, countryManager.getInfluence(CountryManager.SIDE_US, "Japan"));
        assertEquals(4, countryManager.getInfluence(CountryManager.SIDE_USSR, "Japan"));

        countryManager.addInfluence(CountryManager.SIDE_US, "Japan", -7);
        assertEquals(CountryManager.CONTROLLED_USSR, countryManager.getControlledState("Japan"));
        assertEquals(0, countryManager.getInfluence(CountryManager.SIDE_US, "Japan"));
        assertEquals(4, countryManager.getInfluence(CountryManager.SIDE_USSR, "Japan"));
    }

    @Test
    public void getNeighbors() throws Exception {
        CountryManager countryManager = CountryManager.getCountryManager();

        ArrayList<String> countryJapanNeighbors = countryManager.getNeighbors("Japan");
        assertTrue(countryJapanNeighbors.contains("US"));
        assertTrue(countryJapanNeighbors.contains("Taiwan"));
        assertTrue(countryJapanNeighbors.contains("SouthKorea"));
        assertFalse(countryJapanNeighbors.contains("Japan"));
    }

    @Test
    public void getNumOfControlledNeighbors() throws Exception {
        int controlledState_US = CountryManager.CONTROLLED_US;
        int controlledState_USSR = CountryManager.CONTROLLED_USSR;
        int side_US = CountryManager.SIDE_US;
        int side_USSR = CountryManager.SIDE_USSR;
        CountryManager countryManager = CountryManager.getCountryManager();

        assertEquals(1, countryManager.getNumOfControlledNeighbors(controlledState_US, "Japan"));
        assertEquals(0, countryManager.getNumOfControlledNeighbors(controlledState_USSR, "Japan"));

        countryManager.addInfluence(side_US, "Taiwan", 3);
        assertEquals(3, countryManager.getInfluence(side_US, "Taiwan"));

        assertEquals(2, countryManager.getNumOfControlledNeighbors(controlledState_US, "Japan"));
        assertEquals(0, countryManager.getNumOfControlledNeighbors(controlledState_USSR, "Japan"));

        countryManager.addInfluence(side_US, "Taiwan", -1);
        assertEquals(2, countryManager.getInfluence(side_US, "Taiwan"));

        assertEquals(1, countryManager.getNumOfControlledNeighbors(controlledState_US, "Japan"));
        assertEquals(0, countryManager.getNumOfControlledNeighbors(controlledState_USSR, "Japan"));

        countryManager.addInfluence(side_USSR, "SouthKorea", 3);
        assertEquals(3, countryManager.getInfluence(side_USSR, "SouthKorea"));
        assertEquals(2, countryManager.getInfluence(side_US, "Taiwan"));

        assertEquals(1, countryManager.getNumOfControlledNeighbors(controlledState_US, "Japan"));
        assertEquals(1, countryManager.getNumOfControlledNeighbors(controlledState_USSR, "Japan"));
    }

    @Test
    public void getCountryList() throws Exception {
        CountryManager countryManager = CountryManager.getCountryManager();

        ArrayList<String> countryList = countryManager.getCountryList("Asia");
        assertTrue(countryList.contains("Japan"));
        assertTrue(countryList.contains("Taiwan"));
        assertTrue(countryList.contains("SouthKorea"));
        assertTrue(countryList.contains("Philippines"));
        assertFalse(countryList.contains("US"));

        countryList = countryManager.getCountryList("SoutheastAsia");
        assertTrue(countryList.contains("Philippines"));
        assertFalse(countryList.contains("Japan"));

        countryList = countryManager.getCountryList("Asia");
        assertTrue(countryList.contains("Japan"));
        assertTrue(countryList.contains("Taiwan"));
        assertTrue(countryList.contains("SouthKorea"));
        assertTrue(countryList.contains("Philippines"));
        assertFalse(countryList.contains("US"));
    }
}
