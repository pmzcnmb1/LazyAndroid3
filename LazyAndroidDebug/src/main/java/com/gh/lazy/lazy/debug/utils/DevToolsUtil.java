package com.gh.lazy.lazy.debug.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;


public class DevToolsUtil {

    /**
     * 复制数据到剪切板
     *
     * @param context 上下文
     * @param text    要复制的目标文本
     */
    public static void copyToClipboard(Context context, String text) {
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager != null) {
            ClipData clipData = ClipData.newPlainText("dev_toolsBoard", text);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "复制成功", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取剪切板的数据
     *
     * @param context 上下文
     */
    public static String getInClipboard(Context context) {
        try {
            ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            if (clipboardManager.hasPrimaryClip() && clipboardManager.getPrimaryClip() != null) {
                return clipboardManager.getPrimaryClip().getItemAt(0).getText().toString();
            }
            return "";
        } catch (Exception exp) {
            return "";
        }
    }

    /**
     * 清除剪切板的数据
     *
     * @param context 上下文
     */
    public static void clearInClipboard(Context context) {
        if (context == null) {
            return;
        }
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager == null) {
            return;
        }
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                clipboardManager.clearPrimaryClip();
            } else {
                clipboardManager.setPrimaryClip(ClipData.newPlainText(null, null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 启动系统分享
     *
     * @param context context
     * @param text    分享的文本
     */
    public static void doSystemShare(Context context, String text) {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(textIntent, "api数据"));
    }
}
