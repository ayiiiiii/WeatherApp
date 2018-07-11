package com.mobven.weatherapp.data.network.service;

public interface ServiceCallback<T> {
    void onResponse(T response);
    void onError(String errorMessage);
}