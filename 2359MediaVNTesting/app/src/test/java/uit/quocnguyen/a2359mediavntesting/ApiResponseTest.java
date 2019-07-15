package uit.quocnguyen.a2359mediavntesting;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import uit.quocnguyen.a2359mediavntesting.commons.ApiResponse;
import uit.quocnguyen.a2359mediavntesting.commons.Status;

@RunWith(JUnit4.class)
public class ApiResponseTest {
    public static final String error = "something when wrong";

    @Test
    public void exception() {
        Exception exception = new Exception(error);
        ApiResponse apiResponse = new ApiResponse(Status.ERROR, null, exception);
        Assert.assertEquals(apiResponse.error.getMessage(), error);
        Assert.assertNull(apiResponse.data);
        Assert.assertEquals(apiResponse.status, Status.ERROR);
    }

    @Test
    public void success() {
        ApiResponse apiResponse = new ApiResponse(Status.SUCCESS, new Object(), null);
        Assert.assertNull(apiResponse.error);
        Assert.assertNotNull(apiResponse.data);
        Assert.assertEquals(apiResponse.status, Status.SUCCESS);
    }
}
