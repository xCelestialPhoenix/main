package seedu.nova.model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.ListIterator;

import seedu.nova.model.event.Event;
import seedu.nova.model.event.Lesson;

/**
 * The type Day.
 */
public class Day {

    private static final String MESSAGE_SLOT_CONFLICT = "There is another event during that time";

    private LinkedList<Event> events;
    private LocalDate date;

    /**
     * Instantiates a new Day.
     *
     * @param date the date
     */
    public Day(LocalDate date) {

        events = new LinkedList<>();
        this.date = date;
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
        } else if (event.getStartTime().compareTo(events.get(events.size() - 1).getEndTime()) >= 0) {
            events.add(events.size(), event);

        } else {
            boolean canAdd = false;

            while (iterator.hasNext()) {
                Event item = iterator.next();

                if (checkAddBefore(event, item)) {
                    events.add(index, event);
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

    }


    /**
     * determines if an event can be added to the list after a current event
     * @param toAdd
     * @param after
     * @return
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
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append("\n");
        }
        return sb.toString();
    }

}
