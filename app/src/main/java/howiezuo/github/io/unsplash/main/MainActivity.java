package howiezuo.github.io.unsplash.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import howiezuo.github.io.unsplash.AppApplication;
import howiezuo.github.io.unsplash.R;
import howiezuo.github.io.unsplash.api.Photos;
import howiezuo.github.io.unsplash.util.ActivityUtils;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mPresenter;
    @Inject
    Photos mPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }

        MainFragment fragment = (MainFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            fragment = MainFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_container);
        }

        AppApplication.getInstance().getApiComponent().inject(this);

        mPresenter = new MainPresenter(fragment, mPhotos);
    }
}
