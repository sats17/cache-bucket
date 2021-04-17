[![Actions Status](https://github.com/sats17/Cache-Bucket/workflows/Build/badge.svg)](https://github.com/sats17/Cache-Bucket/actions)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Cache-Bucket&metric=coverage)](https://sonarcloud.io/dashboard?id=Cache-Bucket)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Cache-Bucket&metric=alert_status)](https://sonarcloud.io/dashboard?id=Cache-Bucket)
[![License](http://img.shields.io/:license-MIT-brightgreen.svg)](http://opensource.org/licenses/MIT)

<a>
<img align="right" height="70px" src="https://github.com/sats17/cache-bucket/bucket.png">
</a>

# Cache-Bucket
In memory cache bucket, used to store server side cache
<br>

Versions
-----------

|  cache-bucket |
|:-------------:|
|   1.0.0       |

#### Maven Dependency
```xml
<dependency>
  <groupId>com.github.sats17</groupId>
  <artifactId>cache-bucket</artifactId>
  <version>1.0.0</version>
</dependency>
```

#### Required Imports
```java
import com.github.sats17.cache.extern.CacheBucket;
import com.github.sats17.cache.internal.services.BucketController;
```

#### Initialize bucket with size
```java
int size = 100;
CacheBucket bucket = new BucketController(size);
```
#### Initialize bucket with size and TTL
```java
int size = 100;
long ttl = 60000; // 60 seconds
CacheBucket bucket = new BucketController(size, ttl);
```
#### Set cache
```java
String key = "cacheKey";
Object value = new Object();
bucket.setCache(key, value);
```
#### Retrieve cache by key
```java
Object value = bucket.getCache(key); 
```
###### note : By default get cache method returns cache value type as Object, You need to explicitly cast the value according to your use
```java
CacheBucket bucket = new BucketController(10,100000);
bucket.setCache("key", "cacheValue");
String value = (String) bucket.getCache("test");
```
#### Retrieve all cache from bucket
```java
Map<String, Object> values = bucket.getAll();
```
#### Clear cache by key
```java
bucket.clear(key);
```
#### Clear all cache from bucket
```java
bucket.clear();
```
