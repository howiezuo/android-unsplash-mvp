package io.github.howiezuo.unsplash.database.dao;

import com.orhanobut.logger.Logger;

import javax.inject.Inject;

import io.github.howiezuo.unsplash.model.dto.UserDto;
import io.github.howiezuo.unsplash.model.entity.Me;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import rx.Observable;
import rx.functions.Func1;

public class MeDao {

    @Inject
    RealmConfiguration mConfiguration;

    @Inject
    public MeDao() {

    }

    public Observable<Me> createOrUpdate(final UserDto dto) {
        return find()
                .concatMap(new Func1<Me, Observable<? extends Me>>() {
                    @Override
                    public Observable<? extends Me> call(Me me) {
                        Observable<Me> result;
                        Logger.d(me);
                        Realm realm = Realm.getInstance(mConfiguration);
                        realm.beginTransaction();
                        if (me.isValid()) {
                            me.setDto(dto);
                            result = Observable.just(me);
                        } else {
                            me = new Me(dto);
                            result = realm.copyToRealm(me).<Me>asObservable().filter(
                                    new Func1<RealmObject, Boolean>() {
                                        @Override
                                        public Boolean call(RealmObject realmObject) {
                                            return realmObject.isLoaded();
                                        }
                                    }
                            );
                        }
                        realm.commitTransaction();
                        return result;
                    }
                });
    }

    public Observable<Me> find() {
        Realm realm = Realm.getInstance(mConfiguration);
        return realm.where(Me.class).findFirstAsync().<Me>asObservable()
                .filter(new Func1<Me, Boolean>() {
                    @Override
                    public Boolean call(Me me) {
                        return me.isLoaded();
                    }
                })
                .first();
    }

    public boolean isEmpty() {
        Realm realm = Realm.getInstance(mConfiguration);
        RealmQuery<Me> query = realm.where(Me.class);
        return query.count() == 0;
    }
}
