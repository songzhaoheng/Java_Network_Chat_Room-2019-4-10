# Java_Network_Chat_Room-2019-4-10

Java网络聊天室简介：
程序能够连接他人主机（局域网内）、能够进行用户的注册、登录、用户密码的修改；同时能够给对方发送文字内容，并且能够显示聊天时间；局域网内聊天不能超过50个连接（因为服务器套接字ServerSocket类，最多连接的队列数为50）；注册用户时用户名和密码必须是数字和字符的组合形式；用户在填写用户名、密码时不能为空；能够通过数据库交互判断用户名和密码是否一致（使用MyBatis框架进行数据库连接与操作）。
