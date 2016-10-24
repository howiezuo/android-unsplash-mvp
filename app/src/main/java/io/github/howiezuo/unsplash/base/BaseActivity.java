package io.github.howiezuo.unsplash.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.login.LoginFragment;
import io.github.howiezuo.unsplash.util.ActivityUtils;


public abstract class BaseActivity extends AppCompatActivity {

    @BindView(R.id.tool_bar)
    Toolbar mToolbar;
    @BindView(R.id.fragment_login)
    View mLoginFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
    }

    protected @LayoutRes int getLayoutId() {
        return R.layout.activity_base;
    }

    protected void showLogin() {
        LoginFragment fragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_login);
        if (fragment == null) {
            fragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), fragment, R.id.fragment_login);
        }
        mLoginFragment.setVisibility(View.VISIBLE);
    }
}
