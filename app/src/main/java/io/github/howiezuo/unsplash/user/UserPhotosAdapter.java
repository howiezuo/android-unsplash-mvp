package io.github.howiezuo.unsplash.user;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import io.github.howiezuo.unsplash.Config;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.model.User;

public class UserPhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> mDataSet = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        RecyclerView.ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.bindView(mDataSet.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void updateDataSet(List<Photo> dataSet) {
        mDataSet.addAll(dataSet);
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_photo)
        ImageView imagePhoto;
        @BindView(R.id.image_profile)
        CircleImageView imageProfile;
        @BindView(R.id.text_name)
        TextView textName;
        @BindView(R.id.text_likes)
        TextView textLikes;
        @BindView(R.id.text_icon_like)
        TextView textIconLike;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bindView(final Photo photo, final int index) {
            imagePhoto.post(new Runnable() {
                @Override
                public void run() {
                    if (imagePhoto != null && itemView != null) {
                        int w = imagePhoto.getWidth();
                        int h = (int) (w / Config.PHOTO_RATIO);

                        ViewGroup.LayoutParams params = imagePhoto.getLayoutParams();
                        params.height = h;
                        imagePhoto.setLayoutParams(params);
                        Picasso.with(itemView.getContext())
                                .load(photo.getUrls().getSmall())
                                .resize(w, h)
                                .centerCrop()
                                .noFade()
                                .into(imagePhoto);
                    }
                }
            });
            imagePhoto.setBackgroundColor(Color.parseColor(photo.getColor()));
            imagePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mListener.onPhotoClick(photo);
                }
            });

            final User user = photo.getUser();
            Picasso.with(itemView.getContext())
                    .load(user.getProfileImage().getSmall())
                    .noFade()
                    .into(imageProfile);
            textName.setText(user.getName());
            textLikes.setText(String.valueOf(photo.getLikes()));
            if (photo.isLikedByUser()) {
                textIconLike.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.color_liked));
            } else {
                textIconLike.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.color_unliked));
            }
            textIconLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    mListener.onLikeClick(photo, index);
                }
            });
        }
    }

}
