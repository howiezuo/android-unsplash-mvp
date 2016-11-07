package io.github.howiezuo.unsplash.database;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.Config;
import io.realm.RealmConfiguration;

@Module
public class DatabaseModule {

    @Provides
    RealmConfiguration provideRealmConfiguration() {
        return new RealmConfiguration.Builder()
                .name(Config.REALM_DATABASE_NAME)
                .schemaVersion(Config.REALM_SCHEMA_VERSION)
                .build();
    }

}
