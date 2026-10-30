import java.util.Optional;

// Product of the Factory Method: every concrete mark type has its own fee and examination rule
public abstract class Mark {
    private static final int EXTRA_CLASS_FEE = 300;

    private final TrademarkApplication application;

    protected Mark(TrademarkApplication application) {
        this.application = application;
    }

    public TrademarkApplication getApplication() {
        return application;
    }

    // Base fee covers the first Nice class; each extra class costs more
    public int getFee() {
        int extraClasses = application.getNiceClasses().size() - 1;
        return getBaseFee() + extraClasses * EXTRA_CLASS_FEE;
    }

    public abstract String getType();

    protected abstract int getBaseFee();

    // Empty if the mark passes examination, otherwise the reason it is refused
    public abstract Optional<String> findRefusalReason();
}
