package io.rakesh.pixabay.presenter;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

import io.rakesh.pixabay.data.PixaBayDataManager;
import io.rakesh.pixabay.model.Hit;
import io.rakesh.pixabay.model.PixaBay;

public class ImageListPresenter implements ImageListContract.Presenter, PixaBayDataFetchedCallback {
    private ImageListContract.View mView;

    @Override
    public void attachView(ImageListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

    @Override
    public void fetchImages(RequestQueue requestQueue, String searchKeyword) {
        if (mView != null) {
            mView.setLoadingIndicator(true);
            PixaBayDataManager.getInstance(requestQueue).fetchDataFromCloud(searchKeyword, this);
        }
    }

    @Override
    public void onSuccess(PixaBay pixaBay) {
        if (mView != null) {
            mView.setLoadingIndicator(false);
            mView.showImages(getImageUrlList(pixaBay));
        }
    }

    /*
     * prepares the url list for the view
     * */
    private List<String> getImageUrlList(PixaBay pixaBay) {
        List<String> imageUrls = new ArrayList<>();
        List<Hit> hits = pixaBay.getHits();
        for (Hit hit : hits) {
            imageUrls.add(hit.getPreviewURL());
        }

        return imageUrls;
    }

    @Override
    public void onFailure(int errorCode, String message) {
        if (mView != null) {
            mView.setLoadingIndicator(false);
            mView.showError(errorCode, message);
        }
    }
}
