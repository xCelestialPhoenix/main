package seedu.nova.model.progresstracker;

/**
 * Represents team project
 */
public class Tp extends Project {
    private PtWeekList weekList;

    public Tp() {
        weekList = new PtWeekList();
    }

    public PtWeekList getWeekList() {
        return weekList;
    }

    public double getProgress() {
        return weekList.getProgressProject();
    }
}

