# microservice_demo

## 1. Introduction

_**`Simple Practice`**_ With `Spring Cloud` & `Docker` & `Travis CI`

## 2. Travis CI Status
[![Build Status](https://travis-ci.org/zhongmingmao/microservice_demo.svg?branch=master)](https://travis-ci.org/zhongmingmao/microservice_demo)

## 3. Travis CI Strategy

![travis_ci_strategy](http://orqfdhddb.bkt.clouddn.com/travis_ci_strategy.png)
[travis_ci_strategy](http://orqfdhddb.bkt.clouddn.com/travis_ci_strategy.png)

## 5. Verification

### 5.1 Init
```
$ docker-compose up
Creating network "microservicedemo_default" with the default driver
Creating microservicedemo_server1_1
Creating microservicedemo_config-server_1
Creating microservicedemo_hystrix-dashboard_1
Creating microservicedemo_user-consumer_1
Creating microservicedemo_user-provider_1
Creating microservicedemo_zipkin-server-rabbitmq_1
Creating microservicedemo_turbine_1
Creating microservicedemo_rabbitmq_1
Creating microservicedemo_turbine-rabbitmq_1
Creating microservicedemo_zuul_1
Creating microservicedemo_server2_1
......

$ docker-compose stop # another session
Stopping microservicedemo_server2_1 ... done
Stopping microservicedemo_zuul_1 ... done
Stopping microservicedemo_rabbitmq_1 ... done
Stopping microservicedemo_turbine-rabbitmq_1 ... done
Stopping microservicedemo_zipkin-server-rabbitmq_1 ... done
Stopping microservicedemo_turbine_1 ... done
Stopping microservicedemo_user-consumer_1 ... done
Stopping microservicedemo_user-provider_1 ... done
Stopping microservicedemo_config-server_1 ... done
Stopping microservicedemo_server1_1 ... done
Stopping microservicedemo_hystrix-dashboard_1 ... done

$ docker-compose ps
                  Name                                 Command                State     Ports
---------------------------------------------------------------------------------------------
microservicedemo_config-server_1            java -jar -Xms64m -Xmx64m  ...   Exit 143
microservicedemo_hystrix-dashboard_1        java -jar -Xms56m -Xmx56m  ...   Exit 143
microservicedemo_rabbitmq_1                 docker-entrypoint.sh rabbi ...   Exit 137
microservicedemo_server1_1                  java -jar -Xms56m -Xmx56m  ...   Exit 143
microservicedemo_server2_1                  java -jar -Xms56m -Xmx56m  ...   Exit 143
microservicedemo_turbine-rabbitmq_1         java -jar -Xms56m -Xmx56m  ...   Exit 143
microservicedemo_turbine_1                  java -jar -Xms56m -Xmx56m  ...   Exit 143
microservicedemo_user-consumer_1            java -jar -Xms56m -Xmx56m  ...   Exit 143
microservicedemo_user-provider_1            java -jar -Xms56m -Xmx56m  ...   Exit 143
microservicedemo_zipkin-server-rabbitmq_1   java -jar -Xms56m -Xmx56m  ...   Exit 143
microservicedemo_zuul_1                     java -jar -Xms56m -Xmx56m  ...   Exit 137
```

### 5.1 Start Eureka-Server Cluster
```
$ docker-compose start server1
Starting server1 ... done

$ docker-compose start server2
Starting server2 ... done

$ docker ps
CONTAINER ID        IMAGE                                       COMMAND                  CREATED             STATUS              PORTS                    NAMES
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   5 minutes ago       Up About a minute   0.0.0.0:9002->9002/tcp   microservicedemo_server2_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   5 minutes ago       Up 2 minutes        0.0.0.0:9001->9001/tcp   microservicedemo_server1_1  
```
http://localhost:9001/ or http://localhost:9002/
![start_eureka](http://orqfdhddb.bkt.clouddn.com/start_eureka.png)
[start_eureka](http://orqfdhddb.bkt.clouddn.com/start_eureka.png)

### 5.2 Start Config-Server
```
$ docker-compose start config-server
Starting config-server ... done

$ docker ps
CONTAINER ID        IMAGE                                       COMMAND                  CREATED             STATUS              PORTS                    NAMES
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   12 minutes ago      Up 8 minutes        0.0.0.0:9002->9002/tcp   microservicedemo_server2_1
9230aa6a4287        zhongmingmao/config-server:0.0.1-SNAPSHOT   "java -jar -Xms64m..."   12 minutes ago      Up About a minute                            microservicedemo_config-server_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   12 minutes ago      Up 9 minutes        0.0.0.0:9001->9001/tcp   microservicedemo_server1_1
```
http://localhost:9001/ or http://localhost:9002/
![start_config-server](http://orqfdhddb.bkt.clouddn.com/start_config-server.png)
[start_config-server](http://orqfdhddb.bkt.clouddn.com/start_config-server.png)

### 5.3 Start User-Provider & & User-Consumer
```
$ docker-compose start user-provider user-consumer
Starting user-consumer ... done
Starting user-provider ... done

$ docker-compose scale user-provider=2
Creating and starting microservicedemo_user-provider_2 ... done

$ docker ps
CONTAINER ID        IMAGE                                       COMMAND                  CREATED             STATUS              PORTS                    NAMES
832a2f2fb91f        zhongmingmao/user-provider:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   30 seconds ago      Up 28 seconds                                microservicedemo_user-provider_2
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   18 minutes ago      Up 14 minutes       0.0.0.0:9002->9002/tcp   microservicedemo_server2_1
ed0266db8d41        zhongmingmao/user-consumer:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   18 minutes ago      Up About a minute                            microservicedemo_user-consumer_1
b6f610693a08        zhongmingmao/user-provider:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   18 minutes ago      Up About a minute                            microservicedemo_user-provider_1
9230aa6a4287        zhongmingmao/config-server:0.0.1-SNAPSHOT   "java -jar -Xms64m..."   18 minutes ago      Up 7 minutes                                 microservicedemo_config-server_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   18 minutes ago      Up 15 minutes       0.0.0.0:9001->9001/tcp   microservicedemo_server1_1
```
http://localhost:9001/ or http://localhost:9002/
![start_provider-consumer](http://orqfdhddb.bkt.clouddn.com/start_provider-consumer.png)
[start_provider-consumer](http://orqfdhddb.bkt.clouddn.com/start_provider-consumer.png)

### 5.4 Verify Ribbon & Feign
```
# Ribbon
$ docker exec ed0266db8d41 curl http://ed0266db8d41:40295/ribbon/users/1
[832a2f2fb91f response User(id=1, name=zhongmingmao)

$ docker exec ed0266db8d41 curl http://ed0266db8d41:40295/ribbon/users/1
[b6f610693a08 response User(id=1, name=zhongmingmao

# Feign
$ docker exec ed0266db8d41 curl http://ed0266db8d41:40295/feign/users/1
[b6f610693a08 response User(id=1, name=zhongmingmao)

$ docker exec ed0266db8d41 curl http://ed0266db8d41:40295/feign/users/1
[832a2f2fb91f response User(id=1, name=zhongmingmao)
```

### 5.5 Verify HyStrix
```
$ docker-compose stop user-provider
Stopping microservicedemo_user-provider_2 ... done
Stopping microservicedemo_user-provider_1 ... done

$ docker ps
CONTAINER ID        IMAGE                                       COMMAND                  CREATED             STATUS              PORTS                    NAMES
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   34 minutes ago      Up 30 minutes       0.0.0.0:9002->9002/tcp   microservicedemo_server2_1
ed0266db8d41        zhongmingmao/user-consumer:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   34 minutes ago      Up 17 minutes                                microservicedemo_user-consumer_1
9230aa6a4287        zhongmingmao/config-server:0.0.1-SNAPSHOT   "java -jar -Xms64m..."   34 minutes ago      Up 23 minutes                                microservicedemo_config-server_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   34 minutes ago      Up 31 minutes       0.0.0.0:9001->9001/tcp   microservicedemo_server1_1

# Ribbon
$ docker exec ed0266db8d41 curl http://ed0266db8d41:40295/ribbon/users/1
findByIdFallback(1) Error

# Feign
$ docker exec ed0266db8d41 curl http://ed0266db8d41:40295/feign/users/1
findById fallback,id:1,reason:com.netflix.client.ClientException: Load balancer does not have available server for client: user-provider
```

### 5.6 Verify HyStrix + Turbine
```
$ docker-compose start turbine hystrix-dashboard
Starting turbine ... done
Starting hystrix-dashboard ... done

$ docker ps
CONTAINER ID        IMAGE                                           COMMAND                  CREATED             STATUS              PORTS                    NAMES
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT       "java -jar -Xms56m..."   About an hour ago   Up About an hour    0.0.0.0:9002->9002/tcp   microservicedemo_server2_1
740209258c6f        zhongmingmao/turbine:0.0.1-SNAPSHOT             "java -jar -Xms56m..."   About an hour ago   Up About a minute   9100/tcp                 microservicedemo_turbine_1
ed0266db8d41        zhongmingmao/user-consumer:0.0.1-SNAPSHOT       "java -jar -Xms56m..."   About an hour ago   Up About an hour                             microservicedemo_user-consumer_1
9230aa6a4287        zhongmingmao/config-server:0.0.1-SNAPSHOT       "java -jar -Xms64m..."   About an hour ago   Up About an hour                             microservicedemo_config-server_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT       "java -jar -Xms56m..."   About an hour ago   Up About an hour    0.0.0.0:9001->9001/tcp   microservicedemo_server1_1
80168edb0593        zhongmingmao/hystrix-dashboard:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   About an hour ago   Up 44 seconds       0.0.0.0:9200->9200/tcp   microservicedemo_hystrix-dashboard_1
```

http://localhost:9001/ or http://localhost:9002/
![start_turbine_hystrix-dashboard](http://orqfdhddb.bkt.clouddn.com/start_turbine_hystrix-dashboard.png)
[start_turbine_hystrix-dashboard](http://orqfdhddb.bkt.clouddn.com/start_turbine_hystrix-dashboard.png)

http://localhost:9200/hystrix
![hystrix-dashboard-turbine](http://orqfdhddb.bkt.clouddn.com/hystrix-dashboard-turbine.png)
[hystrix-dashboard-turbine](http://orqfdhddb.bkt.clouddn.com/hystrix-dashboard-turbine.png)

Monitor  http://740209258c6f:9100/turbine.stream
![hystrix-dashboard-turbine-stream](http://orqfdhddb.bkt.clouddn.com/hystrix-dashboard-turbine-stream.png)
[hystrix-dashboard-turbine-stream](http://orqfdhddb.bkt.clouddn.com/hystrix-dashboard-turbine-stream.png)


### 5.7 Start RabbitMQ

```
$ docker-compose start rabbitmq
Starting rabbitmq ... done

$ docker ps
CONTAINER ID        IMAGE                                           COMMAND                  CREATED             STATUS              PORTS                                                                     NAMES
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT       "java -jar -Xms56m..."   About an hour ago   Up About an hour    0.0.0.0:9002->9002/tcp                                                    microservicedemo_server2_1
008c8206b850        rabbitmq:3.6.10-management                      "docker-entrypoint..."   About an hour ago   Up 2 minutes        4369/tcp, 5671-5672/tcp, 15671/tcp, 25672/tcp, 0.0.0.0:15673->15672/tcp   microservicedemo_rabbitmq_1
740209258c6f        zhongmingmao/turbine:0.0.1-SNAPSHOT             "java -jar -Xms56m..."   About an hour ago   Up 28 minutes       9100/tcp                                                                  microservicedemo_turbine_1
ed0266db8d41        zhongmingmao/user-consumer:0.0.1-SNAPSHOT       "java -jar -Xms56m..."   About an hour ago   Up About an hour                                                                              microservicedemo_user-consumer_1
9230aa6a4287        zhongmingmao/config-server:0.0.1-SNAPSHOT       "java -jar -Xms64m..."   About an hour ago   Up About an hour                                                                              microservicedemo_config-server_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT       "java -jar -Xms56m..."   About an hour ago   Up About an hour    0.0.0.0:9001->9001/tcp                                                    microservicedemo_server1_1
80168edb0593        zhongmingmao/hystrix-dashboard:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   About an hour ago   Up 27 minutes       0.0.0.0:9200->9200/tcp                                                    microservicedemo_hystrix-dashboard_1
```

http://localhost:15672
![rabbitmq](http://orqfdhddb.bkt.clouddn.com/rabbitmq.png)

### 5.8 Verify HyStrix + Turbine-RabbitMQ
```
$ docker-compose start turbine-rabbitmq
Starting turbine-rabbitmq ... done

$ docker ps
CONTAINER ID        IMAGE                                           COMMAND                  CREATED             STATUS              PORTS                                                                     NAMES
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT       "java -jar -Xms56m..."   About an hour ago   Up About an hour    0.0.0.0:9002->9002/tcp                                                    microservicedemo_server2_1
008c8206b850        rabbitmq:3.6.10-management                      "docker-entrypoint..."   About an hour ago   Up 5 minutes        4369/tcp, 5671-5672/tcp, 15671/tcp, 25672/tcp, 0.0.0.0:15673->15672/tcp   microservicedemo_rabbitmq_1
248a45a932b5        zhongmingmao/turbine-rabbitmq:0.0.1-SNAPSHOT    "java -jar -Xms56m..."   About an hour ago   Up 29 seconds       9110/tcp                                                                  microservicedemo_turbine-rabbitmq_1
740209258c6f        zhongmingmao/turbine:0.0.1-SNAPSHOT             "java -jar -Xms56m..."   About an hour ago   Up 32 minutes       9100/tcp                                                                  microservicedemo_turbine_1
ed0266db8d41        zhongmingmao/user-consumer:0.0.1-SNAPSHOT       "java -jar -Xms56m..."   About an hour ago   Up About an hour                                                                              microservicedemo_user-consumer_1
9230aa6a4287        zhongmingmao/config-server:0.0.1-SNAPSHOT       "java -jar -Xms64m..."   About an hour ago   Up About an hour                                                                              microservicedemo_config-server_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT       "java -jar -Xms56m..."   About an hour ago   Up About an hour    0.0.0.0:9001->9001/tcp                                                    microservicedemo_server1_1
80168edb0593        zhongmingmao/hystrix-dashboard:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   About an hour ago   Up 31 minutes       0.0.0.0:9200->9200/tcp                                                    microservicedemo_hystrix-dashboard_1
```

http://localhost:9200/hystrix
![hystrix-dashboard-turbine_rabbitmq](http://orqfdhddb.bkt.clouddn.com/hystrix-dashboard-turbine_rabbitmq.png)
[hystrix-dashboard-turbine_rabbitmq](http://orqfdhddb.bkt.clouddn.com/hystrix-dashboard-turbine_rabbitmq.png)

Monitor  http://248a45a932b5:9110/turbine.stream
![hystrix-dashboard-turbine_rabbitmq-stream](http://orqfdhddb.bkt.clouddn.com/hystrix-dashboard-turbine_rabbitmq-stream.png)
[hystrix-dashboard-turbine_rabbitmq-stream](http://orqfdhddb.bkt.clouddn.com/hystrix-dashboard-turbine_rabbitmq-stream.png)

```
$ docker-compose stop turbine  turbine-rabbitmq hystrix-dashboard
Stopping microservicedemo_turbine-rabbitmq_1 ... done
Stopping microservicedemo_turbine_1 ... done
Stopping microservicedemo_hystrix-dashboard_1 ... done

$ docker ps
CONTAINER ID        IMAGE                                       COMMAND                  CREATED             STATUS              PORTS                                                                     NAMES
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   2 hours ago         Up 2 hours          0.0.0.0:9002->9002/tcp                                                    microservicedemo_server2_1
008c8206b850        rabbitmq:3.6.10-management                  "docker-entrypoint..."   2 hours ago         Up 14 minutes       4369/tcp, 5671-5672/tcp, 15671/tcp, 25672/tcp, 0.0.0.0:15673->15672/tcp   microservicedemo_rabbitmq_1
ed0266db8d41        zhongmingmao/user-consumer:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   2 hours ago         Up About an hour                                                                              microservicedemo_user-consumer_1
9230aa6a4287        zhongmingmao/config-server:0.0.1-SNAPSHOT   "java -jar -Xms64m..."   2 hours ago         Up About an hour                                                                              microservicedemo_config-server_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   2 hours ago         Up 2 hours          0.0.0.0:9001->9001/tcp                                                    microservicedemo_server1_1
```

### 5.9 Verify Zuul
```
$ docker-compose start zuul
Starting zuul ... done

$ docker ps
CONTAINER ID        IMAGE                                       COMMAND                  CREATED             STATUS              PORTS                                                                     NAMES
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   2 hours ago         Up 2 hours          0.0.0.0:9002->9002/tcp                                                    microservicedemo_server2_1
00a19646d634        zhongmingmao/zuul:0.0.1-SNAPSHOT            "java -jar -Xms56m..."   2 hours ago         Up 38 seconds       0.0.0.0:9300->9300/tcp                                                    microservicedemo_zuul_1
008c8206b850        rabbitmq:3.6.10-management                  "docker-entrypoint..."   2 hours ago         Up 15 minutes       4369/tcp, 5671-5672/tcp, 15671/tcp, 25672/tcp, 0.0.0.0:15673->15672/tcp   microservicedemo_rabbitmq_1
ed0266db8d41        zhongmingmao/user-consumer:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   2 hours ago         Up About an hour                                                                              microservicedemo_user-consumer_1
9230aa6a4287        zhongmingmao/config-server:0.0.1-SNAPSHOT   "java -jar -Xms64m..."   2 hours ago         Up About an hour                                                                              microservicedemo_config-server_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   2 hours ago         Up 2 hours          0.0.0.0:9001->9001/tcp                                                    microservicedemo_server1_1

$ curl 'http://localhost:9300/consumer/feign/users/1'
access token is empty

# User-Consumer Hystrix Fallback
$ curl 'http://localhost:9300/consumer/feign/users/1?accesstoken=123' # Zuul Route
findById fallback,id:1,reason:com.netflix.client.ClientException: Load balancer does not have available server for client: user-provider

$ docker-compose stop user-consumer
Stopping microservicedemo_user-consumer_1 ... done

# Zuul Hystrix Fallback
$ curl 'http://localhost:9300/consumer/feign/users/1?accesstoken=123'
Microservice[user-consumer] is not available, Please try Again later.
```

### 5.10 Verify Config-Server
```
$ docker-compose stop

$ docker-compose start server1 server2
Starting server1 ... done
Starting server2 ... done

$ docker-compose start rabbitmq
Starting rabbitmq ... done

$ docker-compose start config-server
Starting config-server ... done

$ docker-compose scale user-provider=2
Starting microservicedemo_user-provider_1 ... done
Starting microservicedemo_user-provider_2 ... done

$ docker ps
CONTAINER ID        IMAGE                                       COMMAND                  CREATED             STATUS              PORTS                                                                     NAMES
832a2f2fb91f        zhongmingmao/user-provider:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   2 hours ago         Up 34 seconds                                                                                 microservicedemo_user-provider_2
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   2 hours ago         Up 5 minutes        0.0.0.0:9002->9002/tcp                                                    microservicedemo_server2_1
008c8206b850        rabbitmq:3.6.10-management                  "docker-entrypoint..."   2 hours ago         Up 3 minutes        4369/tcp, 5671-5672/tcp, 15671/tcp, 25672/tcp, 0.0.0.0:15673->15672/tcp   microservicedemo_rabbitmq_1
b6f610693a08        zhongmingmao/user-provider:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   2 hours ago         Up 33 seconds                                                                                 microservicedemo_user-provider_1
9230aa6a4287        zhongmingmao/config-server:0.0.1-SNAPSHOT   "java -jar -Xms64m..."   2 hours ago         Up About a minute                                                                             microservicedemo_config-server_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   2 hours ago         Up 5 minutes        0.0.0.0:9001->9001/tcp                                                    microservicedemo_server1_1

$ docker exec b6f610693a08 curl http://b6f610693a08:34905/version
dev-D.0

$ docker exec b6f610693a08 curl http://832a2f2fb91f:32881/version
dev-D.0

# update user-provider-dev.yml in https://git.coding.net/zhongmingmao_coding/microservice-config.git

$ docker exec b6f610693a08 curl http://b6f610693a08:34905/version
dev-D.0

$ docker exec b6f610693a08 curl http://832a2f2fb91f:32881/version
dev-D.0

# Post Config Server /bus/refresh
$ docker exec b6f610693a08 curl -X POST http://9230aa6a4287:46401/bus/refresh

$ docker exec b6f610693a08 curl http://b6f610693a08:34905/version
dev-E.0

$ docker exec b6f610693a08 curl http://832a2f2fb91f:32881/version
dev-E.0 
```

### 5.11 Verify Zipkin-Server-Rabbitmq

```
$ docker-compose stop
Stopping microservicedemo_user-provider_2 ... done
Stopping microservicedemo_server2_1 ... done
Stopping microservicedemo_rabbitmq_1 ... done
Stopping microservicedemo_zipkin-server-rabbitmq_1 ... done
Stopping microservicedemo_user-provider_1 ... done
Stopping microservicedemo_config-server_1 ... done
Stopping microservicedemo_server1_1 ... done

$ docker-compose start server1 server2
Starting server1 ... done
Starting server2 ... done

$ docker-compose start rabbitmq
Starting rabbitmq ... done

$ docker-compose start config-server
Starting config-server ... done

$ docker-compose start zipkin-server-rabbitmq
Starting zipkin-server-rabbitmq ... done

$ docker-compose scale user-provider=1 user-consumer=1
Starting microservicedemo_user-provider_1 ... done
Starting microservicedemo_user-consumer_1 ... done

$ docker ps
CONTAINER ID        IMAGE                                                COMMAND                  CREATED             STATUS              PORTS                                                                     NAMES
f526f7d0f524        zhongmingmao/eureka-server:0.0.1-SNAPSHOT            "java -jar -Xms56m..."   3 hours ago         Up 11 minutes       0.0.0.0:9002->9002/tcp                                                    microservicedemo_server2_1
008c8206b850        rabbitmq:3.6.10-management                           "docker-entrypoint..."   3 hours ago         Up 10 minutes       4369/tcp, 5671-5672/tcp, 15671/tcp, 25672/tcp, 0.0.0.0:15673->15672/tcp   microservicedemo_rabbitmq_1
b57cdda8afa4        zhongmingmao/zipkin-server-rabbitmq:0.0.1-SNAPSHOT   "java -jar -Xms56m..."   3 hours ago         Up About a minute   0.0.0.0:9410->9410/tcp                                                    microservicedemo_zipkin-server-rabbitmq_1
ed0266db8d41        zhongmingmao/user-consumer:0.0.1-SNAPSHOT            "java -jar -Xms56m..."   3 hours ago         Up 8 minutes                                                                                  microservicedemo_user-consumer_1
b6f610693a08        zhongmingmao/user-provider:0.0.1-SNAPSHOT            "java -jar -Xms56m..."   3 hours ago         Up 8 minutes                                                                                  microservicedemo_user-provider_1
9230aa6a4287        zhongmingmao/config-server:0.0.1-SNAPSHOT            "java -jar -Xms64m..."   3 hours ago         Up 9 minutes                                                                                  microservicedemo_config-server_1
00cfc322abea        zhongmingmao/eureka-server:0.0.1-SNAPSHOT            "java -jar -Xms56m..."   3 hours ago         Up 11 minutes       0.0.0.0:9001->9001/tcp                                                    microservicedemo_server1_1

$ docker exec ed0266db8d41 curl http://ed0266db8d41:37015/feign/users/1
[b6f610693a08 response User(id=1, name=zhongmingmao)
```

http://localhost:9410
![zipkin-server-rabbitmq](http://orqfdhddb.bkt.clouddn.com/zipkin-server-rabbitmq.png)
[zipkin-server-rabbitmq](http://orqfdhddb.bkt.clouddn.com/zipkin-server-rabbitmq.png)

Trace
![zipkin-server-rabbitmq-trace](http://orqfdhddb.bkt.clouddn.com/zipkin-server-rabbitmq-trace.png)
[zipkin-server-rabbitmq-trace](http://orqfdhddb.bkt.clouddn.com/zipkin-server-rabbitmq-trace.png)

## 6. Todo List

1. Elasticsearch + Logstash +  Kinaba
2. Jolokia + Telegraf + InfluxDB + Grafana
3. Zipkin Storage (Elasticsearch)
4. Other Spring Cloud Artifact
