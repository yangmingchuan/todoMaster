package cn.white.ymc.todomaster.utils.davik

import android.app.Activity
import java.util.*

/**
 *  activity 生命周期管理
 *
 * @packageName: cn.white.ymc.todomaster.utils
 * @fileName: AppDavikActivityUtil
 * @date: 2018/9/18  15:09
 * @author: ymc
 * @QQ:745612618
 */

private class AppDavikActivityUtil{

    /**
     * 线程安全 懒汉模式
     * 1. constructor 主构造函数
     * 2. companion  伴生对象  通过只使用类名作为限定符来调用
     */
//    class AppDavikActivityUtil constructor(){
//        companion object {
//            var instance : AppDavikActivityUtil? = null
//
//            get() {
//                if(field == null){
//                    field = AppDavikActivityUtil()
//                }
//                return field
//            }
//        }
//
//        /**
//         *  @Synchronized 保证线程安全
//         *  !! 一定不能为空 和 ？ 相反
//         */
//        @Synchronized
//        fun get():AppDavikActivityUtil{
//            return instance!!
//        }
//    }


    /**
     * Lazy是接受一个 lambda 并返回一个 Lazy 实例的函数，
     * 返回的实例可以作为实现延迟属性的委托：
     * 第一次调用 get() 会执行已传递给 lazy() 的 lambda 表达式并记录结果，
     * 后续调用 get() 只是返回记录的结果。
     */
    class AppDavikActivityUtil constructor(){
        companion object {
            val instance: AppDavikActivityUtil by lazy{
                /**
                 * 调用lambda 表达式并记录，以后调用只会返回 记录结果
                 */
                AppDavikActivityUtil()
            }
        }
    }

    /**
     * 存储ActivityStack
     */
    private var activityStack: Stack<Activity>? = Stack<Activity>()

    /**
     * 移除 当前activity
     */
    fun removeActivity( activity : Activity?){
        var activity = activity
        if (null != activity) {
            activityStack!!.remove(activity)
            activity.finish()
        }
    }

    /**
     * 移除所有的 activity
     */
    fun removeAllActivity(){
        if(null!=activityStack && activityStack!!.size >0){
            activityStack!!.forEach { it.finish() }
            activityStack!!.clear()
        }
        System.gc()
        System.exit(0)
    }

    /**
     * 获取当前 activity
     */
    fun currentActivity():Activity?{
        var activity: Activity? = null
        if (!activityStack!!.empty()) {
            activity = activityStack!!.lastElement()
        }
        return activity
    }


    /**
     * 将Act纳入推栈集合中
     * @param activity Act对象
     */
    fun addActivity(activity: Activity) {
        if (null == activityStack) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

}