package starter;

import java.util.ArrayList;

public class InMemoryDBMock implements StoreAble {
    private ArrayList<Double> mockDB;

    public InMemoryDBMock(ArrayList<Double> mockDB) {
        this.mockDB = mockDB;
    }

    public ArrayList<Double> getMockDB() {
        return mockDB;
    }

    @Override
    public boolean save(double result) {
        mockDB.add(result);
        return true;
    }
}