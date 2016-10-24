package io.github.howiezuo.unsplash.user;

import dagger.Component;
import io.github.howiezuo.unsplash.util.FragmentScoped;

@FragmentScoped
@Component(modules = UserPresenterModule.class)
public interface UserComponent {

    void inject(UserActivity activity);

}
