package br.com.supero.hithublist.ui.repository.list;

import java.util.List;

import javax.inject.Inject;

import br.com.supero.hithublist.data.DataManager;
import br.com.supero.hithublist.data.model.Repository;
import br.com.supero.hithublist.injection.ConfigPersistent;
import br.com.supero.hithublist.ui.base.BasePresenter;
import br.com.supero.hithublist.util.RxUtil;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

@ConfigPersistent
public class RepositoryPresenter extends BasePresenter<RepositoriesView> {

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public RepositoryPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(RepositoriesView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadRepositories() {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getRepositories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the repositorys.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Repository> repositorys) {
                        if (repositorys.isEmpty()) {
                            getMvpView().showRepositoriesEmpty();
                        } else {
                            getMvpView().showRepositories(repositorys);
                        }
                    }
                });
    }
}
