package com.offcn.content.service;

import java.util.List;

public interface IGetContentSer {
    List getMsgContent();

    List getMsgConCateroey();

    int AddMsgCon(int ss,String title,String url,int num,boolean selected);
}
