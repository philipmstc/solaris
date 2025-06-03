package net.shchoo.solaris.utils;

public class Timer {
    public Timer(float total, Runnable onStop) {
        this.total = total;
        this.onStop = onStop;
    }

    private boolean isStarted = false;
    public float remaining = 0;
    public float total;
    public Runnable onStop;
    public Timer next;

    public boolean isTicking() {
        if (remaining <= 0 && isStarted) {
            stop();
        }
        return isStarted;
    }

    public void start()
    {
        remaining = total;
        isStarted = true;
    }

    public void stop()
    {
        remaining = 0;
        isStarted = false;
        onStop.run();
        if (next != null) {
            remaining = next.total;
            total = next.total;
            onStop = next.onStop;
            next = next.next;
            start();
        }
    }
}
