package com.vietluggage.order;

import java.util.ArrayList;
import java.util.List;

public class IOSaveToMemory implements Saving {
    private final DBMock dbMock;

    public IOSaveToMemory() {
        this.dbMock = new DBMock();
        this.dbMock.connect();
    }

    public IOSaveToMemory(DBMock dbMock) {
        this.dbMock = dbMock;
        this.dbMock.connect();
    }

    @Override
    public void save(OrderOutputDTO outputData) {
        if (outputData.isSuccess && outputData.totalAmount != null) {
            String formattedTotal = String.format("%.0f", outputData.totalAmount);
            dbMock.insertInvoice(formattedTotal, outputData.isBulkOrder, outputData.message);
        }
    }

    public List<String> getSavedInvoices() {
        List<String> totals = new ArrayList<>();
        for (DBMock.InvoiceRecord record : dbMock.selectAll()) {
            totals.add(record.totalAmount);
        }
        return totals;
    }

    public DBMock getDbMock() {
        return dbMock;
    }
}