package howiezuo.github.io.unsplash.detail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import howiezuo.github.io.unsplash.base.BaseFragment;
import howiezuo.github.io.unsplash.R;
import howiezuo.github.io.unsplash.model.Photo;

public class DetailFragment extends BaseFragment implements DetailContract.View {

    DetailContract.Presenter mPresenter;

    @BindView(R.id.image_view)
    ImageView mImageView;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter.showPhoto();
    }

    @Override
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPhoto(Photo photo) {
        Picasso.with(getContext())
                .load(photo.getUrls().getRegular())
//                .centerCrop()
                .into(mImageView);
    }
}
