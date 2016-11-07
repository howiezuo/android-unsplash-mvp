package io.github.howiezuo.unsplash.database.dao;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.github.howiezuo.unsplash.model.entity.Me;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

public class MeDao {

    @Inject
    RealmConfiguration mConfiguration;

    @Inject
    public MeDao() {

    }

    public void createOrUpdate(final UserDto dto) {
        Realm realm = Realm.getInstance(mConfiguration);
        final Me me = find();

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (me == null) {
                    Me newMe = new Me(dto);
                    realm.copyToRealm(newMe);
                } else {
                    me.setDto(dto);
                }
            }
        });
    }

    public Me find() {
        Realm realm = Realm.getInstance(mConfiguration);
        RealmQuery<Me> query = realm.where(Me.class);
        Me result = query.findFirst();
        realm.close();
        return result;
    }
}
