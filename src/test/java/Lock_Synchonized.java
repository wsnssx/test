import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author zhangmn
 * synchonized 6272ms
 * lock 4750ms faster
 */
public class Lock_Synchonized {

    private static final int M = 100;
    private static final int N = 100000;
    private static final int L = 99;

    private final List<Obj> list = new ArrayList<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private class Obj {

        private int[] arr = new int[10];
    }

    public static void main(String[] args) {
        Lock_Synchonized test = new Lock_Synchonized();
        int n = 10;
        long start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            test.testSynchonized();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);

        test.list.clear();

        start = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            test.testLock();
        }
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private void testSynchonized() {
        List<Thread> l = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < N; j++) {
                    synchronized (list) {
                        list.add(new Obj());
                        if (list.size() >= L) {
                            list.clear();
                        }
                    }
                }
            });
            t.start();
            l.add(t);
        }
        for (Thread t : l) {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void testLock() {
        List<Thread> l = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < N; j++) {
                    try {
                        lock.writeLock().lock();
                        list.add(new Obj());
                        if (list.size() >= L) {
                            list.clear();
                        }
                    } finally {
                        lock.writeLock().unlock();
                    }
                }
            });
            t.start();
            l.add(t);
        }
        for (Thread t : l) {
            try {
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
