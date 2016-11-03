package io.github.howiezuo.unsplash.feature.user.photos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.feature.base.BaseFragment;
import io.github.howiezuo.unsplash.feature.detail.DetailActivity;
import io.github.howiezuo.unsplash.feature.PhotoItemListener;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;


public class UserPhotosFragment extends BaseFragment implements UserPhotosContract.View {

    private UserPhotosContract.Presenter mPresenter;

    @BindView(R.id.recycler_photos)
    RecyclerView mRecyclerPhotos;

    PhotoItemListener mListener = new PhotoItemListener() {
        @Override
        public void onPhotoClick(Photo photo) {
            mPresenter.openPhotoDetails(photo);
        }

        @Override
        public void onUserClick(User user) {
            // nothing to do
        }

        @Override
        public void onLikeClick(Photo photo, int index) {
            if (photo.isLikedByUser()) {
                mPresenter.unlikePhoto(photo, index);
            } else {
                mPresenter.likePhoto(photo, index);
            }
        }
    };
    private UserPhotosAdapter mAdapter = new UserPhotosAdapter(mListener);

    public UserPhotosFragment() {

    }

    public static UserPhotosFragment newInstance() {
        UserPhotosFragment fragment = new UserPhotosFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_photos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerPhotos.setAdapter(mAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerPhotos.setLayoutManager(llm);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.loadPhotos();
    }

    @Override
    public void setPresenter(UserPhotosContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPhotos(List<Photo> photos) {
        mAdapter.updateDataSet(photos);
    }

    @Override
    public void showPhotoDetails(Photo photo) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_PHOTO, photo);
        startActivity(intent);
    }

    @Override
    public void likedPhoto(Photo photo, int index) {
        mAdapter.likePhoto(photo, index);
    }

    @Override
    public void unlikedPhoto(Photo photo, int index) {
        mAdapter.likePhoto(photo, index);
    }

}
