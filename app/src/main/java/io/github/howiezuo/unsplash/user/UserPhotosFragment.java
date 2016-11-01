package io.github.howiezuo.unsplash.user;

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
import io.github.howiezuo.unsplash.base.BaseFragment;
import io.github.howiezuo.unsplash.model.Photo;


public class UserPhotosFragment extends BaseFragment implements UserPhotosContract.View {

    private UserPhotosContract.Presenter mPresenter;

    @BindView(R.id.rv_photos)
    RecyclerView mRVPhotos;

    private UserPhotosAdapter mAdapter = new UserPhotosAdapter();

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

        mRVPhotos.setAdapter(mAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRVPhotos.setLayoutManager(llm);
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

}
