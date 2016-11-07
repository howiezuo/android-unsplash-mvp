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
import io.github.howiezuo.unsplash.feature.me.MeActivity;
import io.github.howiezuo.unsplash.model.dto.PhotoDto;
import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.github.howiezuo.unsplash.feature.user.UserActivity;
import io.github.howiezuo.unsplash.util.UIUtils;


public class MainFragment extends BaseFragment implements MainContract.View {

    MainContract.Presenter mPresenter;

    @BindView(R.id.rv_photos)
    RecyclerView mRVPhotos;

    PhotoItemListener mListener = new PhotoItemListener() {
        @Override
        public void onPhotoClick(PhotoDto photoDto) {
            mPresenter.openPhotoDetails(photoDto);
        }

        @Override
        public void onUserClick(UserDto userDto) {
            mPresenter.openUserDetails(userDto);
        }

        @Override
        public void onLikeClick(PhotoDto photoDto, int index) {
            if (photoDto.isLikedByUser()) {
                mPresenter.unlikePhoto(photoDto, index);
            } else {
                mPresenter.likePhoto(photoDto, index);
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
                Intent intent = new Intent(getActivity(), MeActivity.class);
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
    public void refreshPhotos(List<PhotoDto> list) {
        mAdapter.updateDataSet(list);
        isLoading = false;
    }

    @Override
    public void addPhotos(List<PhotoDto> list) {
        mAdapter.addDataSet(list);
        isLoading = false;
    }

    @Override
    public void showPhotoDetails(PhotoDto photoDto) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra(DetailActivity.EXTRA_PHOTO, photoDto);
        startActivity(intent);
    }

    @Override
    public void showUserDetails(UserDto userDto) {
        Intent intent = new Intent(getActivity(), UserActivity.class);
        intent.putExtra(UserActivity.EXTRA_USER, userDto);
        startActivity(intent);
    }

    @Override
    public void likedPhoto(PhotoDto photoDto, int index) {
        mAdapter.likePhoto(photoDto, index);
    }

    @Override
    public void unlikedPhoto(PhotoDto photoDto, int index) {
        mAdapter.likePhoto(photoDto, index);
    }

    @Override
    public void showError() {
        showErrorSnackbar(getView(), R.string.error_api);
    }
}
