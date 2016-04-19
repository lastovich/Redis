package redis.tpRedis;

import redis.clients.jedis.Jedis;

public class App
{
public static void main( String[] args ) throws InterruptedException
{
	Jedis jedis = new Jedis("localhost");
	
	//Exemple 1
	jedis.set("foo", "bar \n");
	String value = jedis.get("foo");	
	System.err.println(value);	

	System.out.println("**********");
	
	//Exemple 2
	System.out.println(jedis.get("counter"));
	jedis.incr("counter");
	System.out.println(jedis.get("counter"));
	System.out.println("**********");
	
	//Exemple 3
	String cacheKey = "cachekey";
	// adding a new key
	jedis.set(cacheKey, "cached value");
	// setting the TTL in seconds
	jedis.expire(cacheKey, 15);
	// Getting the remaining ttl
	System.out.println("TTL:" + jedis.ttl(cacheKey));
	Thread.sleep(1000);
	System.out.println("TTL:" + jedis.ttl(cacheKey));
	// Getting the cache value
	System.out.println("Cached Value:" + jedis.get(cacheKey));

	// Wait for the TTL finishs
	Thread.sleep(15000);

	// trying to get the expired key
	System.out.println("Expired Key:" + jedis.get(cacheKey));
	
	System.out.println("**********");
	
	//Exemple 4
	String cacheKey2 = "languages";
	
	// Adding a set as value

	jedis.sadd(cacheKey2, "Java");
	jedis.sadd(cacheKey2, "C#");
	jedis.sadd(cacheKey2, "Python");// SADD

	// Getting all values in the set: SMEMBERS
	System.out.println("Languages: " + jedis.smembers(cacheKey2));
	// Adding new values
	jedis.sadd(cacheKey2, "Java");
	jedis.sadd(cacheKey2, "Ruby");
	// Getting the values... it doesn't allow duplicates
	System.out.println("Languages: " + jedis.smembers(cacheKey2));



}
}

