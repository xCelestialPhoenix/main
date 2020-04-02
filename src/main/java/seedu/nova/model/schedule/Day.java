package seedu.nova.model.schedule;

import java.time.Duration;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.EventNotFoundException;
import seedu.nova.model.event.Lesson;
import seedu.nova.model.event.TimeOverlapException;
import seedu.nova.model.util.Copyable;
import seedu.nova.model.util.time.duration.DateTimeDuration;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;

/**
 * The type Day.
 */
public class Day implements Copyable<Day> {

    private static final String MESSAGE_SLOT_CONFLICT = "There is another event during that time";

    private LinkedList<Event> events;
    private LocalDate date;
    private DateTimeSlotList freeSlots;

    /**
     * Instantiates a new Day.
     *
     * @param date the date
     */
    public Day(LocalDate date) {
        events = new LinkedList<>();
        this.date = date;
        freeSlots = DateTimeSlotList.ofDay(date);
    }

    private Day(LinkedList<Event> events, LocalDate date, DateTimeSlotList freeSlots) {
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
        if (events.size() == 0) {
            // if list is empty
            events.add(event);
        } else if (event.getStartTime().compareTo(events.get(events.size() - 1).getEndTime()) >= 0) {
            // if event to be added is after latest event in the list (i.e. add to the back)
            events.add(events.size(), event);

        } else {
            // if event to be added is supposed to be somewhere in the middle of the list
            addToMiddle(event);
        }
    }

    /**
     * adds an event to correct position somewhere in the middle of the list
     * @param toAdd the event to be added
     */
    void addToMiddle(Event toAdd) {
        ListIterator<Event> iterator = events.listIterator();
        int index = 0;

        boolean canAdd = false;

        while (iterator.hasNext()) {
            Event item = iterator.next();

            if (checkAddBefore(toAdd, item)) {
                freeSlots.excludeDuration(toAdd.getDtd());

                events.add(index, toAdd);
                canAdd = true;
                break;
            }

            index++;
        }

        if (!canAdd) {
            // throw an exception if the timing overlaps
            throw new TimeOverlapException();
        }

    }


    /**
     * determines if an event can be added to the list after a current event
     * @param toAdd event to be added
     * @param after event that is supposed to come after the event to be added
     * @return boolean determining whether the event can be added before the event in the list
     */
    public boolean checkAddBefore(Event toAdd, Event after) {
        boolean b1 = toAdd.getStartTime().compareTo(after.getStartTime()) <= 0;
        boolean b2 = toAdd.getEndTime().compareTo(after.getStartTime()) <= 0;

        return b1 && b2;
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
     * @param index index of event in the LinkedList
     */
    public String deleteEvent(int index) {
        if (index > events.size()) {
            throw new EventNotFoundException();
        }
        return events.remove(index - 1).toString();
    }

    /**
     * Adds a note to an event.
     * @param index index of event in the LinkedList
     */
    public String addNote(String desc, int index) {
        if (index > events.size()) {
            throw new EventNotFoundException();
        }
        events.get(index - 1).addNote(desc);
        return events.get(index - 1).toString();
    }


    /**
     * View string.
     *
     * @return the string
     */
    public String view() {

        StringBuilder sb = new StringBuilder();
        ListIterator<Event> iterator = events.listIterator();
        int index = 0;
        while (iterator.hasNext()) {
            sb.append(++index);
            sb.append(". ");
            sb.append(iterator.next());
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Get free slots in the form of DateTimeDuration
     *
     * @return List of DateTimeDuration
     */
    public List<DateTimeDuration> getFreeSlot(Duration greaterThan) {
        return freeSlots.getSlotList(greaterThan);
    }

    @Override
    public Day getCopy() {
        return new Day(new LinkedList<>(events), date, freeSlots.getCopy());
    }
}
