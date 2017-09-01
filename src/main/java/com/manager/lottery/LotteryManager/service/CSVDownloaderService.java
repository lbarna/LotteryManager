package com.manager.lottery.LotteryManager.service;

import com.manager.lottery.LotteryManager.DownloadFailedException;
import com.manager.lottery.LotteryManager.model.WinnerNumbers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVDownloaderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CSVDownloaderService.class);
    private static final TrustManager[] TRUST_ALL_CERTS;

    @Value("${lottery.latestDataURL}")
    private String lotteryURL;

    static {
        TRUST_ALL_CERTS = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(
                    final java.security.cert.X509Certificate[] certs, final String authType) {
                }

                public void checkServerTrusted(
                    final java.security.cert.X509Certificate[] certs, final String authType) {
                }
            }
        };

        try {
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, TRUST_ALL_CERTS, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        } catch (final Exception e) {
            LOGGER.error("An exception occurred while initializing ssl context.", e);
        }
    }

    public List<WinnerNumbers> downloadLatest() throws DownloadFailedException {
        final List<WinnerNumbers> latestWinnerNumbers = new ArrayList<>();

        BufferedReader bufferedReader = null;
        InputStream inputStream = null;

        try {
            final URL url = new URL(lotteryURL);
            final URLConnection connection = url.openConnection();
            inputStream = connection.getInputStream();

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                latestWinnerNumbers.add(new WinnerNumbers(line.split(";")));
            }
        } catch (IOException e) {
            LOGGER.error("An IOException occurred while data was processed.", e);
        } catch (NumberFormatException nfe) {
            LOGGER.error(
                "A NumberFormatException occurred while data was processed. Connection failed or the format of the data has changed.",
                nfe);
            throw new DownloadFailedException();
        } finally {

            try {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (final IOException e) {
                LOGGER.error("An IOException occurred while closing resources.", e);
            }
        }

        return latestWinnerNumbers;
    }
}
