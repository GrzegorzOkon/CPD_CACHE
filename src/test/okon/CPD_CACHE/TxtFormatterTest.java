package okon.CPD_CACHE;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TxtFormatterTest {
    @Test
    public void shouldSayThatFormattedMessageIsEquals() {
        Message messageStub = new Message(100000, 200000, "4000");

        assertEquals("Czas wykonania: 4000 ms.", new String(new TxtFormatter(messageStub).format().get(1), StandardCharsets.UTF_8));
    }
}
