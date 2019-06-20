package net.lzzy.algorithm.algorlib;

/**
 * Created by lzzy_gxy on 2019/6/15.
 * Description:
 */
public class InsertSort <T extends Comparable<? super T>>extends BaseSort {
    InsertSort(T[]items){
        super(items);
    }


    @Override
   public void sort() {
        long start=System.currentTimeMillis();
        for (int i=1;i<items.length;i++){
            int j = i-1;
            if (items[j].compareTo(items[i])<0){
                continue;
            }
            T tun= (T) items[i];
            while (j>=0&&items[j].compareTo(tun)>0){
                items[j+1]=items[j];
                movestep++;
                j--;
            }
            items[j+1]=tun;
        }
        duration=System.currentTimeMillis()-start;
    }
}
