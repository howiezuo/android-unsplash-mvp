package io.github.howiezuo.unsplash.detail;

import dagger.Component;
import io.github.howiezuo.unsplash.util.FragmentScoped;

@FragmentScoped
@Component(modules = DetailPresenterModule.class)
public interface DetailComponent {

    void inject(DetailActivity activity);

}
