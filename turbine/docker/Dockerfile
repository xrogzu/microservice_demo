FROM java:8
MAINTAINER zhongmingmao<zhongmingmao0625@gmail.com>
COPY *.jar app.jar
EXPOSE 9100
ENTRYPOINT ["java","-jar","-Xms64m","-Xmx64m","/app.jar"]