package com.nissens.dagger;

import com.nissens.module.model.AdjustCarModelImpl;
import com.nissens.module.model.CarSingleModelImpl;
import com.nissens.module.model.CarXmlModelImpl;
import com.nissens.module.model.OrganizationModelImpl;
import com.nissens.module.model.SearchByTypeModelImpl;
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
void inject(AdjustCarModelImpl baseModel);
void inject(CarSingleModelImpl carSingleModel);
void inject(SearchByTypeModelImpl searchByTypeModel);
void inject(OrganizationModelImpl organizationModel);
    void inject(CarXmlModelImpl searchByCarModel);

    final class NissensInitialize{
        public static NissensComponent init(){
            return DaggerNissensComponent.builder().build();
        }
    }
}
