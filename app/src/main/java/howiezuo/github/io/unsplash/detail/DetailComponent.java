package howiezuo.github.io.unsplash.detail;

import dagger.Component;
import howiezuo.github.io.unsplash.util.FragmentScoped;

@FragmentScoped
@Component(modules = DetailPresenterModule.class)
public interface DetailComponent {

    void inject(DetailActivity activity);

}
