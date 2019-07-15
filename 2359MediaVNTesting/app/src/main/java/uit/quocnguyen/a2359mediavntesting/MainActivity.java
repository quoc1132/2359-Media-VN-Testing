package uit.quocnguyen.a2359mediavntesting;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import uit.quocnguyen.a2359mediavntesting.adapters.NowPlayingAdapter;
import uit.quocnguyen.a2359mediavntesting.commons.ApiResponse;
import uit.quocnguyen.a2359mediavntesting.commons.Constant;
import uit.quocnguyen.a2359mediavntesting.interfaces.INowPlayerView;
import uit.quocnguyen.a2359mediavntesting.listeners.PaginationScrollListener;
import uit.quocnguyen.a2359mediavntesting.utils.Utils;
import uit.quocnguyen.a2359mediavntesting.models.NowPlayingItem;
import uit.quocnguyen.a2359mediavntesting.models.NowPlayingResponses;
import uit.quocnguyen.a2359mediavntesting.now_playing.NowPlayingViewModel;
import uit.quocnguyen.a2359mediavntesting.now_playing.NowPlayingViewModelFactory;
import uit.quocnguyen.a2359mediavntesting.utils.GridSpacingItemDecoration;

public class MainActivity extends AppCompatActivity implements NowPlayingAdapter.NowPlayingAdapterListener, INowPlayerView {

    @Inject
    NowPlayingViewModelFactory nowPlayingViewModelFactory;

    NowPlayingViewModel nowPlayingViewModel;

    public ProgressDialog progressDialog;
    private List<NowPlayingItem> nowPlayingItems;
    private NowPlayingAdapter mAdapter;
    private RecyclerView recyclerView;
    private PaginationScrollListener paginationScrollListener;
    private GridLayoutManager gridLayoutManager;
    private int mPage = 1;
    private FrameLayout flProgressLoadMore;

    private boolean isLoading = true;
    private int mTotalPage = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initRecyclerView();

        ((CoreApplication) getApplication()).getAppComponent().doInjection(this);

        progressDialog = Utils.getProgressDialog(this, "Data is Loading... ");
        nowPlayingViewModel = ViewModelProviders.of(this, nowPlayingViewModelFactory).get(NowPlayingViewModel.class);
        nowPlayingViewModel.getNowPlayingResponse().observe(this, this::onHandleDataResponse);

        onLoadData(mPage);

    }

    @Override
    public void onLoadData(int page) {
        if (!isCheckInternet(getApplicationContext())) {
            onMakeToast(R.string.network_error);
        } else {
            nowPlayingViewModel.getNowPlayingApi(page);
        }

    }

    @Override
    public void onMakeToast(int stringID) {
        Toast.makeText(getApplicationContext(), getResources().getString(stringID), Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isCheckInternet(Context context) {
        return Utils.checkInternetConnection(context);
    }

    private void onHandleDataResponse(ApiResponse apiResponse) {
        switch (apiResponse.status) {
            case LOADING:
                if (mPage == 1) {
                    progressDialog.show();
                } else
                    onShowProgressLoadMore(true);

                break;

            case SUCCESS:
                if (progressDialog.isShowing())
                    progressDialog.dismiss();

                onShowProgressLoadMore(false);
                onUpdateUINowPlayingList((NowPlayingResponses) apiResponse.data);
                break;

            case ERROR:
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                onShowProgressLoadMore(false);
                Toast.makeText(MainActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }


    private void onUpdateUINowPlayingList(NowPlayingResponses response) {
        if (response != null) {
            Log.d("response=", response.toString());
            mTotalPage = response.total_pages;
            nowPlayingItems.addAll(response.nowPlayingItems);
            mAdapter.notifyItemRangeInserted(nowPlayingItems.size(), response.nowPlayingItems.size());
        } else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }


    private void initRecyclerView() {
        nowPlayingItems = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        flProgressLoadMore = findViewById(R.id.flProgressLoadMore);
        gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        paginationScrollListener = new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                ++mPage;
                onLoadData(mPage);
            }

            @Override
            public boolean isLastPage() {
                return mPage == mTotalPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        };
        recyclerView.addOnScrollListener(paginationScrollListener);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, Utils.dpToPx(4, getApplicationContext()), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        mAdapter = new NowPlayingAdapter(nowPlayingItems, this);
        recyclerView.setAdapter(mAdapter);
    }

    private void onShowProgressLoadMore(boolean isShow) {
        isLoading = isShow;
        flProgressLoadMore.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onPostClicked(NowPlayingItem nowPlayingItem) {

    }
}
