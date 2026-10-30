// The type of mark the applicant ticks on the form. Each type knows which
// factory creates that kind of Mark, so the client never has to guess.
public enum MarkType {
    WORD(new WordMarkFactory()),
    FIGURATIVE(new FigurativeMarkFactory()),
    SOUND(new SoundMarkFactory());

    private final MarkFactory factory;

    MarkType(MarkFactory factory) {
        this.factory = factory;
    }

    public MarkFactory getFactory() {
        return factory;
    }
}
