package com.hyl.core.util;

import cn.hutool.core.net.NetUtil;

import javax.swing.*;

public class ActiveMQUtil {

    public static void checkServer() {
        if(NetUtil.isUsableLocalPort(8161)) {
            JOptionPane.showMessageDialog(null, "ActiveMQ 服务器未启动 ");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        checkServer();
        JOptionPane.showMessageDialog(null, "ActiveMQ 服务器已启动 ");
    }
}
