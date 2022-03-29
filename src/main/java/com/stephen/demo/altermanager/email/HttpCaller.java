package com.stephen.demo.altermanager.email;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;


public class HttpCaller
{

    private static Log logger = LogFactory.getLog(HttpCaller.class);


    /**
     * 向指定 URL 发送POST方法的请求
     * 
     * @param url
     *            发送请求的 URL
     * @param params
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String params) throws Exception {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接,根据url
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //conn.setRequestProperty("Content-Type", "application/json");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);

            // 设置请求超时时间和读取超时时间
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);

            // 获取URLConnection对象对应的输出流，设置utf-8编码
            out = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8"));
            // 发送请求参数
            out.print(params);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应,设置utf-8编码
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null)
                result += line;
        } catch (Exception e) {
            e.printStackTrace();
            result = "";
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return result;
    }

}
