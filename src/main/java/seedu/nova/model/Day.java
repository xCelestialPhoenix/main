package seedu.nova.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ListIterator;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;
import seedu.nova.model.util.time.slotlist.DateTimeSlotList;

/**
 * The type Day.
 */
public class Day {

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

    /**
     * Adds event.
     *
     * @param event the event
     */
    public void addEvent(Event event) {

        ListIterator<Event> iterator = events.listIterator();
        int index = 0;

        if (events.size() == 0) {
            events.add(event);
        } else {
            //boolean hasSlot = false;
            while (iterator.hasNext()) {
                //Check to see if startTime is taken
                Event item = iterator.next();
                index++;
                if (event.getStartTime().compareTo(item.getStartTime()) >= 0) {

                    /*
                    if (iterator.hasNext() && (iterator.next().getStartTime().compareTo(event.getEndTime()) > 0)) {
                        //Slot cannot fit
                    }
                    */
                    //hasSlot = true;
                    freeSlots.excludeDuration(event.getDtd());
                    events.add(index, event);
                    System.err.println(events.get(index) + " has been added to " + date);
                    break;
                }
            }

            /*
            if (!hasSlot) {
                //No slot available
            }
            */
        }

    }

    /**
     * Delete event.
     *
     * @param event the event
     */
    boolean deleteEvent(Event event) {
        if (events.contains(event)) {
            freeSlots.includeDuration(event.getDtd());
            return events.remove(event);
        } else {
            return false;
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
        ListIterator<Event> iterator = events.listIterator();
        while (iterator.hasNext()) {
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
    public DateTimeSlotList getFreeSlot() {
        return freeSlots;
    }

}
