package br.com.supero.hithublist.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import br.com.supero.hithublist.data.model.Pull;
import br.com.supero.hithublist.data.model.Repository;
import br.com.supero.hithublist.data.remote.GithubService;
import rx.Observable;

@Singleton
public class DataManager {

    private final GithubService mGithubService;

    @Inject
    public DataManager(GithubService githubService) {
        mGithubService = githubService;
    }

    public Observable<List<Repository>> getRepositories() {
        return mGithubService.getRepositories();
    }

    public Observable<List<Pull>> getPulls(String owner, String repository) {
        return mGithubService.getPulls(owner, repository);
    }
}
