package howiezuo.github.io.unsplash.api;

import javax.inject.Singleton;

import dagger.Component;
import howiezuo.github.io.unsplash.main.MainActivity;

@Singleton
@Component(modules = ApiModule.class)
public interface ApiComponent {

    void inject(MainActivity activity);

}
