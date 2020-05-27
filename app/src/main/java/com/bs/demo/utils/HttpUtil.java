package com.bs.demo.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Description:
 */
public class HttpUtil {
    //private final static String HOST = "http://10.0.2.2:8080/";
    private final static String HOST = "http://shiyi.free.idcfengye.com/";
    private static HttpUtil httpUtil = null;
    private static final HashMap<String, String> map = new HashMap<>();
    private static OkHttpClient okHttpClient=null;
    public static HttpUtil newInstance() {
        if (httpUtil == null) {
            httpUtil = new HttpUtil();
        }
        map.clear();
        if (okHttpClient==null){
            okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .connectTimeout(30l, TimeUnit.SECONDS)
                    .build();
        }
        return httpUtil;
    }

    public interface HttpListener {
        void onSuccess(String t);

        void onError(String msg);
    }

    public HttpUtil addParam(String key, String value) {
        map.put(key, value);
        return this;
    }


    public void get(final String url, final HttpListener httpListener) {
        final Request request = new Request.Builder()
                .url(HOST + url)
                .get()
                .build();
        Call call = okHttpClient.newCall(request);
        send(call,httpListener);

    }

    private void send(final Call call, final HttpListener httpListener){
        Observable.create((ObservableOnSubscribe<String>) emitter -> call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call1, IOException e) {
                emitter.onComplete();
            }

            @Override
            public void onResponse(Call call1, Response response) {
                try {
                    emitter.onNext(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        })).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    try {
                        if (s.equals("-1")||s.isEmpty()) {
                            httpListener.onError("请求出错!");
                        } else {
                            httpListener.onSuccess(s);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

    }

    public void post(final String url, final HttpListener httpListener) {
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            formBody.add(entry.getKey(), entry.getValue());
        }
        final Request request = new Request.Builder()
                .url(HOST + url)
                .post(formBody.build())
                .build();
        Call call = okHttpClient.newCall(request);
        send(call,httpListener);

    }

}
