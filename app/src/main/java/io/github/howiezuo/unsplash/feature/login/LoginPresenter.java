package io.github.howiezuo.unsplash.feature.login;

import android.content.Context;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.app.AppApplication;
import io.github.howiezuo.unsplash.api.service.OAuthService;
import io.github.howiezuo.unsplash.api.service.UsersService;
import io.github.howiezuo.unsplash.database.dao.MeDao;
import io.github.howiezuo.unsplash.helper.PreferencesHelper;
import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.github.howiezuo.unsplash.model.dto.oauth.TokenDto;
import io.github.howiezuo.unsplash.model.dto.oauth.token.PostDto;
import io.github.howiezuo.unsplash.model.entity.Me;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mView;
    private final Context mContext;

    @Inject
    OAuthService mOAuthService;
    @Inject
    UsersService mUsersService;
    @Inject
    PreferencesHelper mPreferencesHelper;
    @Inject
    MeDao mMeDao;

    @Inject
    public LoginPresenter(LoginContract.View view, Context context) {
        mView = view;
        mView.setPresenter(this);
        mContext = context;
    }

    @Override
    public void getToken(String code) {
        mOAuthService.postOAuth(new PostDto(
                        AppApplication.getInstance().getClientId(),
                        AppApplication.getInstance().getClientSecret(),
                        code))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(new Func1<TokenDto, Observable<UserDto>>() {
                    @Override
                    public Observable<UserDto> call(TokenDto tokenDto) {
                        mPreferencesHelper.saveToken(tokenDto.getAccessToken());
                        return mUsersService.getMe()
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new Subscriber<UserDto>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                    }

                    @Override
                    public void onNext(UserDto userDto) {
                        saveMe(userDto);
                    }
                });
    }

    private void saveMe(UserDto dto) {
        mMeDao.createOrUpdate(dto)
                .subscribe(new Subscriber<Me>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                    }

                    @Override
                    public void onNext(Me me) {
                        Logger.d(me);
                    }
                });
    }
}
