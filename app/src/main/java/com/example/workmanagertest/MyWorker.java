package com.example.workmanagertest;

// https://developer.android.com/topic/libraries/architecture/workmanager/basics
// https://codelabs.developers.google.com/codelabs/android-workmanager/#0

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class MyWorker extends Worker {

    private static final String TAG = MyWorker.class.getName();

    public MyWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Do the work here--in this case, compress the stored images.
        // In this example no parameters are passed; the task is
        // assumed to be "compress the whole library."
        doSomething();

        // Indicate success or failure with your return value:
        return Result.SUCCESS;

        // (Returning RETRY tells WorkManager to try this task again
        // later; FAILURE says not to try again.)
    }

    private void doSomething() {
        Log.i(TAG, "Doing work...");
    }
}
