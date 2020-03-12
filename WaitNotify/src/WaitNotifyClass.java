public class WaitNotifyClass {
    static Object obj = new Object();
    private static volatile char currentLetter = 'A';

    public static void main(String[] args) {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'A'){
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        currentLetter = 'B';
                        System.out.print("A");
                        obj.notifyAll();
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'B'){
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        currentLetter = 'C';
                        System.out.print("B");
                        obj.notifyAll();
                    }
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj){
                    for (int i = 0; i < 5; i++) {
                        while (currentLetter != 'C'){
                            try {
                                obj.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        currentLetter = 'A';
                        System.out.print("C");
                        obj.notifyAll();
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();





    }




}
