package okon.CPD_CACHE;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.assertEquals;

public class TxtFormatterTest {
    @Test
    public void shouldSayThatFormattedMessageIsEquals() {
        LocalDateTime startStub = LocalDateTime.of(2018, Month.DECEMBER, 07, 15, 31, 40);
        LocalDateTime endStub = LocalDateTime.of(2018, Month.DECEMBER, 07, 15, 31, 44);
        Message messageStub = new Message(startStub, endStub);

        assertEquals("Czas wykonania: 4000 ms.", new String(new TxtFormatter(messageStub).format().get(1), StandardCharsets.UTF_8));
    }
}
