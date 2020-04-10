package seedu.nova.testutil;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.nova.model.progresstracker.Ip;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.PtNote;
import seedu.nova.model.progresstracker.PtTask;
import seedu.nova.model.progresstracker.TaskDesc;
import seedu.nova.model.progresstracker.Tp;

/**
 *
 */
public class TypicalPtTasks {
    public static final PtTask ptTaskIp1 = new PtTask(new TaskDesc("task 1"), new Ip(),
            new PtNote("note 1"), 1, false);

    public static final PtTask ptTaskIp2 = new PtTask(new TaskDesc("task 2"), new Ip(),
            new PtNote("note 2"), 2, true);

    public static final PtTask ptTaskTp1 = new PtTask(new TaskDesc("task 1"), new Tp(),
            new PtNote("note 1"), 1, false);

    public static final PtTask ptTaskTp2 = new PtTask(new TaskDesc("task 2"), new Tp(),
            new PtNote("note 2"), 2, true);

    private TypicalPtTasks() {} // prevents instantiation

    /**
     * Returns an {@code ProgressTracker} with all the typical ptTasks.
     */
    public static ProgressTracker getTypicalProgressTracker() {
        ProgressTracker pt = new ProgressTracker();
        for (PtTask ptTask : getTypicalPtTask()) {
            pt.addPtTask(ptTask.getProject().getProjectName(), ptTask.getPtWeek(), ptTask);
        }
        return pt;
    }

    public static ArrayList<PtTask> getTypicalPtTask() {
        return new ArrayList<PtTask>(Arrays.asList(ptTaskIp1, ptTaskIp2, ptTaskTp1, ptTaskTp2));
    }
}
