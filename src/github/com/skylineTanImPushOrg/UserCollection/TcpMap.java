package github.com.skylineTanImPushOrg.UserCollection;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by timeloveboy on 17-3-1.
 */
public class TcpMap {
    static TcpMap tcpMap;

    public static TcpMap getInstance() {
        if (tcpMap == null) {
            tcpMap = new TcpMap();
            return tcpMap;
        } else {
            return tcpMap;
        }
    }

    public ConcurrentMap<Integer, Channel> ids;

    TcpMap() {
        ids = new ConcurrentHashMap<>();
    }
}
