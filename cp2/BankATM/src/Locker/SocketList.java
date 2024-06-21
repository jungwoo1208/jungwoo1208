package Locker;

import java.net.Socket;

class SocketList {
    private Socket socket1;
    private Socket socket2;

    SocketList(Socket socket1, Socket socket2){
        this.socket1 = socket1;
        this.socket2 = socket2;
    }
    public Socket getSocket1(){
        return this.socket1;
    }
    public Socket getSocket2(){
        return this.socket2;
    }
    public void setSocket1(Socket socket1){
        this.socket1 = socket1;
    }
    public void setSocket2(Socket socket2){
        this.socket2 = socket2;
    }
}
