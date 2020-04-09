package seedu.nova.testutil;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import seedu.nova.model.schedule.event.Consultation;
import seedu.nova.model.schedule.event.Event;
import seedu.nova.model.schedule.event.Lesson;
import seedu.nova.model.schedule.event.Meeting;
import seedu.nova.model.schedule.event.StudySession;

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */

public class TypicalEvents {

    public static final Event MEETING = new Meeting("Project Meeting", "COM1-B108",
            LocalTime.of(14, 0), LocalTime.of(15, 0), LocalDate.of(2020, 3, 9));

    public static final Event CONSULTATION = new Consultation("Design Principles", "COM2-0203",
            LocalTime.of(16, 0), LocalTime.of(17, 0), LocalDate.of(2020, 3, 20));

    public static final Event STUDY_SESSION = new StudySession("UML Diagrams", "Home",
            LocalTime.of(11, 0), LocalTime.of(12, 0), LocalDate.of(2020, 3, 12));

    public static final Event LESSON = new Lesson("CS2103T Lecture", "I3 AUD",
            LocalTime.of(14, 0), LocalTime.of(16, 0), DayOfWeek.FRIDAY, LocalDate.of(2020, 3, 27), "Bring charger");

}
