package cn.white.ymc.todomaster.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment

/**
 *  界面跳转工具类
 *
 * @packageName: cn.white.ymc.todomaster.utils
 * @fileName: JumpUtil
 * @date: 2018/9/18  13:45
 * @author: ymc
 * @QQ:745612618
 *
 * out Activity 输出要是activity 或者子类
 */

object JumpUtil {

    /**
     * 不带参数的跳转
     *
     * @param context
     * @param targetClazz
     */
    fun overlay(context: Context, targetClazz: Class<out Activity>) :Unit {
        val mIntent = Intent(context, targetClazz)
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(mIntent)
    }

    /**
     * 带参数的 普通跳转
     * ? 可能为空
     */
    fun overlay(context: Context , targetClazz : Class<out Activity> , bundle : Bundle?) : Unit{
        val mIntent = Intent(context,targetClazz)
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if(bundle != null){
            mIntent.putExtras(bundle)
        }
        context.startActivity(mIntent)
    }

    /**
     * 带参数的 共享元素跳转
     *
     */
    fun overlay(context: Context , targetClazz : Class<out Activity> , bundle : Bundle? , option : Bundle) : Unit{
        val mIntent = Intent(context,targetClazz)
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if(bundle != null){
            mIntent.putExtras(bundle)
        }
        context.startActivity(mIntent,option)
    }

    /**
     * 带参数及 flag 共享元素跳转
     */
    fun overlay(context: Context, targetClazz: Class<out Activity>, bundle: Bundle?, flags: Int?) {
        val mIntent = Intent(context, targetClazz)
        if (bundle != null) {
            mIntent.putExtras(bundle)
        }
        if (flags != null) {
            mIntent.flags = flags
        }
        context.startActivity(mIntent)
    }

    /**
     * 界面跳转带 result
     *
     * @param context
     * @param targetClazz
     * @param requestCode
     * @param bundle
     */
    fun startForResult(context: Activity, targetClazz: Class<out Activity>, requestCode: Int, bundle: Bundle?) {
        val mIntent = Intent(context, targetClazz)
        if (bundle != null) {
            mIntent.putExtras(bundle)
        }
        context.startActivityForResult(mIntent, requestCode)
    }

    /**
     * fragment 界面跳转 带result
     *
     * @param fragment
     * @param targetClazz
     * @param requestCode
     * @param bundle
     */
    fun startForResult(fragment: Fragment, targetClazz: Class<out Activity>, requestCode: Int, bundle: Bundle?) {
        val mIntent = Intent(fragment.activity, targetClazz)
        if (bundle != null) {
            mIntent.putExtras(bundle)
        }
        fragment.startActivityForResult(mIntent, requestCode)
    }

}
