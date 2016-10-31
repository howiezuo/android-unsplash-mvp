package io.github.howiezuo.unsplash.user;

import io.github.howiezuo.unsplash.base.BasePresenter;
import io.github.howiezuo.unsplash.base.BaseView;
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
