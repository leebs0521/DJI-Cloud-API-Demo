package com.dji.sdk.cloudapi.livestream;

public class liveStreamStartResponse {

    private String info;
    private Integer result;

    public liveStreamStartResponse() {
    }

    @Override
    public String toString() {
        return "liveStreamStartResponse{" +
                "info='" + info + '\'' +
                ", result=" + result +
                '}';
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
