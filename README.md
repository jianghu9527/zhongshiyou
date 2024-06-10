这个是一次面试时候，面试官要求写kotlin  viewmodel  room和数据的双向绑定的一个登录界面
这个工程需要注意是room的版本和life的版本和gradle的版本兼容问题


v1.0.0
更新内容：面试需求功能开发： 面试官要求写kotlin  viewmodel  room和数据的双向绑定的一个登录界面

v1.0.1
更新内容:新增部分项目结构

v1.0.2
新加入fragment模块

V1.0.3

cn.sichuan.cd.zsh
修改包路径
com.efficient.cd.production.app
 
面试基础
V1.0.4
部分kotlin代码忘记上传了

.zsh.LoginActivity  数据的双向绑定
mvvm.LoginsActivity  面试 项目结构mvvm  databinding

ModuleActivity  模块组件化
三个子模块


LoginViewModel  模块注意线程切换
viewModelScope.launch (Dispatchers.IO){
val user =   repository.loadAllUsers();

      _UserData.postValue(user);

        }

注意参数：isRelease  是否是多模块模式 

