package howiezuo.github.io.unsplash.main;

import dagger.Component;
import howiezuo.github.io.unsplash.api.ApiComponent;
import howiezuo.github.io.unsplash.util.FragmentScoped;

@FragmentScoped
@Component(
        dependencies = {
                ApiComponent.class
        },
        modules = {
                MainPresenterModule.class
        }
)
public interface MainComponent {

    void inject(MainActivity activity);

}
