package io.github.howiezuo.unsplash.feature.detail;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.feature.base.BaseFragment;
import io.github.howiezuo.unsplash.feature.user.UserActivity;
import io.github.howiezuo.unsplash.model.dto.PhotoDto;
import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.github.howiezuo.unsplash.model.dto.photo.LocationDto;

public class DetailFragment extends BaseFragment implements DetailContract.View {

    DetailContract.Presenter mPresenter;

    @BindView(R.id.image_profile)
    ImageView mImageProfile;
    @BindView(R.id.text_name)
    TextView mTextName;
    @BindView(R.id.image_photo)
    ImageView mImagePhoto;
    @BindView(R.id.text_icon_like)
    TextView mTextIconLike;
    @BindView(R.id.text_likes)
    TextView mTextLikes;
    @BindView(R.id.map_location)
    MapView mMapLocation;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mMapLocation.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter.showPhoto();
        mPresenter.loadPhoto();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (mMapLocation != null) mMapLocation.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mMapLocation != null) mMapLocation.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mMapLocation != null) mMapLocation.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();

        if (mMapLocation != null) mMapLocation.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMapLocation != null) mMapLocation.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if (mMapLocation != null) mMapLocation.onLowMemory();
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
    public void setPresenter(DetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showPhoto(final PhotoDto photoDto) {
        final UserDto userDto = photoDto.getUser();
        Picasso.with(getContext())
                .load(userDto.getProfileImage().getMedium())
                .noFade()
                .into(mImageProfile);
        mTextName.setText(userDto.getName());
        mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.openUserDetails(userDto);
            }
        });
        Picasso.with(getContext())
                .load(photoDto.getUrls().getRegular())
                .into(mImagePhoto);
        mTextLikes.setText(String.valueOf(photoDto.getLikes()));
        if (photoDto.isLikedByUser()) {
            mTextIconLike.setTextColor(ContextCompat.getColor(getContext(), R.color.liked));
        } else {
            mTextIconLike.setTextColor(ContextCompat.getColor(getContext(), R.color.unliked));
        }
        mTextIconLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (photoDto.isLikedByUser()) {
                    mPresenter.unlikePhoto(photoDto);
                } else {
                    mPresenter.likePhoto(photoDto);
                }
            }
        });
    }

    @Override
    public void showLocation(PhotoDto photoDto) {
        final LocationDto locationDto = photoDto.getLocation();
        if (locationDto != null) {
            final LatLng ll = new LatLng(locationDto.getPosition().getLat(), locationDto.getPosition().getLng());
            String title = null;
            if (locationDto.getCity() != null) {
                title = locationDto.getCity();
            }
            if (locationDto.getCountry() != null) {
                title = title == null? locationDto.getCountry() : title + ", " + locationDto.getCountry();
            }
            final String finalTitle = title;

            mMapLocation.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    googleMap.getUiSettings().setAllGesturesEnabled(false);
                    googleMap.addMarker(new MarkerOptions().position(ll).title(finalTitle));
                    googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(ll, 1, 0, 0)));
                }
            });
            mMapLocation.setVisibility(View.VISIBLE);
        } else {
            mMapLocation.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void showUserDetails(UserDto userDto) {
        Intent intent = new Intent(getActivity(), UserActivity.class);
        intent.putExtra(UserActivity.EXTRA_USER, userDto);
        startActivity(intent);
    }

    @Override
    public void likedPhoto(PhotoDto photoDto) {
        mTextIconLike.setTextColor(ContextCompat.getColor(getContext(), R.color.liked));
        mTextLikes.setText(String.valueOf(photoDto.getLikes()));
    }

    @Override
    public void unlikedPhoto(PhotoDto photoDto) {
        mTextIconLike.setTextColor(ContextCompat.getColor(getContext(), R.color.unliked));
        mTextLikes.setText(String.valueOf(photoDto.getLikes()));
    }

    @Override
    public void showError() {
        showErrorSnackbar(getView(), R.string.error_api);
    }
}
