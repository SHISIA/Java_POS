package com.jpos.java_pos.Model;

import com.github.anastaciocintra.escpos.EscPos;
import com.github.anastaciocintra.escpos.barcode.BarCode;
import com.github.anastaciocintra.output.PrinterOutputStream;
import com.jpos.java_pos.Controller.SettingController;
import com.jpos.java_pos.json.JSON;
import com.jpos.java_pos.json.JSONReader;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.print.PrintService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class Ticket {
    String ticketName;
    double productPrice;
    int productCount;
    double productTotal;
    HashMap<String,String> stringHashMap=new HashMap<>();

    StringBuilder stringBuilder;
    JSON json=new JSON();
    JSONReader reader = new JSONReader();

    public void setProductTotal(double productTotal) {
        this.productTotal = productTotal;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public void appendDetails(){
        stringBuilder=new StringBuilder(ticketName+": "+productPrice+" x "+productCount+" = "+productTotal);
        stringHashMap.put(getTicketName(),stringBuilder.toString());
        json.appendJSON("Ticket.json","Tickets","TicketName",getTicketName(),"Data",stringBuilder.toString());
    }

    public void printTicket(){
        try {
            String printer= new DbConnector().streamReader("selected.txt");
            PrintService printService = PrinterOutputStream.getPrintServiceByName(printer);
            PrinterOutputStream printerOutputStream = new PrinterOutputStream(printService);
            EscPos escpos = new EscPos(printerOutputStream);
            JSONArray object = reader.reader("Ticket.json");
            ArrayList<JSONObject> objectArrayList = new ArrayList<>();
            for (Object object1 : object) {
                JSONObject object2 = (JSONObject) object1;
                JSONObject dbObject = (JSONObject) object2.get("Tickets");
                objectArrayList.add(dbObject);
            }
            for (JSONObject object1 : objectArrayList) {
                String content = (String) object1.get("Data");
                escpos.writeLF("* " + content + " *");
            }
            printEnd(escpos);
        } catch (IllegalArgumentException e){
                new SettingController().notification("Printer UnAvailable","puzzled.png",2);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printEnd(EscPos escpos){
        try {
            escpos.writeLF("               ");
            escpos.writeLF("-----------------");
            escpos.writeLF("-----------------");
            escpos.feed(5).cut(EscPos.CutMode.FULL);
            BarCode barcode = new BarCode();
            escpos.write(barcode, "ExampleBarCode");
            escpos.close();
            new SettingController().notification("Printer: Success!!","success.png",2);
        }
        catch (IllegalArgumentException e){
            new SettingController().notification("Printer UnAvailable","puzzled.png",2);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void backUp(){
        JSONArray array=reader.reader("Ticket.json");
        ArrayList<JSONObject> objectArrayList = new ArrayList<>();
        for (Object object1 : array) {
            JSONObject object2 = (JSONObject) object1;
            JSONObject dbObject = (JSONObject) object2.get("Tickets");
            objectArrayList.add(dbObject);
        }
        for (JSONObject object1 : objectArrayList) {
            String data = (String) object1.get("Data");
            String ticket = (String) object1.get("TicketName");
            json.appendJSON("TicketBackup.json","Tickets","TicketName",ticket,"Data",data);
        }
    }
}
