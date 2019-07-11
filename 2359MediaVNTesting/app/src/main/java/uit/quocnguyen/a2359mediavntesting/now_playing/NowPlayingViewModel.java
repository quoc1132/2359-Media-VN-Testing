package uit.quocnguyen.a2359mediavntesting.now_playing;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import uit.quocnguyen.a2359mediavntesting.commons.ApiResponse;
import uit.quocnguyen.a2359mediavntesting.commons.Constant;

public class NowPlayingViewModel extends ViewModel {
    private NowPlayingRepository nowPlayingRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();


    public NowPlayingViewModel(NowPlayingRepository nowPlayingRepository) {
        this.nowPlayingRepository = nowPlayingRepository;
    }

    public MutableLiveData<ApiResponse> getNowPlayingResponse() {
        return responseLiveData;
    }


    public void getNowPlayingApi(int page) {
        disposables.add(nowPlayingRepository.executeGetNowPlaying(Constant.apiKey, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));

    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}
