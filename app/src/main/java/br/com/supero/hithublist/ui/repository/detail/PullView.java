package br.com.supero.hithublist.ui.repository.detail;

import java.util.List;

import br.com.supero.hithublist.data.model.Pull;
import br.com.supero.hithublist.ui.base.MvpView;

public interface PullView extends MvpView{
    void showError();
    void showPullRequests(List<Pull> pullList);
    void showEmptyPullList();
}
