public class WordMarkFactory extends MarkFactory {
    @Override
    public Mark createMark(TrademarkApplication application) {
        return new WordMark(application);
    }
}
