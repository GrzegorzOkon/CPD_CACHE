package okon.CPD_CACHE;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TxtFormatter {
    private final Message message;

    public TxtFormatter(Message message) {
        this.message = message;
    }

    public List<byte[]> format() {
        List<byte[]> formattedText = new ArrayList<>();

        formattedText.add("Polecenie: dbcc proc_cache(free_unused) zostało wykonane poprawnie.".getBytes());
        formattedText.add(("Czas wykonania " + Duration.between(message.getStartTime(), message.getEndTime()).getNano() + "ms").getBytes());

        return formattedText;
    }
}