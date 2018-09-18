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



}
