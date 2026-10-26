package com.vietluggage.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DBMock {
    public static class InvoiceRecord {
        public final int id;
        public final String totalAmount;
        public final boolean isBulkOrder;
        public final String statusMessage;

        public InvoiceRecord(int id, String totalAmount, boolean isBulkOrder, String statusMessage) {
            this.id = id;
            this.totalAmount = totalAmount;
            this.isBulkOrder = isBulkOrder;
            this.statusMessage = statusMessage;
        }

        @Override
        public String toString() {
            return String.format("{id=%d, total='%s', isBulk=%b, status='%s'}",
                    id, totalAmount, isBulkOrder, statusMessage);
        }
    }

    private final String connectionUrl = "jdbc:mock://localhost:3306/vietluggage_db";
    private boolean connected = false;
    private int autoIncrementId = 1;
    private final List<InvoiceRecord> invoiceTable = new ArrayList<>();

    public void connect() {
        if (!connected) {
            connected = true;
            System.out.println("[DBMock] Connected to: " + connectionUrl);
        }
    }

    public void insertInvoice(String totalAmount, boolean isBulkOrder, String statusMessage) {
        connect();
        InvoiceRecord record = new InvoiceRecord(autoIncrementId++, totalAmount, isBulkOrder, statusMessage);
        invoiceTable.add(record);
        System.out.println("[DBMock] SQL > INSERT INTO suitcase_invoices(id, total_amount, is_bulk, status) VALUES ("
                + record.id + ", " + record.totalAmount + ", " + record.isBulkOrder + ", '" + record.statusMessage + "');");
    }

    public List<InvoiceRecord> selectAll() {
        connect();
        return Collections.unmodifiableList(invoiceTable);
    }

    public boolean isConnected() {
        return connected;
    }
}