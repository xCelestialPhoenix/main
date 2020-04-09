package seedu.nova.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nova.testutil.Assert.assertThrows;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.nova.model.progresstracker.Ip;
import seedu.nova.model.progresstracker.ProgressTracker;
import seedu.nova.model.progresstracker.PtNote;
import seedu.nova.model.progresstracker.PtTask;
import seedu.nova.model.progresstracker.PtTaskList;
import seedu.nova.model.progresstracker.PtWeek;
import seedu.nova.model.progresstracker.PtWeekList;
import seedu.nova.model.progresstracker.TaskDesc;
import seedu.nova.model.progresstracker.Tp;

public class ProgressTrackerTest {
    private final ProgressTracker progressTracker = new ProgressTracker();

    @Test
    public void constructor() {
        constructorIp();
        constructorTp();
    }

    @Test
    public void constructorIp() {
        Ip ip = progressTracker.getIp();
        PtWeekList ipWeekList = ip.getWeekList();

        assertEquals(new PtWeekList().getNumWeeks(), ipWeekList.getNumWeeks());

        for (PtWeek week : ipWeekList.getWeekList()) {
            PtTaskList ptTaskList = week.getTaskList();

            assertEquals(Collections.emptyList(), ptTaskList.getList());
        }
    }

    @Test
    public void constructorTp() {
        Tp tp = progressTracker.getTp();
        PtWeekList tpWeekList = tp.getWeekList();

        assertEquals(new PtWeekList().getNumWeeks(), tpWeekList.getNumWeeks());

        for (PtWeek week : tpWeekList.getWeekList()) {
            PtTaskList ptTaskList = week.getTaskList();

            assertEquals(Collections.emptyList(), ptTaskList.getList());
        }
    }

    @Test
    public void addPtTask_nullProjectName_throwsNullPointerException() {
        PtTask task = new PtTask(new TaskDesc("taskDesc"), new Ip(), new PtNote(""), 2, false);
        assertThrows(NullPointerException.class, () -> progressTracker.addPtTask(null, 2, task));
    }

    @Test
    public void addPtTask_nullTask_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> progressTracker.addPtTask("ip", 2, null));
    }



}
