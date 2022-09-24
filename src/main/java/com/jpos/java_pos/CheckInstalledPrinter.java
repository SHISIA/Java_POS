package com.jpos.java_pos;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

public class CheckInstalledPrinter {
    private static String getPrinterNames() {
        String content = "";
        PrintService[] services = PrintServiceLookup.lookupPrintServices(null, null);

        content += "Number of print services: " + services.length;
        content += "\n";

        if(services.length != 0 || services != null) {
            int i = 1;
            for(PrintService service : services) {
                String name = service.getName();

                content += "Printer " + i + " name: " + name;
                content += "\n";
                i++;
            }
        }else {
            System.out.println("Printer Not Present");
        }

        System.out.println(content);

        return content;
    }
    public static void main(String[] args) {
        getPrinterNames();
    }
}
