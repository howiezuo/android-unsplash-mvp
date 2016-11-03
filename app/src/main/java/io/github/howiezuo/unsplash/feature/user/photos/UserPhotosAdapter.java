package io.github.howiezuo.unsplash.feature.user.photos;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.github.howiezuo.unsplash.R;
import io.github.howiezuo.unsplash.feature.PhotoItemListener;
import io.github.howiezuo.unsplash.feature.PhotoItemViewHolder;
import io.github.howiezuo.unsplash.model.Photo;

public class UserPhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> mDataSet = new ArrayList<>();
    private PhotoItemListener mListener;

    public UserPhotosAdapter(PhotoItemListener listener) {
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        RecyclerView.ViewHolder vh = new ViewHolder(v, mListener);
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

    public void likePhoto(Photo photo, int index) {
        Photo item = mDataSet.get(index);
        if (item != null) {
            item.setLikedByUser(photo.isLikedByUser());
            item.setLikes(photo.getLikes());
            mDataSet.set(index, item);

            this.notifyItemChanged(index);
        }
    }

    public static class ViewHolder extends PhotoItemViewHolder {

        public ViewHolder(View itemView, PhotoItemListener listener) {
            super(itemView, listener);
        }

        @Override
        public void bindView(Photo photo, int index) {
            super.bindView(photo, index);

            mImageProfile.setVisibility(View.GONE);
            mTextName.setVisibility(View.GONE);
        }

    }

}
