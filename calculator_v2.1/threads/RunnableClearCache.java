package threads;


import objects.DoubleCache;

public class RunnableClearCache implements Runnable {
    @Override
    public void run() {
        DoubleCache.cacheOperation2.clear();
    }
}
