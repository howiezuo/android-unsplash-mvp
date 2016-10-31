package io.github.howiezuo.unsplash.api;

import javax.inject.Singleton;

import dagger.Component;
import io.github.howiezuo.unsplash.api.service.UsersService;
import io.github.howiezuo.unsplash.api.service.OAuthService;
import io.github.howiezuo.unsplash.api.service.PhotosService;
import io.github.howiezuo.unsplash.helper.HelperComponent;

@Singleton
@Component(
        dependencies = HelperComponent.class,
        modules = ApiModule.class
)
public interface ApiComponent {

    PhotosService photosService();

    OAuthService oauthService();

    UsersService usersService();

}
