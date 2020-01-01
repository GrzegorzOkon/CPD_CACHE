package okon.CPD_CACHE;

import okon.CPD_CACHE.config.ConfigurationParamsReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigurationParamsReaderTest {
    @Test
    public void shouldSayThatStringIsEqualsConvertedHex() {
        String plainString = "maniana";
        String hexString = "6d616e69616e61";
        assertEquals(plainString, ConfigurationParamsReader.fromHex(hexString));
    }
}
