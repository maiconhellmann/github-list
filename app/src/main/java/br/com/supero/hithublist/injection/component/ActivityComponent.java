package br.com.supero.hithublist.injection.component;

import br.com.supero.hithublist.ui.repository.detail.RepositoryTabActivity;
import dagger.Subcomponent;
import br.com.supero.hithublist.injection.PerActivity;
import br.com.supero.hithublist.injection.module.ActivityModule;
import br.com.supero.hithublist.ui.repository.list.RepositoryActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(RepositoryActivity mainActivity);
    void inject(RepositoryTabActivity mainActivity);
}
