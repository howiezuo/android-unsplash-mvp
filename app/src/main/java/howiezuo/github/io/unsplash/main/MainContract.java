package howiezuo.github.io.unsplash.main;

import java.util.List;

import howiezuo.github.io.unsplash.BasePresenter;
import howiezuo.github.io.unsplash.BaseView;
import howiezuo.github.io.unsplash.model.Photo;

public interface MainContract {

    interface Presenter extends BasePresenter {

        void loadPhotos();

        void loadMorePhotos();

    }

    interface View extends BaseView<Presenter> {

        void refreshPhotos(List<Photo> list);

        void addPhotos(List<Photo> list);

    }

}
