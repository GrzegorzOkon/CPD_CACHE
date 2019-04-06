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

        if (isCacheFreed()) {
            formattedText.add("Polecenie 'dbcc proc_cache(free_unused)' zostalo wykonane poprawnie.".getBytes());
            formattedText.add(("Czas wykonania: " + message.getExecutionTime() + " ms.").getBytes());
        } else {
            formattedText.add("Pamięć cache nie została zwolniona przez polecenie 'dbcc proc_cache(free_unused)'.".getBytes());
        }

        return formattedText;
    }

    private boolean isCacheFreed() {
        if (message.getEndingFreeCacheSize() > message.getStartingFreeCacheSize()) {
            return true;
        } else return false;
    }
}