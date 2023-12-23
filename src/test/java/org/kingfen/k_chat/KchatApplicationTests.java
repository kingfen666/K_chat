package org.kingfen.k_chat;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import org.kingfen.k_chat.ChatGpt.MakeChat;
import org.kingfen.k_chat.util.Util;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.*;

@SpringBootTest

public class KchatApplicationTests {
    public static void main(String[] args) throws Exception{
        String s = MakeChat.textToSpeech("你好");
        System.out.println(s);
    }
    @Test
    public void test1(){
        int []arr={1,2,3,54,15,19,97,19,9,19,19,19,1917,1,8};
        sort4(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }
    @Test
    public void test() throws Exception{
        int []arr=new int[1000000];
        Random random=new Random();
        for (int i = 0; i < 1000000; i++) {
            arr[i]=random.nextInt(0, (int) (Math.pow(2,31)-1));
        }
        int []arr1=Arrays.copyOf(arr,arr.length);
        int []arr2=Arrays.copyOf(arr,arr.length);
        int []arr3=Arrays.copyOf(arr,arr.length);
        int []arr4=Arrays.copyOf(arr,arr.length);
        long l = System.currentTimeMillis();
        Arrays.sort(arr);
        System.out.println(Util.format("TimSort用时{}秒", (System.currentTimeMillis() - l) / 1000));
        l = System.currentTimeMillis();
        sort3(arr3);
        System.out.println(Util.format("快速排序用时{}秒", (System.currentTimeMillis() - l) / 1000));
        l = System.currentTimeMillis();
        sort2(arr4);
        System.out.println(Util.format("插入排序用时{}秒", (System.currentTimeMillis() - l) / 1000));
        l = System.currentTimeMillis();
        sort(arr1);
        System.out.println(Util.format("冒泡排序用时{}秒", (System.currentTimeMillis() - l) / 1000));
        l = System.currentTimeMillis();
        sort1(arr2);
        System.out.println(Util.format("选择排序用时{}秒", (System.currentTimeMillis() - l) / 1000));
        System.out.println(Arrays.equals(arr,arr1)&&Arrays.equals(arr1,arr2)&&Arrays.equals(arr2,arr3)&&Arrays.equals(arr4,arr3));
    }
    public void  sort(int [] arr){//冒泡排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
    public void  sort1(int [] arr){//选择排序
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i]>arr[j]){
                    int temp=arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                }
            }
        }
    }
    public void sort2(int []arr){//插入排序
        int StartIndex=-1;
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i]>arr[i+1]){
                StartIndex=i+1;
                break;
            }
        }
        for (int i = StartIndex; i < arr.length; i++) {
            int j=i;
            while (j>0&&arr[j]<arr[j-1]){
                int temp=arr[j];
                arr[j]=arr[j-1];
                arr[j-1]=temp;
                j--;
            }
            StartIndex++;
        }
    } public void sort3(int []arr){
        sort4(arr,0,arr.length-1);
    }

    public void sort4(int []arr,int i,int j){
        if (i>j){
            return;
        }
       int pivot=arr[i];
       int left=i;
       int right=j;
       while (left!=right){
           while (left != right && arr[right] >= pivot) {
               right--;
           }
           while (left != right && arr[left] <= pivot) {
               left++;
           }
           int temp=arr[left];
           arr[left]=arr[right];
           arr[right]=temp;
       }
       arr[i]=arr[left];
       arr[left]=pivot;
       sort4(arr,i,left-1);
       sort4(arr,left+1,j);
    }


}
