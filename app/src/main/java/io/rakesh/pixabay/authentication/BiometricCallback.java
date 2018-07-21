package io.rakesh.pixabay.authentication;

public interface BiometricCallback {

    void onBiometricAuthenticationNotSupported();

    void onBiometricAuthenticationNotAvailable();

    void onBiometricAuthenticationPermissionNotGranted();

    void onAuthenticationFailed();

    void onAuthenticationCancelled();

    void onAuthenticationSuccessful();

    void onAuthenticationHelp(int helpCode, CharSequence helpString);

    void onAuthenticationError(int errorCode, CharSequence errorString);
}
