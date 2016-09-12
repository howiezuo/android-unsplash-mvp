package howiezuo.github.io.unsplash.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import howiezuo.github.io.unsplash.BaseFragment;
import howiezuo.github.io.unsplash.PhotoAdapter;
import howiezuo.github.io.unsplash.R;
import howiezuo.github.io.unsplash.model.Photo;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends BaseFragment implements MainContract.View {

    MainContract.Presenter mPresenter;

    @BindView(R.id.view_recycler)
    RecyclerView mRecyclerView;

    private PhotoAdapter mAdapter = new PhotoAdapter();
    private boolean isLoading = false;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isLoading = true;
        mPresenter.loadPhotos();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView.setAdapter(mAdapter);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(llm);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int last = llm.findLastCompletelyVisibleItemPosition();

                if (dy > 0 && !isLoading && last + 1 >= mAdapter.getItemCount()) {
                    isLoading = true;
                    mPresenter.loadMorePhotos();
                }
            }
        });
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void refreshPhotos(List<Photo> list) {
        mAdapter.updateDataset(list);
        isLoading = false;
    }

    @Override
    public void addPhotos(List<Photo> list) {
        mAdapter.addDataset(list);
        isLoading = false;
    }
}
