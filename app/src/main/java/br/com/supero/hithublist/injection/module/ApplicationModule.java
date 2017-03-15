package br.com.supero.hithublist.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.supero.hithublist.data.remote.GithubService;
import dagger.Module;
import dagger.Provides;
import br.com.supero.hithublist.injection.ApplicationContext;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    GithubService provideRibotsService() {
        return GithubService.Creator.newGithubService();
    }

}
