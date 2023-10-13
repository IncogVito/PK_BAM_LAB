package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class UserService extends Service {

    private List<Thread> currentRunningThreads = new ArrayList<>();
    private int threadIndex = 1;

    public UserService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        startCounter();


        return super.onStartCommand(intent, flags, startId);
    }

    public void startCounter() {

        Thread countingThread = new Thread(() -> {
            int counter = 0;
            String threadName = "Imie-" + threadIndex++;

            try {
                while (!Thread.interrupted()) {
                    Log.d(TAG, threadName + " | Currently counting: " + counter);
                    counter++;
                        Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Log.d(TAG, "Thread interrupted");

                sendBroadcastMessage(threadName, counter);
            }
        });

        countingThread.start();

        this.currentRunningThreads.add(countingThread);

    }

    public void stopService() {
        for (Thread runningThread: this.currentRunningThreads) {
            runningThread.interrupt();
        }
    }

    public void sendBroadcastMessage(String name, int counter) {
        Intent newIntent = new Intent(UserActivity.NUMBER_RECEIVER_ACTION);
        newIntent.putExtra(NumberReceiver.NUMBER_EXTRA, counter);
        newIntent.putExtra(NumberReceiver.USER_NAME_EXTRA, name);
        sendBroadcast(newIntent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Destroying");
        this.stopService();
        super.onDestroy();
    }
}