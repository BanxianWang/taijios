package com.sghy1801.util;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;
import sun.applet.Main;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wrm
 * @create 2019-05-06 15:40
 */
public class JedisUtil {
    private static Jedis jedis = new Jedis("106.14.208.219",6379);

    public static String getTemperature(){
        return jedis.get("temperature");
    }

    public static void setTemperature(String temperature){
      jedis.set("temperature",temperature);
    }

    public static void main(String[] args) {
        System.out.println( JedisUtil.getTemperature());
    }
}
