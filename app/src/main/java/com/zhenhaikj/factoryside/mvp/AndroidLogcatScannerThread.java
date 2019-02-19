package com.zhenhaikj.factoryside.mvp;

import com.zhenhaikj.factoryside.mvp.interfaces.LogcatObserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AndroidLogcatScannerThread extends Thread {
    private LogcatObserver observer;
    public AndroidLogcatScannerThread(LogcatObserver observer) {
        this.observer = observer;
    }

    public void run() {
        String[] cmds = { "logcat", "-c" };
        String shellCmd = "logcat";
        Process process = null;
        InputStream is = null;
        DataInputStream dis = null;
        String line = "";
        Runtime runtime = Runtime.getRuntime();
        try {
            observer.handleLog(line);
            int waitValue;
            waitValue = runtime.exec(cmds).waitFor();
            observer.handleLog("waitValue=" + waitValue + "\n Has do Clear logcat cache.");
            process = runtime.exec(shellCmd);
            is = process.getInputStream();
            dis = new DataInputStream(is);
            while ((line = dis.readLine()) != null) {
                //Log.d("Log","Log.Bestpay:"+line);

                if(observer!=null)
                    observer.handleLog(line);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (is != null) {
                    is.close();
                }
                if (process != null) {
                    process.destroy();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
