package uit.quocnguyen.a2359mediavntesting.now_playing;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import uit.quocnguyen.a2359mediavntesting.models.NowPlayingResponses;

public class NowPlayingRepository {
    private NowPlayingApiCallInterface nowPlayingApiCallInterface;

    public NowPlayingRepository(NowPlayingApiCallInterface nowPlayingApiCallInterface) {
        this.nowPlayingApiCallInterface = nowPlayingApiCallInterface;
    }


    public Observable<NowPlayingResponses> executeGetNowPlaying(String apiKey, int page) {
        return nowPlayingApiCallInterface.login(apiKey, page);
    }
}
