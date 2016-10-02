package howiezuo.github.io.unsplash.detail;

import howiezuo.github.io.unsplash.BasePresenter;
import howiezuo.github.io.unsplash.BaseView;
import howiezuo.github.io.unsplash.model.Photo;

public interface DetailContract {

    interface Presenter extends BasePresenter {

        void showPhoto();

    }

    interface View extends BaseView<Presenter> {

        void showPhoto(Photo photo);

    }

}
