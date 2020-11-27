package com.example.crud_mysql;

import android.widget.Toast;

  public class Hilos extends Thread{
        @Override
        public void run() {
            super.run();
           new OperacionesJSON().RecibirProducto();
        }

    }

