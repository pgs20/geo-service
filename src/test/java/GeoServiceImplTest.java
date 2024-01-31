import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceImplTest {
    @Test
    public void byIpTest() {
        GeoServiceImpl geoService = new GeoServiceImpl();

        Location result = geoService.byIp("127.0.0.1");

        assertLocation(result, null, null, null, 0);
    }

    private void assertLocation(Location location, String city, Country country, String street, int number) {
        assertEquals(city, location.getCity());
        assertEquals(country, location.getCountry());
        assertEquals(street, location.getStreet());
        assertEquals(number, location.getBuiling());
    }
}
