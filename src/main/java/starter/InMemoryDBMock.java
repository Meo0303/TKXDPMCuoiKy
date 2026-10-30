package starter;

import java.util.ArrayList;

public class InMemoryDBMock implements StoreAble {
    private ArrayList<Double> mockDB;

    public ArrayList<Double> getMockDB() {
        return mockDB;
    }

    public InMemoryDBMock(ArrayList<Double> mockDB) {
        this.mockDB = mockDB;
    }

    @Override
    public boolean save(double result) {
        return save(mockDB, result);
    }

    private boolean save(ArrayList<Double> mockDB, double result) {
        return mockDB.add(result);
    }

}