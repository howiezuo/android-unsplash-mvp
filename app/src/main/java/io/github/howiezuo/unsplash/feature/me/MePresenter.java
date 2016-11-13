package io.github.howiezuo.unsplash.feature.me;

import android.support.annotation.NonNull;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.api.service.UsersService;
import io.github.howiezuo.unsplash.database.dao.MeDao;
import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.github.howiezuo.unsplash.model.entity.Me;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MePresenter implements MeContract.Presenter {

    private final MeContract.View mView;

    @Inject
    UsersService mUsersService;
    @Inject
    MeDao mMeDao;

    @Inject
    public MePresenter(@NonNull MeContract.View view) {
        mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void loadMe() {
        loadMeFromDatabase();
//        mUsersService.getMe()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<UserDto>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.showError();
//                    }
//
//                    @Override
//                    public void onNext(UserDto userDto) {
//                        mView.showMe(userDto);
//                    }
//                });
    }

    @Override
    public void loadPhotos() {

    }

    private void loadMeFromDatabase() {
        mMeDao.find()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Me>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e, e.getMessage());
                        mView.showError();
                    }

                    @Override
                    public void onNext(Me me) {
                        UserDto userDto = new UserDto(me);
                        mView.showMe(userDto);
                    }
                });
    }
}
