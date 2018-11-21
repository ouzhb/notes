# 安装Go-lang
解压安装包，配置以下环境变量。注意，GOPATH一般用来存放go语言项目的源码
``` sh
export GOROOT=/usr/local/go
export GOPATH=/usr/local/gopath
export PATH=${PATH}:${GOROOT}/bin:${GOPATH}/bin
```
# 安装dep工具

[dep](https://github.com/golang/dep) 工具是用来管理go语言依赖的! 通过以下命令安装：

``` sh
## 安装需要搭梯子！！
curl https://raw.githubusercontent.com/golang/dep/master/install.sh | sh
```

# 安装SDK

```sh
## 安装需要搭梯子！！
mkdir -p $GOPATH/src/github.com/operator-framework
cd $GOPATH/src/github.com/operator-framework
git clone https://github.com/operator-framework/operator-sdk
cd operator-sdk
git checkout master
make dep
make install
```

# 创建测试工程

```sh
# Go的包管理一般基于Git，因此需要配置一个默认的Git仓库
git config --global user.email "linqing@ruijie.com.cn"
git config --global user.name LinQing2017
# 在GOPATH目录下创建工程模板
mkdir -p $GOPATH/src/github.com/example-inc/
cd $GOPATH/src/github.com/example-inc/
operator-sdk new memcached-operator
cd memcached-operator
```
