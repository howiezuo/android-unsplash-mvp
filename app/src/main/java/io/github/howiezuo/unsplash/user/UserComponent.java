package io.github.howiezuo.unsplash.user;

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
                UserPresenterModule.class,
                LoginPresenterModule.class
        }
)
public interface UserComponent {

    void inject(UserActivity activity);

}
