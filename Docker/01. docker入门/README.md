## 什么是docker?
其实docker 就是一项容器虚拟化技术.

什么是容器虚拟化技术? 举出一个简单的例子, 就是把开发环境和代码一起打包做成一个容器, 放在其他的电脑上就可以直接运行. 类似虚拟机一样, 把环境配好, 项目代码往里面一放, 就可以运行了.
但是 容器虚拟化和虚拟机还是有很大区别的, 只是说前者是建立在后者的基础上的.

## docker 能做什么?
一次构建,随处运行!
更快捷的应用交付和部署;更便捷的升级和扩缩容; 更加简单的系统运维;更高效的计算机资源利用.
因为它解决了 虚拟机存在的资源占用多;冗余步骤多和启动慢的缺点.
因为它把虚拟机中, 模拟硬件,以及不必要的东西都给剔除了, 就只保留了必要的内核和用户自己根据需要安装的容器,所以就会更加轻便.

## docker 三要素
* 镜像(image):Docker 镜像, image, 就是一个只读的模版, 镜像可以用来创建Docker 容器, 一个镜像可以创建很多容器.
* 容器(container): Docker 利用容器独立运行的一个或者一组应用. 容器就是镜像创建的运行实例. 可以把容器看成一个简易版的linux 系统.
* 仓库(repository): 就是存放镜像的地方

## 安装 docker
自己去 官网看吧


## 常用命令

```
启动docker
sudo service docker start

设置开机启动
systemctl enable docker

将指定用户添加到用户组
usermod -aG docker root

安装后查看Docker版本
docker version
```

````
attach    Attach to a running container                 # 当前 shell 下 attach 连接指定运行镜像
build     Build an image from a Dockerfile              # 通过 Dockerfile 定制镜像
commit    Create a new image from a container changes   # 提交当前容器为新的镜像
cp        Copy files/folders from the containers filesystem to the host path   #从容器中拷贝指定文件或者目录到宿主机中
create    Create a new container                        # 创建一个新的容器，同 run，但不启动容器
diff      Inspect changes on a container’s filesystem   # 查看 docker 容器变化
events    Get real time events from the server          # 从 docker 服务获取容器实时事件
exec      Run a command in an existing container        # 在已存在的容器上运行命令
export    Stream the contents of a container as a tar archive   # 导出容器的内容流作为一个 tar 归档文件[对应 import ]
history   Show the history of an image                  # 展示一个镜像形成历史
images    List images                                   # 列出系统当前镜像
import    Create a new filesystem image from the contents of a tarball # 从tar包中的内容创建一个新的文件系统映像[对应export]
info      Display system-wide information               # 显示系统相关信息
inspect   Return low-level information on a container   # 查看容器详细信息
kill      Kill a running container                      # kill 指定 docker 容器
load      Load an image from a tar archive              # 从一个 tar 包中加载一个镜像[对应 save]
login     Register or Login to the docker registry server    # 注册或者登陆一个 docker 源服务器
logout    Log out from a Docker registry server          # 从当前 Docker registry 退出
logs      Fetch the logs of a container                 # 输出当前容器日志信息
port      Lookup the public-facing port which is NAT-ed to PRIVATE_PORT    # 查看映射端口对应的容器内部源端口
pause     Pause all processes within a container        # 暂停容器
ps        List containers                               # 列出容器列表
pull      Pull an image or a repository from the docker registry server   # 从docker镜像源服务器拉取指定镜像或者库镜像
push      Push an image or a repository to the docker registry server    # 推送指定镜像或者库镜像至docker源服务器
restart   Restart a running container                   # 重启运行的容器
rm        Remove one or more containers                 # 移除一个或者多个容器
rmi       Remove one or more images             # 移除一个或多个镜像[无容器使用该镜像才可删除，否则需删除相关容器才可继续或 -f 强制删除]
run       Run a command in a new container              # 创建一个新的容器并运行一个命令
save      Save an image to a tar archive                # 保存一个镜像为一个 tar 包[对应 load]
search    Search for an image on the Docker Hub         # 在 docker hub 中搜索镜像
start     Start a stopped containers                    # 启动容器
stop      Stop a running containers                     # 停止容器
tag       Tag an image into a repository                # 给源中镜像打标签
top       Lookup the running processes of a container   # 查看容器中运行的进程信息
unpause   Unpause a paused container                    # 取消暂停容器
version   Show the docker version information           # 查看 docker 版本号
wait      Block until a container stops, then print its exit code   # 截取容器停止时的退出状态值
```