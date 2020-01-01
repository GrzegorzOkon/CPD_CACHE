package okon.CPD_CACHE;

import java.nio.charset.StandardCharsets;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MessageFormatterTest {
    @Test
    public void shouldSayThatFormattedMessageIsEquals() {
        Message messageStub = new Message(100000, 200000, "4000");

        assertEquals("Czas wykonania: 4000 ms.", new String(new MessageFormatter(messageStub).format().get(1)));
    }
}
