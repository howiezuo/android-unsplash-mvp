package io.github.howiezuo.unsplash.main;

import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.model.Photo;
import io.github.howiezuo.unsplash.util.DeviceUtils;

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
        @BindView(R.id.image_view)
        ImageView imageView;
        @BindView(R.id.card_view)
        CardView cardView;

        MainListener mListener;

        public ViewHolder(View itemView, MainListener listener) {
            super(itemView);
            mListener = listener;

            ButterKnife.bind(this, itemView);
        }

        public void bindView(final Photo photo) {
            Point point = DeviceUtils.getDisplaySize(itemView.getContext());
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            int h = (int) (point.x / 1.6);
            params.height = h;
            imageView.setLayoutParams(params);
            imageView.setBackgroundColor(Color.parseColor(photo.getColor()));
            Picasso.with(itemView.getContext())
                    .load(photo.getUrls().getSmall())
                    .resize(point.x, h)
                    .centerCrop()
                    .into(imageView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.onPhotoClick(photo);
                }
            });
        }
    }
}
