#  IO工具
## IOSTAT
统计CPU使用率和磁盘IO使用率
iostat -d sda 1
## IOTOP
iotop -p 22600 # 打印指定进程
iotop --process # 按照进程进行打印，默认时按照线程打印
## dstat
能够同时查看IO、网络、CPU信息、进程数目信息以及其他统计信息
## atop
类似TOP命令，统计更全
## ioping
用来测试磁盘（目录）是否可写，以及读写延迟