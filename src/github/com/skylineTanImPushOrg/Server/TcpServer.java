package github.com.skylineTanImPushOrg.Server;

/**
 * Created by paidian on 17-2-7.
 */

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TcpServer {


    private static Logger logger = LogManager.getLogger(TcpServer.class);
    private String IP = "127.0.0.1";
    private int PORT = 9999;
    /**
     * 用于分配处理业务线程的线程组个数
     */
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;    //默认
    /**
     * 业务出现线程大小
     */
    protected static final int BIZTHREADSIZE = 4;
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);

    public TcpServer(String ip, int port, Logger log) {
        logger = log;
        IP = ip;
        PORT = port;
    }

    public void run() throws Exception {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup, workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new ByteArrayDecoder());
                pipeline.addLast("encoder", new ByteArrayDecoder());
                pipeline.addLast(new TcpHandler());
            }
        });

        b.bind(IP, PORT).sync();
        logger.info("TCP服务器已启动");
    }
    protected static void shutdown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

}


