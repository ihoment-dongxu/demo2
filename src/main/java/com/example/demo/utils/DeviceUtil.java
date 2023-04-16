package com.example.demo.utils;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户代理信息工具
 * 例如浏览器类型、操作系统、设备类型等等
 *
 * @author dongxu
 */
public class DeviceUtil {

    public static String getDeviceSystem(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        //解析agent字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        //获取操作系统对象
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();
        return operatingSystem.getName();
    }

    public static void main(String[] args) {
        String agent = "Mozilla/5.0 (Linux; Android 10; MI 9 Build/QKQ1.190825.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/76.0.3809.89 Mobile Safari/537.36 T7/11.21 SP-engine/2.17.0 baiduboxapp/11.21.0.10 (Baidu; P1 10) NABar/1.0";
        //解析agent字符串
        UserAgent userAgent = UserAgent.parseUserAgentString(agent);
        //获取浏览器对象
        Browser browser = userAgent.getBrowser();
        //获取操作系统对象
        OperatingSystem operatingSystem = userAgent.getOperatingSystem();

        System.out.println("agent:" + agent);
        System.out.println("浏览器名:" + browser.getName());
        System.out.println("浏览器类型:" + browser.getBrowserType());
        System.out.println("浏览器家族:" + browser.getGroup());
        System.out.println("浏览器生产厂商:" + browser.getManufacturer());
        System.out.println("浏览器使用的渲染引擎:" + browser.getRenderingEngine());
        System.out.println("浏览器版本:" + userAgent.getBrowserVersion());


        System.out.println("操作系统名:" + operatingSystem.getName());
        System.out.println("访问设备类型:" + operatingSystem.getDeviceType());
        System.out.println("操作系统家族:" + operatingSystem.getGroup());
        System.out.println("操作系统生产厂商:" + operatingSystem.getManufacturer());
    }


}
