import java.time.LocalDate;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // 1. Singleton
        TrademarkRegistry registry = TrademarkRegistry.getInstance();
        TrademarkRegistry sameRegistry = TrademarkRegistry.getInstance();
        System.out.println("=== SINGLETON ===");
        System.out.println("Both references point to the same registry: " + (registry == sameRegistry));
        System.out.println();

        // 2. Builder
        // Required fields (applicant, mark name, mark type, Nice classes) go in the constructor;
        // optional ones are chained.
        TrademarkApplication nucaria = new TrademarkApplication.Builder("Nucaria SRL", "Nucaria", MarkType.WORD, 30, 43)
                .description("Walnut sweets and a cafe chain")
                .build();

        TrademarkApplication vinariaLuna = new TrademarkApplication.Builder("Vinaria Luna SA", "Vinaria Luna", MarkType.FIGURATIVE, 33)
                .color("burgundy")
                .color("gold")
                .logoFile("luna_logo.png")
                .priorityDate(LocalDate.of(2026, 3, 15))
                .build();

        TrademarkApplication trolleyChime = new TrademarkApplication.Builder("Chisinau Transport", "Trolley Chime", MarkType.SOUND, 39)
                .soundFile("trolley_chime.mp3")
                .build();

        TrademarkApplication genericCoffee = new TrademarkApplication.Builder("Some Cafe SRL", "Coffee", MarkType.WORD, 30)
                .build();

        TrademarkApplication copycat = new TrademarkApplication.Builder("Copycat SRL", "NUCARIA", MarkType.WORD, 30)
                .build();

        TrademarkApplication logoMissing = new TrademarkApplication.Builder("Forgetful SRL", "Blue Owl", MarkType.FIGURATIVE, 25)
                .color("blue")
                .build();

        System.out.println("=== BUILDER ===");
        System.out.println("Built 6 applications with different optional fields.");
        try {
            new TrademarkApplication.Builder("Bad Data SRL", "Nowhere", MarkType.WORD).build();
        } catch (IllegalStateException e) {
            System.out.println("Invalid application rejected by build(): " + e.getMessage());
        }
        try {
            new TrademarkApplication.Builder("Bad Data SRL", "Nowhere", MarkType.WORD, 50).build();
        } catch (IllegalStateException e) {
            System.out.println("Invalid application rejected by build(): " + e.getMessage());
        }
        System.out.println();

        // 3. Factory Method
        // Main never writes "new WordMark(...)": the mark type on the form gives the factory,
        // and the factory decides the concrete Mark class.
        System.out.println("=== FACTORY METHOD + REGISTRATION ===");
        file(nucaria);
        file(vinariaLuna);
        file(trolleyChime);
        file(genericCoffee);
        file(copycat);
        file(logoMissing);
        System.out.println();

        System.out.println("=== NATIONAL TRADEMARK REGISTER ===");
        for (Map.Entry<String, Mark> entry : TrademarkRegistry.getInstance().getRegisteredMarks().entrySet()) {
            Mark mark = entry.getValue();
            System.out.printf("%s | %-15s | %-13s | owner: %s%n", entry.getKey(), mark.getType(),
                    mark.getApplication().getMarkName(), mark.getApplication().getApplicant());
        }
    }

    private static void file(TrademarkApplication application) {
        MarkFactory factory = application.getMarkType().getFactory();
        Mark mark = factory.createMark(application);
        System.out.printf("%-15s \"%s\" (fee %d MDL) -> ", mark.getType(), application.getMarkName(), mark.getFee());
        try {
            String number = TrademarkRegistry.getInstance().register(mark);
            System.out.println("Registered as " + number);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
