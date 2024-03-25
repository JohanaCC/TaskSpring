FROM openjdk:17
EXPOSE 2003
ADD ./build/libs/manager-financial.jar microfinancial.jar
ENTRYPOINT [ "java","-jar","/microfinancial.jar" ]
