package seedu.nova.model.progresstracker;

/**
 * Represents a note
 */
public class PtNote {
    public static final String MESSAGE_CONSTRAINTS = "Note should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    private String note;

    public PtNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidNote(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return note;
    }
}
