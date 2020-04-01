package seedu.nova.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.nova.model.schedule.Day;
import seedu.nova.model.schedule.Week;

/**
 * The type Json adapted week.
 */
public class JsonAdaptedWeek {

    private final LocalDate startDate;
    private final List<JsonAdaptedDay> days = new ArrayList<>();

    /**
     * Instantiates a new Json adapted week.
     *
     * @param startDate the start date
     * @param days      the days
     */
    @JsonCreator
    public JsonAdaptedWeek(@JsonProperty("startDate") LocalDate startDate,
                           @JsonProperty("days") List<JsonAdaptedDay> days) {

        this.startDate = startDate;
        if (days != null) {
            this.days.addAll(days);
        }
    }

    /**
     * Instantiates a new Json adapted week.
     *
     * @param week the week
     */
    public JsonAdaptedWeek(Week week) {

        startDate = week.getStartDate();
        days.addAll(Arrays.stream(week.getDays())
                .map(JsonAdaptedDay::new)
                .collect(Collectors.toList()));
    }

    /**
     * To model type week.
     *
     * @return the week
     */
    public Week toModelType() {

        Day[] days = new Day[7];

        for (int i = 0; i < days.length; i++) {
            days[i] = this.days.get(i).toModelType();
        }
        return new Week(days, startDate);
    }

}
