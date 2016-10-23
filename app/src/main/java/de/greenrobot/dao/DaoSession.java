package de.greenrobot.dao;

import android.database.sqlite.SQLiteDatabase;

import java.util.Map;

import de.greenrobot.dao.identityscope.IdentityScopeType;
import de.greenrobot.dao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see de.greenrobot.dao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig quickSearchDaoConfig;

    private final QuickSearchDao quickSearchDao;

    public DaoSession(SQLiteDatabase db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        quickSearchDaoConfig = daoConfigMap.get(QuickSearchDao.class).clone();
        quickSearchDaoConfig.initIdentityScope(type);

        quickSearchDao = new QuickSearchDao(quickSearchDaoConfig, this);

        registerDao(QuickSearch.class, quickSearchDao);
    }
    
    public void clear() {
        quickSearchDaoConfig.getIdentityScope().clear();
    }

    public QuickSearchDao getQuickSearchDao() {
        return quickSearchDao;
    }

}
