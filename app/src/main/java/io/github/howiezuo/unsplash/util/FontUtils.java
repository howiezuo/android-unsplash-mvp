package io.github.howiezuo.unsplash.util;

import android.content.Context;
import android.graphics.Typeface;

import java.util.Hashtable;

public class FontUtils {

    private static Hashtable<String, Typeface> cache = new Hashtable<>();

    public static Typeface get(String path, Context context) {
        Typeface tf = cache.get(path);
        if (tf == null) {
            try {
                tf = Typeface.createFromAsset(context.getAssets(), path);
                cache.put(path, tf);
            } catch (Exception e) {
                return null;
            }
        }
        return cache.get(path);
    }
}
