package io.github.howiezuo.unsplash.detail;

import android.os.Bundle;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.AppApplication;
import io.github.howiezuo.unsplash.base.BaseActivity;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.util.ActivityUtils;
import io.github.howiezuo.unsplash.util.UIUtils;

public class DetailActivity extends BaseActivity {

    public static final String EXTRA_PHOTO = "photo";

    @Inject
    DetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(UIUtils.view2Drawable(this, R.layout.view_action_back));

        if (savedInstanceState != null) {
            return;
        }

        Photo photo = getIntent().getParcelableExtra(EXTRA_PHOTO);

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.container_fragment);
        if (fragment == null) {
            fragment = DetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container_fragment);
        }

        DaggerDetailComponent.builder()
                .apiComponent(AppApplication.getInstance().getApiComponent())
                .helperComponent(AppApplication.getInstance().getHelperComponent())
                .detailPresenterModule(new DetailPresenterModule(fragment, photo))
                .loginPresenterModule(getLoginPresenterModule())
                .build()
                .inject(this);
    }
}
