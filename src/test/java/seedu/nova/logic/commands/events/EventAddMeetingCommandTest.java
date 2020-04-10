package seedu.nova.logic.commands.events;

import static seedu.nova.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.nova.logic.commands.sccommands.eventcommands.EventAddMeetingCommand;

public class EventAddMeetingCommandTest {

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventAddMeetingCommand(null));
    }


}
