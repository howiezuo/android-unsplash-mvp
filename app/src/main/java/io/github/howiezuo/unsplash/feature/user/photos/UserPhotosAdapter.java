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
import io.github.howiezuo.unsplash.model.PhotoDto;

public class UserPhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PhotoDto> mDataSet = new ArrayList<>();
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

    public void updateDataSet(List<PhotoDto> dataSet) {
        mDataSet.addAll(dataSet);
        this.notifyDataSetChanged();
    }

    public void likePhoto(PhotoDto photoDto, int index) {
        PhotoDto item = mDataSet.get(index);
        if (item != null) {
            item.setLikedByUser(photoDto.isLikedByUser());
            item.setLikes(photoDto.getLikes());
            mDataSet.set(index, item);

            this.notifyItemChanged(index);
        }
    }

    public static class ViewHolder extends PhotoItemViewHolder {

        public ViewHolder(View itemView, PhotoItemListener listener) {
            super(itemView, listener);
        }

        @Override
        public void bindView(PhotoDto photoDto, int index) {
            super.bindView(photoDto, index);

            mImageProfile.setVisibility(View.GONE);
            mTextName.setVisibility(View.GONE);
        }

    }

}
