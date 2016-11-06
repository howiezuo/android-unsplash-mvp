package io.github.howiezuo.unsplash.feature.main;

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

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PhotoDto> mDataSet = new ArrayList<>();
    private PhotoItemListener mListener;

    public MainAdapter(PhotoItemListener listener) {
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
            RecyclerView.ViewHolder vh = new PhotoItemViewHolder(v, mListener);
            return vh;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int index = position - 1; // because of header
        if (getItemViewType(position) == ViewType.NORMAL.ordinal()) {
            PhotoItemViewHolder vh = (PhotoItemViewHolder) holder;
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

    public void updateDataSet(List<PhotoDto> dataSet) {
        mDataSet = dataSet;
        this.notifyDataSetChanged();
    }

    public void addDataSet(List<PhotoDto> dataSet) {
        mDataSet.addAll(dataSet);
        this.notifyDataSetChanged();
    }

    public void likePhoto(PhotoDto photoDto, int index) {
        PhotoDto item = mDataSet.get(index);
        if (item != null) {
            item.setLikedByUser(photoDto.isLikedByUser());
            item.setLikes(photoDto.getLikes());
            mDataSet.set(index, item);

            this.notifyItemChanged(index + 1);
        }
    }

    public static class HeaderHolder extends RecyclerView.ViewHolder {
        public HeaderHolder(View itemView) {
            super(itemView);
        }
    }

    private enum ViewType {
        HEADER,
        NORMAL
    }
}
