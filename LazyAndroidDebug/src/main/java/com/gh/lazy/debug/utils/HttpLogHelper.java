package com.gh.lazy.debug.utils;

import com.gh.lazy.debug.model.HttpErrorInfoBean;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Request;

public class HttpLogHelper {

    private List<HttpErrorInfoBean> apiLogList;

    private HttpLogHelper() {
        apiLogList = new ArrayList<>();
    }

    public static HttpLogHelper getInstance() {
        return InstanceHelper.instance;
    }

    private static class InstanceHelper {
        private static HttpLogHelper instance = new HttpLogHelper();
    }

    public synchronized void addApiLog(HttpErrorInfoBean bean) {
        if (apiLogList != null) {
            apiLogList.add(0, bean);
        }
    }

    public List<HttpErrorInfoBean> getApiLogList() {
        return apiLogList;
    }

    public void clear() {
        if (apiLogList != null) {
            apiLogList.clear();
        }
    }
    public void parseDataAndAdd(Request request, String requestBody, long requestTime,
                                long duration, String responseBody){
        Headers headers = request.headers();
        JSONObject headerJson = new JSONObject();

//        Iterator<Pair<String, String>> iterator = headers.iterator();
//        while (iterator.hasNext()) {
//            Pair<String, String> pair = iterator.next();
//            try {
//                headerJson.put(pair.getFirst(), pair.getSecond());
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }

        HttpErrorInfoBean apiLogBean = new HttpErrorInfoBean(request.url().toString(), request.method(),
                headerJson.toString(), requestBody, responseBody, requestTime, duration);

        addApiLog(apiLogBean);
    }
}
