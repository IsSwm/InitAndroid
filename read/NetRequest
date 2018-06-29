#### InitAndroid网络请求实现步骤体系。(首先确保你已经clone下[InitAndroid](https://github.com/IsSwm/InitAndroid))

1. 在AppContext 文件下修改请求的URL根目录即 HOST 的内容
2. 修改SwmRxHttpUtils.kt文件下的onNext(baseData)中服务器接口返回的格式：(第40行附近)
```
//  根据您的后台小姐姐返回的名称、格式来修改判断是否获取数据成功(PS:小哥哥几率很大-_-)
if (baseData.suc) {
    // 将服务器数据装入到接口中
    swmIsRequestComListener.onNext(baseData)
} else {
    //  显示服务器返回的错误信息
    SwmToastUtils.showToast(baseData.msg)
}
```
3.在APIConstants文件中生成接口的路径常量。例如：
const val USER_LOGIN = "user/oneLogin"//用户登录
4.在ApiService文件中写出对应的请求方法。例如：
```
//    用户登录接口
//    @FormUrlEncoded
@POST(APIConstants.USER_LOGIN)
fun userLogin(@Field("email") email: String,@Field("") password: String): Observable<BaseData>
```
5.在SwmRxHttpUtils中绑定请求。例如： (最后一步了，可以用了.)
```
/**
 * 用户登录
 * @return
 */
fun userLogin(email: String,password: String): SwmRxHttpUtils {
    AppContext.apiService!!.userLogin(email, password)
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(mBaseDataObserver)
    return this
}
```
6.这步是使用- -
