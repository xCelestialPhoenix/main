package seedu.nova.model.progresstracker;

import seedu.nova.model.note.NotesList;

/**
 * Represents a project task
 */
public class PtTask {
    private TaskDesc taskDesc;
    private NotesList notesList;
    private PtWeek ptWeek;
    private boolean isDone;

    public PtTask(TaskDesc taskDesc, int num) {
        this.taskDesc = taskDesc;
        ptWeek = new PtWeek(num);
        notesList = new NotesList();
        isDone = false;
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

    public NotesList getNotesList() {
        return notesList;
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
