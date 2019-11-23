From java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=matchdb
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=vasansund@3
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/matchdb?useSSL=false
ADD ./target/favouriteservice.jar /usr/src/favouriteservice.jar
EXPOSE 9093
ENTRYPOINT ["java", "-jar", "favouriteservice.jar"]