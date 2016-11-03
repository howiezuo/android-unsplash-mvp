package io.github.howiezuo.unsplash.feature.login;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;

public interface LoginContract {

    interface Presenter extends BasePresenter {

        void getToken(String code);

    }

    interface View extends BaseView<Presenter> {

    }
}
