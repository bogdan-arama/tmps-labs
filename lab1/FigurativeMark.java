import java.util.Optional;

public class FigurativeMark extends Mark {
    public FigurativeMark(TrademarkApplication application) {
        super(application);
    }

    @Override
    public String getType() {
        return "Figurative mark";
    }

    @Override
    protected int getBaseFee() {
        return 1500;
    }

    @Override
    public Optional<String> findRefusalReason() {
        if (getApplication().getLogoFile() == null) {
            return Optional.of("a figurative mark must include a logo image");
        }
        return Optional.empty();
    }
}
