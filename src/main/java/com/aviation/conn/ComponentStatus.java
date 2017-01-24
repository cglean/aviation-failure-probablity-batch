package com.aviation.conn;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ComponentStatus {
	
	// HTTP POST request
	public StringBuffer getToken() throws Exception {
		String url = "https://194a29d4-9d1b-48be-9d3e-7bc145f6824b.predix-uaa.run.aws-usw02-pr.ice.predix.io/oauth/token";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
				con.addRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		con.setRequestProperty  ("Authorization", "Basic c2FtcGxlX2NsaWVudDI6Y2xpZW50X3NlY3JldA==");
		
		String urlParameters = "grant_type=client_credentials";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		
		
		System.out.println("\nSending 'POST' request to URL : " + url);
	//	System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		return response;

	}
	
	public String sendDataWithToken(String token, String data) throws Exception{
		String url = "https://predix-analytics-catalog-release.run.aws-usw02-pr.ice.predix.io/api/v1/catalog/analytics/1f285cae-8db1-488d-b8ae-da9d1f14db6f/execution";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
				con.addRequestProperty("Content-Type", "application/json");
		con.setRequestProperty  ("Authorization", "Bearer " + token);
		con.setRequestProperty("Predix-Zone-Id", "10b16ce2-5a7e-4713-a33e-29ad0b1c151a");
		
		//String urlParameters = "";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(data);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		
		
		System.out.println("\nSending 'POST' request to URL : " + url);
	//	System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		System.out.println(response.toString());
		return response.toString();
	}

}
