/*
 * Copyright 2003 电子计算技术研究所
 * All Right Reserved
 * Author ：alei
 * 编码日期格式：2013-9-12
 */
package com.mwh.test;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import org.jgroups.JChannel;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;
import org.jgroups.util.Util;

/**
 * 
 * 
 */
public class JGroupsTest extends ReceiverAdapter {
  final List<String> state = new LinkedList<String>();

  private JChannel jc = null;

  public void start() throws Exception {
    jc = new JChannel();
    jc.setReceiver(this);
    jc.connect("cluster_test");
    jc.getState(null, 10000);
    eventLoop();
    jc.close();
  }

  private void eventLoop() throws Exception {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    while (true) {
      System.out.println("---->  ");
      System.out.flush();
      String line = bufferedReader.readLine().toLowerCase();
      if ("exit".equals(line))
        break;
      org.jgroups.Message msg = new org.jgroups.Message(null, null, line);
      jc.send(msg);
    }
  }

  @Override
  public void receive(org.jgroups.Message msg) {
    String line = msg.getSrc() + ": " + msg.getObject();
    System.out.println(line);
    synchronized (state) {
      state.add(line);
    }

  }

  @Override
  public void viewAccepted(View view) {
    
    System.out.println("view-------------->" + view);
  }

  public void getState(OutputStream output) throws Exception {
    synchronized (state) {
      Util.objectToStream(state, new DataOutputStream(output));
    }
  }

  public void setState(InputStream input) throws Exception {
    List<String> list;
    list = (List<String>) Util.objectFromStream(new DataInputStream(input));
    synchronized (state) {
      state.clear();
      state.addAll(list);
    }
    System.out.println(list.size() + " messages in chat history):");
    for (String str : list) {
      System.out.println(str);
    }
  }

  public static void main(String[] args) throws Exception {
    new JGroupsTest().start();
  }
}
