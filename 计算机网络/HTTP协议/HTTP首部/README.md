## HTTP/1.1首部字段
### 通用首部字段：
|首部字段名     |说明            |
|--------------|----------------|
|Cache-Control	|控制缓存的行为   |
|Connection   	|逐跳首部、连接的管理 |
|Date	         |创建报文的日期时间   |
|Pragma       	|报文指令            |
|Trailer      	|报文末端的首部一览   |
|Transfer-Encoding	|指定报文主体的传输编码方式|
|Upgrade	      |升级为其他协议       |
|Via	          |代理服务器的相关信息 |
|Waring       	|错误通知            |


### 请求首部字段：

|首部字段名     |说明            |
|--------------|----------------|
Accept	        | 用户代理可处理的媒体类型
Accept-Charset	| 优先的字符集
Accept-Language|	优先的语言
Authorization	 | Web认证信息
Expect	        | 期待服务器的特定行为
From	          | 用户的电子邮箱地址
Host	          | 请求资源所在的服务器
If-Match	      | 比较实体标记
If-Modified-Since | 比较资源的更新时间
If-None-Match	 | 比较实体标记
If-Range	      | 资源未更新时发送实体Byte的范围请求
If-Unmodified-Since	| 比较资源的更新时间
Max-Forwards	  | 最大传输逐跳数
Proxy-Authorization	| 代理服务器要求客户端的认证信息
Range	         | 实体字节范围请求
Referer	       | 对请求中的URI的原始获取方
TE	            | 传输编码的优先级
User-Agent	    | HTTP客户端程序的信息


### 响应首部字段：

|首部字段名 |	说明|
|--------------|----------------|
Accept-Ranges	| 是否接受字节范围请求
Age |	推算资源创建经过时间
ETag |	资源的匹配信息
Location |	令客户端重定向至指定的URI
Proxy-Authenticate |	代理服务器对客户端的认证信息
Retry-After |	对再次发起请求的时机要求
Server |	HTTP服务器的安装信息
Vary |	代理服务器缓存的管理信息
WWW-Authenticate |	服务器对客户端的认证信息

### 实体首部字段：

|首部字段名 |	说明|
|--------------|----------------|
Allow	资源可支持的HTTP方法
Content-Encoding |	实体主体适用的编码方式
Content-Language | 实体主体的自然语言
Content-Length |	实体主体的大小
Content-Location |	替代对应资源的URI
Content-MD5 |	实体主体的报文摘要
Content-Range |	实体主体的位置范围
Content-Type |	实体主体的媒体类型
Expires |	实体主体过期的日期时间
Last-Modified |	资源的最后修改日期时间

## 非HTTP/1.1首部字段
在HTTP协议通信交互中使用到的首部字段不限于RFC2616中定义的47中首部字段。还有Cookie、set-Cookie和Content-Disposition等在其他RFC中定义的首部字段。

1) 为Cookie服务的首部字段
当前广泛使用的Cookie是在网景公司制定的标准上进行扩展的产物，其作用是用户识别和状态管理，即保存用户的身份信息，登录时间等信息，为Cookie服务的首部字段是Set-Cookie，他有以下属性：

  | 属性 |	说明|
  |--------------|----------------|
  | Name | Cookie的名称和值，必须 |
  expires | Cookie有效期
  path | 服务器上适用Cookie的文件目录
  domin | Cookie适用域名（默认为创建的服务器的域名）
  Secure | 仅在HTTP安全通信时才发送Cookie
  HTTPOnly | 使Cookie不能被JavaScript访问

2) 其他首部字段

| 属性 |	说明|
|--------------|----------------|
X-Frame-Options | 属于响应首部，用于控制网站内容在其他Web网站的Frame标签中的显示问题，主要是为了防止点击劫持攻击，其值有：DENY：拒绝；SAMEORIGIN：仅同源域名下的页面允许被加载。
X-XSS-Protextion | 属于响应首部字段，用于控制浏览器XSS（跨站脚本攻击）防护机制的开关，"1"表示开启。
DNT | 属于请求首部，表示拒绝个人信息被收集，拒绝被精准广告追踪的一种方法，1表示开启，需要服务器支持
P3P | 属于响应首部，通过利用P3P可以让个人信息变成一种仅供程序理解的形式，以达到保护用户隐私的目的，但有很多辅助工作需要做。