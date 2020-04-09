package seedu.nova.commons.core;

/**
 * Container for help display messages.
 */
public class HelpMessages {

    //============== The help message for the home page ==================
    public static final String HELP_HOME = "";

    //============== The help message for the address book page ==================
    public static final String HELP_ADDRESS_BOOK = "Help: Address Book Mode \n"
            + "=================== \n"
            + "Add contact: \n"
            + "\u2022 add n\\[name] p\\[phone num] e\\[email addr] c\\[classmate/teammate] \n"
            + "List contacts: \n"
            + "\u2022 list \n"
            + "List classmate/teammate: \n"
            + "\u2022 list c\\[classmate/teammate] \n"
            + "Find contact: \n"
            + "find n\\[name] \n"
            + "To undo, type undo \n"
            + "To redo, type redo \n"
            + "=================== \n"
            + "NOTE: The following commands must be used after using list (including list category) and find command"
            + "\n\n"
            + "Edit contact: (After using list/find) \n"
            + "\u2022 edit i\\[index] n\\[name] p\\[phone num] e\\[email addr] c\\[classmate/teammate] \n"
            + "Delete contact: (After using list/find) \n"
            + "\u2022 delete i\\[index]\n"
            + "Add remark: (After using list/find) \n"
            + "\u2022 remark i\\[index] r\\[remark]"
            + "Remove remark: (After using list/find) \n"
            + "\u2022 remark i\\[index] r\\ \n";


    //============== The help message for the schedule page ==================
    public static final String HELP_SCHEDULE = "Help: Schedule Mode \n"
            + "=================== \n"
            + "View schedule on date: \n"
            + "view t\\[date] \n"
            + "View schedule on week: \n"
            + "view week i\\[week #]";

    //============== The help message for the study planner page ==================
    public static final String HELP_STUDY_PLANNER = "";

    //============== The help message for the progress tracker page ==================
    public static final String HELP_PROGRESS_TRACKER = "";

}
