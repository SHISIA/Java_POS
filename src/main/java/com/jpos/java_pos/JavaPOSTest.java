package com.jpos.java_pos;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.output.PrinterOutputStream;

import javax.print.PrintService;
import java.io.IOException;

public class JavaPOSTest {
    public static void main(String[] args) throws IOException {
        String printerName="Epson";
        //if(args.length!=1){
            System.out.println("Usage: java -jar escpos-simple.jar (\"printer name\")");
            System.out.println("Printer list to use:");
            String[] printServicesNames = PrinterOutputStream.getListPrintServicesNames();
            for(String printServiceName: printServicesNames){
                System.out.println(printServiceName);
            }

           // System.exit(0);
        //}

        PrintService printService = PrinterOutputStream.getPrintServiceByName(printerName);
        PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
        EscPos escpos = new EscPos(printerOutputStream);
        escpos.writeLF("Hello world");
        escpos.feed(5).cut(EscPos.CutMode.FULL);
        escpos.close();
    }
}
