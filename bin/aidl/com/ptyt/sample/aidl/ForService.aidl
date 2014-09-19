package com.ptyt.sample.aidl;
import  com.ptyt.sample.aidl.ForActivity;

interface ForService{
	void registerTestCall(ForActivity cb);  
    void invokCallBack(); 
    }