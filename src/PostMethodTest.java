public class PostMethodTest extends AbstractMethodTest {
/**
 * 前端使用POST方式调用后端的methodB方法
 */
@Test
public void testMethodB() {
    try {
        CloseableHttpClient client = HttpClientBuilder.create().build();

        //定义详细的URL地址：http://localhost:8081/Aproject/commonPart/changeB
        // setParameter(String, String)定义传递参数
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
}
