package uit.quocnguyen.a2359mediavntesting.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NowPlayingResponses {
    @SerializedName("page")
    public int page;
    @SerializedName("results")
    public List<NowPlayingItem> nowPlayingItems;

    @SerializedName("total_pages")
    public int total_pages;
}
