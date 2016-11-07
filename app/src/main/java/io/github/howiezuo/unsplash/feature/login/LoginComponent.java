package io.github.howiezuo.unsplash.feature.login;

import dagger.Component;
import io.github.howiezuo.unsplash.api.ApiComponent;
import io.github.howiezuo.unsplash.database.DatabaseComponent;
import io.github.howiezuo.unsplash.feature.base.BaseActivity;
import io.github.howiezuo.unsplash.helper.HelperComponent;
import io.github.howiezuo.unsplash.util.FragmentScoped;

@FragmentScoped
@Component(
        dependencies = {
                DatabaseComponent.class,
                HelperComponent.class,
                ApiComponent.class,
        },
        modules = {
                LoginPresenterModule.class
        }
)
public interface LoginComponent {

    void inject(BaseActivity activity);

}
