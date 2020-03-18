package seedu.NOVA.model;

import seedu.NOVA.logic.parser.ModeEnum;

/**
 * Class for mode
 */
public class Mode {
    private ModeEnum mode;

    public Mode(ModeEnum mode) {
        this.mode = mode;
    }

    public void setModeEnum (ModeEnum mode) {
        this.mode = mode;
    }

    public ModeEnum getModeEnum() {
        return mode;
    }
}
