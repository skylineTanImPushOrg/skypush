package github.com.skylineTanImPushOrg.Server;

/**
 * Created by paidian on 17-2-7.
 */

import github.com.skylineTanImPushOrg.BytesBuf.MessageBean;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TcpHandler extends SimpleChannelInboundHandler<byte[]> {

    private Logger logger = LogManager.getLogger(TcpHandler.class);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, byte[] msg) throws Exception {

        MessageBean.Msg m = MessageBean.Msg.parseFrom(msg);
        logger.info("SERVER接收到消息:" + m.getData().toStringUtf8());
        ctx.channel().writeAndFlush("yes, server is accepted you ,nice !" + m);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.warn("Unexpected exception from downstream.", cause);
        ctx.close();
    }
}

