package com.vibedesenv.pratostipicosfrontend.repository;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.vibedesenv.pratostipicosfrontend.entity.PratoTipico;

@Repository
public class PratoTipicoRepositoryRepositoryImpl implements PratoTipicoRepository {

	@Override
	public List<PratoTipico> findAll() {
		List<PratoTipico> list = new ArrayList<PratoTipico>();

		try {
			RestTemplate restTemplate = getRestTemplateignoreSsl();

			ResponseEntity<PratoTipico[]> response = restTemplate.getForEntity("https://archtidevops.com/pratosTipicos/todos", PratoTipico[].class);

			Iterator iterator = Arrays.asList(response.getBody()).iterator();

			while (iterator.hasNext()) {
				PratoTipico prato = (PratoTipico) iterator.next();
				prato.setUrl("tacaca.png");
				list.add(prato);
			}

			return list;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar");
		}
	}

	private RestTemplate getRestTemplateignoreSsl() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}

	@Override
	public String getVersion() {
		RestTemplate restTemplate;
		try {
			restTemplate = getRestTemplateignoreSsl();
			ResponseEntity<String> response = restTemplate.getForEntity("https://archtidevops.com/pratosTipicos/versao", String.class);
			return response.getBody();
		} catch (Exception e) {
			throw new RuntimeException("erro getVersion");
		}

	}

}
