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
