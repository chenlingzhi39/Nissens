package com.nissens.dagger;

import com.nissens.module.model.StraightSearchModelImpl;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by PC-20160514 on 2016/9/18.
 */
@Singleton
@Component(modules = NissensModules.class)
public interface NissensComponent {
void inject(StraightSearchModelImpl straightSearchModel);
    final class NissensInitialize{
        public static NissensComponent init(){
            return DaggerNissensComponent.builder().build();
        }
    }
}
