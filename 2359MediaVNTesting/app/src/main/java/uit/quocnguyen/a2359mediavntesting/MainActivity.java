package uit.quocnguyen.a2359mediavntesting;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonElement;

import javax.inject.Inject;

import uit.quocnguyen.a2359mediavntesting.commons.ApiResponse;
import uit.quocnguyen.a2359mediavntesting.commons.Utils;
import uit.quocnguyen.a2359mediavntesting.now_playing.NowPlayingRepository;
import uit.quocnguyen.a2359mediavntesting.now_playing.NowPlayingViewModel;
import uit.quocnguyen.a2359mediavntesting.now_playing.NowPlayingViewModelFactory;

import static uit.quocnguyen.a2359mediavntesting.commons.Status.*;

public class MainActivity extends AppCompatActivity {

    @Inject
    NowPlayingViewModelFactory nowPlayingViewModelFactory;

    NowPlayingViewModel nowPlayingViewModel;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((CoreApplication) getApplication()).getAppComponent().doInjection(this);

        progressDialog = Utils.getProgressDialog(this, "Data is Loading... ");
        nowPlayingViewModel = ViewModelProviders.of(this, nowPlayingViewModelFactory).get(NowPlayingViewModel.class);

        nowPlayingViewModel.getNowPlayingResponse().observe(this, this::consumeResponse);

        onLoginClicked();
    }

    void onLoginClicked() {
        if (!Utils.checkInternetConnection(this)) {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.network_error), Toast.LENGTH_SHORT).show();
        } else {
            nowPlayingViewModel.getNowPlayingApi("SKLSDLSDLSDHSH", 1);
        }

    }

    /*
     * method to handle response
     * */
    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                progressDialog.show();
                break;

            case SUCCESS:
                progressDialog.dismiss();
                renderSuccessResponse(apiResponse.data);
                break;

            case ERROR:
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }
    }

    /*
     * method to handle success response
     * */
    private void renderSuccessResponse(JsonElement response) {
        if (!response.isJsonNull()) {
            Log.d("response=", response.toString());
        } else {
            Toast.makeText(MainActivity.this, getResources().getString(R.string.errorString), Toast.LENGTH_SHORT).show();
        }
    }
}
