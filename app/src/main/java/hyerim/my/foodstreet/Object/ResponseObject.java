package hyerim.my.foodstreet.Object;

import java.util.ArrayList;

public class ResponseObject {
    public String lastBuildDate;
    public int total;
    public int start;
    public int display;
    public ArrayList<ItemObject> items;

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public void setItems(ArrayList<ItemObject> items) {
        this.items = items;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public int getTotal() {
        return total;
    }

    public int getStart() {
        return start;
    }

    public int getDisplay() {
        return display;
    }

    public ArrayList<ItemObject> getItems() {
        return items;
    }
}
