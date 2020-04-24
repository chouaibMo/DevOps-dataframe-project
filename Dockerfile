FROM openjdk:8-jre-alpine

ADD /target/DataFrame-1.0-SNAPSHOT-shaded.jar /home/dataframe.jar

ADD /src/main/ressources/ /home/.

ENTRYPOINT java -jar /home/dataframe.jar "/home/oscars.csv"
