package seedu.nova.model.progresstracker;


/**
 * Represents a project task
 */
public class PtTask {
    private TaskDesc taskDesc;
    private PtNote note;
    private PtWeek ptWeek;
    private boolean isDone;

    public PtTask(TaskDesc taskDesc, int num) {
        this.taskDesc = taskDesc;
        ptWeek = new PtWeek(num);
        isDone = false;
        note = new PtNote("");
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        if (isDone) {
            isDone = false;
        } else {
            isDone = true;
        }
    }

    public TaskDesc getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = new TaskDesc(taskDesc);
    }

    public PtNote getNote() {
        return note;
    }

    public void setNote(String noteDesc) {
        this.note = new PtNote(noteDesc);
    }

    public PtWeek getPtWeek() {
        return ptWeek;
    }

    /**
     * Gets icon for task status.
     * @return icon in string
     */
    public String getStatusIcon() {
        if (isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getTaskDesc();
    }
}
