#指定基础镜像
FROM java:8

#添加jar包到容器
ADD 0514_dubbowebportal-*.jar /app.jar

#声明容器暴露端口
EXPOSE 9001

#设置环境属性，端口号
ENV PORT=""


# 配置容器启动后执行的命令
ENTRYPOINT ["sh","-c","java $PORT   -jar /app.jar"]