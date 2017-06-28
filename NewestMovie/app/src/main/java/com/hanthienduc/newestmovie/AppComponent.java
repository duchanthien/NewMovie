package com.hanthienduc.newestmovie;


import com.hanthienduc.newestmovie.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        AppModule.class,
        NetworkModule.class
})
public interface AppComponent {

}
