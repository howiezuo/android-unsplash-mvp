package io.github.howiezuo.unsplash.api;

import javax.inject.Singleton;

import dagger.Component;
import io.github.howiezuo.unsplash.api.service.MeService;
import io.github.howiezuo.unsplash.api.service.OAuthService;
import io.github.howiezuo.unsplash.api.service.PhotosService;

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {

    PhotosService photosService();

    OAuthService oauthService();

    MeService meService();

}
