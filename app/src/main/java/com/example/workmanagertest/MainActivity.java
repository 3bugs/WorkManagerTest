package com.example.workmanagertest;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a Constraints object that defines when the task should run
        Constraints myConstraints = new Constraints.Builder()
                .setRequiresCharging(true)
                // Many other constraints are available, see the
                // Constraints.Builder reference
                .build();

        // ...then create a OneTimeWorkRequest that uses those constraints
        OneTimeWorkRequest myOneTimeWork =
                new OneTimeWorkRequest.Builder(MyWorker.class)
                        .setConstraints(myConstraints)
                        .build();

        //WorkManager.getInstance().enqueue(myOneTimeWork);

        /*UUID myWorkId = myOneTimeWork.getId();
        WorkManager.getInstance().cancelWorkById(myWorkId);*/

        /*WorkManager.getInstance().getWorkInfoByIdLiveData(myOneTimeWork.getId())
                .observe(lifecycleOwner, workInfo -> {
                    // Do something with the status
                    if (workInfo != null && workInfo.getState().isFinished()) {
                        // ...
                    }
                });

        WorkManager.getInstance().getWorkInfoByIdLiveData(myOneTimeWork.getId())
                .observe(lifecycleOwner, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(@Nullable WorkInfo workInfo) {

                    }
                });*/

        PeriodicWorkRequest myPeriodicWork =
                new PeriodicWorkRequest.Builder(MyWorker.class, 30, TimeUnit.SECONDS, 15, TimeUnit.SECONDS)
                        .build();
        // ...if you want, you can apply constraints to the builder here...

        // Then enqueue the recurring task:
        WorkManager.getInstance().enqueue(myPeriodicWork);
    }
}
