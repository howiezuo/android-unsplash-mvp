package io.github.howiezuo.unsplash.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.AppApplication;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.base.BaseActivity;
import io.github.howiezuo.unsplash.model.User;
import io.github.howiezuo.unsplash.util.ActivityUtils;
import io.github.howiezuo.unsplash.util.UIUtils;


public class UserActivity extends BaseActivity {

    public static final String EXTRA_USER = "user";

    @Inject
    UserPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(UIUtils.view2Drawable(this, R.layout.view_action_back));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState != null) {
            return;
        }

        User user = getIntent().getParcelableExtra(EXTRA_USER);

        UserFragment fragment = (UserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = UserFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
        }

        DaggerUserComponent.builder()
                .apiComponent(AppApplication.getInstance().getApiComponent())
                .helperComponent(AppApplication.getInstance().getHelperComponent())
                .userPresenterModule(new UserPresenterModule(fragment, user))
                .loginPresenterModule(getLoginPresenterModule())
                .build().inject(this);
    }
}
