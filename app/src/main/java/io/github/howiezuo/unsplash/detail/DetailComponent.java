package io.github.howiezuo.unsplash.detail;

import dagger.Component;
import io.github.howiezuo.unsplash.api.ApiComponent;
import io.github.howiezuo.unsplash.login.LoginPresenterModule;
import io.github.howiezuo.unsplash.util.FragmentScoped;

@FragmentScoped
@Component(
        dependencies = {
                ApiComponent.class
        },
        modules = {
                DetailPresenterModule.class,
                LoginPresenterModule.class
        }
)
public interface DetailComponent {

    void inject(DetailActivity activity);

}
