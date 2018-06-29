### 通用的header顶部内容及事件。

**本项目力做一个能够帮助大家初始化android项目所用到的知识。[项目地址](https://github.com/IsSwm/InitAndroid)**

界面内容如下：(界面谁不会写(真正的干货是封装的超简单调用方法))
![](https://github.com/IsSwm/InitAndroid/blob/master/read/img/global_header.png)
[global_header.xml](https://github.com/IsSwm/InitAndroid/blob/master/app/src/main/res/layout/global_header.xml)

实战调用在activity中,方法封装在[BaseActivity中查看](https://github.com/IsSwm/InitAndroid/blob/master/app/src/main/java/com/jjj/initandroid/activity/BaseActivity.kt):
```
// 设置顶部内容
        setTitle("顶部Demo演示")
        // 退出activity
        setBackBtnFinish()
        // 右侧按钮事件
        setBtnClick(View.OnClickListener {
            SwmToastUtils.showToast("我是顶部Demo演示")
        })
```
四不四 so simple
  
