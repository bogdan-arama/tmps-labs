public class FigurativeMarkFactory extends MarkFactory {
    @Override
    public Mark createMark(TrademarkApplication application) {
        return new FigurativeMark(application);
    }
}
