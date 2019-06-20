package com.hyl.core.util;

import cn.hutool.core.net.NetUtil;

import javax.swing.*;

public class RabbitMQUtil {

    public static void checkServer() {
        if(NetUtil.isUsableLocalPort(15672)) {
            JOptionPane.showMessageDialog(null, "RabbitMQ 服务器未启动 ");
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        checkServer();
        JOptionPane.showMessageDialog(null, "RabbitMQ 服务器已启动 ");
    }
}
