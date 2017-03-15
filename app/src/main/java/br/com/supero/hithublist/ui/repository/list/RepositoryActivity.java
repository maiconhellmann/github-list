package br.com.supero.hithublist.ui.repository.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.supero.hithublist.R;
import br.com.supero.hithublist.data.model.Repository;
import br.com.supero.hithublist.ui.base.BaseActivity;
import br.com.supero.hithublist.ui.repository.detail.RepositoryTabActivity;
import br.com.supero.hithublist.util.DialogFactory;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RepositoryActivity extends BaseActivity implements RepositoriesView, RepositorysAdapter.OnItemClickListener {

    @Inject
    RepositoryPresenter mMainPresenter;

    @Inject RepositorysAdapter mRepositoryAdapter;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;

    @BindView(R.id.toolbar) Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_repository);
        ButterKnife.bind(this);

        mRepositoryAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mRepositoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mMainPresenter.attachView(this);
        mMainPresenter.loadRepositories();

        setSupportActionBar(toolbar);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mMainPresenter.detachView();
    }

    @Override
    public void showRepositories(List<Repository> repositories) {
        mRepositoryAdapter.setRepositories(repositories);
        mRepositoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        DialogFactory.createGenericErrorDialog(this, getString(R.string.error_loading_repositories))
                .show();
    }

    @Override
    public void showRepositoriesEmpty() {
        mRepositoryAdapter.setRepositories(Collections.<Repository>emptyList());
        mRepositoryAdapter.notifyDataSetChanged();
        Toast.makeText(this, R.string.empty_repositories, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(Repository repository) {
        try {
            Intent intent = new Intent(this, RepositoryTabActivity.class);
            intent.putExtra(RepositoryTabActivity.EXTRA_REPOSITORY, repository);
            intent.putExtra(RepositoryTabActivity.EXTRA_OWNER, repository.getOwner().getLogin());

            startActivity(intent);
        } catch (Exception e) {
            DialogFactory.createGenericErrorDialog(this, e).show();
        }
    }
}
