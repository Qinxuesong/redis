package com.atguigu;

import org.junit.Test;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

public class JedisTest {


    @Test
    public void testJedis() {
        // 创建一个Jedis连接
        Jedis jedis = new Jedis("192.168.150.128",6379);
        // 密码验证
        jedis.auth("123456");
        // 执行redis命令
        jedis.set("mytest","hello redis ,this is jedis client!!");
        // 从redis中取值
        String mytest = jedis.get("mytest");
        // 控制台打印
        System.out.println(mytest);
        // 关闭连接
        jedis.close();
    }


    @Test
    public void jedisKey() {

        // 创建连接
        Jedis jedis = new Jedis("192.168.150.128",6379);
        // 密码验证
        jedis.auth("123456");
        // 执行redis命令
        jedis.set("username","fangfang");
        // 获取redis中的值
        String username = jedis.get("username");
        // 控制台打印
        System.out.println(username);

        // 关闭连接
        jedis.close();
    }

    @Test
    public void testJedisString() {
        // 创建一个Jedis连接
        Jedis jedis = new Jedis("192.168.150.128",6379);
        // 密码验证
        jedis.auth("123456");
        // 执行redis命令
        jedis.set("string","this is String");
        // 获取redis中的值
        String string = jedis.get("string");
        // 控制台打印
        System.err.println(string);
        // 关闭连接
        jedis.close();
    }

    // Hash
    @Test
    public void testJedisHash() {
        Jedis jedis = new Jedis("192.168.150.128",6379);
        jedis.auth("123456");
        jedis.hset("zhangsan","name","张三丰");
        String name = jedis.hget("zhangsan", "name");
        System.err.println(name);
        jedis.close();
    }

    // List
    @Test
    public void testJedisList() {
        Jedis jedis = new Jedis("192.168.150.128",6379);
        jedis.auth("123456");
        jedis.lpush("list1","v1","v2","v3","v4");
        List<String> list1 = jedis.lrange("list1", 0, -1);
        System.out.println(list1);
        jedis.close();
    }

    // Set
    @Test
    public void testJedisSet() {
        Jedis jedis = new Jedis("192.168.150.128",6379);
        jedis.auth("123456");
        jedis.sadd("members","v1","v2","v3");
        Set<String> members = jedis.smembers("members");
        System.out.println(members);
        jedis.close();
    }

    // Zset
    @Test
    public void testJedisZset() {
        Jedis jedis = new Jedis("192.168.150.128",6379);
        jedis.auth("123456");
        jedis.zadd("k3",20,"v3");
        jedis.zadd("k3",30,"v4");
        jedis.zadd("k3",40,"v5");
        Set<String> zrange = jedis.zrange("k3", 0, -1);
        System.out.println(zrange);
        jedis.close();
    }

    // Geospatial
    @Test
    public void testJedisGeo() {
        Jedis jedis = new Jedis("192.168.150.128",6379);
        jedis.auth("123456");
        jedis.geoadd("china:city",121.47,31.23,"shanghai");
        jedis.geoadd("china:city",106.50,29.53,"chongqing");
        List<GeoCoordinate> shanghai = jedis.geopos("china:city", "shanghai");
        List<GeoCoordinate> chongqing = jedis.geopos("china:city", "chongqing");
        System.out.println(shanghai);
        System.out.println(chongqing);
        jedis.close();
    }

    // HyperLogLogs
    @Test
    public void testJedisHyperLogLogs() {
        Jedis jedis = new Jedis("192.168.150.128",6379);
        jedis.auth("123456");
        jedis.pfadd("pfk","m1","m2");
        long pfk = jedis.pfcount("pfk");
        System.out.println(pfk);
        jedis.close();
    }

    // Bitmaps
    @Test
    public void testJedisBitmaps() {
        Jedis jedis = new Jedis("192.168.150.128",6379);
        jedis.auth("123456");
        jedis.set("b1","a");
        jedis.setbit("b1",6,"1");
        jedis.setbit("b1",7,"0");
        String b1 = jedis.get("b1");
        System.out.println(b1);
        jedis.close();
    }
}
