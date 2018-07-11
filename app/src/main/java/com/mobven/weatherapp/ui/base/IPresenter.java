package com.mobven.weatherapp.ui.base;

public interface IPresenter<V extends IView>
{
    void onAttach(V mvpView);
    void onDetach();
}
