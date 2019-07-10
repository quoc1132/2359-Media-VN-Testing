package uit.quocnguyen.a2359mediavntesting;

import android.app.Application;
import android.content.Context;

import uit.quocnguyen.a2359mediavntesting.di.AppComponent;
import uit.quocnguyen.a2359mediavntesting.di.DaggerAppComponent;
import uit.quocnguyen.a2359mediavntesting.di.NowPlayingModule;

public class CoreApplication extends Application {
    AppComponent appComponent;
    Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        appComponent = DaggerAppComponent.builder().nowPlayingModule(new NowPlayingModule()).build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }
}
