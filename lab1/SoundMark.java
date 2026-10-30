import java.util.Optional;

public class SoundMark extends Mark {
    public SoundMark(TrademarkApplication application) {
        super(application);
    }

    @Override
    public String getType() {
        return "Sound mark";
    }

    @Override
    protected int getBaseFee() {
        return 2000;
    }

    @Override
    public Optional<String> findRefusalReason() {
        String soundFile = getApplication().getSoundFile();
        if (soundFile == null || !soundFile.endsWith(".mp3")) {
            return Optional.of("a sound mark must include an .mp3 recording");
        }
        return Optional.empty();
    }
}
