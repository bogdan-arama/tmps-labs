import java.time.Year;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

// SINGLETON: there is exactly one national trademark register. If two registries existed,
// they could hand out the same registration number or both accept the same mark name.
public class TrademarkRegistry {
    private final Map<String, Mark> registeredMarks = new LinkedHashMap<>();
    private int counter = 0;

    // Private constructor: nobody outside this class can call "new TrademarkRegistry()"
    private TrademarkRegistry() {
    }

    // Holder idiom: the JVM creates INSTANCE lazily and thread-safely on first access
    private static class Holder {
        private static final TrademarkRegistry INSTANCE = new TrademarkRegistry();
    }

    public static TrademarkRegistry getInstance() {
        return Holder.INSTANCE;
    }

    // Returns the registration number, or throws if the mark cannot be registered
    public synchronized String register(Mark mark) {
        Optional<String> refusal = mark.findRefusalReason();
        if (refusal.isPresent()) {
            throw new IllegalArgumentException("Refused: " + refusal.get());
        }
        String name = mark.getApplication().getMarkName();
        if (isNameTaken(name)) {
            throw new IllegalArgumentException("Refused: \"" + name + "\" is already registered");
        }
        counter++;
        String number = String.format("MD-%d-%04d", Year.now().getValue(), counter);
        registeredMarks.put(number, mark);
        return number;
    }

    public Map<String, Mark> getRegisteredMarks() {
        return Collections.unmodifiableMap(registeredMarks);
    }

    private boolean isNameTaken(String name) {
        for (Mark mark : registeredMarks.values()) {
            if (mark.getApplication().getMarkName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
