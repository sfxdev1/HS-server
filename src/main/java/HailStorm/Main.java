package HailStorm;

import HailStorm.threading.ThreadManager;

/**
 * Created by Development on 9/29/2016.
 */
public class Main {
    public static void main(String[] args) {
        ThreadManager instance = ThreadManager.getInstance();
        instance.setMainClass();
        instance.advance();

    }
}
