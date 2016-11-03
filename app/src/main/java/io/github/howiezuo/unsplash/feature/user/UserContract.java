package io.github.howiezuo.unsplash.feature.user;

import io.github.howiezuo.unsplash.feature.base.BasePresenter;
import io.github.howiezuo.unsplash.feature.base.BaseView;
import io.github.howiezuo.unsplash.model.User;

public interface UserContract {

    interface Presenter extends BasePresenter {

        void loadMe();

        void showUser();

    }

    interface View extends BaseView<Presenter> {

        void showMe(User user);

        void showUser(User user);

    }
}
