package io.github.howiezuo.unsplash.feature.me;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.AppApplication;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.feature.base.BaseActivity;
import io.github.howiezuo.unsplash.util.ActivityUtils;
import io.github.howiezuo.unsplash.util.UIUtils;


public class MeActivity extends BaseActivity {

    @Inject
    MePresenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(UIUtils.view2Drawable(this, R.layout.view_action_back));
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState != null) {
            return;
        }

        MeFragment fragment = (MeFragment) getSupportFragmentManager().findFragmentById(R.id.container_fragment);
        if (fragment == null) {
            fragment = MeFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container_fragment);
        }

        DaggerMeComponent.builder()
                .apiComponent(AppApplication.getInstance().getApiComponent())
                .helperComponent(AppApplication.getInstance().getHelperComponent())
                .mePresenterModule(new MePresenterModule(fragment))
                .loginPresenterModule(getLoginPresenterModule())
                .build().inject(this);
    }

}
