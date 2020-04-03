package seedu.nova.model.progresstracker;

/**
 *
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
}
