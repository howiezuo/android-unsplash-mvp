package io.github.howiezuo.unsplash.main;

import android.graphics.Color;
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

    private List<Photo> mDataset = new ArrayList<>();
    private MainListener mListener;

    public MainAdapter(MainListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
        RecyclerView.ViewHolder vh = new ViewHolder(v, mListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        vh.bindView(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void updateDataset(List<Photo> dataset) {
        mDataset = dataset;
        this.notifyDataSetChanged();
    }

    public void addDataset(List<Photo> dataset) {
        mDataset.addAll(dataset);
        this.notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photo)
        ImageView ivPhoto;
        @BindView(R.id.civ_profile)
        CircleImageView civProfile;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_likes)
        TextView tvLikes;

        MainListener mListener;

        public ViewHolder(View itemView, MainListener listener) {
            super(itemView);
            mListener = listener;

            ButterKnife.bind(this, itemView);
        }

        public void bindView(final Photo photo) {
            ivPhoto.post(new Runnable() {
                @Override
                public void run() {
                    if (ivPhoto != null && itemView != null) {
                        int w = ivPhoto.getWidth();
                        int h = (int) (w / Config.PHOTO_RATIO);

                        ViewGroup.LayoutParams params = ivPhoto.getLayoutParams();
                        params.height = h;
                        ivPhoto.setLayoutParams(params);
                        Picasso.with(itemView.getContext())
                                .load(photo.getUrls().getSmall())
                                .resize(w, h)
                                .centerCrop()
                                .into(ivPhoto);
                    }
                }
            });
            ivPhoto.setBackgroundColor(Color.parseColor(photo.getColor()));
            ivPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onPhotoClick(photo);
                }
            });

            User user = photo.getUser();
            Picasso.with(itemView.getContext())
                    .load(user.getProfileImage().getSmall())
                    .noFade()
                    .into(civProfile);
            tvName.setText(user.getName());
            tvLikes.setText(String.valueOf(photo.getLikes()));
        }
    }
}
