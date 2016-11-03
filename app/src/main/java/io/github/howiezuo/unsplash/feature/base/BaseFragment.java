package io.github.howiezuo.unsplash.feature.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.github.howiezuo.unsplash.R;

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mUnbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mUnbinder.unbind();
    }

    /**
     * Error Snackbar
     *
     * @param view
     * @param strId
     */
    protected void showErrorSnackbar(View view, @StringRes int strId) {
        Snackbar snackbar = Snackbar.make(view, strId, Snackbar.LENGTH_SHORT);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.background_snackbar_error));
        TextView tv = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(ContextCompat.getColor(getContext(), R.color.text_snackbar_error));
        snackbar.show();
    }
}
