public class Sandwich implements Item{
    Item item;
    public Sandwich(Item item) {
        this.item = item;
    }

    public double cost(){
        double TotalCost =0;
        if(item!=null){
            TotalCost= item.cost();
        }
        return TotalCost + 1.00;
    }
}
class Meat implements Item{
    Item item;
    public Meat(Item item) {
        this.item = item;
    }

    public double cost(){
        double TotalCost =0;
        if(item!=null){
            TotalCost= item.cost();
        }
        return TotalCost + 3.00;
    }
}
class Vegetables implements Item{
    Item item;
    public Vegetables(Item item) {
        this.item = item;
    }

    public double cost(){
        double TotalCost =0;
        if(item!=null){
            TotalCost= item.cost();
        }
        return TotalCost + 2.00;
    }
}
class Mayo implements Item{
    Item item;
    public Mayo(Item item) {
        this.item = item;
    }

    public double cost(){
        double TotalCost =0;
        if(item!=null){
            TotalCost= item.cost();
        }
        return TotalCost + .50;
    }
}
class Ketchup implements Item{


    Item item;
    public Ketchup(Item item) {
        this.item = item;
    }

    public double cost(){
        double TotalCost =0;
        if(item!=null){
            TotalCost= item.cost();
        }
        return TotalCost + .50;
    }
}
class Tofu implements Item{

    Item item;
    public Tofu(Item item) {
        this.item = item;
    }

    public double cost(){
        double TotalCost =0;
        if(item!=null){
            TotalCost= item.cost();
        }
        return TotalCost + 2.00;
    }
}
class Cheese implements Item{
    Item item;
    public Cheese(Item item) {
        this.item = item;
    }

    public double cost(){
        double TotalCost =0;
        if(item!=null){
            TotalCost= item.cost();
        }
        return TotalCost + 1.00;
    }
}

class Drink implements Item{
    Item item;

    public Drink(Item item) {
        this.item = item;
    }


    public double cost(){
        double TotalCost =0;
        if(item!=null){
            TotalCost= item.cost();
        }
        return TotalCost + 2.00;
    }
}

