package io.github.howiezuo.unsplash.database;

import dagger.Subcomponent;
import io.realm.RealmConfiguration;

@Subcomponent(
        modules = DatabaseModule.class
)
public interface DatabaseComponent {

    RealmConfiguration realmConfiguration();

    @Subcomponent.Builder
    interface Builder {
        Builder requestModule(DatabaseModule module);
        DatabaseComponent build();
    }
}
