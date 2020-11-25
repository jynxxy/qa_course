package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceHttpGet;
import com.lavasoft.GeoIPServiceSoap;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("194.28.29.152");
        assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>47</State></GeoIP>");
    }

    @Test
    public void testInvalidIP() {
        String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("194.28.29.xxx");
        assertEquals(geoIP, "<GeoIP><Country>RU</Country><State>47</State></GeoIP>");
    }
}
