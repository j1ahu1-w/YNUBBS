package ynu.com.ynubbs.Web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import ynu.com.ynubbs.Entity.Post;
import ynu.com.ynubbs.Entity.User;
import ynu.com.ynubbs.Entity.Reply;


public class WebService {
    private static String IP = "192.168.1.101:8080";

    /**
     * 通过Get方式获取HTTP服务器数据
     *
     * @return
     */
    public static String LoginExecuteHttpGet(String url,String id, String password ) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url + "?u_id=" + id + "&u_password=" + password;
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                is = conn.getInputStream();
                System.out.println("webservice-start5");
                return parseInfo(is);
            }
            return null;

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return "服务器连接超时...";
    }

    public static String RegisterExecuteHttpGet(String url,int id,String name,String password ) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url + "?u_id=" + id + "&u_name=" + name +"&u_password=" + password;
            System.out.println(id);
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                is = conn.getInputStream();
                System.out.println("webservice-start5");
                return parseInfo(is);
            }
            return null;

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return "服务器连接超时...";
    }

    public static String PInsertExecuteHttpGet(String url,String p_class,String p_theme,String p_content,String p_time,String p_user ) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url + "?p_class=" +p_class+ "&p_theme=" + p_theme +"&p_content=" + p_content+ "&p_time=" + p_time+ "&p_user=" + p_user;
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                is = conn.getInputStream();
                System.out.println("webservice-start5");
                return parseInfo(is);
            }
            return null;

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return "服务器连接超时...";
    }

    public static String RInsertExecuteHttpGet(String url,String r_to_p_id,String r_to_class,String r_to_id,String r_content,String r_time,String r_user ) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url + "?r_to_p_id=" +r_to_p_id+ "&r_to_class=" + r_to_class +"&r_to_id=" + r_to_id+ "&r_content=" + r_content+ "&r_time=" + r_time + "&r_user=" + r_user;
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                is = conn.getInputStream();
                System.out.println("webservice-start5");
                return parseInfo(is);
            }
            return null;

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return "服务器连接超时...";
    }

    public static String DeletePostExecuteHttpGet(String url,String p_id) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url + "?p_id=" +p_id;
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                is = conn.getInputStream();
                System.out.println("webservice-start5");
                return parseInfo(is);
            }
            return null;

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return "服务器连接超时...";
    }

    public static String DeleteReplyExecuteHttpGet(String url,String r_id) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url + "?r_id=" +r_id;
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                is = conn.getInputStream();
                System.out.println("webservice-start5");
                return parseInfo(is);
            }
            return null;

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return "服务器连接超时...";
    }

    public static ArrayList<Post> SelectAllExecuteHttpGet(String url) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url ;
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                InputStream inStream=conn.getInputStream();
                System.out.println("webservice-start5");
                return parseJSON(inStream);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public static ArrayList<Post> SelectSingleExecuteHttpGet(String url,String id) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url+ "?id=" + id  ;
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                InputStream inStream=conn.getInputStream();
                System.out.println("webservice-start5");
                return parseJSON(inStream);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public static ArrayList<Post> SelectByTypeExecuteHttpGet(String url,String type) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url+ "?type=" + type  ;
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                InputStream inStream=conn.getInputStream();
                System.out.println("webservice-start5");
                return parseJSON(inStream);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public static ArrayList<Reply> SelectReplyExecuteHttpGet(String url,String r_to_p_id) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url+ "?r_to_p_id=" + r_to_p_id  ;
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                InputStream inStream=conn.getInputStream();
                System.out.println("webservice-start5");
                return parseJSON3(inStream);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }
    public static ArrayList<Reply> SelectReply1ExecuteHttpGet(String url,String r_id) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url+ "?r_id=" + r_id  ;
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                InputStream inStream=conn.getInputStream();
                System.out.println("webservice-start5");
                return parseJSON3(inStream);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    public static ArrayList<User> SelectUserExecuteHttpGet(String url,String id) {
        HttpURLConnection conn = null;
        InputStream is = null;
        System.out.println("webservice-start1");
        try {
            // 用户名 密码
            // URL 地址
            String path = "http://" + IP + "/YNUBBSWeb/";
            path = path + url+ "?id=" + id  ;
            System.out.println(path);
            System.out.println("webservice-start2");
            System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1.0,SSLv3,SSLv2Hello");
            conn = (HttpURLConnection) new URL(path).openConnection();
            System.out.println("webservice-start3");
            conn.setConnectTimeout(1000); // 设置超时时间
            conn.setReadTimeout(1000);
            conn.setDoInput(true);
            conn.setRequestMethod("GET"); // 设置获取信息方式
            conn.setRequestProperty("Charset", "UTF-8"); // 设置接收数据编码格式

            if (conn.getResponseCode() == 200) {
                System.out.println("webservice-start4");
                InputStream inStream=conn.getInputStream();
                System.out.println("webservice-start5");
                return parseJSON2(inStream);
            }
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 意外退出时进行连接关闭保护
            if (conn != null) {
                conn.disconnect();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }

    // 将输入流转化为 String 型
    private static String parseInfo(InputStream inStream) throws Exception {
        byte[] data = read(inStream);
        // 转化为字符串
        return new String(data, "UTF-8");
    }

    private static ArrayList<Post> parseJSON(InputStream inStream) throws Exception {
        ArrayList<Post> posts = new ArrayList<Post>();
        ArrayList<Post> einfo = new ArrayList<Post>();
        Post e=new Post(0,0,"0","0","0",0);
        einfo.add(e);
        byte[] data =StreamTool.read(inStream);
        //将字节数组转换成字符串
        String json= new String(data);
        //将json对象转换成json的数组对象
        if(json!=null&&!"".equals(json)){
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject=array.getJSONObject(i);
                Post p=new Post(jsonObject.getInt("p_id"),jsonObject.getInt("p_class"),jsonObject.getString("p_theme"),
                        jsonObject.getString("p_content"),jsonObject.getString("p_time"),jsonObject.getInt("p_user"));
                posts.add(p);
            }
            return posts;
        }else{
            return einfo;
        }
    }

    private static ArrayList<User> parseJSON2(InputStream inStream) throws Exception {
        ArrayList<User> user = new ArrayList<User>();
        ArrayList<User> einfo = new ArrayList<User>();
        User e=new User(0,"0","0");
        einfo.add(e);
        byte[] data =StreamTool.read(inStream);
        //将字节数组转换成字符串
        String json= new String(data);
        //将json对象转换成json的数组对象
        if(json!=null&&!"".equals(json)){
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject=array.getJSONObject(i);
                User u=new User(jsonObject.getInt("u_id"),jsonObject.getString("u_name"),jsonObject.getString("u_password"));
                user.add(u);
            }
            return user;
        }else{
            return einfo;
        }
    }

    private static ArrayList<Reply> parseJSON3(InputStream inStream) throws Exception {
        ArrayList<Reply> replys = new ArrayList<Reply>();
        ArrayList<Reply> einfo = new ArrayList<Reply>();
        Reply e=new Reply(0,0,0,0,"0","0",0);
        einfo.add(e);
        byte[] data =StreamTool.read(inStream);
        //将字节数组转换成字符串
        String json= new String(data);
        //将json对象转换成json的数组对象
        if(json!=null&&!"".equals(json)){
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsonObject=array.getJSONObject(i);
                Reply r=new Reply(jsonObject.getInt("r_id"),jsonObject.getInt("r_to_p_id"),jsonObject.getInt("r_to_class"),
                        jsonObject.getInt("r_to_id"), jsonObject.getString("r_content"),jsonObject.getString("r_time"),
                        jsonObject.getInt("r_user"));
                replys.add(r);
            }
            return replys;
        }else{
            return einfo;
        }
    }


    // 将输入流转化为byte型
    public static byte[] read(InputStream inStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        inStream.close();
        return outputStream.toByteArray();
    }



}