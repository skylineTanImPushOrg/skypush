package github.com.skylineTanImPushOrg.Cache;

import redis.clients.jedis.Jedis;

/**
 * Created by timeloveboy on 17-3-1.
 */
public class RedisCache {
    static Jedis instance;

    public static Jedis getInstance() {
        if (instance == null) {
            synchronized (Jedis.class) {
                instance = new Jedis("localhost");
            }
        }
        return instance;
    }
}
