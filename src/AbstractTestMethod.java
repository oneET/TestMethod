/**
 * 定义抽象测试方法，所有的测试类都要继承改抽象类
 */
public abstract class AbstractTestMethod {
    //定义URL地址的共同部分
    protected String String url = "localhost:8081/AProject";

    //按链式结构，定义URL的地址
    protected URIBuilder URIBuilder(String path) {
        return new URIBuilder().setScheme("http").setHost(url + path);
    }
}
