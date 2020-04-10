package seedu.nova.model.progresstracker;

import static java.util.Objects.requireNonNull;

/**
 * Represents a progress tracker
 */
public class ProgressTracker {
    private Ip ip;
    private Tp tp;

    public ProgressTracker() {
        this.ip = new Ip();
        this.tp = new Tp();
    }

    public Ip getIp() {
        return ip;
    }

    public Tp getTp() {
        return tp;
    }

    /**
     * Lists tasks in specified project and week
     * @param projectName specified project
     * @param weekNum specified week
     * @return listing of the tasks in the specified week and project
     */
    public String listPtTask(String projectName, int weekNum) {
        requireNonNull(projectName);

        Project project;
        boolean isIpProject = projectName.toLowerCase().equals("ip");

        if (isIpProject) {
            project = getIp();
        } else {
            project = getTp();
        }

        PtWeekList ptWeekList = project.getWeekList();
        PtWeek ptWeek = ptWeekList.getWeek(weekNum);
        PtTaskList ptTaskList = ptWeek.getTaskList();

        return ptTaskList.listTasks();
    }

    /**
     * adds a ptTask to the specified project and week
     * @param projectName specified project
     * @param weekNum specified week
     * @param task to be added
     */
    public void addPtTask(String projectName, int weekNum, PtTask task) {
        requireNonNull(projectName);
        requireNonNull(task);

        Project project;
        boolean isIpProject = projectName.toLowerCase().equals("ip");

        if (isIpProject) {
            project = getIp();
        } else {
            project = getTp();
        }

        PtWeekList ptWeekList = project.getWeekList();
        PtWeek ptWeek = ptWeekList.getWeek(weekNum);
        PtTaskList ptTaskList = ptWeek.getTaskList();

        ptTaskList.addTask(task);
    }

    /**
     * deletes task from specified project and week
     * @param projectName specified project
     * @param weekNum specified week
     * @param taskNum specified task
     * @return true if successful, false if no task
     */
    public boolean deletePtTask(String projectName, int weekNum, int taskNum) {
        requireNonNull(projectName);

        Project project;
        boolean isIpProject = projectName.toLowerCase().equals("ip");

        if (isIpProject) {
            project = getIp();
        } else {
            project = getTp();
        }

        PtWeekList ptWeekList = project.getWeekList();
        PtWeek ptWeek = ptWeekList.getWeek(weekNum);
        PtTaskList ptTaskList = ptWeek.getTaskList();

        PtTask taskDelete = ptTaskList.getTask(taskNum);

        if (taskDelete == null) {
            return false;
        } else {
            ptTaskList.deleteTask(taskNum);
            return true;
        }
    }

    /**
     * edits task from specified project and week
     * @param projectName specified project
     * @param weekNum specified week
     * @param taskNum specified task
     * @return true if successful, false if no task
     */
    public boolean editPtTask(String projectName, int weekNum, int taskNum, String taskDesc) {
        requireNonNull(projectName);
        requireNonNull(taskDesc);

        Project project;
        boolean isIpProject = projectName.toLowerCase().equals("ip");

        if (isIpProject) {
            project = getIp();
        } else {
            project = getTp();
        }


        PtWeekList ptWeekList = project.getWeekList();
        PtWeek ptWeek = ptWeekList.getWeek(weekNum);
        PtTaskList ptTaskList = ptWeek.getTaskList();

        PtTask taskEdit = ptTaskList.getTask(taskNum);

        if (taskEdit == null) {
            return false;
        } else {
            taskEdit.setTaskDesc(taskDesc);
            return true;
        }
    }

    /**
     * sets done for specified task
     * @param projectName specified project
     * @param weekNum specified week
     * @param taskNum specified task
     * @return true if successful, false if no task
     */
    public boolean setDonePtTask(String projectName, int weekNum, int taskNum) {
        requireNonNull(projectName);

        Project project;
        boolean isIpProject = projectName.toLowerCase().equals("ip");

        if (isIpProject) {
            project = getIp();
        } else {
            project = getTp();
        }

        PtWeekList ptWeekList = project.getWeekList();
        PtWeek ptWeek = ptWeekList.getWeek(weekNum);
        PtTaskList ptTaskList = ptWeek.getTaskList();
        PtTask taskToSetDone = ptTaskList.getTask(taskNum);

        if (taskToSetDone == null) {
            return false;
        } else {
            taskToSetDone.setDone();
            return true;
        }
    }

    /**
     * adds ptNote to a specified task
     * @param projectName specified project
     * @param weekNum specified week
     * @param taskNum specified task
     * @param note ptNote to add
     * @return true if successful, false if no task
     */
    public boolean addPtNote(String projectName, int weekNum, int taskNum, String note) {
        requireNonNull(projectName);
        requireNonNull(note);

        Project project;
        boolean isIpProject = projectName.toLowerCase().equals("ip");

        if (isIpProject) {
            project = getIp();
        } else {
            project = getTp();
        }


        PtWeekList ptWeekList = project.getWeekList();
        PtWeek ptWeek = ptWeekList.getWeek(weekNum);
        PtTaskList ptTaskList = ptWeek.getTaskList();
        PtTask taskToAddNote = ptTaskList.getTask(taskNum);

        if (taskToAddNote == null) {
            return false;
        } else {
            taskToAddNote.setNote(note);
            return true;
        }
    }

    /**
     * deletes the note of the specified task
     * @param projectName specified project
     * @param weekNum specified week
     * @param taskNum specified task
     * @return true if successful, false if not successful
     */
    public boolean deletePtNote(String projectName, int weekNum, int taskNum) {
        requireNonNull(projectName);

        Project project;
        boolean isIpProject = projectName.toLowerCase().equals("ip");

        if (isIpProject) {
            project = getIp();
        } else {
            project = getTp();
        }

        PtWeekList ptWeekList = project.getWeekList();
        PtWeek ptWeek = ptWeekList.getWeek(weekNum);
        PtTaskList ptTaskList = ptWeek.getTaskList();

        PtTask taskToDeleteNote = ptTaskList.getTask(taskNum);

        if (taskToDeleteNote == null) {
            return false;
        }

        PtNote noteDelete = taskToDeleteNote.getNote();
        boolean isEmptyNote = noteDelete.toString().equals("");

        if (isEmptyNote) {
            return false;
        } else {
            taskToDeleteNote.setNote("");
            return true;
        }
    }

    /**
     * edits the note of the specified task
     * @param projectName specified project
     * @param weekNum specified week
     * @param taskNum specified task
     * @return true if successful, false if not successful
     */
    public boolean editPtNote(String projectName, int weekNum, int taskNum, String note) {
        requireNonNull(projectName);
        requireNonNull(note);

        Project project;
        boolean isIpProject = projectName.toLowerCase().equals("ip");

        if (isIpProject) {
            project = getIp();
        } else {
            project = getTp();
        }

        PtWeekList ptWeekList = project.getWeekList();
        PtWeek ptWeek = ptWeekList.getWeek(weekNum);
        PtTaskList ptTaskList = ptWeek.getTaskList();

        PtTask taskToEditNote = ptTaskList.getTask(taskNum);

        if (taskToEditNote == null) {
            return false;
        }

        PtNote noteDelete = taskToEditNote.getNote();
        boolean isEmptyNote = noteDelete.toString().equals("");

        if (isEmptyNote) {
            return false;
        } else {
            taskToEditNote.setNote(note);
            return true;
        }
    }
}
