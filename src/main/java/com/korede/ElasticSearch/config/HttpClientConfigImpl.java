package com.korede.ElasticSearch.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;

import org.elasticsearch.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import java.io.File;

@Configuration
public class HttpClientConfigImpl implements RestClientBuilder.HttpClientConfigCallback {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientConfigImpl.class);

    @Override
    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {

        try {

            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("elastic", "korede");
            credentialsProvider.setCredentials(AuthScope.ANY, usernamePasswordCredentials);
            httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);

            String trustLocationStore = "C:\\Program Files\\elasticsearch-8.15.3\\config\\certs\\truststore.p12";
            File trustLocationFile = new File(trustLocationStore);

            if (!trustLocationFile.exists()) {
                logger.error("Truststore file not found at: {}", trustLocationStore);
                throw new RuntimeException("Truststore file not found");
            }

            SSLContextBuilder sslContextBuilder = SSLContexts.custom()
                    .loadTrustMaterial(trustLocationFile, "korede".toCharArray());

            SSLContext sslContext = sslContextBuilder.build();
            httpAsyncClientBuilder.setSSLContext(sslContext);

            logger.info("SSL context successfully configured with truststore: {}", trustLocationStore);

        } catch (Exception e) {
            logger.error("Error configuring HttpAsyncClientBuilder", e);
            throw new RuntimeException("Failed to configure HttpAsyncClientBuilder", e);
        }

        return httpAsyncClientBuilder;
    }
}