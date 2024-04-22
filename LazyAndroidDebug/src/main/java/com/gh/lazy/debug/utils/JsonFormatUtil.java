package com.gh.lazy.debug.utils;

import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import com.gh.lazy.debug.model.HttpErrorInfoBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JsonFormatUtil {

    /**
     * 匹配Key值的正则表达式
     */
    private static final String PROPERTY_NAME_REGEX = "(\\\"(.*?)\\\":)";

    /**
     * JSON语法着色
     *
     * @param formatJsonStr 格式化后的JSON字符串
     * @return coloring json
     */
    public static SpannableString coloring(String formatJsonStr) {
        SpannableString spannableString = new SpannableString(formatJsonStr);

        // 给key值设置绿色
        coloringJson(spannableString, formatJsonStr, PROPERTY_NAME_REGEX, "#009933", 0, -1);

        // 给指定的标题设置玫红色
        for (String title : HttpErrorInfoBean.API_TITLE_ARRAY) {
            coloringJson(spannableString, formatJsonStr, title, "#990000", 0, 0);
        }

        return spannableString;
    }

    /**
     * 设置富文本
     *
     * @param spannableString 目标富文本
     * @param jsonStr         源文本
     * @param regex           表达式
     * @param colorStr        颜色
     * @param startOffset     匹配到开始位置的索引偏移量
     * @param endOffset       匹配到结束的索引偏移量
     */
    private static void coloringJson(SpannableString spannableString, String jsonStr,
                                     String regex, String colorStr, int startOffset, int endOffset) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(jsonStr);

        while (matcher.find()) {
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.parseColor(colorStr));
            spannableString.setSpan(colorSpan, matcher.start() + startOffset, matcher.end() + endOffset, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
    }

    /**
     * 删除空格
     *
     * @param jsonStr jsonStr
     * @return string
     */
    private static String removeSpace(String jsonStr) {
        StringBuilder sb = new StringBuilder();
        char last;
        char current = '\0';
        char inStringBegin = '\0';
        boolean inString = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            if (inString && current == inStringBegin) {
                // 判断前一个字符是否为 \
                if (last != '\\') {
                    inString = false;
                    inStringBegin = '\0';
                }
                sb.append(current);
            } else if (!inString && (current == '"' || current == '\'')) {
                inString = true;
                inStringBegin = current;
                sb.append(current);
            } else if (!inString && (current == ' ' || current == '\t' || current == '\n')) {
                current = '\0';
            } else {
                sb.append(current);
            }
        }
        return sb.toString();
    }

    /**
     * 删除空格并转义
     *
     * @param jsonStr jsonStr
     * @return string
     */
    @SuppressWarnings("unused")
    public static String removeSpaceAndEscape(String jsonStr) {
        jsonStr = removeSpace(jsonStr);
        return jsonStr.replaceAll("\"", "\\\\\"");
    }

    /**
     * 去除转义
     *
     * @param jsonStr jsonStr
     * @return string
     */
    @SuppressWarnings("unused")
    public static String removeEscape(String jsonStr) {
        return jsonStr.replaceAll("\\\\\"", "\"");
    }

    public static String format(String content) {
        return format(content, true, true);
    }

    /**
     * 对json字符串进行格式化
     *
     * @param content        要格式化的json字符串
     * @param indent         是否进行缩进(false时压缩为一行)
     * @param colonWithSpace key冒号后是否加入空格
     * @return string format
     */
    public static String format(String content, boolean indent, boolean colonWithSpace) {
        if (content == null) return null;
        StringBuilder sb = new StringBuilder();
        int count = 0;
        boolean inString = false;
        String tab = "\t";
        for (int i = 0; i < content.length(); i++) {
            char ch = content.charAt(i);
            switch (ch) {
                case '{':
                case '[':
                    sb.append(ch);
                    if (!inString) {
                        if (indent) {
                            sb.append("\n");
                            count++;
                            for (int j = 0; j < count; j++) {
                                sb.append(tab);
                            }
                        }

                    }
                    break;
                case '\uFEFF': //非法字符
                    if (inString) sb.append(ch);
                    break;
                case '}':
                case ']':
                    if (!inString) {
                        if (indent) {
                            count--;
                            sb.append("\n");
                            for (int j = 0; j < count; j++) {
                                sb.append(tab);
                            }
                        }

                        sb.append(ch);
                    } else {
                        sb.append(ch);
                    }
                    break;
                case ',':
                    sb.append(ch);
                    if (!inString) {
                        if (indent) {
                            sb.append("\n");
                            for (int j = 0; j < count; j++) {
                                sb.append(tab);
                            }
                        } else {
                            if (colonWithSpace) {
                                sb.append(' ');
                            }
                        }
                    }
                    break;
                case ':':
                    sb.append(ch);

                    if (!inString) {
                        if (colonWithSpace) {  //key名称冒号后加空格
                            sb.append(' ');
                        }
                    }
                    break;
                case ' ':
                case '\n':
                case '\t':
                    if (inString) {
                        sb.append(ch);
                    }
                    break;
                case '"':
                    if (i > 0 && content.charAt(i - 1) != '\\') {
                        inString = !inString;
                    }
                    sb.append(ch);
                    break;
                default:
                    sb.append(ch);
                    break;
            }
        }
        return sb.toString();
    }
}
