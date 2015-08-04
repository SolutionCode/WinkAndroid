package com.solutioncode.winkandroid;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {


    public static final String TAG = "LoginFragment";
    private CallbackManager callbackManager;

    public LoginFragment() {
        // Required empty public constructor
    }

    @InjectView(R.id.login_btn)
    Button loginBtn;
    @InjectView(R.id.terms_of_use_txt)
    TextView termsOfUseTxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View contentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.inject(this, contentView);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, facebookCallback);

        loginBtn.setOnClickListener(onClickListener);
        termsOfUseTxt.setOnClickListener(onClickListener);

        if (AccessToken.getCurrentAccessToken() != null && !AccessToken.getCurrentAccessToken()
                .isExpired())
            goToMain();

        return contentView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void goToMain() {
        startActivity(new Intent(getActivity(), HomeActivity.class).addFlags(Intent
                .FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        getActivity().finish();
    }

    private FacebookCallback<LoginResult> facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            // App code
            Log.e(TAG, "onSuccess " + loginResult.getAccessToken().getToken());
            goToMain();
        }

        @Override
        public void onCancel() {
            // App code
            Log.e(TAG, "onCancel");
        }

        @Override
        public void onError(FacebookException exception) {
            // App code
            Log.e(TAG, "onError");
        }
    };

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.login_btn:
                    LoginManager.getInstance().logInWithReadPermissions(LoginFragment.this,
                            Arrays.asList("public_profile", "user_friends"));

                    break;

                case R.id.terms_of_use_txt:

                    break;
            }
        }
    };

}
