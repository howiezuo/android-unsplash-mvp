package io.github.howiezuo.unsplash.feature.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import io.github.howiezuo.unsplash.feature.user.UserActivity;
import io.github.howiezuo.unsplash.util.UIUtils;


public class MainFragment extends BaseFragment implements MainContract.View {

    MainContract.Presenter mPresenter;

    @BindView(R.id.rv_photos)
    RecyclerView mRVPhotos;

    PhotoItemListener mListener = new PhotoItemListener() {
        @Override
        public void onPhotoClick(Photo photo) {
            mPresenter.openPhotoDetails(photo);
        }

        @Override
        public void onUserClick(User user) {
            mPresenter.openUserDetails(user);
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
    private MainAdapter mAdapter = new MainAdapter(mListener);
    private boolean isLoading = false;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRVPhotos.setAdapter(mAdapter);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRVPhotos.setLayoutManager(llm);
        mRVPhotos.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int last = llm.findLastCompletelyVisibleItemPosition();

                if (dy > 0 && !isLoading && last + 1 >= mAdapter.getItemCount()) {
                    isLoading = true;
                    mPresenter.loadMorePhotos();
                }
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isLoading = true;
        mPresenter.loadPhotos();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);

        final MenuItem item = menu.findItem(R.id.action_profile);
        item.setIcon(UIUtils.view2Drawable(getActivity(), R.layout.view_action_profile));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                Intent intent = new Intent(getActivity(), UserActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void refreshPhotos(List<Photo> list) {
        mAdapter.updateDataSet(list);
        isLoading = false;
    }

    @Override
    public void addPhotos(List<Photo> list) {
        mAdapter.addDataSet(list);
        isLoading = false;
    }

    @Override
    public void showPhotoDetails(Photo photo) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_PHOTO, photo);
        startActivity(intent);
    }

    @Override
    public void showUserDetails(User user) {
        Intent intent = new Intent(getActivity(), UserActivity.class);
        intent.putExtra(UserActivity.EXTRA_USER, user);
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

    @Override
    public void showError() {
        showErrorSnackbar(getView(), R.string.error_api);
    }
}
