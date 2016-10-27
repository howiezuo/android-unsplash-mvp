package io.github.howiezuo.unsplash.login;

import dagger.Component;
import io.github.howiezuo.unsplash.api.ApiComponent;
import io.github.howiezuo.unsplash.base.BaseActivity;
import io.github.howiezuo.unsplash.helper.HelperComponent;
import io.github.howiezuo.unsplash.util.FragmentScoped;

@FragmentScoped
@Component(
        dependencies = {
                ApiComponent.class,
                HelperComponent.class
        },
        modules = {
                LoginPresenterModule.class
        }
)
public interface LoginComponent {

    void inject(BaseActivity activity);

}
