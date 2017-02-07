import github.com.skylineTanImPushOrg.TcpServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * Created by paidian on 17-2-7.
 */
public class Appserver {

    private static final Logger logger = LogManager.getLogger(TcpServer.class);
    public static void main(String[] args) throws Exception {

        logger.info("开始启动TCP服务器...");
        new TcpServer("localhost", 8081, logger).run();
//		TcpServer.shutdown();
    }
}
