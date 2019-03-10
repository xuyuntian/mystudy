# JavaScript 重点
## 介绍
javascript区分大小写，使用Unicode字符集
注释分为2种
```
//单行注释

/*
多
行
注
释
*/
```
## 变量
### 声明方式

**var** 声明一个变量<br>
**let** 声明一个局部作用于的变量<br>
**const**声明一个块作用域的只读常量<br>

### 类型
#### 基本类型
1. Boolean
2. null
3. undefinded
4. Number
5. String
#### 对象
1. Object

javascript的变量是**动态**的，同一个变量可以被赋值为多种类型
```
var x = "123";//String 类型
var x = 123;//Number 类型
var x = {key:1,value:"123" };//对象类型
...
...
```
### 字面量
javascript的语法中使用字符来表达各种类型的常量，被称为字面量
1. Boolean类型的**字面量**
```
//Boolean类型只有2种字面量
var a = true;
var b = false;
```

2. Array类型的**字面量**
```
//如果','之间没有数据的话，该位置会用一个undefined的值代替。
var array = ['0','2',,];//array的内容为['0','2',empty]
console.log(array[3])//undefined
array = ['a','b']//array的内容为['a','b'];
```

### 作用域
## 语法

1. 条件语句
```
//condition为条件表达式,与其他语言类似
//简单的if 与其他语言类似
if(condition){

}
//if else
if(condition){
    ...
}else{
    ...
}
//if else if
if(condition){
    ...
}else if(condtion2){
    ...
}else{
    ...
}

//switch 语句 与其他语言类似
switch(condition){
    choice1:...;break;
    choice2:...;break;
    ...
    default:
    ....;
    break;
}
//三元运算符 与其他语言类似
condition？code1:code2;
```