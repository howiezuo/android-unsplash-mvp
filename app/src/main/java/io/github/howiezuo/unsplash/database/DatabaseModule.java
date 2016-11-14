package io.github.howiezuo.unsplash.database;

import dagger.Module;
import dagger.Provides;
import io.github.howiezuo.unsplash.app.Constants;
import io.realm.RealmConfiguration;

@Module
public class DatabaseModule {

    @Provides
    RealmConfiguration provideRealmConfiguration() {
        return new RealmConfiguration.Builder()
                .name(Constants.REALM_DATABASE_NAME)
                .schemaVersion(Constants.REALM_SCHEMA_VERSION)
                .build();
    }

}
