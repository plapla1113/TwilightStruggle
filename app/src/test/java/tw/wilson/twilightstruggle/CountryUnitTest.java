package tw.wilson.twilightstruggle;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;

import tw.wilson.twilightstruggle.country.Country;

import static org.junit.Assert.*;

/**
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@Config(manifest=Config.NONE)
@RunWith(RobolectricTestRunner.class)
public class CountryUnitTest {

    @Test
    public void getCountryName() throws Exception {
        Country countryJapan = new Country("Japan");
        Country countryTaiwan = new Country("Taiwan");
        assertEquals("Japan",countryJapan.getCountryName());
        assertEquals("Taiwan",countryTaiwan.getCountryName());
    }

    @Test
    public void getCountryStability() throws Exception {
        Country countryJapan = new Country("Japan");
        Country countryTaiwan = new Country("Taiwan");
        assertEquals(4, countryJapan.getCountryStability());
        assertEquals(3, countryTaiwan.getCountryStability());
    }

    @Test
    public void getUSInfluence() throws Exception {
        Country countryJapan = new Country("Japan");
        Country countryTaiwan = new Country("Taiwan");
        assertEquals(0, countryJapan.getUSInfluence());
        assertEquals(0, countryTaiwan.getUSInfluence());
    }

    @Test
    public void setUSInfluence() throws Exception {
        Country countryJapan = new Country("Japan");
        countryJapan.setUSInfluence(3);
        assertEquals(3, countryJapan.getUSInfluence());
    }

    @Test
    public void getUSSRInfluence() throws Exception {
        Country countryJapan = new Country("Japan");
        Country countryTaiwan = new Country("Taiwan");
        assertEquals(0, countryJapan.getUSSRInfluence());
        assertEquals(0, countryTaiwan.getUSSRInfluence());
    }

    @Test
    public void setUSSRInfluence() throws Exception {
        Country countryJapan = new Country("Japan");
        countryJapan.setUSSRInfluence(3);
        assertEquals(3, countryJapan.getUSSRInfluence());
    }

    @Test
    public void isBattleground() throws Exception {
        Country countryJapan = new Country("Japan");
        Country countryTaiwan = new Country("Taiwan");
        assertTrue(countryJapan.isBattleground());
        assertFalse(countryTaiwan.isBattleground());
    }

    @Test
    public void setBattleground() throws Exception {
        Country countryJapan = new Country("Japan");
        countryJapan.setBattleground(false);
        assertFalse(countryJapan.isBattleground());
    }

    @Test
    public void getNeighbors() throws Exception {
        Country countryJapan = new Country("Japan");
        Country countryTaiwan = new Country("Taiwan");
        ArrayList<String> countryJapanNeighbors = countryJapan.getNeighbors();
        ArrayList<String> countryTaiwanNeighbors = countryTaiwan.getNeighbors();
        assertTrue(countryJapanNeighbors.contains("US"));
        assertTrue(countryJapanNeighbors.contains("Taiwan"));
        assertTrue(countryJapanNeighbors.contains("SouthKorea"));
        assertTrue(countryJapanNeighbors.contains("Philippines"));
        assertFalse(countryJapanNeighbors.contains("Japan"));

        assertTrue(countryTaiwanNeighbors.contains("SouthKorea"));
        assertTrue(countryTaiwanNeighbors.contains("Japan"));
        assertFalse(countryTaiwanNeighbors.contains("US"));
    }

    @Test
    public void getContinent() throws Exception {
        Country countryJapan = new Country("Japan");
        Country countryTaiwan = new Country("Taiwan");
        Country countryPhilippines = new Country("Philippines");
        assertEquals("Asia", countryJapan.getContinent());
        assertEquals("Asia", countryTaiwan.getContinent());
        assertEquals("Asia", countryPhilippines.getContinent());
    }

    @Test
    public void getSubContinent() throws Exception {
        Country countryJapan = new Country("Japan");
        Country countryTaiwan = new Country("Taiwan");
        Country countryPhilippines = new Country("Philippines");

        assertEquals("", countryJapan.getSubContinent());
        assertEquals("", countryTaiwan.getSubContinent());
        assertEquals("SoutheastAsia", countryPhilippines.getSubContinent());
    }
}