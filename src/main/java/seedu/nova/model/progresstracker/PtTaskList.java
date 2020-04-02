package seedu.nova.model.progresstracker;

import java.util.ArrayList;

/**
 * Handles list related methods for tasks
 */
public class PtTaskList {
    private final String listHeader = "Tasks for the week: \n";

    private ArrayList<PtTask> list;

    public PtTaskList() {
        list = new ArrayList<>();
    }

    public int getNumTask() {
        return list.size();
    }

    public PtTask getTask(int taskNum) {
        return list.get(taskNum - 1);
    }

    /**
     * Lists the tasks in the task list
     * @return String listing out the tasks
     */
    public String listTasks() {
        String str = "";

        for (int i = 0; i < list.size(); i++) {
            PtTask task = list.get(i);

            str = str + (i + 1) + ") " + task.toString() + "\n";
        }
        return str;
    }

    public void addTask(PtTask task) {
        list.add(task);
    }

    public void deleteTask(int taskNum) {
        list.remove(taskNum - 1);
    }

    public double getProgressTasks() {
        int numDone = 0;
        int totalTask = getNumTask();

        for (int i = 0; i < list.size(); i++) {
            PtTask task = list.get(i);

            if (task.isDone()) {
                numDone++;
            }
        }

        if (totalTask == 0) {
            return 0;
        } else {
            return numDone / totalTask;
        }
    }
}
