package br.com.supero.hithublist.injection.component;

import br.com.supero.hithublist.ui.repository.detail.PullListFragment;
import br.com.supero.hithublist.ui.repository.detail.RepositoryDetailFragment;
import dagger.Component;
import br.com.supero.hithublist.injection.ConfigPersistent;
import br.com.supero.hithublist.injection.module.ActivityModule;
import br.com.supero.hithublist.ui.base.BaseActivity;

/**
 * A dagger component that will live during the lifecycle of an Activity but it won't
 * be destroy during configuration changes. Check {@link BaseActivity} to see how this components
 * survives configuration changes.
 * Use the {@link ConfigPersistent} scope to annotate dependencies that need to survive
 * configuration changes (for example Presenters).
 */
@ConfigPersistent
@Component(dependencies = ApplicationComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

    void inject(RepositoryDetailFragment repositoryDetailFragment);

    void inject(PullListFragment pullListFragment);
}