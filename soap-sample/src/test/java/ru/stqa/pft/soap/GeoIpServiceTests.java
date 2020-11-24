package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GeoIpServiceTests {

    @Test
    public void testMyIp() {
        String geoIP = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("194.28.29.152");
        assertEquals(geoIP, "RUS");
    }
}
