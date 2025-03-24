Spring Boot CRUD with Redis Caching

🚀 Project Overview
This project demonstrates a Spring Boot CRUD application with Redis caching using RedisTemplate. It uses H2 in-memory database for persistence and Redis for caching user data.

📌 Features
✅ CRUD operations for User entity
✅ Spring Boot + Spring Data JPA
✅ Redis Caching for performance optimization
✅ H2 Database for in-memory persistence
✅ RedisTemplate for cache operations

🏗️ Tech Stack
Spring Boot (Web, Data JPA, Redis)
H2 Database (for persistence)
Redis (for caching)
Spring Cache Abstraction

⚙️ Project Setup
1️⃣ Clone Repository
git clone https://github.com/your-repo-url.git
cd spring-boot-redis-crud
2️⃣ Run Redis Server
  1.Install Redis locally.
  2. Run redis server.
3️⃣ Run the Application

📜 TEST API Endpoints
➤ Create User
POST /users
  {
    "name": "John Doe",
    "email": "john@example.com"
  }

➤ Get User by ID (Cached in Redis)

GET /users/{id}
➤ Get All Users
GET /users
➤ Update User
PUT /users/{id}
  {
    "name": "John Updated",
    "email": "john_updated@example.com"
  }

➤ Delete User (Removes from Redis & DB)

DELETE /users/{id}


📄 application.properties

# H2 Database Config
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true

# Redis Configuration
spring.data.redis.host=localhost
spring.data.redis.port=6379
spring.cache.type=redis

⚙️ Redis Configuration Details

1️⃣ Add Redis Dependency in pom.xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

2️⃣ Configure Redis in Spring Boot (RedisConfig.java)

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}

🏆 Conclusion
This project demonstrates an efficient way to implement caching with Redis in a Spring Boot application, improving performance and reducing database load.

