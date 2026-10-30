// FACTORY METHOD: the creator declares createMark(), and each subclass decides
// which concrete Mark gets created. Client code only depends on MarkFactory and Mark.
// The client gets the right factory from the mark type on the application (see MarkType).
public abstract class MarkFactory {
    public abstract Mark createMark(TrademarkApplication application);
}
