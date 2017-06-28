package com.hanthienduc.newestmovie.util;

import rx.Subscription;

public class RxUtils {
    public static void unsubscribe(Subscription subscription) {
        if (subscription == null) {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            } else {
                // Already unsubscribed
            }
        } else {
            // Subscription doeasn't exist
        }
    }

    public static void unsubscribe(Subscription... subscriptions) {
        for (Subscription subscription : subscriptions) {
            if (subscription != null) {
                if (!subscription.isUnsubscribed()) {
                    subscription.unsubscribe();
                } else {
                    // Already unsubscribed
                }
            } else {
                // Subscription doeasn't exist
            }
        }
    }
}
