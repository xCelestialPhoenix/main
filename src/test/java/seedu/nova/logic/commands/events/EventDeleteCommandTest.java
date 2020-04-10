package seedu.nova.logic.commands.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nova.testutil.Assert.assertThrows;
import static seedu.nova.testutil.TypicalEvents.MEETING;
import static seedu.nova.testutil.TypicalIndexes.INDEX_FIRST_EVENT;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.nova.logic.commands.CommandResult;
import seedu.nova.logic.commands.exceptions.CommandException;
import seedu.nova.logic.commands.sccommands.eventcommands.EventDeleteCommand;
import seedu.nova.model.schedule.event.Event;
import seedu.nova.model.schedule.event.EventNotFoundException;
import seedu.nova.testutil.ModelStub;

public class EventDeleteCommandTest {
    private static final LocalDate VALID_DATE = LocalDate.parse("2020-03-09");

    @Test
    public void execute_validIndexValidDate_success() throws Exception {
        ModelStubAcceptingDelete modelStub = new ModelStubAcceptingDelete();

        CommandResult commandResult = new EventDeleteCommand(VALID_DATE, INDEX_FIRST_EVENT).execute(modelStub);

        assertEquals(String.format(EventDeleteCommand.MESSAGE_SUCCESS, MEETING.toString()),
                commandResult.getFeedbackToUser());

        assertEquals(Arrays.asList(), modelStub.events);

    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ModelStubInvalidIndex modelStub = new ModelStubInvalidIndex();

        EventDeleteCommand eventDeleteCommand = new EventDeleteCommand(VALID_DATE, INDEX_FIRST_EVENT);

        assertThrows(CommandException.class, EventDeleteCommand.MESSAGE_INVALID_INDEX, () ->
                eventDeleteCommand.execute(modelStub));


    }

    @Test
    public void execute_invalidDate_throwsCommandException() {

    }

    private class ModelStubAcceptingDelete extends ModelStub {
        private List<Event> events = new LinkedList<>();

        @Override
        public String deleteEvent(LocalDate date, int index) {
            events.add(MEETING);
            Event removed = events.remove(index);

            return removed.toString();
        }
    }

    private class ModelStubInvalidIndex extends ModelStub {
        private List<Event> events = new LinkedList<>();

        @Override
        public String deleteEvent(LocalDate date, int index) {
            if (index >= events.size()) {
                throw new EventNotFoundException();
            }

            return events.remove(index).toString();
        }
    }
}
