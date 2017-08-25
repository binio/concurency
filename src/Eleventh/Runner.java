package Eleventh;

import com.sun.org.apache.xalan.internal.xsltc.runtime.InternalRuntimeError;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by tomasz.biniecki on 16/08/2017.
 */
public class Runner {

    Account a1 = new Account();
    Account a2 = new Account();

    Lock lock1 = new ReentrantLock();
    Lock lock2 = new ReentrantLock();

    public void acquireLocks( Lock firstLock, Lock secondLock) throws InterruptedException{
            while(true) {
                //acquire locks
                boolean gotFirstLock = false;
                boolean gotSecondLock = false;

                try{
                    gotFirstLock = firstLock.tryLock();
                    gotSecondLock = secondLock.tryLock();
                }
                finally {
                    if(gotFirstLock && gotSecondLock) {
                        return;
                    }
                    if(gotFirstLock) {
                        firstLock.unlock();
                    }
                    if(gotSecondLock){
                        secondLock.unlock();
                    }

                }
                //lock not aquired
                Thread.sleep(1);
            }
    }


    public void firstThread() throws InterruptedException {
        Random random = new Random();
        for(int i = 0; i<10000; i++){
            acquireLocks(lock1,lock2);
            try {
                Account.transfer(a1, a2, random.nextInt(100));
            }finally {

                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void secondThread() throws InterruptedException{
        Random random = new Random();
        for(int i = 0; i<10000; i++){
            acquireLocks(lock1,lock2);
            try {
            Account.transfer(a2, a1, random.nextInt(100));
            }finally {

                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    public void finished(){
        System.out.println("Account 1 balance :" + a1.getBalance());
        System.out.println("Account 2 balance :" + a2.getBalance());
        System.out.println("Total balance :" + (a1.getBalance() +a2.getBalance()));
    }
}
