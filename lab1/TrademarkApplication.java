import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// BUILDER: an application has 4 required fields and many optional ones,
// so instead of a constructor with 9 parameters we build it step by step.
public class TrademarkApplication {
    private final String applicant;
    private final String markName;
    private final MarkType markType;
    private final List<Integer> niceClasses;
    private final List<String> colors;
    private final LocalDate priorityDate;
    private final String description;
    private final String logoFile;
    private final String soundFile;

    // Private: the only way to create an application is through the Builder
    private TrademarkApplication(Builder builder) {
        this.applicant = builder.applicant;
        this.markName = builder.markName;
        this.markType = builder.markType;
        this.niceClasses = Collections.unmodifiableList(builder.niceClasses);
        this.colors = Collections.unmodifiableList(builder.colors);
        this.priorityDate = builder.priorityDate;
        this.description = builder.description;
        this.logoFile = builder.logoFile;
        this.soundFile = builder.soundFile;
    }

    public String getApplicant() {
        return applicant;
    }

    public String getMarkName() {
        return markName;
    }

    public MarkType getMarkType() {
        return markType;
    }

    public List<Integer> getNiceClasses() {
        return niceClasses;
    }

    public List<String> getColors() {
        return colors;
    }

    public LocalDate getPriorityDate() {
        return priorityDate;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoFile() {
        return logoFile;
    }

    public String getSoundFile() {
        return soundFile;
    }

    public static class Builder {
        private final String applicant;
        private final String markName;
        private final MarkType markType;
        private final List<Integer> niceClasses = new ArrayList<>();
        private final List<String> colors = new ArrayList<>();
        private LocalDate priorityDate;
        private String description;
        private String logoFile;
        private String soundFile;

        // Required fields go in the Builder's constructor: who applies, the mark name,
        // the type of mark ticked on the form, and the Nice classes
        // (the categories of goods/services the mark will protect)
        public Builder(String applicant, String markName, MarkType markType, int... niceClasses) {
            this.applicant = applicant;
            this.markName = markName;
            this.markType = markType;
            for (int niceClass : niceClasses) {
                this.niceClasses.add(niceClass);
            }
        }

        // Optional fields: each setter returns the Builder so calls can be chained
        public Builder color(String color) {
            colors.add(color);
            return this;
        }

        public Builder priorityDate(LocalDate priorityDate) {
            this.priorityDate = priorityDate;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder logoFile(String logoFile) {
            this.logoFile = logoFile;
            return this;
        }

        public Builder soundFile(String soundFile) {
            this.soundFile = soundFile;
            return this;
        }

        // Validation happens once, here, so an invalid application can never exist
        public TrademarkApplication build() {
            if (applicant == null || applicant.isBlank()) {
                throw new IllegalStateException("Applicant is required");
            }
            if (markName == null || markName.isBlank()) {
                throw new IllegalStateException("Mark name is required");
            }
            if (markType == null) {
                throw new IllegalStateException("Mark type is required");
            }
            if (niceClasses.isEmpty()) {
                throw new IllegalStateException("At least one Nice class is required");
            }
            for (int niceClass : niceClasses) {
                if (niceClass < 1 || niceClass > 45) {
                    throw new IllegalStateException("Nice class must be between 1 and 45, got " + niceClass);
                }
            }
            return new TrademarkApplication(this);
        }
    }
}
