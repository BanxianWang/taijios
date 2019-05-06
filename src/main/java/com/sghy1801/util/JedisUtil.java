package com.sghy1801.util;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;

/**
 * @author wrm
 * @create 2019-05-06 15:40
 */
public class JedisUtil {
    private static JedisCluster jc;
    private static  JedisUtil jedisUtil = new JedisUtil();
    private JedisUtil(){
        Set<HostAndPort> jedisClusterNode = new HashSet<HostAndPort>();
        jedisClusterNode.add(new HostAndPort("192.168.8.168",7001));
        jedisClusterNode.add(new HostAndPort("192.168.8.168",7002));
        jedisClusterNode.add(new HostAndPort("192.168.8.168",7003));
        jedisClusterNode.add(new HostAndPort("192.168.8.168",7004));
        jedisClusterNode.add(new HostAndPort("192.168.8.168",7005));
        jedisClusterNode.add(new HostAndPort("192.168.8.168",7006));
        JedisPoolConfig  cfg = new JedisPoolConfig();
        cfg.setMaxTotal(100);
        cfg.setMaxIdle(20);
        cfg.setMaxWaitMillis(-1);
        cfg.setTestOnBorrow(true);
        jc = new JedisCluster(jedisClusterNode,600,100,cfg);
    }
    public static JedisCluster getJc(){
        return jc;
    }
}
