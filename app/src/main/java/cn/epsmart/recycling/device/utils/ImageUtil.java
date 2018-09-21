package cn.epsmart.recycling.device.utils;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import cn.epsmart.recycling.device.R;
import cn.epsmart.recycling.device.base.BaseApplication;

/**
 * @Author: Administrator
 * @Time: 2018 2018/9/19 14:42
 * @description: （添加一句描述）
 */
public class ImageUtil {

    /**
     * 通过uri 设置图片
     *
     * @param resId 加载资源文件
     * @param defId 默认资源图片
     */
    public static void setImageViewResId(ImageView imageView, int resId, int defId) {
        RequestOptions options = new RequestOptions()
                .placeholder(defId)    //加载成功之前占位图
                .error(defId)    //加载错误之后的错误图

                //.override(400, 400)    //指定图片的尺寸
                //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
                .fitCenter()
                //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
                .centerCrop()
                .circleCrop()//指定图片的缩放类型为centerCrop （圆形）
                .skipMemoryCache(true)    //跳过内存缓存
                .diskCacheStrategy(DiskCacheStrategy.ALL)    //缓存所有版本的图像
                .diskCacheStrategy(DiskCacheStrategy.NONE)    //跳过磁盘缓存
                .diskCacheStrategy(DiskCacheStrategy.DATA)    //只缓存原来分辨率的图片
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);    //只缓存最终的图片
        Glide.with(BaseApplication.getContext())
                .load(resId)
                .apply(options)
                .into(imageView);
    }

    public static void setImageViewResId(ImageView imageView, String resId, int defId) {
        RequestOptions options = new RequestOptions()
                .placeholder(defId)    //加载成功之前占位图
                .error(defId)    //加载错误之后的错误图
               // .dontTransform()
                //.override(400, 400)    //指定图片的尺寸
                //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
                //.fitCenter()
                //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
               // .centerCrop()
                //.circleCrop()//指定图片的缩放类型为centerCrop （圆形）
                .skipMemoryCache(true)    //跳过内存缓存
                /*.diskCacheStrategy(DiskCacheStrategy.ALL)    //缓存所有版本的图像
                .diskCacheStrategy(DiskCacheStrategy.NONE)    //跳过磁盘缓存
                .diskCacheStrategy(DiskCacheStrategy.DATA) */   //只缓存原来分辨率的图片
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);    //只缓存最终的图片
        Glide.with(BaseApplication.getContext())
                .asBitmap()///只加载静态图片，如果是git图片则只加载第一帧。
                .load(resId)
                .apply(options)
                .into(imageView);
    }

    public static void load(String uri, ImageView view, int placeholder) {
        Glide.with(BaseApplication.getContext())
                .load(uri)
                .into(view);
    }
}
