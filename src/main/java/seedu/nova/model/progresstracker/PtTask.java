package seedu.nova.model.progresstracker;

import static java.util.Objects.requireNonNull;

/**
 * Represents a project task
 */
public class PtTask {
    private Project project;
    private TaskDesc taskDesc;
    private PtNote note;
    private int ptWeek;
    private boolean isDone;

    public PtTask(TaskDesc taskDesc, Project project, PtNote note, int ptWeek, boolean isDone) {
        this.taskDesc = taskDesc;
        this.project = project;
        this.ptWeek = ptWeek;
        this.isDone = isDone;
        this.note = note;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * return string of boolean isDone
     * @return true or false
     */
    public String isDoneString() {
        if (isDone) {
            return "true";
        } else {
            return "false";
        }
    }

    public void setDone() {
        if (isDone) {
            isDone = false;
        } else {
            isDone = true;
        }
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        requireNonNull(project);
        this.project = project;
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

    public int getPtWeek() {
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
