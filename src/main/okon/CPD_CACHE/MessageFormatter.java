package okon.CPD_CACHE;

import java.util.ArrayList;
import java.util.List;

public class MessageFormatter {
    private final Message message;

    public MessageFormatter(Message message) {
        this.message = message;
    }

    public List<String> format() {
        List<String> result = new ArrayList<>();
        if (isCacheFreed()) {
            result.add("Polecenie 'dbcc proc_cache(free_unused)' zostalo wykonane poprawnie.");
            result.add(("Czas wykonania: " + message.getExecutionTime() + " ms."));
        } else {
            result.add("Pamięć cache nie została zwolniona przez polecenie 'dbcc proc_cache(free_unused)'.");
        }
        return result;
    }

    private boolean isCacheFreed() {
        if (message.getEndingFreeCacheSize() > message.getStartingFreeCacheSize()) {
            return true;
        } else return false;
    }
}