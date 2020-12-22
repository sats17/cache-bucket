[![Actions Status](https://github.com/sats17/Cache-Bucket/workflows/Build/badge.svg)](https://github.com/sats17/Cache-Bucket/actions)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Cache-Bucket&metric=coverage)](https://sonarcloud.io/dashboard?id=Cache-Bucket)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Cache-Bucket&metric=alert_status)](https://sonarcloud.io/dashboard?id=Cache-Bucket)


# Cache-Bucket
In memory cache bucket, used to store server side cache
<br>
#### Dependency
```
<dependency>
  <groupId>com.github.sats17</groupId>
  <artifactId>cache-bucket</artifactId>
  <version>1.0.0</version>
</dependency>
```

#### Imports
```
import com.github.sats17.cache.extern.CacheBucket;
import com.github.sats17.cache.internal.services.BucketController;
```

#### Initialize bucket with size
```
int size = 100;
CacheBucket bucket = new BucketController(size);
```
#### Initialize bucket with size and TTL(Newly created cache will be expires after TTL)
```
int size = 100;
long ttl = 60000; // 60 seconds
CacheBucket bucket = new BucketController(size, ttl);
```
#### Set cache
```
String key = "cacheKey";
Object value = new Object();
bucket.setCache(key, value);
```
#### Retrieve cache by key
```
bucket.getCache(key);
```
#### Retrieve all cache from bucket
```
bucket.getAll();
```
#### Clear cache by key
```
bucket.clear(key);
```
#### Clear all cache from bucket
```
bucket.clear();
```
