package com.jecyhw.shared.request;

import com.jecyhw.shared.request.exception.RequestFailedException;
import com.jecyhw.shared.request.params.Get;
import com.jecyhw.shared.request.params.Post;
import okhttp3.OkHttpClient;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by jecyhw on 16-8-19.
 */
final public class Request {

    static public String post(String url, Post postParams) throws RequestFailedException{
        OkHttpClient httpClient = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).post(postParams.requestBody()).build();
        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new RequestFailedException(url, postParams.toString(), response.toString());
            }
        } catch (IOException e) {
            throw new RequestFailedException(url, postParams.toString(), e);
        }
    }

    static public String get(String url) throws RequestFailedException{
        try {
            return getRequest(url, new TResult<String>() {
                @Override
                public String response(Response response) throws IOException {
                    return response.body().string();
                }
            });
        } catch (IOException e) {
            throw new RequestFailedException(url, e);
        }
    }

    static public String get(String url, Get getParams) throws RequestFailedException{
        return get(url + "?" + getParams.params());
    }

    static public Response getResponse(String url) throws RequestFailedException {
        try {
            return getRequest(url, new TResult<Response>() {
                @Override
                public Response response(Response response) {
                    return response;
                }
            });
        } catch (IOException e) {
            throw new RequestFailedException(url, e);
        }
    }

    static public Response getResponse(String url, Get getParams) throws RequestFailedException {
        return getResponse(url + "?" + getParams.params());
    }

    static private <T> T getRequest(String url, TResult<T> callback) throws IOException {
        OkHttpClient httpClient = new OkHttpClient();
        okhttp3.Request request = new okhttp3.Request.Builder().url(url).get().build();
        Response response = httpClient.newCall(request).execute();
        if (response.isSuccessful()) {
            return callback.response(response);
        } else {
            throw new IOException(response.toString());
        }
    }

    private interface TResult<T> {
        T response(Response response) throws IOException;
    }
}
