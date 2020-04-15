FROM openjdk:8-jre-alpine

ADD /target/DataFrame-1.0-SNAPSHOT-shaded.jar dataframe.jar

ENV CLASSPATH src/main/ressources/

ENTRYPOINT ["java","-jar","dataframe.jar"]