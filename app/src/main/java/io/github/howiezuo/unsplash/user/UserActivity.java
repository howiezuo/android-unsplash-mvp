package io.github.howiezuo.unsplash.user;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.AppApplication;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.base.BaseActivity;
import io.github.howiezuo.unsplash.util.ActivityUtils;
import io.github.howiezuo.unsplash.util.UIUtils;


public class UserActivity extends BaseActivity {

    @Inject
    UserPresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(UIUtils.view2Drawable(this, R.layout.view_action_back));

        if (savedInstanceState != null) {
            return;
        }

        UserFragment fragment = (UserFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = UserFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
        }

        DaggerUserComponent.builder()
                .apiComponent(AppApplication.getInstance().getApiComponent())
                .helperComponent(AppApplication.getInstance().getHelperComponent())
                .userPresenterModule(new UserPresenterModule(fragment))
                .loginPresenterModule(getLoginPresenterModule())
                .build().inject(this);
    }
}
