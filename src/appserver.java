import github.com.skylineTanImPushOrg.TcpServer;

/**
 * Created by paidian on 17-2-7.
 */
public class appserver {


    public static void main(String[] args) throws Exception {
        logger.info("开始启动TCP服务器...");
        TcpServer.run();
//		TcpServer.shutdown();
    }
}
