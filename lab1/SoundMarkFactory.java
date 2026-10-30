public class SoundMarkFactory extends MarkFactory {
    @Override
    public Mark createMark(TrademarkApplication application) {
        return new SoundMark(application);
    }
}
