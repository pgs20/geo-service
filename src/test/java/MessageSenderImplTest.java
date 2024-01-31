import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;


public class MessageSenderImplTest {
    @Test
    public void testRussianMessageSendingWhenIpAddressBelongsToRussianSegment() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.212.0.1");
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать!");
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        String expectedMessage = "Добро пожаловать!";

        String actualMessage = messageSender.send(headers);

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void testEnglishMessageSendingWhenIpAddressBelongsToAmericanSegment() {
        GeoService geoService = Mockito.mock(GeoService.class);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);

        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.212.0.1");
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome!");
        Mockito.when(geoService.byIp(Mockito.anyString())).thenReturn(new Location("New York", Country.USA, null,  0));

        String expectedMessage = "Welcome!";

        String actualMessage = messageSender.send(headers);

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
