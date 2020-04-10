package seedu.nova.model.progresstracker;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

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
     *
     * @param projectName specified project
     * @param weekNum     specified week
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
     *
     * @param projectName specified project
     * @param weekNum     specified week
     * @param task        to be added
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
     *
     * @param projectName specified project
     * @param weekNum     specified week
     * @param taskNum     specified task
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
     *
     * @param projectName specified project
     * @param weekNum     specified week
     * @param taskNum     specified task
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
     *
     * @param projectName specified project
     * @param weekNum     specified week
     * @param taskNum     specified task
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
     *
     * @param projectName specified project
     * @param weekNum     specified week
     * @param taskNum     specified task
     * @param note        ptNote to add
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
        } else if (!taskToAddNote.getNote().toString().equals("")) {
            return false;
        } else {
            taskToAddNote.setNote(note);
            return true;
        }
    }

    /**
     * deletes the note of the specified task
     *
     * @param projectName specified project
     * @param weekNum     specified week
     * @param taskNum     specified task
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
     *
     * @param projectName specified project
     * @param weekNum     specified week
     * @param taskNum     specified task
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

        PtNote noteEdit = taskToEditNote.getNote();
        boolean isEmptyNote = noteEdit.toString().equals("");

        if (isEmptyNote) {
            return false;
        } else {
            taskToEditNote.setNote(note);
            return true;
        }
    }

    /**
     * Returns a list of ptTasks in Ip project
     */
    public ArrayList<PtTask> toIpPtTaskList() {
        ArrayList<PtTask> ipTasks = new ArrayList<>();

        PtWeekList ipWeekList = getIp().getWeekList();

        for (PtWeek week : ipWeekList.getWeekList()) {
            int weekNum = week.getWeekNum();

            for (PtTask task : ipWeekList.getWeek(weekNum).getTaskList().getList()) {
                ipTasks.add(task);
            }
        }
        return ipTasks;
    }

    /**
     * Returns a list of ptTasks in Tp project
     */
    public ArrayList<PtTask> toTpPtTaskList() {
        ArrayList<PtTask> tpTasks = new ArrayList<>();

        PtWeekList tpWeekList = getTp().getWeekList();

        for (PtWeek week : tpWeekList.getWeekList()) {
            int weekNum = week.getWeekNum();

            for (PtTask task : tpWeekList.getWeek(weekNum).getTaskList().getList()) {
                tpTasks.add(task);
            }
        }

        return tpTasks;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof ProgressTracker) {
            boolean ipTaskEqual = true;
            boolean tpTaskEqual = true;

            ArrayList<PtTask> ipTasks = toIpPtTaskList();
            ArrayList<PtTask> tpTasks = toTpPtTaskList();
            ArrayList<PtTask> otherIpTasks = ((ProgressTracker) other).toIpPtTaskList();
            ArrayList<PtTask> otherTpTasks = ((ProgressTracker) other).toTpPtTaskList();

            if (ipTasks.size() != otherIpTasks.size() || tpTasks.size() != otherTpTasks.size()) {
                ipTaskEqual = false;
                tpTaskEqual = false;
            } else {
                for (int i = 0; i < ipTasks.size(); i++) {
                    PtTask ipTaskCurrent = ipTasks.get(i);
                    PtTask otherIpTaskCurrent = otherIpTasks.get(i);

                    if (!ipTaskCurrent.equals(otherIpTaskCurrent)) {
                        ipTaskEqual = false;
                        break;
                    }
                }

                for (int i = 0; i < tpTasks.size(); i++) {
                    PtTask tpTaskCurrent = tpTasks.get(i);
                    PtTask otherTpTaskCurrent = otherIpTasks.get(i);

                    if (!tpTaskCurrent.equals(otherTpTaskCurrent)) {
                        tpTaskEqual = false;
                        break;
                    }
                }
            }
            return ipTaskEqual && tpTaskEqual;
        } else {
            return false;
        }
    }
}
