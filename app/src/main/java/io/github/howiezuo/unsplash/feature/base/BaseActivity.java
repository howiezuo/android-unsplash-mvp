package io.github.howiezuo.unsplash.feature.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.Lazy;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.feature.login.LoginFragment;
import io.github.howiezuo.unsplash.feature.login.LoginPresenter;
import io.github.howiezuo.unsplash.feature.login.LoginPresenterModule;
import io.github.howiezuo.unsplash.util.ActivityUtils;


public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.container_login)
    View mContainerLogin;

    @Inject
    Lazy<LoginPresenter> mLoginPresenter;

    protected LoginFragment mLoginFragment;
    private LoginPresenterModule mLoginPresenterModule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());
        ButterKnife.bind(this);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

        mLoginPresenterModule = new LoginPresenterModule(mLoginFragment, this);
    }

    protected @LayoutRes int getLayoutId() {
        return R.layout.activity_base;
    }

    protected LoginPresenterModule getLoginPresenterModule() {
        return mLoginPresenterModule;
    }

    protected void showLogin() {
        mLoginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.container_login);
        if (mLoginFragment == null) {
            mLoginFragment = LoginFragment.newInstance();
            ActivityUtils.addFragmentToBackStack(getSupportFragmentManager(), mLoginFragment, R.id.container_login);
        }

        mLoginPresenterModule.setView(mLoginFragment);
        mLoginPresenter.get();
        mContainerLogin.setVisibility(View.VISIBLE);
    }
}
