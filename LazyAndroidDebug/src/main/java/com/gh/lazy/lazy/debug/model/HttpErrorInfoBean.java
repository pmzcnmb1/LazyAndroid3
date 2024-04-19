package com.gh.lazy.lazy.debug.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.gh.lazy.lazy.debug.utils.JsonFormatUtil;


public class HttpErrorInfoBean implements Parcelable {

    public static final String[] API_TITLE_ARRAY = {"URL", "耗时", "Method", "Headers", "RequestBody", "ResponseBody"};

    private String url;
    private String method;
    private String headers;
    private String request;
    private String response;
    private long requestTime;
    private long durationTime;

    public HttpErrorInfoBean(String url, String method, String headers, String request, String response, long requestTime, long durationTime) {
        this.url = url;
        this.method = method;
        this.headers = headers;
        this.request = request;
        this.response = response;
        this.requestTime = requestTime;
        this.durationTime = durationTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(long requestTime) {
        this.requestTime = requestTime;
    }

    public long getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(long durationTime) {
        this.durationTime = durationTime;
    }

    @NonNull
    @Override
    public String toString() {
        return API_TITLE_ARRAY[0] + " : " + this.getUrl() + "\n\n"
                + API_TITLE_ARRAY[1] + " : " + this.getDurationTime() + "ms\n\n"
                + API_TITLE_ARRAY[2] + " : " + this.getMethod() + "\n\n"
                + API_TITLE_ARRAY[3] + " : " + JsonFormatUtil.format(this.getHeaders()) + "\n\n"
                + API_TITLE_ARRAY[4] + " : " + JsonFormatUtil.format(this.getRequest()) + "\n\n"
                + API_TITLE_ARRAY[5] + " : " + JsonFormatUtil.format(this.getResponse()) + "\n\n";
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.method);
        dest.writeString(this.headers);
        dest.writeString(this.request);
        dest.writeString(this.response);
        dest.writeLong(this.requestTime);
        dest.writeLong(this.durationTime);
    }

    protected HttpErrorInfoBean(Parcel in) {
        this.url = in.readString();
        this.method = in.readString();
        this.headers = in.readString();
        this.request = in.readString();
        this.response = in.readString();
        this.requestTime = in.readLong();
        this.durationTime = in.readLong();
    }

    public static final Creator<HttpErrorInfoBean> CREATOR = new Creator<HttpErrorInfoBean>() {
        @Override
        public HttpErrorInfoBean createFromParcel(Parcel source) {
            return new HttpErrorInfoBean(source);
        }

        @Override
        public HttpErrorInfoBean[] newArray(int size) {
            return new HttpErrorInfoBean[size];
        }
    };
}
