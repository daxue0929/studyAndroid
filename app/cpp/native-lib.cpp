//
// Created by NING MEI on 2019/12/18.
//

#include<jni.h>
#include<string.h>

extern "C" JNIEXPORT jsstring;

JNICALL
Java_com_test_ndk2_MainActivity_stringFormJNI(
    JNIENV * env,
    jobject /*this*/) {

    std:string hello = "Hello world C++";

    return env->NewStringUTF(hello.c_str);
}