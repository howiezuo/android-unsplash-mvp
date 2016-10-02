package howiezuo.github.io.unsplash.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import howiezuo.github.io.unsplash.R;
import howiezuo.github.io.unsplash.model.Photo;
import howiezuo.github.io.unsplash.util.ActivityUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (savedInstanceState != null) {
            return;
        }

        Photo photo = getIntent().getParcelableExtra(EXTRA_PHOTO);

        DetailFragment fragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = DetailFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
        }

        new DetailPresenter(fragment, photo);
    }
}
