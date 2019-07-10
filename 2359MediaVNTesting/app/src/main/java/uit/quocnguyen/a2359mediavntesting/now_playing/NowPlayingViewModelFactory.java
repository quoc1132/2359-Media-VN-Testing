package uit.quocnguyen.a2359mediavntesting.now_playing;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import javax.inject.Inject;

public class NowPlayingViewModelFactory implements ViewModelProvider.Factory {

    private NowPlayingRepository nowPlayingRepository;

    @Inject
    public NowPlayingViewModelFactory(NowPlayingRepository nowPlayingRepository) {
        this.nowPlayingRepository = nowPlayingRepository;
    }


    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NowPlayingViewModel.class)) {
            return (T) new NowPlayingViewModel(nowPlayingRepository);
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}