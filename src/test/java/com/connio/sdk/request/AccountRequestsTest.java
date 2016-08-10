package com.connio.sdk.request;

import com.connio.sdk.Connio;
import com.connio.sdk.ConnioApiClientImpl;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.account.AccountCreateRequest;
import com.connio.sdk.request.account.AccountDeleteRequest;
import com.connio.sdk.request.account.AccountUpdateRequest;
import com.connio.sdk.resource.account.Account;
import com.google.common.collect.ImmutableSet;
import mockit.Expectations;
import mockit.Mocked;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class AccountRequestsTest {

    @Mocked
    private ConnioApiClientImpl connioApiClient;

    @Mocked
    private Response response;

    private final Account account = new Account("_acc_125632547856958745", "account", null, "_apf_125478569325874125",
            "_app_125632547856985478", Account.Status.Active, Account.Type.Full, null, null, null, null, DateTime.now(), null);

    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test
    public void addRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final AccountCreateRequest addRequest = new AccountCreateRequest("account", Account.Type.Full)
                    .setOrgImageUri("image")
                    .setOrgName("company")
                    .setOrgWebsite("www.test.com")
                    .setStatus(Account.Status.Active);

            final Request request = Request.post("accounts", addRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final AccountCreateRequest request = Account.create("account", Account.Type.Full)
                .setOrgImageUri("image")
                .setOrgName("company")
                .setOrgWebsite("www.test.com")
                .setStatus(Account.Status.Active);

            request.execute();
            request.executeAsync();

            request.execute(connioApiClient);
            request.executeAsync(connioApiClient);
    }

    @Test
    public void updateRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final AccountUpdateRequest updateRequest = new AccountUpdateRequest(account)
                    .setName("updated_name")
                    .setOrgImageUri("image")
                    .setOrgName("company")
                    .setOrgWebsite("www.test.com")
                    .setStatus(Account.Status.Active)
                    .setTags(ImmutableSet.<String>of("tag1"))
                    .setType(Account.Type.Trial);

            final String path = "accounts/" + account.getId();
            final Request request = Request.put(path, updateRequest);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final AccountUpdateRequest request = account.update()
                .setName("updated_name")
                .setOrgImageUri("image")
                .setOrgName("company")
                .setOrgWebsite("www.test.com")
                .setStatus(Account.Status.Active)
                .setTags(ImmutableSet.<String>of("tag1"))
                .setType(Account.Type.Trial);

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }

    @Test
    public void deleteRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "accounts/" + account.getId();
            final Request request = Request.delete(path);
            response.isSuccess(); result = true;
            connioApiClient.request(request); times = 2;
            connioApiClient.requestAsync(request); result = CompletableFuture.completedFuture(response); times = 2;
        }};

        final AccountDeleteRequest request = account.delete();

        request.execute();
        request.executeAsync();

        request.execute(connioApiClient);
        request.executeAsync(connioApiClient);
    }
}