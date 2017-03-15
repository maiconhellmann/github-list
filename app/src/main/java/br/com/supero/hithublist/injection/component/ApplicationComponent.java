package br.com.supero.hithublist.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import br.com.supero.hithublist.data.DataManager;
import br.com.supero.hithublist.data.remote.GithubService;
import br.com.supero.hithublist.injection.ApplicationContext;
import br.com.supero.hithublist.injection.module.ApplicationModule;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    @ApplicationContext Context context();
    Application application();
    GithubService githubService();
    DataManager dataManager();

}
