package br.com.supero.hithublist.ui.repository.list;

import java.util.List;

import br.com.supero.hithublist.data.model.Repository;
import br.com.supero.hithublist.ui.base.MvpView;

public interface RepositoriesView extends MvpView {

    void showRepositories(List<Repository> repositories);

    void showRepositoriesEmpty();

    void showError();

}
