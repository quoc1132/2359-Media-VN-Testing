package uit.quocnguyen.a2359mediavntesting.di;

import javax.inject.Singleton;

import dagger.Component;
import uit.quocnguyen.a2359mediavntesting.MainActivity;

@Component(modules = {NowPlayingModule.class})
@Singleton
public interface AppComponent {
    void doInjection(MainActivity mainActivity);
}
