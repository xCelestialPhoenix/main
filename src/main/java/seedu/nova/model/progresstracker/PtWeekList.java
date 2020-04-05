package seedu.nova.model.progresstracker;

import java.util.ArrayList;

/**
 * Handles week objects
 */
public class PtWeekList {
    private ArrayList<PtWeek> weekList;

    public PtWeekList() {
        weekList = new ArrayList<>();
    }

    /**
     * Adds week into list
     * @param week
     */
    public void addWeek(PtWeek week) {
        int num = week.getWeekNum();

        weekList.add(num - 1, week);
    }

    public PtWeek getWeek(int num) {
        if (weekList.size() >= num) {
            return weekList.get(num - 1);
        } else {
            return null;
        }
    }

    public void removeWeek(int num) {
        weekList.remove(num - 1);
    }

    public double getProgressProject() {
        boolean weekExist;
        int numWeeks = 0;
        double totalPercentage = 0;

        for (int i = 0; i < weekList.size(); i++) {
            PtWeek current = weekList.get(i);

            weekExist = current != null;

            if (weekExist) {
                numWeeks++;
                totalPercentage = totalPercentage + current.getProgressWeek();
            }
        }

        if (numWeeks == 0) {
            return 0;
        } else {
            return totalPercentage / numWeeks;
        }
    }
}
