FROM java:8
MAINTAINER zhongmingmao<zhongmingmao0625@gmail.com>
COPY *.jar app.jar
EXPOSE 9200
ENTRYPOINT ["java","-jar","-Xms56m","-Xmx56m","/app.jar"]