import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  
/** 
 * 基本测试 
 */  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration("classpath:/spring-redis.xml")  
public class TestSpringRedis {  
  
    @Autowired  
    RedisTemplate jedisTemplate;  
  
    @Test  
     public void putAndGet(){
    	HashOperations hashOper = jedisTemplate.opsForHash();
    	
    	hashOper.put("user","name","zhangsan");
    	hashOper.put("user2","name","lishi");
        Object name =  jedisTemplate.opsForHash().get("user","name");  
        System.out.println(name);  
    }  
}
