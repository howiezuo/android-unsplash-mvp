package io.github.howiezuo.unsplash.login;

import android.content.Context;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.OAuthService;
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
    public LoginPresenter(LoginContract.View view, Context context) {
        mView = view;
        mView.setPresenter(this);
        mContext = context;
    }

    @Override
    public void getToken(String code) {
        mOAuthService.postOAuth(new Post(code))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Token>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Token token) {
                        Logger.d(token);
                        PreferencesHelper.getInstance(mContext).saveToken(token.getAccessToken());
                    }
                });

    }
}
