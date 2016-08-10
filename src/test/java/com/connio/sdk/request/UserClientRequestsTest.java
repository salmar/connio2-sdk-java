package com.connio.sdk.request;

import com.connio.sdk.Connio;
import com.connio.sdk.ConnioApiClient;
import com.connio.sdk.ConnioApiClientImpl;
import com.connio.sdk.http.JerseyClient;
import com.connio.sdk.http.Request;
import com.connio.sdk.http.Response;
import com.connio.sdk.request.apiclient.ApiClientAddRequest;
import com.connio.sdk.request.apiclient.ApiClientDeleteRequest;
import com.connio.sdk.request.apiclient.ApiClientUpdateRequest;
import com.connio.sdk.request.user.UserDeleteRequest;
import com.connio.sdk.request.user.UserInviteRequest;
import com.connio.sdk.request.user.UserUpdateRequest;
import com.connio.sdk.resource.apiclient.ApiClient;
import com.connio.sdk.resource.user.User;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import mockit.Expectations;
import mockit.Mocked;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

public class UserClientRequestsTest {

    @Mocked
    private JerseyClient httpClient;

    @Mocked
    private Response response;

    private final User user = new User("_usr_125632587452159687", "test@gmail.com", "password", "sadfasgt",
            User.Status.Confirmed, ImmutableSet.<User.Role>of(User.Role.Admin), "fullName", "_acc_125632541256325896",
            ImmutableMap.<String, Object>of(), null, null, null, DateTime.now(), null);
    @Before
    public void setUp() throws Exception {
        Connio.init("key", "password");
    }

    @Test
    public void addRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final UserInviteRequest addRequest = new UserInviteRequest(user.getEmail(), user.getFullName())
                    .setLang(user.getLang().orElse(""))
                    .setRoles(user.getRoles())
                    .setTimezone(user.getTimezone().orElse(""));

            final Request request = Request.post("users/invite", addRequest);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final UserInviteRequest request = User.invite(user.getEmail(), user.getFullName())
                .setLang(user.getLang().orElse(""))
                .setRoles(user.getRoles())
                .setTimezone(user.getTimezone().orElse(""));

            request.execute();
            request.executeAsync();

            final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");
            request.execute(apiClient);
            request.executeAsync(apiClient);
    }

    @Test
    public void updateRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final UserUpdateRequest updateRequest = new UserUpdateRequest(user)
                    .setLang(user.getLang().orElse(""))
                    .setRoles(user.getRoles())
                    .setTimezone(user.getTimezone().orElse(""))
                    .setAvatarUri(user.getAvatarUri().orElse(""))
                    .setFullName(user.getFullName())
                    .setLang(user.getLang().orElse(""))
                    .setPassword(user.getPassword())
                    .setPrefs(user.getPrefs())
                    .setRoles(user.getRoles())
                    .setTimezone(user.getTimezone().orElse(""));

            final String path = "users/" + user.getId();
            final Request request = Request.put(path, updateRequest);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final UserUpdateRequest request = user.update()
                .setLang(user.getLang().orElse(""))
                .setRoles(user.getRoles())
                .setTimezone(user.getTimezone().orElse(""))
                .setAvatarUri(user.getAvatarUri().orElse(""))
                .setFullName(user.getFullName())
                .setLang(user.getLang().orElse(""))
                .setPassword(user.getPassword())
                .setPrefs(user.getPrefs())
                .setRoles(user.getRoles())
                .setTimezone(user.getTimezone().orElse(""));

        request.execute();
        request.executeAsync();

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");
        request.execute(apiClient);
        request.executeAsync(apiClient);
    }

    @Test
    public void deleteRequestShouldPerformExpectedRequest() throws Exception {
        new Expectations() {{
            final String path = "users/" + user.getId();
            final Request request = Request.delete(path);
            response.isSuccess(); result = true;
            httpClient.request(request); times = 2;
            httpClient.requestAsync(request); result = CompletableFuture.supplyAsync(() -> response); times = 2;
        }};

        final UserDeleteRequest request = user.delete();

        request.execute();
        request.executeAsync();

        final ConnioApiClient apiClient = new ConnioApiClientImpl("key", "secret");
        request.execute(apiClient);
        request.executeAsync(apiClient);
    }
}