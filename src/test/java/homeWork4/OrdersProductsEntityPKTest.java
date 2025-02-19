package homeWork4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OrdersProductsEntityPKTest {

    @Test
    void testEqualsAndHashCode_whenEqual_shouldReturnTrue() {
        OrdersProductsEntityPK key1 = new OrdersProductsEntityPK();
        key1.setOrderId((short) 1);
        key1.setProductId((short) 10);

        OrdersProductsEntityPK key2 = new OrdersProductsEntityPK();
        key2.setOrderId((short) 1);
        key2.setProductId((short) 10);

        Assertions.assertEquals(key1, key2);
        Assertions.assertEquals(key1.hashCode(), key2.hashCode());
    }

    @Test
    void testEquals_whenNotEqual_shouldReturnFalse() {
        OrdersProductsEntityPK key1 = new OrdersProductsEntityPK();
        key1.setOrderId((short) 1);
        key1.setProductId((short) 10);

        OrdersProductsEntityPK key2 = new OrdersProductsEntityPK();
        key2.setOrderId((short) 2);
        key2.setProductId((short) 20);

        Assertions.assertNotEquals(key1, key2);
    }

    @Test
    void testHashCode_whenNotEqual_shouldReturnDifferentHashCodes() {
        OrdersProductsEntityPK key1 = new OrdersProductsEntityPK();
        key1.setOrderId((short) 1);
        key1.setProductId((short) 10);

        OrdersProductsEntityPK key2 = new OrdersProductsEntityPK();
        key2.setOrderId((short) 2);
        key2.setProductId((short) 20);

        Assertions.assertNotEquals(key1.hashCode(), key2.hashCode());
    }
}
