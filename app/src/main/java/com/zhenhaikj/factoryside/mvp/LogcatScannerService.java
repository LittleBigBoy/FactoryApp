package com.zhenhaikj.factoryside.mvp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.zhenhaikj.factoryside.mvp.interfaces.LogcatObserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LogcatScannerService extends Service implements LogcatObserver {
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        new AndroidLogcatScannerThread(this).start();
    }

    @Override
    public void handleLog(String info) {
        if (info.contains("android.intent.action.DELETE")
                && info.contains(getPackageName())) {
            //do something yourself
            Toast.makeText(this, "xiezaile", Toast.LENGTH_SHORT).show();
            openAssetMusics(getApplicationContext(),"new_messsage_voice.mp3");
        }
    }
    /**
     * 打开assets下的音乐mp3文件
     */
    private void openAssetMusics(Context context, String fileName) {

        try {
            //播放 assets/a2.mp3 音乐文件
            AssetFileDescriptor fd = context.getAssets().openFd(fileName);
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(), fd.getLength());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private class AndroidLogcatScannerThread extends Thread {

        private LogcatObserver mObserver;

        public AndroidLogcatScannerThread(LogcatObserver observer) {
            mObserver = observer;
        }

        @Override
        public void run() {
            String[] cmds = { "logcat", "-c" };
            String shellCmd = "logcat";
            Process process = null;
            InputStream is = null;
            DataInputStream dis = null;
            String line = "";
            Runtime runtime = Runtime.getRuntime();
            try {
                mObserver.handleLog(line);
                int waitValue;
                waitValue = runtime.exec(cmds).waitFor();
                mObserver.handleLog("waitValue=" + waitValue
                        + "\n Has do Clear logcat cache.");
                process = runtime.exec(shellCmd);
                is = process.getInputStream();
                dis = new DataInputStream(is);
                while ((line = dis.readLine()) != null) {
                    if (mObserver != null)
                        mObserver.handleLog(line);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException ie) {
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
                }
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}

