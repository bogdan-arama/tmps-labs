import java.util.Optional;
import java.util.Set;

public class WordMark extends Mark {
    // Common words cannot be monopolised by a single company
    private static final Set<String> GENERIC_WORDS = Set.of("COFFEE", "WINE", "BEST", "SUPER", "SHOP");

    public WordMark(TrademarkApplication application) {
        super(application);
    }

    @Override
    public String getType() {
        return "Word mark";
    }

    @Override
    protected int getBaseFee() {
        return 1200;
    }

    @Override
    public Optional<String> findRefusalReason() {
        String name = getApplication().getMarkName().toUpperCase();
        if (GENERIC_WORDS.contains(name)) {
            return Optional.of("\"" + name + "\" is a generic word and lacks distinctiveness");
        }
        return Optional.empty();
    }
}
