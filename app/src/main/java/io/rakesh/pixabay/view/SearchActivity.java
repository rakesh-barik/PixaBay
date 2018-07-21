package io.rakesh.pixabay.view;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Objects;

import io.rakesh.pixabay.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText textInputEditText;
    Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        textInputEditText = findViewById(R.id.search_input);
        searchButton = findViewById(R.id.search_btn);
        searchButton.setOnClickListener(this);
        textInputEditText.requestFocus();
        Objects.requireNonNull(getWindow()).setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onClick(View view) {
        String keyWord = String.valueOf(textInputEditText.getText());
        if (!keyWord.isEmpty()) {
            MainActivity.newInstance(this,keyWord);
        }
    }
}
