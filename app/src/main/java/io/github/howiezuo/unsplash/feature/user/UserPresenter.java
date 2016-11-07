package io.github.howiezuo.unsplash.feature.user;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.UsersService;
import io.github.howiezuo.unsplash.model.dto.UserDto;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UserPresenter implements UserContract.Presenter {

    private final UserContract.View mView;
    private final UserDto mUserDto;

    @Inject
    UsersService mUsersService;

    @Inject
    public UserPresenter(@NonNull UserContract.View view, UserDto userDto) {
        mView = view;
        mView.setPresenter(this);
        mUserDto = userDto;
    }

    @Override
    public void loadMe() {
        mUsersService.getMe()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserDto>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                    }

                    @Override
                    public void onNext(UserDto userDto) {
                        mView.showMe(userDto);
                    }
                });
    }

    @Override
    public void showUser() {
        mView.showUser(mUserDto);
    }

}
