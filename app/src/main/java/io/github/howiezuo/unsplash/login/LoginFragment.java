package io.github.howiezuo.unsplash.login;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import io.github.howiezuo.unsplash.AppApplication;
import io.github.howiezuo.unsplash.Config;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.base.BaseFragment;

public class LoginFragment extends BaseFragment implements LoginContract.View {

    LoginContract.Presenter mPresenter;

    @BindView(R.id.web_view)
    WebView mWebView;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // TODO need to remove
        CookieManager.getInstance().removeAllCookie();

        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (uri.getScheme().equals(Config.APP_SCHEME) && uri.getHost().equals(Config.APP_HOST)) {
                    String code = uri.getQueryParameter("code");
                    mPresenter.getToken(code);
                    return true;
                }
                return false;
            }
        });
        mWebView.loadUrl(String.format(Config.LOGIN_URL, AppApplication.getInstance().getClientId()));
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
