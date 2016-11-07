package io.github.howiezuo.unsplash.feature.user;

import dagger.Component;
import io.github.howiezuo.unsplash.api.ApiComponent;
import io.github.howiezuo.unsplash.database.DatabaseComponent;
import io.github.howiezuo.unsplash.helper.HelperComponent;
import io.github.howiezuo.unsplash.feature.login.LoginPresenterModule;
import io.github.howiezuo.unsplash.util.FragmentScoped;

@FragmentScoped
@Component(
        dependencies = {
                DatabaseComponent.class,
                HelperComponent.class,
                ApiComponent.class,
        },
        modules = {
                UserPresenterModule.class,
                LoginPresenterModule.class
        }
)
public interface UserComponent {

    void inject(UserActivity activity);

}
