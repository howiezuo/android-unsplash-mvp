package io.github.howiezuo.unsplash.feature.login;

import android.content.Context;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.AppApplication;
import io.github.howiezuo.unsplash.api.service.OAuthService;
import io.github.howiezuo.unsplash.helper.PreferencesHelper;
import io.github.howiezuo.unsplash.model.oauth.Token;
import io.github.howiezuo.unsplash.model.oauth.token.Post;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mView;
    private final Context mContext;

    @Inject
    OAuthService mOAuthService;
    @Inject
    PreferencesHelper mPreferencesHelper;

    @Inject
    public LoginPresenter(LoginContract.View view, Context context) {
        mView = view;
        mView.setPresenter(this);
        mContext = context;
    }

    @Override
    public void getToken(String code) {
        mOAuthService.postOAuth(new Post(
                        AppApplication.getInstance().getClientId(),
                        AppApplication.getInstance().getClientSecret(),
                        code))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                    }

                    @Override
                    public void onNext(Token token) {
                        mPreferencesHelper.saveToken(token.getAccessToken());
                    }
                });

    }
}
