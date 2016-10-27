package io.github.howiezuo.unsplash.helper;

import dagger.Subcomponent;

@Subcomponent(
        modules = {
                HelperModule.class
        }
)
public interface HelperComponent {

    PreferencesHelper preferencesHelper();

    @Subcomponent.Builder
    interface Builder {
        Builder requestModule(HelperModule module);
        HelperComponent build();
    }

}
