### Definition
A test repository to try out new technologies, microservices, leanring stuff.

### Application Architecture
- config server - setup external configuration.
- eureka server - service registry server.
- auth-server - Oauth2 authorization server.
- api-gateway - API gateway that proxies all the micro-services.
- Tracing Server (Zipkin) - http://localhost:9411/zipkin/ (we use openzipkin)
- Kibana Dashboards - http://localhost:5601
- license-service - License micro-service built with latest spring boot 3
- organization-service - Organiztion micro-service built with latest spring boot 3

### Starting services locally with docker-compose
In order to start entire infrastructure using Docker, you have to change dir to common. After that, execute below command:
```bash
docker-compose up -d
```
### Details Techstack
| Spring Cloud components         | Resources  |
|---------------------------------|------------|
| Configuration server            | [Config server properties] and [Configuration repository] |
| Service Discovery               | [Eureka server] and [Service discovery client] |
| API Gateway                     | [Spring Cloud Gateway starter](spring-petclinic-api-gateway/pom.xml) and [Routing configuration] |
| Docker Compose                  | [Spring Boot with Docker guide](https://spring.io/guides/gs/spring-boot-docker/) and [docker-compose file](docker-compose.yml) |
| Circuit Breaker                 | [Resilience4j fallback method](https://resilience4j.readme.io/docs/getting-started)  |
| ELK Monitoring                  | [ElasticSearch, Logstash, Kibana monitoring stack](https://micrometer.io/), [Spring Boot Actuator Production Ready Metrics] |
| Zipkin tracing                  | [Zipkin](https://zipkin.io/), [Spring Boot Actuator Production Ready Metrics] |

                       


