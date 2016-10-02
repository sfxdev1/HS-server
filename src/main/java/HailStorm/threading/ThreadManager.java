package HailStorm.threading;

import sun.reflect.Reflection;

import java.util.HashMap;

/**
 * Created by Development on 9/28/2016.
 */
public class ThreadManager {
    public static ThreadManager instance;
    private static Class mainClass;
    private Phase state = Phase.NotInitialised;
    private HashMap<String, Thread> threads;
    private HashMap<String, Thread> AAThread;

    public static ThreadManager getInstance() {
        if (instance != null) {
            return instance;
        }
        instance = new ThreadManager();
        return instance;
    }

    public void advance() {
        if (Reflection.getCallerClass().equals(mainClass)) {
            state = state.next() != null ? state.next() : Phase.Initialised;
        }
    }

    public void registerThread(String id, EThread thread) {
        registerThread(id, thread, true);
    }

    public void registerThread(String id, EThread thread, boolean suspendOnPause) {
        if (state == Phase.Initializing) {
            if (suspendOnPause) {
                threads.put(id, thread);
            } else {
                AAThread.put(id, thread);
            }
        }
    }

    public void pauseAll() {
        try {
            for (Thread thread : threads.values()) {
                thread.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resumeAll() {
        for (Thread thread : threads.values()) {
            thread.notify();
        }
    }

    public void setMainClass() {
        mainClass = Reflection.getCallerClass();
    }

    public enum Phase {
        NotInitialised, Initializing, Initialised;

        public Phase next() {
            switch (this) {
                case NotInitialised:
                    return Initializing;
                case Initializing:
                    return Initialised;
                default:
                    return null;
            }
        }
    }
}
