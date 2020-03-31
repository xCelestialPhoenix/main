package seedu.nova.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.TreeSet;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;
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

    private Day(TreeSet<Event> events, LocalDate date, DateTimeSlotList freeSlots) {
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
        if (events.contains(event)) {
            return;
        }
        boolean hasSlot = freeSlots.isSupersetOf(event.getDtd());
        if (hasSlot) {
            freeSlots.excludeDuration(event.getDtd());
            events.add(event);
            System.err.println(event + " has been added to " + date);
        } else {
            System.err.println("cannot fit " + event + " into " + date);
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
     * View string.
     *
     * @return the string
     */
    public String view() {

        StringBuilder sb = new StringBuilder();
        for (Event event : events) {
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
