package howiezuo.github.io.unsplash;

import android.graphics.Point;
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
import howiezuo.github.io.unsplash.model.Photo;
import howiezuo.github.io.unsplash.util.DeviceUtils;

public class PhotoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> mDataset = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.photo_item, parent, false);
        RecyclerView.ViewHolder vh = new ViewHolder(v);
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

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        public void bindView(Photo photo) {
            Point point = DeviceUtils.getDisplaySize(itemView.getContext());
            ViewGroup.LayoutParams params = imageView.getLayoutParams();
            int h = (int) (point.x / 1.6);
            params.height = h;
            imageView.setLayoutParams(params);
            Picasso.with(itemView.getContext())
                    .load(photo.getUrls().getRegular())
                    .resize(point.x, h)
                    .centerCrop()
                    .into(imageView);
        }
    }
}
