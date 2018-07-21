package io.rakesh.pixabay.presenter;

import io.rakesh.pixabay.model.PixaBay;

public interface PixaBayDataFetchedCallback {
    void onSuccess(PixaBay pixaBay);
    void onFailure(int errorCode, String message);
}
