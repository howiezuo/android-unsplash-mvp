package io.github.howiezuo.unsplash.feature.main;

import android.os.Bundle;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.AppApplication;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.feature.base.BaseActivity;
import io.github.howiezuo.unsplash.util.ActivityUtils;
import io.github.howiezuo.unsplash.util.UIUtils;

public class MainActivity extends BaseActivity {

    @Inject
    MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(UIUtils.view2Drawable(this, R.layout.view_logo));

        if (savedInstanceState != null) {
            return;
        }

        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.container_fragment);
        if (fragment == null) {
            fragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container_fragment);
        }

        DaggerMainComponent.builder()
                .apiComponent(AppApplication.getInstance().getApiComponent())
                .databaseComponent(AppApplication.getInstance().getDatabaseComponent())
                .helperComponent(AppApplication.getInstance().getHelperComponent())
                .mainPresenterModule(new MainPresenterModule(fragment))
                .loginPresenterModule(getLoginPresenterModule())
                .build().inject(this);

//        showLogin();
    }
}
