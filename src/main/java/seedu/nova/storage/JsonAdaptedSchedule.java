package seedu.nova.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.nova.model.Schedule;
import seedu.nova.model.schedule.Week;

/**
 * The type Json adapted schedule.
 */
public class JsonAdaptedSchedule {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<JsonAdaptedWeek> weeks = new ArrayList<>();

    /**
     * Instantiates a new Json adapted schedule.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @param weeks     the weeks
     */
    @JsonCreator
    public JsonAdaptedSchedule(@JsonProperty("startDate") LocalDate startDate,
                               @JsonProperty("endDate") LocalDate endDate,
                               @JsonProperty("weeks") List<JsonAdaptedWeek> weeks) {

        this.startDate = startDate;
        this.endDate = endDate;
        if (weeks != null) {
            this.weeks.addAll(weeks);
        }
    }

    /**
     * Instantiates a new Json adapted schedule.
     *
     * @param schedule the schedule
     */
    public JsonAdaptedSchedule(Schedule schedule) {

        startDate = schedule.getStartDate();
        endDate = schedule.getEndDate();
        weeks.addAll(Arrays.stream(schedule.getWeeks())
                .map(JsonAdaptedWeek::new)
                .collect(Collectors.toList()));
    }

    /**
     * To model type schedule.
     *
     * @return the schedule
     */
    public Schedule toModelType() {

        Week[] weeks = new Week[17];

        for (int i = 0; i < weeks.length; i++) {
            weeks[i] = this.weeks.get(i).toModelType();
        }
        return new Schedule(startDate, endDate, weeks);
    }

}
