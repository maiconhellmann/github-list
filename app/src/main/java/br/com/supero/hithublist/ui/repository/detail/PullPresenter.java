package br.com.supero.hithublist.ui.repository.detail;

import java.util.List;

import javax.inject.Inject;

import br.com.supero.hithublist.data.DataManager;
import br.com.supero.hithublist.data.model.Pull;
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
public class PullPresenter extends BasePresenter<PullView>{

    private final DataManager mDataManager;
    private Subscription mSubscription;

    @Inject
    public PullPresenter(DataManager dataManager) {
        mDataManager = dataManager;
    }

    @Override
    public void attachView(PullView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    public void loadPullRequests(String owner, String repo) {
        checkViewAttached();
        RxUtil.unsubscribe(mSubscription);
        mSubscription = mDataManager.getPulls(owner, repo)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Pull>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "There was an error loading the repositorys.");
                        getMvpView().showError();
                    }

                    @Override
                    public void onNext(List<Pull> pullList) {
                        if (pullList.isEmpty()) {
                            getMvpView().showEmptyPullList();
                        } else {
                            getMvpView().showPullRequests(pullList);
                        }
                    }
                });
    }
}
