package uit.quocnguyen.a2359mediavntesting.interfaces;

import android.content.Context;

public interface INowPlayerView {
    void onLoadData(int page);

    void onMakeToast(int stringID);

    boolean isCheckInternet(Context context);

}
