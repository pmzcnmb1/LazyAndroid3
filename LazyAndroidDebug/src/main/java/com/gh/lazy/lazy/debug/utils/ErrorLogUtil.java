package com.gh.lazy.lazy.debug.utils;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;

import com.gh.lazy.lazy.debug.LazyDebugTool;
import com.gh.lazy.lazy.debug.model.ErrorLogModel;
import com.tencent.mmkv.MMKV;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ErrorLogUtil implements Thread.UncaughtExceptionHandler{


    private static ErrorLogUtil errorUtil;
    private Context mContext;
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private List<ErrorLogModel> errorLogList;
    private static final String TAG = "ErrorLog";

    public static ErrorLogUtil getInstance(){
        if (errorUtil == null){
            errorUtil = new ErrorLogUtil();
        }
        return errorUtil;
    }

    public void init(){
        errorLogList = new ArrayList<>();
        //获取系统默认的UncaughtException
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //将自己的Crash放进去
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable throwable) {
        //获取崩溃时的UNIX时间戳
        long timeMillis = System.currentTimeMillis();
        //时间戳转换
        String time = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(timeMillis));
        StringBuilder stringBuilder = new StringBuilder(time);
        stringBuilder.append(":\n");
        //获取错误信息
        stringBuilder.append(throwable.getMessage());
        String title = throwable.getMessage();
        stringBuilder.append("\n");
        //获取堆栈信息
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        stringBuilder.append(sw);
        //拿到的错误信息
        String errorLog = stringBuilder.toString();
        //保存日志信息
        ErrorLogModel logModel = new ErrorLogModel(title, errorLog, time);
        saveErrorLog(logModel);


        //如何处理该崩溃，下面使用默认的处理方式让APP停止运行
        mDefaultHandler.uncaughtException(thread, throwable);
    }


    public void saveErrorLog(ErrorLogModel logModel) {
        String json = MMKV.defaultMMKV().getString(TAG, "");
        if (TextUtils.isEmpty(json)){
            List<ErrorLogModel> logList = new ArrayList<>();
            logList.add(logModel);
            MMKV.defaultMMKV().putString(TAG, JsonUtils.toJson(logList));
        }else {
            List<ErrorLogModel> logList = JsonUtils.toList(json, ErrorLogModel.class);
            if (logList != null){
                logList.add(0, logModel);
            }
            MMKV.defaultMMKV().putString(TAG, JsonUtils.toJson(logList));
        }
    }

    public List<ErrorLogModel> getErrorLogList(){
        String json = MMKV.defaultMMKV().getString(TAG, "");
        errorLogList = JsonUtils.toList(json, ErrorLogModel.class);
        return errorLogList;
    }

    public ErrorLogModel getErrorLogModel(int index){
        String json = MMKV.defaultMMKV().getString(TAG, "");
        errorLogList = JsonUtils.toList(json, ErrorLogModel.class);
        return errorLogList != null ? errorLogList.get(index) : null;
    }

    public String getErrorLogJson(){
        return MMKV.defaultMMKV().getString(TAG, "");
    }

    public void clearLog(){
        MMKV.defaultMMKV().removeValueForKey(TAG);
    }

}
