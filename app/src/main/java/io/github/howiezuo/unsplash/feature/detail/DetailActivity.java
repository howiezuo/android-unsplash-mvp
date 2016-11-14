package io.github.howiezuo.unsplash.feature.detail;

import android.os.Bundle;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.app.AppApplication;
import io.github.howiezuo.unsplash.feature.base.BaseActivity;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.model.dto.PhotoDto;
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
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        if (savedInstanceState != null) {
            return;
        }

        PhotoDto photoDto = getIntent().getParcelableExtra(EXTRA_PHOTO);

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.container_fragment);
        if (fragment == null) {
            fragment = DetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.container_fragment);
        }

        DaggerDetailComponent.builder()
                .apiComponent(AppApplication.getInstance().getApiComponent())
                .databaseComponent(AppApplication.getInstance().getDatabaseComponent())
                .helperComponent(AppApplication.getInstance().getHelperComponent())
                .detailPresenterModule(new DetailPresenterModule(fragment, photoDto))
                .loginPresenterModule(getLoginPresenterModule())
                .build().inject(this);
    }
}
