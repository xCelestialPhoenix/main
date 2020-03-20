package seedu.nova.model.schedule.timeunit;

import java.time.Duration;
import java.util.List;

import seedu.nova.model.common.Copyable;
import seedu.nova.model.common.time.duration.DateTimeDuration;
import seedu.nova.model.common.time.slotlist.DateTimeSlotList;
import seedu.nova.model.event.Event;

/**
 * Time unit which contain events
 */
public interface TimeUnit extends Copyable<TimeUnit> {
    boolean addEvent(Event event);

    boolean deleteEvent(Event event);

    List<Event> getEventList();

    DateTimeDuration getDuration();

    List<DateTimeDuration> getFreeSlotList(Duration greaterThan);

    DateTimeSlotList getFreeSlotList();
}
