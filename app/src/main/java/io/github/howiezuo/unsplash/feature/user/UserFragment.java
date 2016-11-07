package io.github.howiezuo.unsplash.feature.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.feature.base.BaseFragment;
import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.github.howiezuo.unsplash.widget.CircleImageView;


public class UserFragment extends BaseFragment implements UserContract.View {

    UserContract.Presenter mPresenter;

    @BindView(R.id.image_profile)
    CircleImageView mImageProfile;
    @BindView(R.id.text_name)
    TextView mTextName;
    @BindView(R.id.text_bio)
    TextView mTextBio;
    @BindView(R.id.text_photos)
    TextView mTextPhotos;
    @BindView(R.id.text_likes)
    TextView mTextLikes;
    @BindView(R.id.text_collections)
    TextView mTextCollections;

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

        mPresenter.showUser();
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
    public void showMe(UserDto userDto) {
        Picasso.with(getActivity())
                .load(userDto.getProfileImage().getLarge())
                .noFade()
                .into(mImageProfile);
        mTextName.setText(userDto.getName());
        mTextBio.setText(userDto.getBio());
        mTextPhotos.setText(String.valueOf(userDto.getTotalPhotos()));
        mTextLikes.setText(String.valueOf(userDto.getTotalLikes()));
        mTextCollections.setText(String.valueOf(userDto.getTotalCollections()));
    }

    @Override
    public void showUser(UserDto userDto) {
        Picasso.with(getActivity())
                .load(userDto.getProfileImage().getLarge())
                .noFade()
                .into(mImageProfile);
        mTextName.setText(userDto.getName());
        mTextBio.setText(userDto.getBio());
        mTextPhotos.setText(String.valueOf(userDto.getTotalPhotos()));
        mTextLikes.setText(String.valueOf(userDto.getTotalLikes()));
        mTextCollections.setText(String.valueOf(userDto.getTotalCollections()));
    }

}
