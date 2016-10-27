package io.github.howiezuo.unsplash.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.base.BaseFragment;
import io.github.howiezuo.unsplash.model.User;


public class UserFragment extends BaseFragment implements UserContract.View {

    UserContract.Presenter mPresenter;

    @BindView(R.id.tv_name)
    TextView mTextViewName;
    @BindView(R.id.iv_profile)
    CircleImageView mImageViewProfile;

    public UserFragment() {

    }

    public static UserFragment newInstance() {
        UserFragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.loadMe();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(UserContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showMe(User user) {
        mTextViewName.setText(user.getName());
        Picasso.with(getActivity())
                .load(user.getProfileImage().getMedium())
                .noFade()
                .into(mImageViewProfile);
    }
}
