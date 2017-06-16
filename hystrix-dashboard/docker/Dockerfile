FROM java:8
MAINTAINER zhongmingmao<zhongmingmao0625@gmail.com>
COPY *.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","-Xms64m","-Xmx128m","/app.jar"]