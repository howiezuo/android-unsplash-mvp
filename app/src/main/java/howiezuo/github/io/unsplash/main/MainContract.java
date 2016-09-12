package howiezuo.github.io.unsplash.main;

import java.util.List;

import howiezuo.github.io.unsplash.BasePresenter;
import howiezuo.github.io.unsplash.BaseView;
import howiezuo.github.io.unsplash.model.Photo;

public interface MainContract {

    interface Presenter extends BasePresenter {

        void loadPhotos();

    }

    interface View extends BaseView<Presenter> {

        void refreshList(List<Photo> list);

    }

}
