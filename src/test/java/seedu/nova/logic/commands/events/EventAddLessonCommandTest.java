package seedu.nova.logic.commands.events;

import static seedu.nova.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.nova.logic.commands.sccommands.eventcommands.EventAddLessonCommand;

public class EventAddLessonCommandTest {
    @Test
    public void constructor_nullLesson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventAddLessonCommand(null));
    }


}
