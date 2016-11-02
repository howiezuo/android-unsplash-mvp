package io.github.howiezuo.unsplash.main;

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

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> mDataSet = new ArrayList<>();
    private MainListener mListener;

    public MainAdapter(MainListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ViewType.HEADER.ordinal()) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_header, parent, false);
            RecyclerView.ViewHolder vh = new HeaderHolder(v);
            return vh;
        } else if (viewType == ViewType.NORMAL.ordinal()) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
            RecyclerView.ViewHolder vh = new ViewHolder(v, mListener);
            return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int index = position - 1; // because of header
        if (getItemViewType(position) == ViewType.NORMAL.ordinal()) {
            ViewHolder vh = (ViewHolder) holder;
            vh.bindView(mDataSet.get(index), index);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return ViewType.HEADER.ordinal();
        return ViewType.NORMAL.ordinal();
    }

    @Override
    public int getItemCount() {
        return mDataSet.size() + 1;
    }

    public void updateDataSet(List<Photo> dataSet) {
        mDataSet = dataSet;
        this.notifyDataSetChanged();
    }

    public void addDataSet(List<Photo> dataset) {
        mDataSet.addAll(dataset);
        this.notifyDataSetChanged();
    }

    public void likePhoto(Photo photo, int index) {
        Photo item = mDataSet.get(index);
        if (item != null) {
            item.setLikedByUser(photo.isLikedByUser());
            item.setLikes(photo.getLikes());
            mDataSet.set(index, item);

            this.notifyItemChanged(index + 1);
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View itemView) {
            super(itemView);
        }
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

        MainListener mListener;

        public ViewHolder(View itemView, MainListener listener) {
            super(itemView);
            mListener = listener;

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
                    mListener.onPhotoClick(photo);
                }
            });

            final User user = photo.getUser();
            Picasso.with(itemView.getContext())
                    .load(user.getProfileImage().getSmall())
                    .noFade()
                    .into(imageProfile);
            textName.setText(user.getName());
            textName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onUserClick(user);
                }
            });
            textLikes.setText(String.valueOf(photo.getLikes()));
            if (photo.isLikedByUser()) {
                textIconLike.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.liked));
            } else {
                textIconLike.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.unliked));
            }
            textIconLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onLikeClick(photo, index);
                }
            });
        }
    }

    private enum ViewType {
        HEADER,
        NORMAL
    }
}
