import java.io.File;
import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Gallery {
    public static int iter = 0;
    private final Lock locker = new ReentrantLock();
    private HashMap<File, String> filesMap;


    public Gallery(HashMap filesMap) {
        this.filesMap = filesMap;
    }

    synchronized File lockPicture() throws InterruptedException {
        return hasState("raw");
    }

    synchronized void unlockPicture(File key) throws InterruptedException {
        filesMap.put(key, "resized");
        notifyAll();
    }

    synchronized File hasState(String state) {
        synchronized (filesMap) {
            for (File f : filesMap.keySet()) {
                if (filesMap.get(f).equals(state)) {
                    filesMap.put(f, "processing");
                    return f;
                }
            }
            return null;
        }
    }


}
