package io.rakesh.pixabay.presenter;

import com.android.volley.RequestQueue;

import java.util.List;

public class ImageListContract {
    public interface Presenter {
        void attachView(View view);

        void detachView();

        void fetchImages(RequestQueue requestQueue, String searchKeyword);
    }

    public interface View {

        void setLoadingIndicator(boolean active);

        void showImages(List<String> previewUrls);

        void showError(int errorCode, String errorMessage);

    }
}
