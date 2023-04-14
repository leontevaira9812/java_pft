package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class GeoIpServiceTests {

  @Test
  public void testMyIp() {

    String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("183.88.211.87");
    assertEquals(ipLocation, "<GeoIP><Country>TH</Country><State>43</State></GeoIP>");
  }
}
