import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

public class TestRun {

    private static final int DEFAULT_RUM_TIMES = 1;
    private static final int DEVICE_ALL = -1;
    private static final int DEVICE_ANDROID = 0;
    private static final int DEVICE_IOS = 1;
    private static final String POST_URL = "https://fb.app.cntvwb.cn/fb/service?cmd=8";


    public static void main(String[] args) {
        System.out.println("HELLO");
        System.out.println("获取参数中......");
        int type = DEVICE_IOS;
        int times = DEFAULT_RUM_TIMES;
        if(args.length>0) {
            type = getType(args[0]);
            if(args.length>1) {
                try {
                    times = Integer.parseInt(args[1]);
                }catch (Exception ex) {
                    //
                }
            }
        }
        System.out.print("本次测试运行");
        if(type==DEVICE_ALL) {
            System.out.print("Android和iOS");
        } else if(type==DEVICE_ANDROID) {
            System.out.print("Android");
        } else if(type==DEVICE_IOS) {
            System.out.print("iOS");
        }
        System.out.println("访问" + times + "次");

        if(type==DEVICE_ANDROID||type==DEVICE_ALL) {
            System.out.println("开始Android访问"+DEFAULT_RUM_TIMES+"次");
            for(int i=0; i<times;) {
                i++;
                System.out.println("第" + i + "次返回:" + sendPostByJdkApi(getParameters(type)));
            }
            System.out.println("Android访问结束");
        }
        if(type==DEVICE_IOS||type==DEVICE_ALL) {
            System.out.println("开始iOS访问"+DEFAULT_RUM_TIMES+"次");
            for(int i=0; i<times;) {
                i++;
                System.out.println("第" + i + "次返回:" + sendPostByJdkApi(getParameters(type)));
            }
            System.out.println("iOS访问结束");
        }
        System.out.println("测试运行结束");
        System.out.println("BYE");
    }

    private static StringBuilder getCommonParas() {
        StringBuilder paraStringBuilder = new StringBuilder();
        paraStringBuilder.append("&appSystemVersion=1.1.0");

        paraStringBuilder.append("&userId=100000460");
        paraStringBuilder.append("&userName=邮箱");
        paraStringBuilder.append("&business_id=VIDEHfADGXRc875efDNZLseE200107");
        paraStringBuilder.append("&business_type=2");
        paraStringBuilder.append("&productId=29");

        paraStringBuilder.append("&business_time=7");
        paraStringBuilder.append("&category=2");
        paraStringBuilder.append("&content=");
        paraStringBuilder.append("&email=");
        paraStringBuilder.append("&feedbackId=70");
        paraStringBuilder.append("&path=");

        paraStringBuilder.append("&sourceId=29");
        paraStringBuilder.append("&subcategory=2");
        paraStringBuilder.append("&tel=15911057354");
        paraStringBuilder.append("&terminalId=2");
        paraStringBuilder.append("&title=刘国梁——“不会打球的胖子”");
        paraStringBuilder.append("&url=");

        paraStringBuilder.append("&utdid=Xa2EkFE5gWwDAH9WL\\/SsDvEh");
        paraStringBuilder.append("&verifyCode=BB16B6EAB30207DBE4AD492299D9E701");
        paraStringBuilder.append("&versionId=25");
        return paraStringBuilder;
    }


    private static int getType(String type) {
        if("android".equals(type)) {
            return DEVICE_ANDROID;
        }
        if("ios".equals(type)) {
            return DEVICE_IOS;
        }
        return DEVICE_ALL;
    }

    private static String sendPostByJdkApi(String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(POST_URL);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally{
            try{
                if(out!=null){
                    out.close();
                }
                if(in!=null){
                    in.close();
                }
            }
            catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result.toString();
    }

    private static String getParameters(int type) {
        StringBuilder sb = new StringBuilder();
        if(type== DEVICE_ANDROID ||type== DEVICE_ALL) {
            sb.append("appSystem=android 9");
            sb.append("&phoneSystem=android");
            sb.append("&phoneModel=Redmi Note 7");
        }
        if(type== DEVICE_IOS ||type== DEVICE_ALL) {
            sb.append("appSystem=ios13.3.1");
            sb.append("&phoneSystem=ios");
            sb.append("&phoneModel=iPhone 6s");
        }
        return sb.append(getCommonParas()).toString();
    }







}
