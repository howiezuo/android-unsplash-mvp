package io.github.howiezuo.unsplash.login;

import io.github.howiezuo.unsplash.base.BasePresenter;
import io.github.howiezuo.unsplash.base.BaseView;

public interface LoginContract {

    interface Presenter extends BasePresenter {

        void getToken(String code);

    }

    interface View extends BaseView<Presenter> {

    }
}
