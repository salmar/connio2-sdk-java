package com.connio.sdk.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.commons.codec.net.URLCodec;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.client.rx.Rx;
import org.glassfish.jersey.client.rx.RxClient;
import org.glassfish.jersey.client.rx.RxInvocationBuilder;
import org.glassfish.jersey.client.rx.RxWebTarget;
import org.glassfish.jersey.client.rx.java8.RxCompletionStageInvoker;
import org.glassfish.jersey.filter.LoggingFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public class JerseyClient implements HttpClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(JerseyClient.class);

    private final RxClient<RxCompletionStageInvoker> client;

    private final RxWebTarget<RxCompletionStageInvoker> target;

    public JerseyClient(String user, String password, String baseUrl, ExecutorService executor) throws Exception {
        final Client client = buildClient(user, password);
        this.client = Rx.from(client, RxCompletionStageInvoker.class, executor);
        this.target = this.client.target(baseUrl);
    }

    @Override
    public Response request(Request request) {
        RxInvocationBuilder<RxCompletionStageInvoker> requestBuilder = buildRequest(request);

        switch (request.getMethod()) {
            case GET:
                return new ResponseAdapter(requestBuilder.get());

            case POST:
                return new ResponseAdapter(requestBuilder.post(Entity.json(request.getBody())));

            case PUT:
                return new ResponseAdapter(requestBuilder.put(Entity.json(request.getBody())));

            case DELETE:
                return new ResponseAdapter(requestBuilder.delete());

            default:
                throw new RuntimeException("Invalid request");
        }
    }

    @Override
    public CompletableFuture<Response> requestAsync(Request request) {
        final RxInvocationBuilder<RxCompletionStageInvoker> requestBuilder = buildRequest(request);

        switch (request.getMethod()) {
            case GET:
                return requestBuilder.rx().get()
                        .thenApply(response -> {
                            return (Response) new ResponseAdapter(response);
                        }).toCompletableFuture();

            case POST:
                return requestBuilder.rx().post(Entity.json(request.getBody()))
                        .thenApply(response -> {
                            return (Response) new ResponseAdapter(response);
                        }).toCompletableFuture();

            case PUT:
                return requestBuilder.rx().put(Entity.json(request.getBody()))
                        .thenApply(response -> {
                            return (Response) new ResponseAdapter(response);
                        }).toCompletableFuture();

            case DELETE:
                return requestBuilder.rx().delete()
                        .thenApply(response -> {
                            return (Response) new ResponseAdapter(response);
                        }).toCompletableFuture();

            default:
                throw new RuntimeException("Invalid request");
        }
    }


    private Client buildClient(String key, String secret) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslcontext = SSLContext.getInstance("TLS");
        sslcontext.init(null, new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

        }}, new java.security.SecureRandom());

        // Configure jackson marshaller
        final ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new GuavaModule());
        mapper.registerModule(new JodaModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        jsonProvider.setMapper(mapper);

        // Basic auth feature
        HttpAuthenticationFeature auth = HttpAuthenticationFeature.basic(key, secret);

        return ClientBuilder.newBuilder()
                .sslContext(sslcontext)
                .hostnameVerifier((a, b) -> true)
                .register(jsonProvider)
                .register(auth)
                //.register(new LoggingFilter())
                .build();
    }

    private RxInvocationBuilder<RxCompletionStageInvoker> buildRequest(Request request) {
        RxWebTarget<RxCompletionStageInvoker> webTarget = target.path(request.getPath());

        for (Map.Entry<String, String> entry : request.getQueryParams().entrySet()) {
            try {
                webTarget = webTarget.queryParam(entry.getKey(), URLEncoder.encode(entry.getValue(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("Unsupported encoding UTF-8 query parameters will be discarded", e);
            }
        }

        return webTarget.request(MediaType.APPLICATION_JSON_TYPE);
    }
}
