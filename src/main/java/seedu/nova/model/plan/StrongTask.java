package seedu.nova.model.plan;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.WeakEvent;
import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;

/**
 * Task which generates definite events
 */
public class StrongTask extends Task {

    private StrongTask(TaskDetails details) {
        super(details);
    }

    public static StrongTask get(String name, Duration duration, TaskFreq freq) {
        return new StrongTask(new TaskDetails(name, duration, freq));
    }

    @Override
    public Event generateEventOnDay(LocalDate date, DateTimeSlotList dtsl) throws ImpossibleTaskException {
        List<DateTimeDuration> possibleSlot = dtsl.getSlotList(getBaseDuration());
        if (possibleSlot.isEmpty()) {
            throw new ImpossibleTaskException();
        } else if (hasEventOn(date)) {
            return getEventOn(date);
        } else {
            DateTimeDuration dtd = getBestTimeframe(possibleSlot);
            Event newEvent = new WeakEvent(getName(), dtd, this);
            addEvent(newEvent);
            return newEvent;
        }
    }

    private DateTimeDuration getBestTimeframe(List<DateTimeDuration> freeSlots) {
        assert !freeSlots.isEmpty() : "no free slot to choose";
        List<DateTimeDuration> lst = new ArrayList<>(freeSlots);
        Collections.shuffle(lst);
        DateTimeDuration dtd = lst.get(0);
        return new DateTimeDuration(dtd.getStartDateTime(), dtd.getStartDateTime().plus(getBaseDuration()));
    }
}
