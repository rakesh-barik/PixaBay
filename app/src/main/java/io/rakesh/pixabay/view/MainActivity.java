package io.rakesh.pixabay.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.toolbox.Volley;

import java.util.List;

import io.rakesh.pixabay.R;
import io.rakesh.pixabay.presenter.ImageListContract;
import io.rakesh.pixabay.presenter.ImageListPresenter;

public class MainActivity extends AppCompatActivity implements
        ImageListContract.View {
    private static final String QUERY_KEY_WORD = "QUERY_KEY_WORD";
    private ImageListPresenter presenter;
    private ImageListAdapter imageListAdapter;
    private ProgressBar progressBar;
    private String searchQuery = "";

    static void newInstance(Activity activity,String keyword){
        Intent mainIntent = new Intent(activity, MainActivity.class);
        mainIntent.putExtra(QUERY_KEY_WORD, keyword);
        activity.startActivity(mainIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchQuery = getIntent().getStringExtra(QUERY_KEY_WORD);
        progressBar = findViewById(R.id.progress_bar);
        presenter = new ImageListPresenter();
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView imageRecyclerView = findViewById(R.id.image_recycler);
        imageRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        imageListAdapter = new ImageListAdapter();
        imageRecyclerView.setAdapter(imageListAdapter);
        RecyclerView.ItemDecoration listSpacing = new ItemMarginDecoration(this,R.dimen.item_margin);
        imageRecyclerView.addItemDecoration(listSpacing);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.attachView(this);
        if (!searchQuery.isEmpty()) {
            presenter.fetchImages(Volley.newRequestQueue(this), searchQuery);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        progressBar.setVisibility(active ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void showImages(List<String> previewUrls) {
        imageListAdapter.setImageList(previewUrls);
    }

    @Override
    public void showError(int errorCode, String errorMessage) {
        Log.e(getLocalClassName(),String.valueOf(errorCode));
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
    }
}
