package io.github.howiezuo.unsplash.util;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class ActivityUtils {

    public static void addFragmentToActivity(@NonNull FragmentManager manager, @NonNull Fragment fragment, @IdRes int id) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(id, fragment);
        transaction.commit();
    }
}
