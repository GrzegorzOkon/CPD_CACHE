package okon.CPD_CACHE;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataSourceBuilderTest {
    @Test
    public void shouldSayThatStringIsEqualsConvertedHex() {
        DataSourceBuilder builder = new DataSourceBuilder();

        String plainString = "maniana";
        String hexString = "6d616e69616e61";

        assertEquals(plainString, builder.fromHex(hexString));
    }
}
