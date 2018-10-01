package com.br.stf.bootjira.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.springframework.stereotype.Component;

@Component
public class ConnectionJira {

	@SuppressWarnings("restriction")
	public void consumesJira(String login, String senha) {

		BufferedReader br = null;
		final Logger LOGGER = Logger.getLogger(ConnectionJira.class.getName());

		try {

			String url = "http://jiracaixa.apps.stfcia.com.br/rest/api/2/auditing/record";

			URL obj = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			conn.setRequestMethod("GET");

			String userpass = login + ":" + senha;
			String basicAuth = "Basic "
					+ javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
			conn.setRequestProperty("Authorization", basicAuth);

			if (200 <= conn.getResponseCode() && conn.getResponseCode() <= 299) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			} else {
				LOGGER.info("Sua requisi��o retornou o codigo: " + conn.getResponseCode());
			}

			String lineOfResponse = "";
			StringBuilder strBuild = new StringBuilder();

			while ((lineOfResponse = br.readLine()) != null) {
				strBuild.append(lineOfResponse);
			}

			String response = strBuild.toString();

			System.out.println(response);

			conn.disconnect();
			br.close();
			
		} catch (Exception e) {
			LOGGER.warning("Erro na solicita��o, verifique o problema ou tente novamente mais tarde!");
			e.printStackTrace();
			
		}

	}

}
