import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class DeleteMethodTest extends AbstractTestMethod {

	@Test
	public void testMethodD() {
		try {
			CloseableHttpClient client = HttpClientBuilder.create().build();
			
			//定义详细的URL地址：http://localhost:8081/Aproject/commonPart/changeD
      URI uri = URIBuilder("/commonPart").setPath("/changeD").setParameter("id", "10").build();
			HttpDelete method = new HttpDelete(uri);
			System.out.println(method.getRequestLine());
      
      //执行delete请求
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
