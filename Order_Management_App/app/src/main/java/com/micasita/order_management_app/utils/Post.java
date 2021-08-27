package com.micasita.order_management_app.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Post  extends Thread {

    private String url, method;
    String result_data = "Empty";
    String[] data, field;
    HttpURLConnection httpURLConnection = null;
    Map<String, String> headers = new HashMap<>();

    public Post(String url, String method, String[] field, String[] data) {
        this.url = url;
        this.method = method;
        this.data = new String[data.length];
        this.field = new String[field.length];
        System.arraycopy(field, 0, this.field, 0, field.length);
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    @Override
    public void run() {
        try {
            String UTF8 = "UTF-8", iso = "iso-8859-1";
            URL url = new URL(this.url);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(this.method);
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, UTF8));
            StringBuilder post_data = new StringBuilder();
            for (int i = 0; i < this.field.length; i++) {
                post_data.append(URLEncoder.encode(this.field[i], "UTF-8")).append("=").append(URLEncoder.encode(this.data[i], UTF8)).append("&");
            }
            bufferedWriter.write(post_data.toString());
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, iso));
            StringBuilder result = new StringBuilder();
            String result_line;
            while ((result_line = bufferedReader.readLine()) != null) {
                result.append(result_line);
            }
            bufferedReader.close();
            inputStream.close();

            httpURLConnection.disconnect();
            setData(result.toString());
        } catch (IOException e) {
            setData(e.toString());
        }
    }

    public boolean startPost() {
        Post.this.start();
        return true;
    }


    public boolean onComplete() {
        while (true) {
            if (!this.isAlive()) {
                return true;
            }
        }
    }

    public String getResult() {
        return this.getData();
    }

    public void setData(String result_data) {
        this.result_data = result_data;
    }


    public String getData() {
        return result_data;
    }
}
