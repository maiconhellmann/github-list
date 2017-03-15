package br.com.supero.hithublist;

import android.app.Application;
import android.content.Context;

import br.com.supero.hithublist.injection.component.ApplicationComponent;
import br.com.supero.hithublist.injection.component.DaggerApplicationComponent;
import br.com.supero.hithublist.injection.module.ApplicationModule;
import timber.log.Timber;

public class HitHubListApplication extends Application  {

    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
            //Não utilizarei no projeto
//            Fabric.with(this, new Crashlytics());
        }
    }

    public static HitHubListApplication get(Context context) {
        return (HitHubListApplication) context.getApplicationContext();
    }

    public ApplicationComponent getComponent() {
        if (mApplicationComponent == null) {
            mApplicationComponent = DaggerApplicationComponent.builder()
                    .applicationModule(new ApplicationModule(this))
                    .build();
        }
        return mApplicationComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
