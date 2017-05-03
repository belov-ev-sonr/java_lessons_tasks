package ru.javaLessonTask.calculator.client;


import ru.javaLessonTask.calculator.client.cache.DoubleCache;

public class RunnableClearCache implements Runnable {
    @Override
    public void run() {
        DoubleCache.cacheOperation2.clear();
    }
}
