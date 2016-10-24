package io.github.howiezuo.unsplash.main;

import android.os.Bundle;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.AppApplication;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.base.BaseActivity;
import io.github.howiezuo.unsplash.util.ActivityUtils;

public class MainActivity extends BaseActivity {

    @Inject
    MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            return;
        }

        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
        }

        DaggerMainComponent.builder()
                .apiComponent(AppApplication.getInstance().getApiComponent())
                .mainPresenterModule(new MainPresenterModule(fragment))
                .build()
                .inject(this);

//        showLogin();
    }
}
