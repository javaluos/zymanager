
package com.zy.cms.common;

import java.lang.reflect.Array;
import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

@Component
public class RedisOperator {
	private static final ZyLogger logger = ZyLogger.getLogger(RedisOperator.class);
	private JedisSentinelPool jedisSentinelPool = null;

	public JedisSentinelPool getJedisSentinelPool() {
		return jedisSentinelPool;
	}

	@Resource
	public void setJedisSentinelPool(JedisSentinelPool jedisSentinelPool) {
		this.jedisSentinelPool = jedisSentinelPool;
	}

	public String hget(String key, String field) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return null;
		try {
			jedis.hget(key, field);
			return jedis.hget(key, field);
		} catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return null;
	}
	
	public Jedis getRedisClient() {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return null;
		return jedis;
	}

	public Long hset(String key, String field, String value) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return null;
		try {
			return jedis.hset(key, field, value);
		} catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return null;
	}

	public String set(String key, String value) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return null;
		try {
			return jedis.set(key, value);
		} catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return null;
	}
	
	public String get(String key) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return null;
		try {
			return jedis.get(key);
		} catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return null;
	}
	public long expire(String key, int seconds) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null) return 0;
        try {
            return jedis.expire(key, seconds);
        } catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
        return 0;
    }
	public long incr(String key) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null) return 0;
        // 从池中获取一个jedis实例
        try {
            return jedis.incr(key);
        } catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
        return 0;
    }
	public Long hdel(String key, String field) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return null;
		// 从池中获取一个jedis实例
		try {
			return jedis.hdel(key, field);
		}catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return (long) 0;
	}
	
	public Long del(String key) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return null;
		// 从池中获取一个jedis实例
		try {
			return jedis.del(key);
		}catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return (long) 0;
	}
	
	public void setex(String key, int seconds, String value) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return ;
		// 从池中获取一个jedis实例
		try {
			jedis.setex(key, seconds, value);
		} catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
	}
	
	public Map<String, String> hgetAll(String key) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return null;
		// 从池中获取一个jedis实例
		try {
			Pipeline p=jedis.pipelined();
			Response<Map<String, String>> rs=p.hgetAll(key);
			p.sync();
			return rs.get();
		} catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return null;
	}
	public Long hdels(String key, String ... fields) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return null;
		// 从池中获取一个jedis实例
		try {
			Pipeline p=jedis.pipelined();
			Response<Long> rs=p.hdel(key, fields);
			p.sync();
			return rs.get();
		} catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return (long) 0;
	}
	
	/**
	 * getset 
	 * @param key
	 * @param cost
	 * @return
	 */
	public String getSet(String key, String value) {

		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return "0";
		// 从池中获取一个jedis实例
		try {
			String rs = jedis.getSet(key, value);
			return rs;
		} catch (Exception e) {
			logger.error("Redis pipe exception：" + e.getMessage(), e);
		} finally {
			jedis.close();
		}
		return "0";
	}
	
    public List< String > hmget( Collection< String > keySet, String key )
    {
        Jedis jedis = jedisSentinelPool.getResource();
        if ( jedis == null )
            return null;

        try
        {
            Pipeline pipelined = jedis.pipelined();

            String[] array = new String[ keySet.size() ];
            keySet.toArray( array );

            Response< List< String > > hmget = pipelined.hmget( key, array );

            pipelined.syncAndReturnAll();

            return hmget.get();

        }
        catch ( Exception e )
        {
            logger.error( "Redis pipe exception：{}."+e.getMessage(), e );
        }
        finally
        {
            jedis.close();
        }

        return null;
    }
    
	public long decrby(String key,Long count) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null) return 0;
        // 从池中获取一个jedis实例
        try {
            jedis.decrBy(key, count);
            return 1;
        } catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
        return 0;
    }
	public long incrby(String key,Long count) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null) return 0;
        // 从池中获取一个jedis实例
        try {
            jedis.incrBy(key, count);
            return 1;
        } catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
        return 0;
    }
	
	public void batchDel(String prefix){  
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null){
			return ;
		}
		try {
			Set<String> set = jedis.keys(prefix +"*");  
	        Iterator<String> it = set.iterator();  
	        while(it.hasNext()){  
	            String keyStr = it.next();  
	            jedis.del(keyStr);  
	        }  
		}catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
    }

	public void lpush(String key, List<String> value) {
		Jedis jedis = jedisSentinelPool.getResource();
		if (jedis == null)
			return ;
		// 从池中获取一个jedis实例
		try {
			String[] values = value.toArray(new String[0]);
			jedis.lpush(key, values);
		} catch (Exception e) {
			logger.error("Redis pipe exception："+ e.getMessage(), e);
		} finally {
			jedis.close();
		}
	}

    /**
     * 添加到Set中
     *
     * @param key
     * @param value
     * @return
     */
    public boolean addSet(String key, String... value) {

        Jedis jedis = jedisSentinelPool.getResource();
        if (jedis == null)
            return false;
        // 从池中获取一个jedis实例
        try {
            Pipeline p = jedis.pipelined();
            p.sadd(key, value);
            return true;
        } catch (Exception e) {
            logger.error("Redis addSet pipe exception：" + e.getMessage(), e);
        } finally {
            jedis.close();
        }
        return false;
    }
}
