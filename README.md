## 树洞网

集成开发环境：IDEA 2019
数据库：mysql-8.0.16-winx64

##部署与使用

1.导入数据库文件和测试数据（下面）;
2.配置application.yml文件：修改端口号（默认端口9999），使数据库的名称以及密码与本机适应；
3.运行项目，在http://localhost:9999/index上访问主页；

## 资料

创建项目：https://spring.io/guides
模型网站：https://elasticsearch.cn

## 数据库文件以及测试数据：

-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: treehole
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `commentator` varchar(30) COLLATE utf8_bin NOT NULL,
  `gmt_create` bigint(60) NOT NULL,
  `like_count` int(11) DEFAULT '0',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `gmt_modified` bigint(60) NOT NULL,
  `comment_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (1,1,1,'2250432111@qq.com',1584967365435,0,'这是一个回复',1584967365435,0),(2,1,1,'2250432111@qq.com',1584967397558,0,'这是一个回复',1584967397558,0),(3,1,1,'2250432111@qq.com',1584967799320,0,'这是一个回复',1584967799320,0),(4,1,1,'2250432111@qq.com',1585048420778,0,'这是一个回复',1585048420778,0),(5,1,1,'2250432111@qq.com',1585048484690,0,'这是一个回复',1585048484690,0),(9,15,1,'2250432111@qq.com',1585104211581,0,'1515',1585104211581,1),(10,15,1,'2250432111@qq.com',1585104836164,0,'干啥呢老弟',1585104836164,1),(11,15,1,'111@qq.com',1585112488058,0,'怎了啦',1585112488058,1),(12,15,1,'2250432111@qq.com',1585116759693,0,'气你',1585116759692,1),(13,16,1,'2250432111@qq.com',1585122343141,1,'睡大觉！',1585122343141,0),(14,15,1,'2250432111@qq.com',1585124000794,0,'12314',1585124000794,0),(15,17,1,'2250432111@qq.com',1585124381458,0,'新问题~',1585124381457,2),(16,17,1,'2250432111@qq.com',1585124407053,0,'666',1585124407053,0),(17,17,1,'2250432111@qq.com',1585124436344,0,'777',1585124436344,0),(18,17,1,'2250432111@qq.com',1585124536924,0,'hhahah',1585124536924,0),(19,15,1,'25872587@qq.com',1585136354269,0,'hhah',1585136354269,0),(20,17,1,'2250432111@qq.com',1585138186053,0,'重构后的新评论~',1585138186053,0),(22,17,1,'111@qq.com',1585145135621,0,'重构一下吧',1585145135621,0),(26,15,2,'1767978484@qq.com',1585192391060,0,'你这个问题问的不错~',1585192391060,0),(27,15,2,'1767978484@qq.com',1585192477354,0,'真的很不错~',1585192477354,0),(28,18,1,'1767978484@qq.com',1585227164354,3,'挺好的！',1585227164354,2),(29,28,2,'1767978484@qq.com',1585227171067,0,'不错！',1585227171067,0),(37,19,1,'2250432111@qq.com',1585285443989,0,'不许笑。最新回复！',1585285443989,1),(38,37,2,'1767978484@qq.com',1585285679733,0,'笑一下怎么啦？',1585285679733,0),(39,15,1,'2250432111@qq.com',1585285766351,0,'你还要我怎呀？',1585285766351,1),(40,14,1,'2250432111@qq.com',1585290780790,0,'我教你吖  加我qq2250432111！',1585290780790,1),(41,40,2,'2250432111@qq.com',1585290867686,0,'不想加！',1585290867686,0),(42,39,2,'2250432111@qq.com',1585291432697,0,'唱、跳、Rap',1585291432697,0),(43,28,2,'1767978484@qq.com',1585292481546,0,'ok',1585292481546,0),(44,19,1,'25872587@qq.com',1585292688945,0,'棒哦~',1585292688945,0),(45,11,2,'25872587@qq.com',1585292802813,0,'不错~',1585292802813,0),(46,12,2,'25872587@qq.com',1585292812514,0,'真的不错~',1585292812514,0),(47,9,2,'2250432111@qq.com',1585292855031,0,'有问题！',1585292855031,0),(48,10,2,'2250432111@qq.com',1585292860525,0,'不太行！',1585292860525,0),(49,22,1,'7758258@qq.com',1585379038928,3,'哈哈哈',1585379038928,1),(50,49,2,'7758258@qq.com',1585379044148,0,'个性！',1585379044148,0),(51,23,1,'7777777@qq.com',1585389417889,2,'你的网名真的nb',1585389417889,2),(52,51,2,'7777777@qq.com',1585389430103,0,'狠人！',1585389430103,0),(53,51,2,'2250432111@qq.com',1587118779109,0,'不错！',1587118779109,0),(54,20,1,'2250432111@qq.com',1587118822401,0,'我太难了吧！',1587118822401,1),(55,54,2,'2250432111@qq.com',1587118832220,0,'真的困呐！',1587118832220,0),(56,22,1,'11121@qq.com',1590974394414,1,'nb',1590974394414,1),(57,56,2,'11121@qq.com',1590974406767,0,'白总nb',1590974406767,0);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likehistory`
--

DROP TABLE IF EXISTS `likehistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likehistory` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `likecomment` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likehistory`
--

LOCK TABLES `likehistory` WRITE;
/*!40000 ALTER TABLE `likehistory` DISABLE KEYS */;
INSERT INTO `likehistory` VALUES (14,'2250432111@qq.com',49),(15,'111@qq.com',49),(16,'25872587@qq.com',49),(17,'25872587@qq.com',28),(18,'7758258@qq.com',28),(19,'2250432111@qq.com',28),(20,'2250432111@qq.com',13),(21,'7777777@qq.com',51),(22,'2250432111@qq.com',51),(23,'11121@qq.com',56);
/*!40000 ALTER TABLE `likehistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `notifier` varchar(30) COLLATE utf8_bin NOT NULL,
  `receiver` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `outer_id` int(11) NOT NULL COMMENT '发布日期',
  `type` int(11) NOT NULL,
  `gmt_create` bigint(60) NOT NULL,
  `status` int(11) NOT NULL COMMENT '评论',
  `notifier_name` varchar(100) COLLATE utf8_bin NOT NULL,
  `outer_title` varchar(256) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (4,'2250432111@qq.com','2250432111@qq.com',14,1,1585290780822,1,'zhang','关于解决Input标签下的回显问题'),(5,'2250432111@qq.com','2250432111@qq.com',14,2,1585290867701,1,'zhang','关于解决Input标签下的回显问题'),(6,'2250432111@qq.com','2250432111@qq.com',15,2,1585291432736,1,'zhang','如何可以有效地减肥呢？'),(7,'1767978484@qq.com','1767978484@qq.com',18,2,1585292481580,1,'LiLei','测试一下标签筛选功能~'),(8,'25872587@qq.com','1767978484@qq.com',19,1,1585292688964,1,'苦逼程序员','哈哈哈哈我笑'),(9,'25872587@qq.com','111@qq.com',15,2,1585292802827,1,'苦逼程序员','如何可以有效地减肥呢？'),(10,'25872587@qq.com','2250432111@qq.com',15,2,1585292812538,1,'苦逼程序员','如何可以有效地减肥呢？'),(11,'2250432111@qq.com','2250432111@qq.com',15,2,1585292855047,1,'zhang','如何可以有效地减肥呢？'),(12,'2250432111@qq.com','2250432111@qq.com',15,2,1585292860547,1,'zhang','如何可以有效地减肥呢？'),(13,'7758258@qq.com','7758258@qq.com',22,1,1585379038952,1,'无敌帅的小志','测试一下吧~'),(14,'7758258@qq.com','7758258@qq.com',22,2,1585379044159,1,'无敌帅的小志','测试一下吧~'),(15,'7777777@qq.com','7777777@qq.com',23,1,1585389417904,1,'Java之王','终极测试~'),(16,'7777777@qq.com','7777777@qq.com',23,2,1585389430120,1,'Java之王','终极测试~'),(17,'2250432111@qq.com','7777777@qq.com',23,2,1587118779126,0,'zhang','终极测试~'),(18,'2250432111@qq.com','2250432111@qq.com',20,1,1587118822413,1,'zhang','帮忙看一下这段代码~'),(19,'2250432111@qq.com','2250432111@qq.com',20,2,1587118832244,1,'zhang','帮忙看一下这段代码~'),(20,'11121@qq.com','7758258@qq.com',22,1,1590974394438,0,'baizong','测试一下吧~'),(21,'11121@qq.com','11121@qq.com',22,2,1590974406783,1,'baizong','测试一下吧~');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `description` longtext,
  `gmt_create` bigint(60) DEFAULT NULL,
  `gmt_modified` bigint(60) DEFAULT NULL,
  `creator` varchar(45) DEFAULT NULL,
  `comment_count` int(11) DEFAULT '0',
  `view_count` int(11) DEFAULT '0',
  `like_count` int(11) DEFAULT '0',
  `tag` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'如何在html中添加分割线','问题解决：（网站）\nhttps://blog.csdn.net/qhwc2009/article/details/45047365',1584319903726,1584319903726,'2250432111@qq.com',1,22,0,'html，学习笔记'),(2,'Bootstrap网站','简洁、直观、强悍的前端开发框架，让web开发更迅速、简单！\nhttps://www.bootcss.com/',1584348046642,1584348046642,'2250432111@qq.com',0,7,0,'工具网站'),(3,'中文论坛社区','详细地址：https://elasticsearch.cn/\n',1584352401511,1584352401511,'1767978484@qq.com',0,6,0,'工具网站'),(4,'xduzhang的个人博客网站地址','http://www.zhangxdu.top/',1584356920005,1584356920005,'111@qq.com',0,2,0,'工具网站'),(5,'Spring MVC中数据绑定流程原理及应用','参考：http://www.zhangxdu.top:8080/blog/13',1584357013373,1584357013373,'2250432111@qq.com',0,12,0,'问题解决，学习笔记'),(6,'通过宝塔Linux面板连接云服务器进行相关配置（CentOS7）','详情看博客吧',1584357095493,1584357095493,'111@qq.com',0,3,0,'问题解决'),(7,'如何通过Shell脚本启动部署在服务器中的jar包','博客里面有！！',1584357125875,1584357125875,'111@qq.com',0,4,0,'学习笔记'),(8,'好久没有打球了.....','什么时候可以开学a？',1584705753467,1584705753467,'111@qq.com',0,1,0,'吐槽'),(9,'网吧何时开业呢？','迫不及待了,哈哈哈哈！',1584767675183,1584767675183,'1767978484@qq.com',0,1,0,'问题询问'),(10,'NBA球星怎么都得了新冠？','神奇...',1584767716847,1584767716847,'1767978484@qq.com',0,2,0,'吐槽'),(11,'疫苗研制ok？','据说是这样的,是真是假咱也不知道...',1584767791711,1584767791711,'1767978484@qq.com',0,3,0,'吐槽'),(12,'论程序员能有多么苦','掉头发就完事了hah',1584778856624,1584778856624,'25872587@qq.com',0,1,0,'吐槽'),(13,'Input标签下的回显问题(等待被删除)','使用value！！！哈哈哈哈哈！',1584884140102,1584884140102,'2250432111@qq.com',0,19,0,'学习'),(14,'关于解决Input标签下的回显问题','使用value！！！1212',1584885594843,1584885594843,'2250432111@qq.com',1,24,0,'学习'),(15,'如何可以有效地减肥呢？','jump jump jump！',1584885642332,1584885642332,'2250432111@qq.com',7,120,0,'经验分享'),(16,'怎么培养一个兴趣爱好','闭眼睛',1585122316511,NULL,'25872587@qq.com',1,18,0,'吐槽'),(17,'我想升天~','不如跳舞~',1585122440731,NULL,'25872587@qq.com',7,85,0,'吐槽'),(18,'测试一下标签筛选功能~','看看标签存在不存在库中!!在！',1585227136927,NULL,'1767978484@qq.com',1,18,0,'踩坑记录'),(19,'哈哈哈哈我笑','我就笑~',1585227238809,NULL,'1767978484@qq.com',2,31,0,'踩坑记录,生活琐事,日常吐槽'),(20,'帮忙看一下这段代码~','```java\r\n你好么？dear\r\n   public NotificationDTO read(Integer id, User user) {\r\n        Notification notification = notificationMapper.selectById(id);\r\n        if(notification == null){\r\n            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);\r\n        }\r\n        if(!Objects.equals(notification.getReceiver(),user.getId())){\r\n            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);\r\n        }\r\n        notification.setStatus(NotificationStatusEnum.READ.getStatus());\r\n        notificationMapper.updateById(notification);\r\n\r\n        NotificationDTO notificationDTO = new NotificationDTO();\r\n        BeanUtils.copyProperties(notification,notificationDTO);  //复制相同的属性\r\n        notificationDTO.setTypeName(NotificationEnum.nameOfType(notification.getType()));\r\n        return notificationDTO;\r\n\r\n    }\r\n```',1585294170008,NULL,'2250432111@qq.com',1,56,0,'问题询问'),(21,'最后一个测试~','看一看吧！',1585370318614,NULL,'111@qq.com',0,5,0,'生活琐事'),(22,'测试一下吧~','# 看看可不可以成功~\r\n应该差不多吧~！！还行！',1585379024490,NULL,'7758258@qq.com',2,85,0,'生活琐事'),(23,'终极测试~','哈哈哈哈不错！',1585389390783,NULL,'7777777@qq.com',1,10,0,'日常吐槽'),(24,'真好~','hhah1',1585403219322,NULL,'111@qq.com',0,1,0,'生活琐事'),(25,'陕西什么时候开学？','# 我盲猜一波27号\r\n行吗！',1587118973540,NULL,'2250432111@qq.com',0,1,0,'日常吐槽'),(26,'我吐了','吐了',1590314668625,NULL,'2250432111@qq.com',0,2,0,'日常吐槽'),(27,'白总nb','nb',1590974297405,NULL,'11121@qq.com',0,2,0,'日常吐槽');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(30) COLLATE utf8_bin NOT NULL,
  `name` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `date` varchar(30) COLLATE utf8_bin NOT NULL,
  `imgurl` varchar(256) COLLATE utf8_bin DEFAULT 'null',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('11121@qq.com','baizong','1234','2020-06-01 09:17:31.76','https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3984473917,238095211&fm=26&gp=0.jpg'),('111@qq.com','Tom','1234','2020-03-16 19:07:00.268','https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3162816245,4100265419&fm=111&gp=0.jpg'),('1767978484@qq.com','LiLei','1234','2020-03-15 09:02:02.802','http://inews.gtimg.com/newsapp_match/0/11323547242/0.jpg'),('2250432111@qq.com','zhang','1234','2020-03-15 09:05:42.662','http://pic2.zhimg.com/50/v2-fb824dbb6578831f7b5d92accdae753a_hd.jpg'),('25872587@qq.com','苦逼程序员','1234','2020-03-21 16:20:10.154','https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2798216820,1841993320&fm=26&gp=0.jpg'),('7758258@qq.com','无敌帅的小志','1234','2020-03-21 16:17:24.935','https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2453195201,1973886115&fm=26&gp=0.jpg'),('7777777@qq.com','Java之王','1234','2020-03-28 17:55:49.51','https://dss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4016333918,4269266815&fm=26&gp=0.jpg');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-05 15:35:40

