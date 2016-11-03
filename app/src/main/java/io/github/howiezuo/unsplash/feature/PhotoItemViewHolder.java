package io.github.howiezuo.unsplash.feature;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.howiezuo.unsplash.Config;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;
import io.github.howiezuo.unsplash.widget.CircleImageView;


public class PhotoItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_photo)
    protected ImageView mImagePhoto;
    @BindView(R.id.image_profile)
    protected CircleImageView mImageProfile;
    @BindView(R.id.text_name)
    protected TextView mTextName;
    @BindView(R.id.text_likes)
    protected TextView mTextLikes;
    @BindView(R.id.text_icon_like)
    protected TextView mTextIconLike;

    private PhotoItemListener mListener;

    public PhotoItemViewHolder(View itemView, PhotoItemListener listener) {
        super(itemView);

        mListener = listener;
        ButterKnife.bind(this, itemView);
    }

    public void bindView(final Photo photo, final int index) {
        mImagePhoto.post(new Runnable() {
            @Override
            public void run() {
                if (mImagePhoto != null && itemView != null) {
                    int w = mImagePhoto.getWidth();
                    int h = (int) (w / Config.PHOTO_RATIO);

                    ViewGroup.LayoutParams params = mImagePhoto.getLayoutParams();
                    params.height = h;
                    mImagePhoto.setLayoutParams(params);
                    Picasso.with(itemView.getContext())
                            .load(photo.getUrls().getSmall())
                            .resize(w, h)
                            .centerCrop()
                            .noFade()
                            .into(mImagePhoto);
                }
            }
        });
        mImagePhoto.setBackgroundColor(Color.parseColor(photo.getColor()));
        mImagePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onPhotoClick(photo);
            }
        });

        final User user = photo.getUser();
        Picasso.with(itemView.getContext())
                .load(user.getProfileImage().getSmall())
                .noFade()
                .into(mImageProfile);
        mTextName.setText(user.getName());
        mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onUserClick(user);
            }
        });
        mTextLikes.setText(String.valueOf(photo.getLikes()));
        if (photo.isLikedByUser()) {
            mTextIconLike.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.liked));
        } else {
            mTextIconLike.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.unliked));
        }
        mTextIconLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onLikeClick(photo, index);
            }
        });
    }

}
