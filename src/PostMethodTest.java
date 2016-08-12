import java.net.URI;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

public class PostMethodTest extends AbstractTestMethod {
    /**
     * 前端使用POST方式调用后端的methodB方法
     */
    @Test
    public void testMethodB() {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();

            //定义详细的URL地址：http://localhost:8081/Aproject/commonPart/changeB
            URI uri = URIBuilder("/commonPart").setPath("/changeB").build();
            HttpPost method = new HttpPost(uri);
            System.out.println(method.getRequestLine());

            //创建参数队列
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(new BasicNameValuePair("id", "8"));
            formparams.add(new BasicNameValuePair("name", "Tom"));
            formparams.add(new BasicNameValuePair("age", "13"));
            formparams.add(new BasicNameValuePair("score", "98"));
            UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);
            
            //post方法关联参数
            method.setEntity(requestEntity);
            
            //执行post请求 
            CloseableHttpResponse response = client.execute(method);
            //获取响应消息实体
            HttpEntity entity = response.getEntity();
            //响应状态
            System.out.println("status:" + response.getStatusLine());
            //判断响应实体是否为空  
            if (entity != null) {
                System.out.println("response content:" + EntityUtils.toString(entity));
            }
            response.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 前端使用POST方式调用后端的methodB2方法,参数以json形式传递
     */
    @Test
    public void testMethodB2() {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();

            //定义详细的URL地址：http://localhost:8081/Aproject/commonPart/changeB2
            URI uri = URIBuilder("/commonPart").setPath("/changeB2").build();
            HttpPost method = new HttpPost(uri);
            System.out.println(method.getRequestLine());

            //创建参数对象
            Student stu = new Student();
            stu.setId(8);
            stu.setName("Tom");
            stu.setAge(12);
            stu.setScore(98);
            
            //设置参数的name-value(此处使用param作为名字，是使用的前端框架传JSON是自动生成的名字)
            NameValuePair param = new BasicNameValuePair("param", JSON.toJSONString(stu));
            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
            formparams.add(param);
            UrlEncodedFormEntity requestEntity = new UrlEncodedFormEntity(formparams, Consts.UTF_8);

            //post方法关联参数
            method.setEntity(requestEntity);
            
            //执行post请求 
            CloseableHttpResponse response = client.execute(method);
            //获取响应消息实体
            HttpEntity entity = response.getEntity();
            //响应状态
            System.out.println("status:" + response.getStatusLine());
            //判断响应实体是否为空  
            if (entity != null) {
                System.out.println("response content:" + EntityUtils.toString(entity));
            }
            response.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
