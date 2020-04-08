package seedu.nova.logic.commands.events;

import static seedu.nova.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.nova.logic.commands.sccommands.eventcommands.EventAddConsultationCommand;

public class EventAddConsultationCommandTest {
    @Test
    public void constructor_nullConsultation_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EventAddConsultationCommand(null));
    }
}
