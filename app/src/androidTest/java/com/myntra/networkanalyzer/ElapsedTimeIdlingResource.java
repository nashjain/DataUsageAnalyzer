package com.myntra.networkanalyzer;

import android.support.test.espresso.IdlingResource;

class ElapsedTimeIdlingResource implements IdlingResource{

    private final long startTime;
    private final long waitingTime;
    private IdlingResource.ResourceCallback resourceCallback;

    public ElapsedTimeIdlingResource(long waitingTime) {
        this.startTime = System.currentTimeMillis();
        this.waitingTime = waitingTime;
    }

    @Override
    public String getName() {
        return ElapsedTimeIdlingResource.class.getName() + ":" + waitingTime;
    }

    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - startTime;
        boolean idle = (elapsed >= waitingTime);
        if (idle) {
            resourceCallback.onTransitionToIdle();
        }
        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(IdlingResource.ResourceCallback resourceCallback) {
        this.resourceCallback = resourceCallback;
    }
}
