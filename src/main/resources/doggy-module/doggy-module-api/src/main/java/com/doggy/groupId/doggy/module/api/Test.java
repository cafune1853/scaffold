package com.doggy.groupId.doggy.module.api;

import okhttp3.*;
import okhttp3.logging.HttpLoggingInterceptor;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException{
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.HEADERS);
		OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();
		RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), "{\"hhah\":453}");
		Request request = new Request.Builder().url("https://www.baidu.com").header("a", "ha").post(requestBody).build();
		Call call = httpClient.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				System.out.println("Oh fuck.");
			}
			
			@Override
			public void onResponse(Call call, Response response) throws IOException {
				System.out.println("success");
				System.out.println(response.body().string());
			}
		});
	}
}
