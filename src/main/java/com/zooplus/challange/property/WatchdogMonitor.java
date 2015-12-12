package com.zooplus.challange.property;

import java.io.FileNotFoundException;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

public class WatchdogMonitor {
    private static WatchdogMonitor instance;;
    private Timer timer;
    private Hashtable<String, MonitorTask> timerEntries;

    private WatchdogMonitor() {
        // Create timer, run timer thread as daemon.
        timer = new Timer(this.getClass().getSimpleName(), true);
        timerEntries = new Hashtable<String, MonitorTask>();
    }

    public static WatchdogMonitor getInstance() {
        if (instance == null) {
            synchronized (WatchdogMonitor.class) {
                if (instance == null)
                    instance = new WatchdogMonitor();
            }
        }
        return instance;
    }

    public static void destroy() {
        if (instance == null) {
            synchronized (WatchdogMonitor.class) {
                // Double check
                if (instance != null)
                    instance = null;
            }
        }
    }

    /**
     * 
     * @param monitorTask
     *            monitorTask to notify when the file changed.
     * @param period
     *            polling period in milliseconds.
     */
    public void addMonitorTask(MonitorTask monitorTask, long period) throws FileNotFoundException {
        removeMonitorTask(monitorTask);
        timerEntries.put(monitorTask.getObjectName() + monitorTask.getListener().hashCode(), monitorTask);
        timer.schedule(monitorTask, period, period);
    }

    /**
     * Remove the monitorTask from the notification list.
     * 
     * @param monitorTask
     *            the monitorTask to be removed.
     */
    public void removeMonitorTask(MonitorTask monitorTask) {
        TimerTask task = timerEntries.remove(monitorTask.getObjectName() + monitorTask.getListener().hashCode());
        if (task != null) {
            task.cancel();
        }
    }

    /**
     * Add a monitored file with a FileChangeListener.
     * 
     * @param listener
     *            listener to notify when the file changed.
     * @param fileName
     *            name of the file to monitor.
     * @param period
     *            polling period in milliseconds.
     */
    public void addFileChangeListener(ChangeListener listener, String fileName, long period)
            throws FileNotFoundException {
        removeFileChangeListener(listener, fileName);
        FileMonitorTask task = new FileMonitorTask(listener, fileName);
        timerEntries.put(fileName + listener.hashCode(), task);
        timer.schedule(task, period, period);
    }

    /**
     * Remove the listener from the notification list.
     * 
     * @param listener
     *            the listener to be removed.
     */
    public void removeFileChangeListener(ChangeListener listener, String fileName) {
        MonitorTask task = timerEntries.remove(fileName + listener.hashCode());
        if (task != null) {
            task.cancel();
        }
    }
}
