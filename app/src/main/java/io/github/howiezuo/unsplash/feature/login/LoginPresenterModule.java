package io.github.howiezuo.unsplash.feature.login;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module
public class LoginPresenterModule {

    private LoginContract.View mView;
    private final Context mContext;

    public LoginPresenterModule(LoginContract.View view, Context context) {
        mView = view;
        mContext = context;
    }

    public void setView(LoginContract.View view) {
        mView = view;
    }

    @Provides
    LoginContract.View provideLoginContractView() {
        return mView;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

}
