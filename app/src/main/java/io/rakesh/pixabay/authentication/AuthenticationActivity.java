package io.rakesh.pixabay.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import io.rakesh.pixabay.R;
import io.rakesh.pixabay.view.SearchActivity;

public class AuthenticationActivity extends AppCompatActivity implements BiometricCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        new BiometricManager.BiometricBuilder(AuthenticationActivity.this)
                .setTitle(getString(R.string.biometric_title))
                .setSubtitle(getString(R.string.biometric_subtitle))
                .setDescription(getString(R.string.biometric_description))
                .setNegativeButtonText(getString(R.string.biometric_negative_button_text))
                .build()
                .authenticate(AuthenticationActivity.this);
    }


    @Override
    public void onBiometricAuthenticationNotSupported() {
        Toast.makeText(this,"BiometricAuthentication is not supported",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBiometricAuthenticationNotAvailable() {
        Toast.makeText(this,"BiometricAuthentication is not available",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBiometricAuthenticationPermissionNotGranted() {
        Toast.makeText(this,"Permission is required for biometric authentication",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationFailed() {

    }

    @Override
    public void onAuthenticationCancelled() {
        this.finish();
    }

    @Override
    public void onAuthenticationSuccessful() {
        Intent mainIntent = new Intent(this,SearchActivity.class);
        startActivity(mainIntent);
        this.finish();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errorString) {

    }
}
