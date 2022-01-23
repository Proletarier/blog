# ES6 新特性
##  let 关键字
特性：

let 关键字用来声明变量，使用 let 声明的变量有几个特点：
1. 不允许重复声明；
2. 块儿级作用域（局部变量）；
3. 不存在变量提升；
4. 不影响作用域链；

````javaScript
// let关键字使用示例： 
let a; // 单个声明 
let b,c,d; // 批量声明 
let e = 100; // 单个声明并赋值
let f = 521, g = 'iloveyou', h = []; // 批量声明并赋值
````
* 不允许重复声明：
````javaScript
// 1. 不允许重复声明； 
let dog = "狗"; 
let dog = "狗"; 
// 报错：Uncaught SyntaxError: Identifier 'dog' has already been declared
````

* 块儿级作用域（局部变量）：

````javaScript
// 2. 块儿级作用域（局部变量）； 
{ 
  let cat = "猫"; console.log(cat);
}
console.log(cat); 
// 报错：Uncaught ReferenceError: cat is not defined
````

* 不存在变量提升：

  什么是变量提升：
  就是在变量创建之前使用（比如输出：输出的是默认值），let不存在，var存在；

````javaScript
 console.log(people1); // 可输出默认值 
 console.log(people2); 
 // 报错：Uncaught ReferenceError: people2 is not defined var 
 people1 = "大哥"; // 存在变量提升 
 let people2 = "二哥"; // 不存在变量提升
````

* 不影响作用域链：
````javaScript
// 4. 不影响作用域链； 
// 什么是作用域链：很简单，就是代码块内有代码块，跟常规编程语言一样，上级代码块中 的局部变量下级可用
{ 
  let p = "大哥"; 
  function fn(){
     console.log(p); // 这里是可以使用的 }fn(); 
}
````

## const 关键字
const 关键字用来声明常量，const 声明有以下特点：
1. 声明必须赋初始值；
2. 标识符一般为大写（习惯）；
3. 不允许重复声明；
4. 值不允许修改；
5. 块儿级作用域（局部变量）；

````html
<!DOCTYPE html>
 <html>
   <head>
     <meta charset="utf-8"> 
     <title>const</title> 
   </head> 
   <body>
     <script> 
      // const声明常量 
      const DOG = "旺财";
      console.log(DOG); 
     </script> 
    </body> 
  </html>
````

## 变量和对象的解构赋值

什么是解构赋值：

ES6 允许按照一定模式，从数组和对象中提取值，对变量进行赋值，这被称为解构赋值；

````html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>解构赋值</title>
  </head>
  <body>
    <script>
      // ES6 允许按照一定模式，从数组和对象中提取值，对变量进行赋值，这被称为解构赋值；
      // 1、数组的解构赋值
      const F4 = ["大哥","二哥","三哥","四哥"]; let [a,b,c,d] = F4;
      // 这就相当于我们声明4个变量a,b,c,d，其值分别对应"大哥","二哥","三哥","四哥" 
      console.log(a + b + c + d); // 大哥二哥三哥四哥
      // 2、对象的解构赋值
      const F3 = {
        name : "大哥", age : 22,
        sex : "男",
        xiaopin : function(){ // 常用
        console.log("我会演小品！");
        }
      }
      let {name,age,sex,xiaopin} = F3; // 注意解构对象这里用的是{} 
      console.log(name + age + sex + xiaopin); // 大哥22男
      xiaopin(); // 此方法可以正常调用
    </script>
  </body>
</html>
````
## 模板字符串

模板字符串（template string）是增强版的字符串，用反引号（`）标识，特点：
* 字符串中可以出现换行符；
* 可以使用 ${xxx} 形式引用变量


````html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>模板字符串</title>
</head>
<body>
    <script>
        // ES6 引入新的声明字符串的方式 『``』 '' "" 
        //1. 声明
        // let str = `我也是一个字符串哦!`;
        // console.log(str, typeof str);

        //2. 内容中可以直接出现换行符
        let str = `<ul>
                    <li>沈腾</li>
                    <li>玛丽</li>
                    <li>魏翔</li>
                    <li>艾伦</li>
                    </ul>`;
        //3. 变量拼接
        let lovest = '魏翔';
        let out = `${lovest}是我心目中最搞笑的演员!!`;
        console.log(out);

    </script>
</body>
</html>
````
## 简化对象和函数写法
ES6 允许在大括号里面，直接写入变量和函数，作为对象的属性和方法。这样的书写更加简洁；

````html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>简化对象写法</title>
</head>
<body>
    <script>
        //ES6 允许在大括号里面，直接写入变量和函数，作为对象的属性和方法。
        //这样的书写更加简洁
        let name = '尚硅谷';
        let change = function(){
            console.log('我们可以改变你!!');
        }

        const school = {
            name,
            change,
            improve(){
                console.log("我们可以提高你的技能");
            }
        }

        console.log(school);

    </script>
</body>
</html>
````
## 箭头函数
ES6允许使用箭头（=>）定义函数，箭头函数提供了一种更加简洁的函数书写方式，箭头函数多用于匿名函数的定义；

箭头函数的注意点：

1. 如果形参只有一个，则小括号可以省略；
2. 函数体如果只有一条语句，则花括号可以省略，函数的返回值为该条语句的执行结果；
3. 箭头函数 this 指向声明时所在作用域下 this 的值；
4. 箭头函数不能作为构造函数实例化；
5. 不能使用 arguments；

特性：

1. 箭头函数的this是静态的，始终指向函数声明时所在作用域下的this的值；
2. 不能作为构造实例化对象；
3. 不能使用 arguments 变量；

代码演示及相关说明：

注意：箭头函数不会更改 this 指向，用来指定回调函数会非常合适；

````html
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>箭头函数</title>
</head>

<body>
    <script>
        // ES6 允许使用「箭头」（=>）定义函数。
        //声明一个函数
        // let fn = function(){

        // }
        // let fn = (a,b) => {
        //     return a + b;
        // }
        //调用函数
        // let result = fn(1, 2);
        // console.log(result);


        //1. this 是静态的. this 始终指向函数声明时所在作用域下的 this 的值
        function getName(){
            console.log(this.name);
        }
        let getName2 = () => {
            console.log(this.name);
        }

        //设置 window 对象的 name 属性
        window.name = '尚硅谷';
        const school = {
            name: "ATGUIGU"
        }

        //直接调用
        // getName();
        // getName2();

        //call 方法调用
        // getName.call(school);
        // getName2.call(school);

        //2. 不能作为构造实例化对象
        // let Person = (name, age) => {
        //     this.name = name;
        //     this.age = age;
        // }
        // let me = new Person('xiao',30);
        // console.log(me);

        //3. 不能使用 arguments 变量
        // let fn = () => {
        //     console.log(arguments);
        // }
        // fn(1,2,3);

        //4. 箭头函数的简写
            //1) 省略小括号, 当形参有且只有一个的时候
            // let add = n => {
            //     return n + n;
            // }
            // console.log(add(9));
            //2) 省略花括号, 当代码体只有一条语句的时候, 此时 return 必须省略
            // 而且语句的执行结果就是函数的返回值
            let pow = n => n * n;
                
            console.log(pow(8));

    </script>
</body>

</html>
````
## ES6中函数参数的默认值
ES允许给函数的参数赋初始值

````html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>函数参数默认值</title>
</head>
<body>
    <script>
        //ES6 允许给函数参数赋值初始值
        //1. 形参初始值 具有默认值的参数, 一般位置要靠后(潜规则)
        // function add(a,c=10,b) {
        //     return a + b + c;
        // }
        // let result = add(1,2);
        // console.log(result);

        //2. 与解构赋值结合
        function connect({host="127.0.0.1", username,password, port}){
            console.log(host)
            console.log(username)
            console.log(password)
            console.log(port)
        }
        connect({
            host: 'atguigu.com',
            username: 'root',
            password: 'root',
            port: 3306
        })
    </script>
</body>
</html>
```