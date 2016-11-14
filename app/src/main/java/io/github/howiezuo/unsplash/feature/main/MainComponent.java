package io.github.howiezuo.unsplash.feature.main;

import dagger.Component;
import io.github.howiezuo.unsplash.api.ApiComponent;
import io.github.howiezuo.unsplash.database.DatabaseComponent;
import io.github.howiezuo.unsplash.helper.HelperComponent;
import io.github.howiezuo.unsplash.feature.login.LoginPresenterModule;
import io.github.howiezuo.unsplash.app.scope.FragmentScoped;

@FragmentScoped
@Component(
        dependencies = {
                DatabaseComponent.class,
                HelperComponent.class,
                ApiComponent.class,
        },
        modules = {
                MainPresenterModule.class,
                LoginPresenterModule.class
        }
)
public interface MainComponent {

    void inject(MainActivity activity);

}
