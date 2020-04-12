package seedu.nova.model;

import static java.util.Objects.requireNonNull;

import seedu.nova.model.progresstracker.ProgressTracker;

/**
 * Nova class
 */
public class Nova {

    private VersionedAddressBook addressBook;
    private Schedule schedule;
    private ProgressTracker progressTracker;

    public VersionedAddressBook getAddressBookNova() {
        return this.addressBook;
    }

    public Schedule getScheduleNova() {
        return this.schedule;
    }

    public ProgressTracker getProgressTracker() {
        return this.progressTracker;
    }

    public void setAddressBookNova(VersionedAddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public void setScheduleNova(Schedule schedule) {
        this.schedule = schedule;
    }

    public void setProgressTrackerNova(ProgressTracker progressTracker) {
        requireNonNull(progressTracker);
        this.progressTracker = progressTracker;
    }
}
