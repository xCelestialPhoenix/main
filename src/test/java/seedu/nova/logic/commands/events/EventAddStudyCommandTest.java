package seedu.nova.logic.commands.events;

import static seedu.nova.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.nova.logic.commands.sccommands.eventcommands.EventAddStudyCommand;

public class EventAddStudyCommandTest {
    @Test
    public void constructor_nullStudy_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventAddStudyCommand(null));
    }


}
