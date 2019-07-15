package uit.quocnguyen.a2359mediavntesting;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import uit.quocnguyen.a2359mediavntesting.now_playing.NowPlayingViewModel;

@RunWith(MockitoJUnitRunner.class)
public class NowPlayingViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    NowPlayingViewModel nowPlayingViewModel;
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        nowPlayingViewModel = Mockito.mock(NowPlayingViewModel.class);
    }


}
