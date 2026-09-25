import dao.ProductDao;
import service.impl.InventoryServiceImpl;
import ui.ConsoleMenu;

public class InventoryManagment {
    public static void main(String[] args) {
        InventoryServiceImpl inventoryService = new InventoryServiceImpl();

        ConsoleMenu menu = new ConsoleMenu(inventoryService);

        ProductDao.init();
        menu.init();
    }
}