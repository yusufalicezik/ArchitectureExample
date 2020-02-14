package com.example.dagger.ui.auth;

import androidx.lifecycle.ViewModelProvider;
import dagger.android.support.DaggerAppCompatActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import com.bumptech.glide.RequestManager;
import com.example.dagger.R;
import com.example.dagger.viewmodels.AuthViewModel;
import com.example.dagger.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

public class AuthActivity extends DaggerAppCompatActivity {

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Inject
    ViewModelProviderFactory providerFactory;

    private AuthViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        viewModel = new ViewModelProvider(this, providerFactory).get(AuthViewModel.class);
        setLogo();
    }

    private void setLogo() {
        requestManager
                .load(logo)
                .into((ImageView) findViewById(R.id.login_logo));

    }
}
