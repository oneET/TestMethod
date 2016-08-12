public class GetMethodTest extends AbstractTestMethod {
    /**
     * 前端使用GET方式调用后端的methodA方法
     */
    @Test
    public void testMethodA() {
        try {
            CloseableHttpClient client = HttpClientBuilder.create().build();
			
            //定义详细的URL地址：http://localhost:8081/Aproject/commonPart/change
            // setParameter(String, String)定义传递参数
            URI uri = URIBuilder("/commonPart").setPath("/changeA").setParameter("tplName", "")
			.build();
            HttpGet method = new HttpGet(uri);
            System.out.println(method.getRequestLine());
            //执行get请求
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
