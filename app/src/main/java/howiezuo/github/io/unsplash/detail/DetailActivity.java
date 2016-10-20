package howiezuo.github.io.unsplash.detail;

import android.os.Bundle;

import javax.inject.Inject;

import howiezuo.github.io.unsplash.base.BaseActivity;
import howiezuo.github.io.unsplash.R;
import howiezuo.github.io.unsplash.model.Photo;
import howiezuo.github.io.unsplash.util.ActivityUtils;

public class DetailActivity extends BaseActivity {

    public static final String EXTRA_PHOTO = "photo";

    @Inject
    DetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);

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
