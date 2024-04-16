package com.gh.lazy.lazy.debug.utils;

import com.gh.lazy.lazy.debug.model.ApiLogBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import kotlin.Pair;
import okhttp3.Headers;
import okhttp3.Request;

public class ApiLogHelper {

    private List<ApiLogBean> apiLogList;

    private ApiLogHelper() {
        apiLogList = new ArrayList<>();
    }

    public static ApiLogHelper getInstance() {
        return InstanceHelper.instance;
    }

    private static class InstanceHelper {
        private static ApiLogHelper instance = new ApiLogHelper();
    }

    public synchronized void addApiLog(ApiLogBean bean) {
        if (apiLogList != null) {
            apiLogList.add(0, bean);
        }
    }

    public List<ApiLogBean> getApiLogList() {
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

        ApiLogBean apiLogBean = new ApiLogBean(request.url().toString(), request.method(),
                headerJson.toString(), requestBody, responseBody, requestTime, duration);

        addApiLog(apiLogBean);
    }
}
