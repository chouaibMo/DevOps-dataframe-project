FROM openjdk:8-jre-alpine

ADD /target/DataframeDemo.jar /home/dataframe.jar

ADD /src/main/ressources/ /home/.

ENTRYPOINT java -jar /home/dataframe.jar "/home/oscars.csv"
