# gift_giving
## 基于MySQL、Springboot，采用B/S结构设计实现一个网上赠送礼物系统
### 系统功能描述如下：

#### 1、系统基本功能
##### 后台功能：至少包括如下后台功能。

###### 1）用户管理
对系统中用户进行添加、信息修改（包括密码修改）和删除的维护。其中添加的用户仅为管理员用户，普通用户由用户前台注册进行添加。

###### 2）商品管理
对系统中的商品信息进行添加、修改和删除等维护，商品包括虚拟鲜花、实物鲜花和实际礼品。
商品信息至少包括商品名称、商品图片、商品价格、商品含义（花语）、商品类型等。

###### 3）订单管理
对系统中的订单进行查看，并对其派送情况进行维护。订单信息至少包括订单号、赠送人、赠予人、商品信息、数量、总价值等。
对订单的派送情况进行维护，包括派送状态、派送时间、派送人等。用户购买商品成功后，商品的赠送人的花芽数量会按商品价格减少，
若赠送的是虚拟鲜花，则赠予人当前花芽数量会按商品价格增加

###### 4）花芽购买管理
用户购买花芽的信息维护，包括购买人，购买时间，购买数量，消费金额等。其中10元可购买100花芽。

###### 5）花冠提现管理
用户花冠提现信息维护。
注：普通用户通过前台功能可将自己的花芽兑换为花冠，其中1500花芽可兑换一个花冠，每一个花冠在提现时可提现5元。

##### 前台功能：至少包括用户注册、今日男神、今日女神、送礼、回赠、我的等功能。
###### 1) 普通用户注册
用户在注册时，需要输入手机号和校验码（可选），同意用户协议后可进行注册。用户的帐号为该用户输入的手机号码，同一个手机号码仅可注册一次。
在进入注册界面后，可填写用户基本信息，包括昵称、密码、姓名、生日、头像、个性签名、美照、地址等。

###### 2) 查看今日男神（女神）
可以查看实时的收到礼物价值或送出礼物价值最高的男性（女性）。可以查看到该用户的生日、美照、个性签名等。
但不可见用户的昵称、头像、帐号，均以特定匿名信息或图片代替。可以查看到他送出的礼物和收出的礼物列表，
并能查看每单的详细信息。送礼（或收礼）者的信息均为匿名。

###### 3）送礼
选定特定用户，选定特定商品进行送礼，填写数量，进行留言，系统自动计算订单总额，当用户支付提交后，订单生成，
送礼人和收礼人的花芽数量更新，同时派送状态为未派送，订单状态为“提交未派送”、派送完成后，状态改为“完成”。注：送礼时礼物不可选“含羞草”和“向阳花”。
在选定送礼用户时，若对方尚未注册，则自动为该用户注册，但仅注册手机号、姓名和地址，其他信息等用户自己登录时进行完善。

###### 4）回赠
收到礼物后，用户可选回赠在商品回赠中若用户回赠的礼品是含羞草，则为拒绝。
若用户回赠的礼物为虚拟鲜花“向阳花”，则各对方公开自己的联系方式，一切信息对方可见。

###### 5）我的
用户可以检索自己的花芽、花冠数量及购买兑换情况；检索送礼，收礼财富及情况，并可按用户送礼时间、数量、金额进行排序；检索和修改个人信息等。

#### 2、用户描述
##### 系统分为前台功能和后台功能。后台主要由系统管理员维护，前台功能是普通用户可以操作的功能模块。
##### 系统的用户至少分为两种角色，分别为系统管理员、普通用户。在系统中，对用户分配相应的角色，并基于角色实现访问控制。
（1）系统管理员：负责系统后台维护功能模块，可以实现用户信息查看和修改、订单信息的查看、派送信息的维护等。
（2）普通用户：实现前台相应模块功能。
