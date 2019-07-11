package uit.quocnguyen.a2359mediavntesting.now_playing;

import com.google.gson.JsonElement;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import uit.quocnguyen.a2359mediavntesting.commons.Urls;
import uit.quocnguyen.a2359mediavntesting.models.NowPlayingResponses;

public interface NowPlayingApiCallInterface {
    @GET(Urls.NOW_PLAYING)
    Observable<NowPlayingResponses> login(@Query("api_key") String apiKey, @Query("page") int page);
}
