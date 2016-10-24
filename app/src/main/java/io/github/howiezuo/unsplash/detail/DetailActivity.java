package io.github.howiezuo.unsplash.detail;

import android.os.Bundle;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.base.BaseActivity;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.util.ActivityUtils;

public class DetailActivity extends BaseActivity {

    public static final String EXTRA_PHOTO = "photo";

    @Inject
    DetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setHomeIcon(R.layout.view_action_back);

        if (savedInstanceState != null) {
            return;
        }

        Photo photo = getIntent().getParcelableExtra(EXTRA_PHOTO);

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = DetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
        }

        DaggerDetailComponent.builder()
                .detailPresenterModule(new DetailPresenterModule(fragment, photo))
                .build()
                .inject(this);
    }
}
