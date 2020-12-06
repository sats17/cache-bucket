[![Build Status](https://github.com/sats17/Cache-Bucket/workflows/Build/badge.svg)](https://github.com/sats17/Cache-Bucket/actions)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Cache-Bucket&metric=coverage)](https://sonarcloud.io/dashboard?id=Cache-Bucket)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Cache-Bucket&metric=alert_status)](https://sonarcloud.io/dashboard?id=Cache-Bucket)


# In Memory Serverside Cache
Simple In-Memory java cache library.
<br>
## How to use
Clone the repository, create jar and use it in your project.
<br>

#### Create cache
```
import com.sats.main.CacheController;
int size = 10;
CacheController controller = new CacheController(size);
```

#### Create cache with time limit
```
import com.sats.main.CacheController;
int size = 10;
long timeLimit = 1000 * 60;
CacheController controller = new CacheController(size,timeLimit);
```
#### Set cache
```
controller.setCache("key", "value");
```
#### Get cache by key
```
controller.getCache("key");
```
#### Get all cache
```
controller.getAll();
```
#### Clear cache by key
```
controller.clear("key");
```
#### Clear all
```
controller.clear();
```
