package br.com.supero.hithublist.tests.ui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import br.com.supero.hithublist.data.DataManager;
import br.com.supero.hithublist.data.model.Repository;
import br.com.supero.hithublist.ui.repository.list.RepositoriesView;
import br.com.supero.hithublist.ui.repository.list.RepositoryPresenter;
import rx.Observable;
import rx.Scheduler;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

public class RepositoryPresenterTest {

    private RepositoryPresenter presenter;
    private DataManager dataManager;
    private RepositoriesView view;

    @Before
    public void setup(){
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });

        view = Mockito.mock(RepositoriesView.class);
        dataManager = Mockito.mock(DataManager.class);
        presenter = new RepositoryPresenter(dataManager);

        presenter.attachView(view);

    }

    @Test
    public void shouldShowRepositoryList(){
        presenter.loadRepositories();

        Repository repository = new Repository();
        repository.setId(1);
        repository.setName("Repository 1");
        repository.setDescription("A sample project");
        repository.setFullName("Repository 1");
        List<Repository> repositories = new ArrayList<>();
        repositories.add(repository);

        final Observable repObs = Observable.from(repositories);

        Mockito.when(dataManager.getRepositories()).then(new Answer<Observable<List<Repository>>>() {
            @Override
            public Observable<List<Repository>> answer(InvocationOnMock invocation) throws Throwable {
                return repObs;
            }
        });

        Mockito.verify(view).showRepositories(repositories);

        Mockito.verifyNoMoreInteractions(view);
    }
}
