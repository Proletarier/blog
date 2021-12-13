##  install docker


官网

https://docs.docker.com/engine/install/ubuntu/

```
sudo apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common


curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -


sudo add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"


sudo apt-get update

sudo apt-get install docker-ce docker-ce-cli containerd.io
```

指定版本

```
apt-cache madison docker-ce
sudo apt-get install docker-ce=<VERSION_STRING> docker-ce-cli=<VERSION_STRING> containerd.io
```

## docker source

配置镜像加速器

https://cr.console.aliyun.com/cn-hangzhou/instances/mirrors

```
sudo mkdir -p /etc/docker
sudo tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://aykfevb9.mirror.aliyuncs.com"],
  "insecure-registries":["https://10.22.224.66"]
}
EOF
sudo systemctl daemon-reload
sudo systemctl restart docker
```

## 帮助命令
```
docker version

docker info

docker --help
```

## 镜像命令

列出本地主机上的镜像

```
docker images 

-a :列出本地所有的镜像（含中间映像层）

-q :只显示镜像ID。'

--digests :显示镜像的摘要信息

--no-trunc :显示完整的镜像信息

```

docker search 某个XXX镜像名字

```
docker search [OPTIONS] 镜像名字

--no-trunc : 显示完整的镜像描述

-s : 列出收藏数不小于指定值的镜像。

--automated : 只列出 automated build类型的镜像；
```

下载镜像

```
docker pull 某个XXX镜像名字
```

删除镜像

```
删除单个
docker rmi -f 镜像ID

删除多个
docker rmi -f 镜像名1:TAG 镜像名2:TAG

删除全部
docker rmi -f $(docker images -qa)
```

## 容器命令

新建并启动容器
```
docker run [OPTIONS] IMAGE [COMMAND] [ARG...]

```

列出当前所有正在运行的容器
```
docker ps [OPTIONS]
```

退出容器
```
exit  容器停止退出

ctrl+P+Q 容器不停止退出
```

启动容器
```
docker start 容器ID或者容器名
```

重启容器
```
docker restart 容器ID或者容器名
```

停止容器
```
docker stop 容器ID或者容器名
```

强制停止容器
```
docker kill 容器ID或者容器名
```

删除已停止的容器
```
docker rm 容器ID

一次性删除多个容器
docker rm -f $(docker ps -a -q)
docker ps -a -q | xargs docker rm
```

启动守护式容器
```
docker run -d 容器名
```

查看容器日志
```
docker logs -f -t --tail 容器ID

* -t 是加入时间戳

* -f 跟随最新的日志打印

* --tail 数字 显示最后多少条
```

查看容器内运行的进程
```
docker top 容器ID
```

查看容器内部细节
```
docker inspect 容器ID
```

进入正在运行的容器并以命令行交互
```
docker exec -it 容器ID bashShell

重新进入docker attach 容器ID

attach 直接进入容器启动命令的终端，不会启动新的进程
exec 是在容器中打开新的终端，并且可以启动新的进程
```

从容器内拷贝文件到主机上
```
docker cp 容器ID:容器内路径 目的主机路径
```