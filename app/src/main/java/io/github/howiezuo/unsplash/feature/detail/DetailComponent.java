package io.github.howiezuo.unsplash.feature.detail;

import dagger.Component;
import io.github.howiezuo.unsplash.api.ApiComponent;
import io.github.howiezuo.unsplash.helper.HelperComponent;
import io.github.howiezuo.unsplash.feature.login.LoginPresenterModule;
import io.github.howiezuo.unsplash.util.FragmentScoped;

@FragmentScoped
@Component(
        dependencies = {
                ApiComponent.class,
                HelperComponent.class
        },
        modules = {
                DetailPresenterModule.class,
                LoginPresenterModule.class
        }
)
public interface DetailComponent {

    void inject(DetailActivity activity);

}