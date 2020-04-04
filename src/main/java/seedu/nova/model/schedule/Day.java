package seedu.nova.model.schedule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import seedu.nova.model.schedule.event.Event;
import seedu.nova.model.schedule.event.EventNotFoundException;
import seedu.nova.model.schedule.event.Lesson;
import seedu.nova.model.schedule.event.TimeOverlapException;
import seedu.nova.model.schedule.event.WeakEvent;
import seedu.nova.model.util.Copyable;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;

/**
 * The type Day.
 */
public class Day implements Copyable<Day> {

    private static final String MESSAGE_SLOT_CONFLICT = "There is another event during that time";

    private Set<Event> events;
    private LocalDate date;
    private DateTimeSlotList freeSlots;

    /**
     * Instantiates a new Day.
     *
     * @param date the date
     */
    public Day(LocalDate date) {
        events = new TreeSet<>();
        this.date = date;
        freeSlots = DateTimeSlotList.ofDay(date);
    }

    private Day(Set<Event> events, LocalDate date, DateTimeSlotList freeSlots) {
        this.events = events;
        this.date = date;
        this.freeSlots = freeSlots;
    }

    /**
     * Adds event.
     *
     * @param event the event
     */
    void addEvent(Event event) {
        boolean hasSlot = freeSlots.isSupersetOf(event.getDtd());
        if (hasSlot) {
            freeSlots.excludeDuration(event.getDtd());
            events.add(event);
        } else {
            throw new TimeOverlapException();
        }
    }

    /**
     * Add lesson.
     *
     * @param lesson the lesson
     */
    public void addLesson(Lesson lesson) {
        Lesson tmp = new Lesson(lesson);
        tmp.setDate(date);
        addEvent(tmp);
    }

    /**
     * Removes an event.
     *
     * @param index index of event in the LinkedList
     */
    Event deleteEvent(int index) {
        if (index > events.size()) {
            throw new EventNotFoundException();
        }
        Event deleted = new ArrayList<>(events).remove(index - 1);
        events.remove(deleted);
        freeSlots.includeDuration(deleted.getDtd());
        if (deleted instanceof WeakEvent) {
            WeakEvent wkE = (WeakEvent) deleted;
            wkE.destroy();
        }
        return deleted;
    }

    /**
     * Adds a note to an event.
     *
     * @param index index of event in the LinkedList
     */
    public String addNote(String desc, int index) {
        if (index > events.size()) {
            throw new EventNotFoundException();
        }
        Event e = new ArrayList<>(events).get(index - 1);
        e.addNote(desc);
        return e.toString();
    }


    /**
     * View string.
     *
     * @return the string
     */
    public String view() {

        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Event event : events) {
            sb.append(++index);
            sb.append(". ");
            sb.append(event);
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Get free slots in the form of DateTimeDuration
     *
     * @return List of DateTimeDuration
     */
    public DateTimeSlotList getFreeSlotList() {
        return freeSlots;
    }

    @Override
    public Day getCopy() {
        return new Day(new TreeSet<>(events), date, freeSlots.getCopy());
    }
}
