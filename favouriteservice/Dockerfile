From java:8-jre
WORKDIR usr/src
EXPOSE 8083
ENV spring.datasource.url=jdbc:mysql://localhost:3306/matchdb?useSSL=false
ENV spring.datasource.username=root
ENV spring.datasource.password=root
ENV spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
ENV spring.jpa.hibernate.ddl-auto=update
ENV spring.jpa.properties.hibernate.show_sql=true
ENV spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
ADD ./target/favouriteservice-1.0.jar /usr/src/favouriteservice-1.0.jar
ENTRYPOINT ["java", "-jar", "favouriteservice-1.0.jar"]
