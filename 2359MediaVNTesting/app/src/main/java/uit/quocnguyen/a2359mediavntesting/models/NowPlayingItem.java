package uit.quocnguyen.a2359mediavntesting.models;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import uit.quocnguyen.a2359mediavntesting.commons.Constant;
import uit.quocnguyen.a2359mediavntesting.commons.Urls;

public class NowPlayingItem {
    @SerializedName("poster_path")
    private String poster_path;
    @SerializedName("vote_average")
    private String vote_average;


    @BindingAdapter("poster_path")
    public static void loadImage(ImageView view, String imageUrl) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(false);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(view.getContext())
                .setDefaultRequestOptions(requestOptions)
                .load(Urls.THUMB_URL_ROOT + imageUrl)
                .into(view);
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }
}
