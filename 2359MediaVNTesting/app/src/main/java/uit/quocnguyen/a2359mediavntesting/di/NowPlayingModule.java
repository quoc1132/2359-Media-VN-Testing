package uit.quocnguyen.a2359mediavntesting.di;

import android.arch.lifecycle.ViewModelProvider;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uit.quocnguyen.a2359mediavntesting.commons.NetworkUtils;
import uit.quocnguyen.a2359mediavntesting.commons.Urls;
import uit.quocnguyen.a2359mediavntesting.now_playing.NowPlayingApiCallInterface;
import uit.quocnguyen.a2359mediavntesting.now_playing.NowPlayingRepository;
import uit.quocnguyen.a2359mediavntesting.now_playing.NowPlayingViewModelFactory;

@Module
public class NowPlayingModule {

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return NetworkUtils.createRetrofit();
    }

    @Provides
    @Singleton
    NowPlayingApiCallInterface getApiCallInterface(Retrofit retrofit) {
        return retrofit.create(NowPlayingApiCallInterface.class);
    }


    @Provides
    @Singleton
    NowPlayingRepository getNowPlayingRepository(NowPlayingApiCallInterface apiCallInterface) {
        return new NowPlayingRepository(apiCallInterface);
    }

    @Provides
    @Singleton
    ViewModelProvider.Factory getNowPlayingViewModelFactory(NowPlayingRepository myRepository) {
        return new NowPlayingViewModelFactory(myRepository);
    }
}

