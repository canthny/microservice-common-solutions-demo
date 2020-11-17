# es-data-design-demo

## es数据结构设计

### 嵌套类型，nested
{
 "pet_shop_id":"202006110001",
 "pet_shop_name":"歹宠",
 "pets":[
   {
     "pet_id":"20200530001",
     "pet_name":"咕咚",
     "pet_labels":["叛徒","好色","歹"],
     "pet_user_relations":[
       {
         "user_id":"001",
         "user_name":"主人一",
         "user_phone":"18709858763"
       },
       {
         "user_id":"002",
         "user_name":"主人二",
         "user_phone":"18868817837"
       }
     ]
   },
   {
     "pet_id":"20200530002",
     "pet_name":"布丁",
     "pet_labels":["乖","可爱","可怜"],
     "pet_user_relations":[
       {
         "user_id":"001",
         "user_name":"主人一",
         "user_phone":"18709858763"
       },
       {
         "user_id":"002",
         "user_name":"主人二",
         "user_phone":"18868817837"
       }
     ]
   }
 ]
}
    
### 第二版，拆分redis中间件
